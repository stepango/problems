package contest;

import java.util.*;

public class ContestFeb4 {

    boolean execicsed = false;

    public boolean validPalindrome(String s) {
        System.out.println(s);
        if (s.length() <= 2 && !execicsed) return true;
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else if (!execicsed) {
                execicsed = true;
                return validPalindrome(s.substring(i, j)) || validPalindrome(s.substring(i + 1, j + 1));
            } else {
                return false;
            }
        }
        return true;
    }

    static class MapSum {

        class Node {
            Character c;
            int value = 0;
            Map<Character, Node> children = new HashMap<>();

            Node(Character c) {
                this.c = c;
            }

            @Override
            public int hashCode() {
                return c.hashCode();
            }

            @Override
            public String toString() {
                return c + "->" + value;
            }
        }

        Node root = new Node(null);

        /**
         * Initialize your data structure here.
         */
        public MapSum() {

        }

        public void insert(String key, int val) {
            Node node = root;
            Node child = null;
            for (char c : key.toCharArray()) {
                child = node.children.get(c);
                if (child == null) {
                    child = new Node(c);
                    node.children.put(c, child);
                }
                node = child;
            }
            if (child != null) {
                child.value = val;
            }
        }

        public int sum(String prefix) {
            Node node = root;
            int result = 0;

            for (char c : prefix.toCharArray()) {
                if (node == null) return result;
                node = node.children.get(c);
            }
            return sum(node);
        }

        int sum(Node node) {
            if (node == null) return 0;
            int result = 0;
            Stack<Node> stack = new Stack<>();
            stack.push(node);
            while (!stack.isEmpty()) {
                Node n = stack.pop();
                result += n.value;
                System.out.println(result + " " + n);
                for (Node value : n.children.values()) {
                    stack.push(value);
                }

            }
            return result;
        }
    }

    class Solution {
        public boolean checkValidString(String s) {
            return checkValidString(s, 0);
        }

        public boolean checkValidString(String s, int startIndex) {
            Stack<Character> stack = new Stack<>();
            char[] charArray = s.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                char c = charArray[i];
                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                } else if (c == '*') {
                    
                }
            }
            return true;
        }

    }

    public static void main(String[] args) {
        System.out.println(new ContestFeb4().validPalindrome("abc"));
//        System.out.println(new ContestFeb4().validPalindrome("abba"));
//        System.out.println(new ContestFeb4().validPalindrome("abbac"));
//        System.out.println(new ContestFeb4().validPalindrome("abbacc"));
//        System.out.println(new ContestFeb4().validPalindrome("abajj"));
//        MapSum m = new MapSum();
//        m.insert("apple", 3);
//        System.out.println(m.sum("ap"));
//        m.insert("app", 2);
//        System.out.println(m.sum("app"));

    }

}
