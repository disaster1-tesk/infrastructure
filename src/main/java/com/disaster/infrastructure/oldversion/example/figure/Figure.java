package com.disaster.infrastructure.oldversion.example.figure;

import java.util.LinkedList;
import java.util.List;


/*
Graph graph;
boolean[] visited;

*//* 图遍历框架 *//*
void traverse(Graph graph, int s) {
     if (visited[s]) return;

     // 经过节点 s
     visited[s] = true;

     for (TreeNode neighbor : graph.neighbors(s))
        traverse(neighbor);

     // 离开节点 s
     visited[s] = false;
}
*/
public class Figure {
    private static List<List<Integer>> res = new LinkedList<>();
    // 记录遍历过的节点，防止走回头路
    private static boolean[] visited;
    // 记录一次 traverse 递归经过的节点
    private static boolean[] onPath;
    private static boolean hasCycle = false;


    public static void main(String[] args) {
        int[][] ints = new int[4][4];
        int[] ints1 = new int[2];
        ints1[0] = 1;
        ints1[1] = 2;
        int[] ints2 = new int[1];
        ints2[0] = 3;
        int[] ints3 = new int[1];
        ints3[0] = 3;
        ints[0] = ints1;
        ints[1] = ints2;
        ints[2] = ints3;
        System.out.println(allPathsSourceTarget(ints));
    }


    public static List<Integer>[] buildGragh(int numCourses, int[][] prerequisites) {
        LinkedList[] gragh = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            gragh[i] = new LinkedList();
        }

        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

        for (int[] prerequisite : prerequisites) {
            int from = prerequisite[1];
            int to = prerequisite[0];
            gragh[from].add(to);
        }
        return gragh;
    }


    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] gragh = buildGragh(numCourses, prerequisites);

        for (int i = 0; i < numCourses; i++) {
            graghTraverse(gragh, i);
        }
        return !hasCycle;
    }

    public void graghTraverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            //如果找到环，则停止遍历
            return;
        }
        visited[s] = true;
        onPath[s] = true;
        for (int t : graph[s]) {
            graghTraverse(graph, t);
        }
        onPath[s] = false;
    }


    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }

    /**
     * 图的遍历框架
     *
     * @param graph
     * @param s
     * @param path
     */
    public static void traverse(int[][] graph, int s, LinkedList<Integer> path) {
        //添加s节点到路径
        path.addFirst(s);

        int n = graph.length;
        if (s == n - 1) {
            //到达终点
            res.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }

        //递归每个相邻节点
        for (int v : graph[s]) {
            traverse(graph, v, path);
        }

        //从路径移出节点
        path.removeLast();

    }

}
