package com.disaster.infrastructure.oldversion.example.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MainTest {
    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        LinkedList<Integer> linkedList1 = new LinkedList<>();

    }


    private static boolean isEqual(List<Object> list1, List<Object> list2) {
        HashMap<Object, Integer> list1Map = new HashMap<>();
        HashMap<Object, Integer> list2Map = new HashMap<>();
        for (Object o : list1) {
            if (list1Map.containsKey(o)) {
                Integer integer = list1Map.get(o);
                integer += 1;
                list1Map.put(o, integer);
            } else {
                list1Map.put(o, 1);
            }
        }
        for (Object o : list2) {
            if (list2Map.containsKey(o)) {
                Integer integer = list2Map.get(o);
                integer += 1;
                list2Map.put(o, integer);
            } else {
                list2Map.put(o, 1);
            }
        }
        if (list1Map.size() != list2Map.size()) return false;
        for (Object o : list1Map.keySet()) {
            if (!list2Map.containsKey(o)) return false;
            if (!Objects.equals(list1Map.get(o), list2Map.get(o))) return false;
        }
        return true;
    }
}
