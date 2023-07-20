package com.disaster.infrastructure.oldversion.example.limit;

/**
 * 令牌桶限流
 *
 * @author disaster
 * @version 1.0
 */
public class TokenBucketRateLimiter {
    private final int capacity;
    private final double rate;
    private int tokens;
    private long lastRefillTime;

    public TokenBucketRateLimiter(int capacity, double rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.tokens = 0;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public boolean isAllowed(int quota) {
        refillTokens();
        if (tokens >= quota) {
            tokens -= quota;
            return true;
        }
        return false;
    }

    private void refillTokens() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastRefillTime;
        int tokensToRefill = (int) ((elapsedTime - lastRefillTime) * rate / 1000);
        lastRefillTime = currentTime;
        Math.min(tokensToRefill + tokens, tokens);
    }
}
