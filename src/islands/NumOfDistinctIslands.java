package islands;

import java.util.HashSet;
import java.util.Set;

public class NumOfDistinctIslands {

    int il;
    int jl;
    int hash;

    public int numDistinctIslands(int[][] grid) {
        il = grid.length - 1;
        if (il < 0) return 0;
        jl = grid[0].length - 1;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i <= il; i++) {
            int[] chars = grid[i];
            for (int j = 0; j <= jl; j++) {
                int c = chars[j];
                if (c == 1) {
                    hash = 7;
                    dfs(grid, i, j, i, j);
                    set.add(hash);
                }
            }
        }
        return set.size();
    }

    private void dfs(int[][] grid, int i, int j, int starti, int startj) {
        if (i >= 0 && j >= 0 && i <= il && j <= jl && grid[i][j] != 0) {
            grid[i][j] = 0;
            hash = hash * 31 + (i - starti) + (j - startj) * il;
            dfs(grid, i, j + 1, starti, startj);
            dfs(grid, i, j - 1, starti, startj);
            dfs(grid, i + 1, j, starti, startj);
            dfs(grid, i - 1, j, starti, startj);
        }
    }

    public static void main(String[] args) {
        System.out.println(new NumOfDistinctIslands().numDistinctIslands(new int[][]{
                {1, 1, 1},
                {0, 0, 0},
                {1, 0, 1},
        }));
    }

}
