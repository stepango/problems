package contest;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;

public class ContestFeb3 {

    public static int[] sumEvenAfterQueries(int[] A, int[][] queries) {
        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            A[queries[i][1]] += queries[i][0];
            for (int j : A)
                if (j % 2 == 0) result[i] += j;
        }
        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class Solution {
        public String smallestFromLeaf(TreeNode root) {
            TreeSet<String> set = new TreeSet<>();
            HashMap<TreeNode, TreeNode> parents = new HashMap<>();
            Stack<TreeNode> stack = new Stack<>();
            stack.push(root);
            TreeNode node;
            while (!stack.isEmpty()) {
                node = stack.pop();
                if (node.left != null) {
                    stack.push(node.left);
                    parents.put(node.left, node);
                }
                if (node.right != null) {
                    stack.push(node.right);
                    parents.put(node.right, node);
                }
                if (node.left == null && node.right == null) {
                    TreeNode deadEnd = node;
                    StringBuilder builder = new StringBuilder();
                    while (deadEnd != null) {
                        builder.append(String.valueOf((char) ('a' + node.val)));
                        deadEnd = parents.get(deadEnd);
                    }
                    set.add(builder.toString());
                }
            }
            return set.first();
        }
    }


    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return start + " <> " + end;
        }
    }

    public static Interval[] intervalIntersection(Interval[] A, Interval[] B) {
        if (A.length == 0 || B.length == 0) return new Interval[0];
        LinkedList<Interval> list = new LinkedList<>();
        int ia = 0;
        int ib = 0;
        while (ia < A.length && ib < B.length) {
            if (isIntersect(A[ia], B[ib])) {
                int start = Math.max(A[ia].start, B[ib].start);
                int end = Math.min(A[ia].end, B[ib].end);
                if (!list.isEmpty() && list.getLast().end >= start) {
                    start = list.pollLast().start;
                }
                list.add(new Interval(start, end));
            }
            System.out.println(A[ia].end + "<" + B[ib].end);
            System.out.println(A[ia].end < B[ib].end);
            if (A[ia].end < B[ib].end) {
                ia++;
            } else {
                ib++;
            }
        }
        Interval[] result = new Interval[list.size()];
        int i = 0;
        for (Interval interval : list) {
            result[i] = interval;
            i++;
        }
        return result;
    }

    static boolean isIntersect(Interval a, Interval b) {
        System.out.println(a + "--" + b);
        return (a.start >= b.start && a.start <= b.end) || (b.start >= a.start && b.start <= a.end);
    }


    public static void main(String[] args) {
//        int[] ints = sumEvenAfterQueries(new int[]{1, 2, 3, 4}, new int[][]{{1, 0}, {-3, 1}, {-4, 0}, {2, 3}});
//        for (int i = 0; i < ints.length; i++) {
//            System.out.println(ints[i]);
//        }
        Interval[] intervals = intervalIntersection(new Interval[]{
                new Interval(0, 10)
        }, new Interval[]{
                new Interval(1, 1),
                new Interval(2, 3),
                new Interval(9, 11)
        });
        for (Interval interval : intervals) {
            System.out.println(interval);
        }

    }

}
