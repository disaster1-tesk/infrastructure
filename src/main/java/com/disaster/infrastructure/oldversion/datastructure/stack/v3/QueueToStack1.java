package com.disaster.infrastructure.oldversion.datastructure.stack.v3;

import java.util.LinkedList;
import java.util.Queue;

public class QueueToStack1<T> {
    private Queue<T> queue1, queue2;

    public QueueToStack1() {
        this.queue1 = new LinkedList();
        this.queue2 = new LinkedList();
    }

    /**
     * 添加元素到栈顶
     */
    public void push(T x) {
        queue2.offer(x);
        while (!queue1.isEmpty()) {
            queue2.offer(queue1.poll());
        }
        while (!queue2.isEmpty()) {
            queue1.offer(queue2.poll());
        }
    }

    /**
     * 删除栈顶的元素并返回
     */
    public T pop() {
       return queue1.poll();
    }

    /**
     * 返回栈顶元素
     */
    public T top() {
       return queue1.peek();
    }

    /**
     * 判断栈是否为空
     */
    public boolean empty() {
        return queue1.isEmpty();
    }

    public static void main(String[] args) {
        QueueToStack1<Integer> stack1 = new QueueToStack1<>();
        stack1.push(1);
        stack1.push(2);
        stack1.push(3);
        System.out.println(stack1.pop());
        System.out.println(stack1.empty());
        System.out.println(stack1.pop());
        System.out.println(stack1.pop());
        System.out.println(stack1.empty());
    }
}
