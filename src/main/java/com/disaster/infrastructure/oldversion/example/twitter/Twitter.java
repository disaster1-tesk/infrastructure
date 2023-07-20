package com.disaster.infrastructure.oldversion.example.twitter;

import java.util.*;

public class Twitter {
    private static int timestamp = 0;
    // 我们需要一个映射将 userId 和 User 对象对应起来
    private HashMap<Integer, User> userMap = new HashMap<>();

    /**
     * user 发表一条 tweet 动态
     */
    public void postTweet(int userId, int tweetId) {
        // 若 userId 不存在，则新建
        if (!userMap.containsKey(userId)){
            userMap.put(userId, new User(userId));
        }
        User u = userMap.get(userId);
        u.post(tweetId);
    }

    /**
     * follower 关注 followee
     */
    public void follow(int followerId, int followeeId) {
        // 若 follower 不存在，则新建
        if (!userMap.containsKey(followerId)) {
            User u = new User(followerId);
            userMap.put(followerId, u);
        }
        // 若 followee 不存在，则新建
        if (!userMap.containsKey(followeeId)) {
            User u = new User(followeeId);
            userMap.put(followeeId, u);
        }
        userMap.get(followerId).follow(followeeId);
    }

    /**
     * follower 取关 followee，如果 Id 不存在则什么都不做
     */
    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            User flwer = userMap.get(followerId);
            flwer.unfollow(followeeId);
        }
    }


    /** 返回该 user 关注的人（包括他自己）最近的动态 id，
     最多 10 条，而且这些动态必须按从新到旧的时间线顺序排列。*/
    public List<Integer> getNewsFeed(int userId) {
        // 需要理解算法，见下文
        List<Integer> res = new ArrayList<>();
        if (!userMap.containsKey(userId)) return res;
        // 关注列表的用户 Id
        Set<Integer> users = userMap.get(userId).followed;
        // 自动通过 time 属性从大到小排序，容量为 users 的大小
        PriorityQueue<Tweet> pq =
                new PriorityQueue<>(users.size(), (a, b)-> Math.toIntExact((b.getTime() - a.getTime())));

        // 先将所有链表头节点插入优先级队列
        for (int id : users) {
            Tweet twt = userMap.get(id).head;
            if (twt == null) continue;
            pq.add(twt);
        }

        while (!pq.isEmpty()) {
            // 最多返回 10 条就够了
            if (res.size() == 10) break;
            // 弹出 time 值最大的（最近发表的）
            Tweet twt = pq.poll();
            res.add(twt.getId());
            // 将下一篇 Tweet 插入进行排序
            if (twt.getNext() != null)
                pq.add(twt.getNext());
        }
        return res;
    }


    public static void main(String[] args) {

    }


}
