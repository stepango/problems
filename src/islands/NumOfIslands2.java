package islands;

import java.util.ArrayList;
import java.util.List;

public class NumOfIslands2 {

    int[] map;
    int[] rank;
    int count = 0;

    void union(int a, int b) {
        int ia = find(a);
        int ib = find(b);
        if (a == b && rank[a] == -1) {
            rank[a] = a;
            count++;
        } else if (ia != ib) {
            map[ib] = ia;
            int rb = rank[b];
            if (rb == -1) {
                count++;
            } else if (rb != ia) {
                count--;
            }
            rank[b] = ia;
        }
    }

    int find(int v) {
        int iv = v;
        while (v != map[v]) v = map[v];
        map[iv] = v;
        return v;
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        ArrayList<Integer> result = new ArrayList<>(positions.length);
        map = new int[m * n];
        rank = new int[m * n];
        int length = map.length;
        for (int i = 0; i < length; i++) {
            map[i] = i;
            rank[i] = -1;
        }
        int mm = m - 1;
        int nn = n - 1;
        for (int i = 0, positionsLength = positions.length; i < positionsLength; i++) {
            int[] position = positions[i];
            int x = position[0];
            int y = position[1];
            int point = x + m * y;
            union(point, point);
            if (x > 0) union(point, m,  y, x - 1);
            if (x < mm) union(point, m,  y, x + 1);
            if (y > 0) union(point, m, y - 1, x);
            if (y < nn) union(point, m, y + 1, x);
            result.add(count);
        }

        return result;
    }

    void union(int point, int m, int y, int x) {
        int p = x + m * y;
        if (rank[p] != -1) {
            union(point, p);
        }
    }

    public static void main(String[] args) {
        System.out.println(new NumOfIslands2().numIslands2(
                1, 1, new int[][]{{0, 0}, {0, 0}}
        ));
        System.out.println(new NumOfIslands2().numIslands2(
                3, 3, new int[][]{{1, 1}, {0, 1}}
        ));

        System.out.println(new NumOfIslands2().numIslands2(
                3, 3, new int[][]{{0, 0}, {1, 1}, {0, 1}}
        ));
        System.out.println(new NumOfIslands2().numIslands2(
                3, 3, new int[][]{{0, 0}, {1, 1}, {0, 1}}
        ));
        System.out.println(new NumOfIslands2().numIslands2(
                3, 3, new int[][]{{0, 1}, {1, 0}, {1, 2}, {2, 1}, {1, 1}}
        ));
        System.out.println(new NumOfIslands2().numIslands2(
                8, 2, new int[][]{{7, 0}}
        ));
        System.out.println(new NumOfIslands2().numIslands2(
                8, 1, new int[][]{{7, 0}}
        ));
        System.out.println(new NumOfIslands2().numIslands2(
                3, 10, new int[][]{{0, 1}, {1, 0}, {1, 2}, {2, 1}, {1, 1}}
        ));
    }

}
