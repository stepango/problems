package contest;

import java.sql.SQLOutput;
import java.util.*;

public class ContestMar3 {

    public List<String> commonChars(String[] A) {
        if (A.length == 0) return Collections.emptyList();
        int[] map = new int[26];
        Arrays.fill(map, -1);
        List<String> result = new ArrayList<>(26);
        char[] charArray = A[0].toCharArray();
        // Iter over 1st word chars
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            for (int j = 0; j < A.length; j++) {
                int val = map[c - 'a'];
                if (val == -1) {
                    map[c - 'a'] = numOfChars(A[j], Integer.MAX_VALUE, c);
                } else {
                    map[c - 'a'] = Math.min(val, numOfChars(A[j], val, c));
                }

            }
        }
        System.out.println(Arrays.toString(map));
        for (int i1 = 0; i1 < map.length; i1++) {
            int i = map[i1];
            while (i > 0) {
                result.add(String.valueOf((char) ('a' + i1)));
                i--;
            }
        }
        return result;
    }

    int numOfChars(String s, int max, char c) {
        int result = 0;
        for (char ch : s.toCharArray()) {
            if (c == ch) {
                result++;
            }
            if (result >= max) {
                return max;
            }
        }
        return result;
    }

    public int longestOnes(int[] A, int K) {
        if (A.length <= K) return A.length;
        int numOnes = 0;
        int numZeroes = 0;
        int i = 0;
        int j = 0;
        if (A[0] == 1) numOnes++;
        if (A[0] == 0) numZeroes++;
        int result = numOnes + Math.min(numZeroes, K);
        while (true) {
            if (j + 1 >= A.length) break;
            if (numZeroes <= K) {
                j++;
                if (A[j] == 1) numOnes++;
                if (A[j] == 0) numZeroes++;
                if (numZeroes <= K) {
                    result = Math.max(numOnes + numZeroes, result);
                    System.out.println(result + " " + i + " " + j);
                }
            } else {
                if (A[i] == 1) numOnes--;
                if (A[i] == 0) numZeroes--;
                i++;
                j++;
                if (A[j] == 1) numOnes++;
                if (A[j] == 0) numZeroes++;
                if (numZeroes <= K) {
                    result = Math.max(numOnes + numZeroes, result);
                    System.out.println(result + " " + i + " " + j);
                }
            }
        }
        return result;
    }

//    public int mergeStones(int[] stones, int K) {
//
//    }

    public boolean isValid(String S) {
        char[] charArray = S.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < charArray.length; i++) {
            char c = charArray[i];
            if (c == 'a' || c == 'b') {
                stack.push(c);
            }

            if (c == 'c') {
                if (stack.isEmpty()) return false;
                if (stack.pop() == 'b') {
                    if (stack.isEmpty()) return false;
                    if (stack.pop() != 'a') return false;
                } else {
                    return false;
                }
            }
            System.out.println(stack);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ContestMar3().isValid("aabcbc"));
//        System.out.println(new ContestMar3().longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
//        System.out.println(new ContestMar3().longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));
//        System.out.println(new ContestMar3().commonChars(new String[]{"bella", "label", "roller"}));
//        System.out.println(new ContestMar3().commonChars(new String[]{"cool", "lock", "cook"}));
//        System.out.println(new ContestMar3().commonChars(new String[]{"acabcddd","bcbdbcbd","baddbadb","cbdddcac","aacbcccd","ccccddda","cababaab","addcaccd"}));
    }
}
