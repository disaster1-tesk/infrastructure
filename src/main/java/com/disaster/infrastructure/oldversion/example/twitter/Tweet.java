package com.disaster.infrastructure.oldversion.example.twitter;

public class Tweet {
    private int id;
    private long time;
    private Tweet next;

    // 需要传入推文内容（id）和发文时间
    public Tweet(int id) {
        this.id = id;
        this.time = System.currentTimeMillis();
        this.next = null;
    }

    public void setNext(Tweet next) {
        this.next = next;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Tweet getNext() {
        return next;
    }
}
