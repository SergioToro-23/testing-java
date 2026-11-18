package app.ratelimit;

import java.time.Clock;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    private final int limit;
    private final Clock clock;
    private final Map<String, Deque<Instant>> requests = new HashMap<>();
    private static final int SECS_TIME_OFF = 60;//because is shared

    public RateLimiter(int limit, Clock clock){
        this.limit = limit;
        this.clock = clock;
    }

    public synchronized boolean allow(String userId){
        Instant now = clock.instant(); 
        Instant cutoff = now.minusSeconds(SECS_TIME_OFF);       
        Deque<Instant> userEntries = requests.computeIfAbsent(userId, k -> new ArrayDeque<>());//adds the user if still not here, and returns the deque attach to the one in hashmap
                
        while(!userEntries.isEmpty() && !userEntries.peekFirst().isAfter(cutoff)){
            userEntries.pollFirst();
        }
        
        
        if(userEntries.size() >= limit){
           return false;
        }

        userEntries.addLast(now);
        return true;
    }
    
}
