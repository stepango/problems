package islands;

import java.util.Arrays;

public class NumOfIslands {

    int il;
    int jl;

    public int numIslands(char[][] grid) {
        int num = 0;
        il = grid.length - 1;
        if (il < 0) return 0;
        jl = grid[0].length - 1;
        for (int i = 0; i <= il; i++) {
            char[] chars = grid[i];
            for (int j = 0; j <= jl; j++) {
                char c = chars[j];
                if (c == '1') {
                    num++;
                    dfs(grid, i, j);
                }
            }
        }
        return num;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i >= 0  && j >= 0 && i <= il && j <= jl && grid[i][j] != '0') {
            grid[i][j] = '0';
            dfs(grid, i, j + 1);
            dfs(grid, i, j - 1);
            dfs(grid, i + 1, j);
            dfs(grid, i - 1, j);
        }
    }

    public static void main(String[] args) {
        System.out.println(new NumOfIslands().numIslands(new char[][]{}));
        System.out.println(new NumOfIslands().numIslands(new char[][]{{
                '1'
        }}));
        System.out.println(new NumOfIslands().numIslands(new char[][]{{
                '0'
        }}));
        System.out.println(new NumOfIslands().numIslands(new char[][]{
                {'0', '1'}
        }));
        System.out.println(new NumOfIslands().numIslands(new char[][]{
                {'0', '1'},
                {'1', '0'}
        }));
        System.out.println(new NumOfIslands().numIslands(new char[][]{
                {'0', '1'},
                {'1', '1'}
        }));
        System.out.println(new NumOfIslands().numIslands(new char[][]{
                {'0', '1'},
                {'1', '1'},
                {'1', '0'},
        }));
        System.out.println(new NumOfIslands().numIslands(new char[][]{
                {'0', '1', '1'},
                {'1', '0', '1'},
                {'1', '0', '1'},
        }));
        System.out.println(new NumOfIslands().numIslands(new char[][]{
                {'1', '1', '1'},
                {'1', '0', '1'},
                {'1', '0', '1'},
        }));
    }

}
