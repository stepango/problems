package google;

public class GameOfLife {

    //Any live cell with fewer than two live neighbors dies, as if caused by under-population.
    //Any live cell with two or three live neighbors lives on to the next generation.
    //Any live cell with more than three live neighbors dies, as if by over-population..
    //Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
    public void gameOfLife(int[][] board) {
        int[][] b = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                b[i][j] = isAlive(i, j, board);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = b[i][j];
            }
        }
    }

    static int[][] map = new int[][]{{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    int isAlive(int i, int j, int[][] board) {
        int n = 0;
        int imax = board.length - 1;
        int jmax = board[0].length - 1;
        for (int[] k : map) {
            int ci = i + k[0];
            int cj = j + k[i];
            if (ci <= imax && ci >= 0 && cj <= jmax && cj >= 0) n += board[i][j];
        }

        if (board[i][j] == 1) {
            if (n == 2 || n == 3) return 1;
            else return 0;
        } else {
            if (n == 3) return 1;
            else return 0;
        }
    }

}
