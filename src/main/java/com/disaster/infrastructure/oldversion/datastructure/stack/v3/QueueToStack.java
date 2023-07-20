package com.disaster.infrastructure.oldversion.datastructure.stack.v3;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueToStack<T> {
    Queue<T> queue;
    T top_num;

    public QueueToStack() {
        queue = new ArrayDeque();
        top_num = null;
    }

    /**
     * 添加元素到栈顶
     */
    public void push(T x) {
        queue.offer(x);
        top_num = x;
    }

    /**
     * 删除栈顶的元素并返回
     */
    public T pop() {
        int size = queue.size();
        while (size > 1) {
            queue.add(queue.poll());
            size--;
        }
        return queue.poll();
    }

    /**
     * 返回栈顶元素
     */
    public T top() {
        return top_num;
    }

    /**
     * 判断栈是否为空
     */
    public boolean empty() {
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        QueueToStack<Integer> stack = new QueueToStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
