package google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SpiralMatrix {

    static class Solution {
        public List<Integer> spiralOrder(int[][] matrix) {
            int m = matrix.length;
            if (m == 0) return Collections.emptyList();
            int n = matrix[0].length;
            List<Integer> result = new ArrayList<>(m * n);
            int k = 0;
            int l = 0;
            while (k < m && l < n) {
                for (int i = l; i < n; ++i) {
                    result.add(matrix[k][i]);
                }
                k++;
                for (int i = k; i < m; ++i) {
                    result.add(matrix[i][n - 1]);
                }
                n--;
                if (k < m) {
                    for (int i = n - 1; i >= l; --i) {
                        result.add(matrix[m - 1][i]);
                    }
                    m--;
                }
                if (l < n) {
                    for (int i = m - 1; i >= k; --i) {
                        result.add(matrix[i][l]);
                    }
                    l++;
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        new Solution().spiralOrder(new int[][]{
                {1}
        }).forEach(System.out::println);
        System.out.println();
        new Solution().spiralOrder(new int[][]{
                {1, 2},
                {3, 4}
        }).forEach(System.out::println);
        System.out.println();
        new Solution().spiralOrder(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }).forEach(System.out::println);
    }
}
