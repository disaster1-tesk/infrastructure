package com.disaster.infrastructure.oldversion.example.limit;

public class CountLimiter {
    private int requestCount;
    private long lastRefillTime;
    private long limitTime;
    private int requestLimit;

    public CountLimiter(int requestCount, int requestLimit, long lastRefillTime, long limitTime) {
        this.requestCount = requestCount;
        this.requestLimit = requestLimit;
        this.lastRefillTime = lastRefillTime;
        this.limitTime = limitTime;
    }

    public boolean isAllowed() {
        synchronized (this) {
            long currTime = System.currentTimeMillis();
            long elapsedTime = currTime - lastRefillTime;
            if (elapsedTime > limitTime) {
                reset(currTime);
            }
            if (requestCount > requestLimit){
                return false;
            }
            requestCount++;
            return true;
        }
    }

    private void reset(long currTime) {
        this.lastRefillTime = currTime;
        this.requestCount = 0;
    }
}
