package com.disaster.infrastructure.oldversion.example.twitter;

import java.util.HashSet;
import java.util.Set;

public class User {
    private int id;
    public Set<Integer> followed;
    // 用户发表的推文链表头结点
    public Tweet head;

    public User(int userId) {
        followed = new HashSet<>();
        this.id = userId;
        this.head = null;
        // 关注一下自己
        follow(id);
    }

    public void follow(int userId) {
        followed.add(userId);
    }

    public void unfollow(int userId) {
        // 不可以取关自己
        if (userId != this.id) {
            followed.remove(userId);
        }
    }

    public void post(int tweetId) {
        Tweet twt = new Tweet(tweetId);
        // 将新建的推文插入链表头
        // 越靠前的推文 time 值越大
        twt.setNext(head);
        head = twt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Integer> getFollowed() {
        return followed;
    }

    public void setFollowed(Set<Integer> followed) {
        this.followed = followed;
    }

    public Tweet getHead() {
        return head;
    }

    public void setHead(Tweet head) {
        this.head = head;
    }
}
