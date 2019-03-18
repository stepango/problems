package contest;

import java.util.Arrays;
import java.util.Stack;

public class Contest10Mar {
    public int largestSumAfterKNegations(int[] A, int K) {
        Arrays.sort(A);
        int i = 0;
        while (K > 0) {
            if (A[i] < 0) {
                A[i] = -A[i];
                if (i < A.length - 1 && Math.abs(A[i]) >= Math.abs(A[i + 1])) i++;
                K--;
            } else {
                A[i] = -A[i];
                K--;
            }
            System.out.println(Arrays.toString(A));
        }
        int result = 0;
        for (int i1 : A) {
            result += i1;
        }
        return result;
    }

    public int clumsy(int N) {
        Stack<Integer> stack = new Stack<>();
        int counter = 0;

        stack.push(N);
        while (N > 0) {
            N--;
            if (N == 0) break;
            if (counter % 4 == 0) {
                stack.push(stack.pop() * N);
            } else if (counter % 4 == 1) {
                Integer pop = stack.pop();
                int neg = 1;
                if (pop < 0) {
                    pop = -pop;
                    neg = -1;
                }
                stack.push(neg * ((int) Math.floor(pop / (float) N)));
            } else if (counter % 4 == 2) {
                stack.push(N);
            } else if (counter % 4 == 3) {
                stack.push(-N);
            }
            counter++;
            System.out.println(stack);
        }
        int result = 0;
        for (Integer integer : stack) {
            result += integer;
        }
        return result;
    }

    public int minDominoRotations(int[] A, int[] B) {
        int a = A[0];
        int b = B[0];
        if (a == b) {
            int min = Math.min(flipBottom(a, B, A), flipBottom(a, A, B));
            return min == Integer.MAX_VALUE ? -1 : min;
        } else {
            int min = Math.min(
                    Math.min(flipBottom(a, B, A), flipBottom(a, A, B)),
                    Math.min(flipBottom(b, B, A), flipBottom(b, A, B)));
            return min == Integer.MAX_VALUE ? -1 : min;
        }
    }

    private int flipBottom(int a, int[] A, int[] B) {
        int flips = 0;
        for (int i1 = 0, a1Length = A.length; i1 < a1Length; i1++) {
            int i = A[i1];
            int j = B[i1];
            if (i != a) {
                if (j == a) {
                    flips++;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
        }
        return flips;
    }


    public static void main(String[] args) {
//        System.out.println(new Contest10Mar().largestSumAfterKNegations(
//                new int[]{1, 3, 2, 6, 7, 9}, 3
//        ));
//        System.out.println(new Contest10Mar().largestSumAfterKNegations(
//                new int[]{-2, 5, 0, 2, -2}, 3
//        ));
//        System.out.println(new Contest10Mar().clumsy(10));
//        System.out.println(new Contest10Mar().clumsy(4));

        System.out.println(new Contest10Mar().minDominoRotations(
                new int[]{2, 1, 2, 4, 2, 2},
                new int[]{5, 2, 6, 2, 3, 2}
        ));
        System.out.println(new Contest10Mar().minDominoRotations(
                new int[]{3, 5, 1, 2, 3},
                new int[]{3, 6, 3, 3, 4}
        ));


    }
}
