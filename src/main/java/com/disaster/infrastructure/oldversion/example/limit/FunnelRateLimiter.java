package com.disaster.infrastructure.oldversion.example.limit;

import java.util.concurrent.TimeUnit;

/**
 * 漏斗限流
 *
 * @author disaster
 * @version 1.0
 */
public class FunnelRateLimiter {
    private final int capacity;
    private final double rate;
    private int leftQuota;
    private long lastRefillTime;

    public FunnelRateLimiter(int capacity, double rate) {
        this.capacity = capacity;
        this.rate = rate;
        this.leftQuota = capacity;
        this.lastRefillTime = System.currentTimeMillis();
    }

    public boolean isAllowed(int quota) {
        refillQuota();
        if (this.leftQuota >= quota) {
            this.leftQuota -= quota;
            return true;
        }
        return false;
    }

    private void refillQuota() {
        long currentTimeMillis = System.currentTimeMillis();
        long seconds = TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis - this.lastRefillTime);
        System.out.println("seconds = " + seconds);
        double quotaRefilled = Math.ceil(seconds * rate);
        System.out.println("quotaRefilled = " + quotaRefilled);
        this.lastRefillTime = currentTimeMillis;
        this.leftQuota = (int) Math.min(leftQuota + quotaRefilled, capacity);
        System.out.println("quotaRefilled = " + quotaRefilled);
    }

    public static void main(String[] args) throws InterruptedException {
        FunnelRateLimiter funnelRateLimiter = new FunnelRateLimiter(5, 0.9d);
        for (int i = 0; i < 10; i++) {
            boolean allowed = funnelRateLimiter.isAllowed(1);
            if (allowed) {
                System.out.println("i = " + i + " is allowed");
            } else {
                System.out.println("i = " + i + " is not allowed");
            }
            TimeUnit.MILLISECONDS.sleep(1000);
        }
    }
}
