package com.disaster.infrastructure.oldversion.example.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class NestedInteger {
    public static void main(String[] args) {
        InnerNestedInteger innerNestedInteger = new InnerNestedInteger(1);
        InnerNestedInteger innerNestedInteger1 = new InnerNestedInteger(1);
        List<InnerNestedInteger> list = new LinkedList<>();
        list.add(innerNestedInteger);
        list.add(innerNestedInteger1);
        InnerNestedInteger innerNestedInteger2 = new InnerNestedInteger(list);
        InnerNestedInteger innerNestedInteger3 = new InnerNestedInteger(2);
        InnerNestedInteger innerNestedInteger4 = new InnerNestedInteger(list);
        List<InnerNestedInteger> list1 = new LinkedList<>();
        list1.add(innerNestedInteger2);
        list1.add(innerNestedInteger3);
        list1.add(innerNestedInteger4);
        NestedIterator nestedIterator = new NestedIterator(list1);
        NestedIterator1 nestedIterator1 = new NestedIterator1(list1);
//        while (nestedIterator.hasNext()){
//            System.out.println(nestedIterator.next());
//        }
        while (nestedIterator1.hasNext()) {
            System.out.println(nestedIterator1.next());
        }
    }


    public static class InnerNestedInteger {
        private Integer val;
        private List<InnerNestedInteger> list;

        public InnerNestedInteger(Integer val) {
            this.val = val;
            this.list = null;
        }

        public InnerNestedInteger(List<InnerNestedInteger> list) {
            this.list = list;
            this.val = null;
        }

        public boolean isInteger() {
            return this.val != null;
        }

        public Integer getInteger() {
            return this.val;
        }

        public List<InnerNestedInteger> getList() {
            return this.list;
        }
    }

    public static class NestedIterator1 implements Iterator<Integer> {
        private LinkedList<InnerNestedInteger> list;

        public NestedIterator1(List<InnerNestedInteger> list) {
            // 不直接用 nestedList 的引用，是因为不能确定它的底层实现
            // 必须保证是 LinkedList，否则下面的 addFirst 会很低效
            this.list = new LinkedList<>(list);
        }

        /**
         * 优化后的迭代器，符合懒加载的效果
         *
         * @return
         */
        @Override
        public boolean hasNext() {
            if (!list.isEmpty() && !list.getFirst().isInteger()) {
                List<InnerNestedInteger> first = this.list.removeFirst().getList();
                for (int i = first.size() - 1; i >= 0; i--) {
                    this.list.addFirst(first.get(i));
                }
            }
            return !this.list.isEmpty();
        }

        @Override
        public Integer next() {
            return list.removeFirst().getInteger();
        }
    }

    public static class NestedIterator implements Iterator<Integer> {
        private Iterator<Integer> it;

        //这种方法在创建迭代器的时候默认就将树的所有数据全部拍平，如果数据量比较大的时候对内存会有一定的影响
        public NestedIterator(List<InnerNestedInteger> innerNestedIntegerList) {
            List<Integer> list = new LinkedList<>();
            for (InnerNestedInteger innerNestedInteger : innerNestedIntegerList) {
                traverse(innerNestedInteger, list);
            }
            this.it = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Integer next() {
            return it.next();
        }

        public void traverse(InnerNestedInteger root, List list) {
            if (root.isInteger()) {
                list.add(root.val);
                return;
            }
            List<InnerNestedInteger> list1 = root.getList();
            for (InnerNestedInteger innerNestedInteger : list1) {
                traverse(innerNestedInteger, list);
            }
        }
    }
}
