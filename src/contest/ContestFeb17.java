package contest;

import java.util.*;

public class ContestFeb17 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class TreeCousins {

        int depthX = -1;
        int depthY = -1;
        int parentX = -1;
        int parentY = -1;

        public boolean isCousins(TreeNode root, int x, int y) {


            dfs(root, 0, x, y);

            return depthX != -1 && depthY != -1 && depthX == depthY && parentX != parentY;
        }

        private void dfs(TreeNode root, int depth, int x, int y) {
            if (root == null || depthX != -1 && depthY != -1) return;
            if (root.left != null && root.left.val == x && depthX != -1) {
                parentX = root.val;
                depthX = depth + 1;
            }
            if (root.right != null && root.right.val == x && depthX != -1) {
                parentX = root.val;
                depthX = depth + 1;
            }
            if (root.left != null && root.left.val == y && depthY != -1) {
                parentY = root.val;
                depthY = depth + 1;
            }
            if (root.right != null && root.right.val == y && depthY != -1) {
                parentY = root.val;
                depthY = depth + 1;
            }
            dfs(root.left, depth + 1, x, y);
            dfs(root.right, depth + 1, x, y);
        }
    }

    static class OrangesRotting {

        int il;
        int jl;
        int time = 0;

        public int orangesRotting(int[][] grid) {
            il = grid.length - 1;
            if (il < 0) return 0;
            jl = grid[0].length - 1;
            boolean rot = true;
            while (rot) {
                rot = false;
                for (int i = 0; i <= il; i++) {
                    int[] org = grid[i];
                    for (int j = 0; j <= jl; j++) {
                        int c = org[j];
                        if (c >= 2 && c != time + 3) {
                            rot = spread(grid, i + 1, j) || rot;
                            rot = spread(grid, i, j + 1) || rot;
                            rot = spread(grid, i - 1, j) || rot;
                            rot = spread(grid, i, j - 1) || rot;
                        }
                    }
                }
                if (rot) time++;
                System.out.println(Arrays.deepToString(grid));
            }
            for (int i = 0; i <= il; i++) {
                int[] org = grid[i];
                for (int j = 0; j <= jl; j++) {
                    int c = org[j];
                    if (c == 1) {
                        return -1;
                    }
                }
            }
            return time;
        }

        boolean spread(int[][] grid, int i, int j) {
            if (i >= 0 && j >= 0 && i <= il && j <= jl && grid[i][j] == 1) {
                grid[i][j] = time + 3;
                return true;
            }
            return false;
        }

    }

    static class OrangesRotting2 {

        int il;
        int jl;
        int time = 0;

        class Point {
            int x, y;

            Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        public int orangesRotting(int[][] grid) {
            il = grid.length - 1;
            if (il < 0) return 0;
            jl = grid[0].length - 1;
            boolean rot = true;
            Queue<Point> queue = new LinkedList<>();
            while (rot) {
                rot = false;
                for (int i = 0; i <= il; i++) {
                    int[] org = grid[i];
                    for (int j = 0; j <= jl; j++) {
                        int c = org[j];
                        if (c == 2) {
                            queue.add(new Point(i, j));
                        }
                    }
                }
            }
            while (!queue.isEmpty()) {
                int size = queue.size();
                while (size > 0) {
                    Point p = queue.remove();
                    size--;
                    spread(queue, grid, p.x + 1, p.y);
                    spread(queue, grid, p.x - 1, p.y);
                    spread(queue, grid, p.x, p.y + 1);
                    spread(queue, grid, p.x, p.y - 1);
                }
                time++;
            }
            for (int i = 0; i <= il; i++) {
                int[] org = grid[i];
                for (int j = 0; j <= jl; j++) {
                    int c = org[j];
                    if (c == 1) {
                        return -1;
                    }
                }
            }
            return time;
        }

        void spread(Queue<Point> q, int[][] grid, int i, int j) {
            if (i >= 0 && j >= 0 && i <= il && j <= jl && grid[i][j] == 1) {
                grid[i][j] = 2;
                q.add(new Point(i, j));
            }
        }

    }

    //31622 ~ 2^15

//    public static void main(String[] args) {
//        System.out.println(new OrangesRotting2().orangesRotting(new int[][]{
//                {2, 1, 1},
//                {1, 1, 0},
//                {0, 1, 1}
//        }));
//    }

    static class SquarefulPerms {

        boolean[] visited;
        int[] pert;
        int[] A;
        Set<Integer> set = new HashSet<>();

        public int numSquarefulPerms(int[] A) {
            Arrays.sort(A);
            this.A = A;
            visited = new boolean[A.length];
            pert = new int[A.length];
            List<Integer>[] map = new ArrayList[A.length];
            int filled = 0;
            for (int i = 0; i < A.length; i++) {
                for (int j = 0; j < A.length; j++) {
                    if (i != j && isSquare(A[i] + A[j])) {
                        List<Integer> positions = map[i];
                        if (positions == null) {
                            positions = new ArrayList<>(12);
                            map[i] = positions;
                            filled++;
                        }
                        positions.add(j);
                    }
                }
            }
            if (filled < A.length) return 0;
            int val = -1;
            for (int i = 0, mapLength = map.length; i < mapLength; i++) {
                int tmpa = A[i];
                if (val == tmpa) continue;
                dfs(map, i, 0, A.length, set);
                val = tmpa;
            }

            return set.size();
        }

        void dfs(List<Integer>[] map, int position, int numVisited, int max, Set<Integer> set) {
            visited[position] = true;
            pert[numVisited] = A[position];
            numVisited++;
            if (numVisited == max) {
                set.add(Arrays.hashCode(pert));
                visited[position] = false;
                return;
            }
            List<Integer> integers = map[position];
            for (int i1 = 0, integersSize = integers.size(); i1 < integersSize; i1++) {
                Integer i = integers.get(i1);
                if (!visited[i]) dfs(map, i, numVisited, max, set);
            }
            visited[position] = false;
        }

        Boolean isSquare(int n) {
            int x = (int) Math.sqrt(n);
            return x * x == n;
        }

    }

    public static void main(String[] args) {
        System.out.println(new SquarefulPerms().numSquarefulPerms(new int[]{1, 17, 8}));
        System.out.println(new SquarefulPerms().numSquarefulPerms(new int[]{2, 2, 2}));
        System.out.println(new SquarefulPerms().numSquarefulPerms(new int[]{2, 2, 2, 7}));
        System.out.println(new SquarefulPerms().numSquarefulPerms(new int[]{0, 0, 0, 1, 1, 1}));
    }

    class Solution {

        public int minKBitFlips(int[] A, int K) {
            int flips = 0;
            for (int i = 0, aLength = A.length; i < aLength; i++) {
                int item = A[i];
                if (item == 0) {
                    flips++;
                    int dk = 0;
                    while (dk < K) {
                        int i1 = i + dk;
                        if (i1 < aLength) {
                            if (A[i] == 1) A[i] = 0;
                            if (A[i] == 0) A[i] = 1;
                        } else {
                            return -1;
                        }
                        dk++;
                    }
                }
            }
            return flips;
        }
    }

}
