package app.ratelimit;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class NewRateLimiter {
    private final int MAXIMUM_REQUEST = 100;
    private final int WINDOW_SECS = 60;
    private final ConcurrentHashMap<String, UserRateLimit> RequestUsers = new ConcurrentHashMap<>();
    private final Clock time;

    public NewRateLimiter(Clock time) {
        this.time = time;
    }

    public boolean allowRequest(String id) {
        if ( id == null || id.isBlank()) {
            return false;
        }
        Instant now = time.instant();
        Instant beforeNow = now.minusSeconds(WINDOW_SECS);
        Deque<Instant> RequestOneUser = RequestUsers.computeIfAbsent(id, key -> new UserRateLimit(MAXIMUM_REQUEST)).getTimeStamps();
        synchronized(RequestOneUser){
            while (!RequestOneUser.isEmpty() && !RequestOneUser.peekFirst().isAfter(beforeNow)) {
                RequestOneUser.pollFirst();
            }

            if (RequestOneUser.size() >= MAXIMUM_REQUEST){
                return false;
            }
            RequestOneUser.addLast(now);
            return true;
        }
    }

}
