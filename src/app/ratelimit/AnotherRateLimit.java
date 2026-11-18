package app.ratelimit;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/* 
RateLimiterTest class with a method allow(userId) that returns true if that user has made fewer than N 
requests in the last 60 seconds, and false otherwise. 
Take the current time from a clock that is passed in, 
so it can be controlled in tests. Write one or two unit tests.
If you have time, make it safe to call from many threads.
Please start by asking me any questions you have.*/

public class AnotherRateLimit {

    private final Clock clock;
    private final Map<String, Deque<Instant>> requests = new HashMap<>();
    private final int TIME_BEFORE = 60;
    private final int limit = 5;

    public AnotherRateLimit(Clock clock) {
        this.clock = clock;
    }

    public synchronized boolean allow(String userId) {
        Instant now = clock.instant();
        Instant beforeNow = now.minusSeconds(TIME_BEFORE);
        Deque<Instant> requestsPerUser = requests.computeIfAbsent(userId, k -> new ArrayDeque<>());

        while (!requestsPerUser.isEmpty() && !requestsPerUser.peekFirst().isAfter(beforeNow)) {
            requestsPerUser.pollFirst();
        }

        if (requestsPerUser.size() >= limit) {
            return false;
        }

        requestsPerUser.addLast(now);

        return true;

    }
}
