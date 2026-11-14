
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    private final int limit;
    private final Clock clock;

    private final Map<String, Deque<Instant>> requests =
            new HashMap<>();

    public RateLimiter(int limit, Clock clock) {
        this.limit = limit;
        this.clock = clock;
    }

    public synchronized boolean allow(String userId) {
        Instant now = clock.instant();

        Deque<Instant> userRequests =
                requests.computeIfAbsent(
                    userId, id -> new ArrayDeque<>()
                );

        // Remove requests outside the last 60 seconds
        Instant cutoff = now.minusSeconds(60);

        while (!userRequests.isEmpty()
                && !userRequests.peekFirst().isAfter(cutoff)) {
            userRequests.pollFirst();
        }

        // Check limit
        if (userRequests.size() >= limit) {
            return false;
        }

        // Register accepted request
        userRequests.addLast(now);

        return true;
    }
}
