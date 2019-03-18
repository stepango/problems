package contest;

import java.util.*;

public class Contest24Feb {

    static class Judge {
        public int findJudge(int N, int[][] trust) {
            // Who
            int[] n = new int[N];
            // Whom
            boolean[] n2 = new boolean[N];
            for (int i = 0; i < trust.length; i++) {
                n[trust[i][1] - 1] += 1;
                n2[trust[i][0] - 1] = true;
            }
            int count = 0;
            int result = -1;
            for (int i = 0; i < n.length; i++) {
                if (n[i] == N - 1 && !n2[i]) {
                    count++;
                    result = i + 1;
                }
            }
            if (count == 1) {
                return result;
            } else {
                return -1;
            }
        }
    }

    class Shess {
        public int numRookCaptures(char[][] board) {
            int x = -1, y = -1;
            int[][] dir = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
            // R, B, p
            int result = 0;
            for (int i = 0, boardLength = board.length; i < boardLength; i++) {
                char[] chars = board[i];
                for (int i1 = 0, chars1Length = chars.length; i1 < chars1Length; i1++) {
                    char c = chars[i1];
                    if (c == 'R') {
                        for (int i2 = 0; i2 < dir.length; i2++) {
                            result += find(board, dir[i2], i, i1);
                        }
                        return result;
                    }
                }
            }
            return 0;
        }

        private int find(char[][] board, int[] dir, int i, int j) {
            while (i >= 0 && j >= 0 && i <= 7 && j <= 7) {
                i += dir[0];
                j += dir[1];
                if (board[i][j] == 'p') {
                    return 1;
                } else if (board[i][j] != '.') {
                    return 0;
                }
            }
            return 0;
        }

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    class MaxTree {


        public TreeNode insertIntoMaxTree(TreeNode root, int val) {
            if (root == null || root.val < val) {
                TreeNode node = new TreeNode(val);
                node.left = root;
                return node;
            }
            root.right = insertIntoMaxTree(root.right, val);
            return root;
        }

    }

    static class Solution {
//        public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
//            int[] result = new int[queries.length];
//            for (int i = 0; i < queries.length; i++) {
//                int[] query = queries[i];
//                for (int j = 0; j < lamps.length; j++) {
//                    int[] lamp = lamps[j];
//
//                }
//            }
//        }
//        int distance(int[] a, int[] b){
//            Math.abs(a[0] - b[0])x
//        }

        public boolean wordBreak(String s, List<String> wordDict) {
            System.out.println(s);
            boolean[] memo = new boolean[s.length()];
            return wordBreak(0, s, new ArrayList<>(wordDict), memo);
        }

        public boolean wordBreak(int index, String s, List<String> wordDict, boolean[] memo) {
            if (memo[index]) return false;
            memo[index] = true;
            System.out.println(Arrays.toString(memo) + " " + index);
            for (int i = 0; i < wordDict.size(); i++) {
                String prefix = wordDict.get(i);
                if (s.startsWith(prefix)) {
                    System.out.println(s + " " + prefix);
                    if (s.length() == prefix.length()) return true;
                    if (wordBreak(index + prefix.length(), s.substring(prefix.length()), wordDict, memo)) return true;
                }
            }
            return false;
        }

        public int wordsTyping(String[] sentence, int rows, int cols) {
            int index = 0;
            int cr = 0;
            int cc = 0;
            int result = 0;
            int[] l = new int[sentence.length];
            while (cr < rows) {
                if (l[index] == 0) {
                    l[index] = sentence[index].length();
                }
                if (l[index] + cc > cols) {
                    cc = 0;
                    cr++;
                } else {
                    cc += l[index] + 1;
                    index++;
                    if (index >= sentence.length) {
                        index = 0;
                        result++;
                    }
                }
            }
            return result;
        }
    }

    /*
"12"
""
"0"
"01"
"10"
"100"
"10101"
"1020"
"102030"
     */


    public static void main(String[] args) {
//        System.out.println(new Solution().wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
//        System.out.println(new Solution().wordBreak("applepenapple", Arrays.asList("apple", "pen")));
//        System.out.println(new Solution().wordBreak("cars", Arrays.asList("car", "ca", "rs")));
//        System.out.println(new Solution().wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", Arrays.asList("a", "aa", "aaa", "aaaa", "aaaaa", "aaaaaa")));
//        System.out.println(new Solution().findJudge(2, new int[][]{{1, 2}}));
//        System.out.println(new Solution().findJudge(3, new int[][]{{1, 2}, {2, 3}}));
//        System.out.println(new Solution().findJudge(4, new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}}));

//        System.out.println(new Solution().wordsTyping(new String[]{"hello", "world"}, 2, 8));
        System.out.println(new Solution().wordsTyping(new String[]{"f", "p", "a"}, 8, 7));
    }
}
