package google;

import kotlin.jvm.JvmField;

public class LongestUnivaluePath {

    static public class TreeNode {
        int val;
        @JvmField
        TreeNode left;
        @JvmField
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    static class Solution {
        int result = 0;

        public int longestUnivaluePath(TreeNode root) {
            if (root == null) return 0;
            result = Math.max(calcBranch(root, root.val), result);
            result = Math.max(longestUnivaluePath(root.left), result);
            result = Math.max(longestUnivaluePath(root.right), result);
            return result;
        }

        int calcBranch(TreeNode node, int val) {
            if (node == null || val != node.val) return 0;
            return 1 + Math.max(calcBranch(node.left, val), calcBranch(node.right, val));
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(5);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(1);
        System.out.println(new Solution().longestUnivaluePath(root));
    }
}
