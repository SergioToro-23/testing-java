package app.ratelimit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class AnotherRateLimitTest {

    
    private MutableClock clock;

    
    private AnotherRateLimit anotherRateLimit;

    @BeforeEach 
    void setUp(){
        clock = new MutableClock(Instant.now());
        anotherRateLimit = new AnotherRateLimit(clock);
    }

    @Test 
    void shouldBlockRequestsWhenLimitIsReached(){
        for(int i=0; i<5; i++){
            assertTrue(anotherRateLimit.allow("User-123"));
        }
        assertFalse(anotherRateLimit.allow("User-123"));        
        clock.advanceSeconds(61);
        assertTrue(anotherRateLimit.allow("User-123"));
    }
    
    
    
    
    public class MutableClock extends Clock {

        private Instant currentTime;

        public MutableClock (Instant initialTime){
            this.currentTime = initialTime;
        }
        
        @Override
        public ZoneId getZone() {
            return ZoneId.of("UTC");
        }

        @Override
        public Instant instant() {
            return currentTime;
        }

        @Override
        public Clock withZone(ZoneId zone) {
            return this;
        }

        public void advanceSeconds(long seconds) {
            this.currentTime = currentTime.plusSeconds(seconds);
        }
    }

}
