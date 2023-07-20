package com.disaster.infrastructure.oldversion.datastructure.queue.v3;

import java.util.Stack;

/**
 *
 */
public class StackToQueue {
    private Stack<Integer> head, tail;

    public StackToQueue() {
        head = new Stack<>();
        tail = new Stack<>();
    }

    /**
     * 添加元素到队尾
     */
    public void push(int x) {
        tail.push(x);
    }

    /**
     * 删除队头的元素并返回
     */
    public int pop() {
        peek();
        return head.pop();
    }

    /**
     * 返回队头元素
     */
    public int peek() {
        if (head.isEmpty()) {
            while (!tail.isEmpty()) {
                head.push(tail.pop());
            }
        }
        return head.peek();
    }

    /**
     * 判断队列是否为空
     */
    public boolean empty() {
        return head.isEmpty() && tail.isEmpty();
    }
}
