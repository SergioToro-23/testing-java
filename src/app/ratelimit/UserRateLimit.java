package app.ratelimit;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;

public class UserRateLimit {
    private final int maxRequests;
    private final Deque<Instant> timestamps;

    public UserRateLimit(int maxRequests) {
        this.maxRequests = maxRequests;
        this.timestamps = new ArrayDeque<>();
    }

    public Deque<Instant> getTimeStamps(){
        return timestamps;
    }
}