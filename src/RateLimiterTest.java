import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

public class RateLimiterTest {

    @Test
    void shouldAllowUserAndBlockAfterLimitReached() {

        // Simulamos un reloj estático en un instante fijo
        Clock mockClock = Clock.fixed(Instant.parse("2026-01-01T10:00:00Z"), ZoneId.of("UTC"));

        RateLimiter limit = new RateLimiter(2, mockClock);

        assertTrue(limit.allow("123"));
        assertTrue(limit.allow("123"));
        assertFalse(limit.allow("123"));
    }

    @Test
    void shouldAllowRequestsAfterWindowExpires() {

        // Simulamos un reloj estático en un instante fijo
        MutableClock testClock = new MutableClock(Instant.parse("2026-01-01T10:00:00Z"));
        RateLimiter limit = new RateLimiter(2, testClock);

        assertTrue(limit.allow("123"));
        assertTrue(limit.allow("123"));
        assertFalse(limit.allow("123"));
        testClock.advanceSeconds(61);
        assertTrue(limit.allow("123"));
        assertTrue(limit.allow("123"));
        assertFalse(limit.allow("123"));
    }

    private static class MutableClock extends Clock {

        private Instant currentTime; 

        public MutableClock(Instant initialTime) {
            this.currentTime = initialTime;
        }

        public void advanceSeconds(long seconds) {
            this.currentTime = currentTime.plusSeconds(seconds);
        }

        @Override
        public Instant instant() {
            return currentTime;
        }

        @Override
        public ZoneId getZone() {
            return ZoneId.of("UTC");
        }

        @Override
        public Clock withZone(ZoneId zone) {
            return this;
        }
    }
}
