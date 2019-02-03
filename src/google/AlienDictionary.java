package google;

import java.util.*;

public class AlienDictionary {

    static class Node {
        Character value;
        List<Node> connections = new LinkedList<>();

        Node(Character value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public String toString() {
            return "[" + value + "]";
        }
    }

    static class Graph {
        Node root;
        //Try to replace with array
        Map<Character, Node> lookup = new LinkedHashMap<>();

        void addEdge(Character parent, Character child) {
//            System.out.println(parent + "->" + child);
            if (root == null) {
                root = new Node(parent);
                lookup.put(root.value, root);
            }
            Node nParent = lookup.get(parent);
            if (nParent == null) {
                nParent = new Node(parent);
            }
            lookup.put(parent, nParent);
            if (child == null) return;
            Node nChild = lookup.get(child);
            if (nChild == null) {
                nChild = new Node(child);
            }
            lookup.put(child, nChild);
            nParent.connections.add(nChild);
        }
    }

    public String alienOrder(String[] words) {
        if (words.length == 0) return "";
        Graph graph = new Graph();
        if (words.length == 1) {
            for (char c : words[0].toCharArray()) {
                graph.addEdge(c, null);
            }
            StringBuilder sb = new StringBuilder();
            for (Character character : graph.lookup.keySet()) {
                sb.append(character);
            }
            return sb.toString();
        }
        for (int i = 0; i < words.length - 1; i++) {
            String word = words[i];
            String word1 = words[i + 1];
            if (word.equals("") || word1.equals("")) continue;
            int l = word.length();
            int l1 = word1.length();
            int lmax = Math.max(l, l1);
            boolean ordered = true;
            for (int j = 0; j < lmax; j++) {
                if (j > l - 1) {
                    graph.addEdge(word1.charAt(j), null);
                    continue;
                }
                if (j > l1 - 1) {
                    graph.addEdge(word.charAt(j), null);
                    continue;
                }
                char c = word.charAt(j);
                char c1 = word1.charAt(j);
                if (ordered && c != c1) {
                    graph.addEdge(c, c1);
                    ordered = false;
                } else {
                    graph.addEdge(c, null);
                    graph.addEdge(c1, null);
                }
            }
        }
        return dfs(graph);
    }

    String dfs(Graph graph) {
        Set<Character> visited = new LinkedHashSet<>();
        Stack<Node> stack = new Stack<>();
        Set<Character> result = new LinkedHashSet<>();
        for (Node value : graph.lookup.values()) {
            Node node = value;
            stack.push(value);
            while (!stack.isEmpty()) {
                if (visited.contains(node.value)) {
                    Node tmp = node;
                    node = stack.pop();
                    result.add(node.value);
                    System.out.println(result);
                    if (stack.search(tmp) != -1) {
                        return "";
                    }
                } else {
                    visited.add(node.value);
                    for (Node child : node.connections) {
                        stack.push(child);
                    }
                    node = stack.peek();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character character : result) {
            sb.insert(0, character);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        System.out.println(new AlienDictionary().alienOrder(new String[]{"abcbac"}));
//        System.out.println(new AlienDictionary().alienOrder(new String[]{
//                "abcbac",
//                "abcbagiii"
//        }));
        System.out.println(new AlienDictionary().alienOrder(new String[]{
                "vlxpwiqbsg",
                "cpwqwqcd"
        })); //"bdgilpqsvcwx"

//        System.out.println(new AlienDictionary().alienOrder(new String[]{
//                "wrt", "wrf", "er", "ett", "rftt"
//        }));
//        System.out.println(new AlienDictionary().alienOrder(new String[]{
//                "z", "x", "z", "x"
//        }));
//
//        System.out.println(new AlienDictionary().alienOrder(new String[]{
//                "aac", "aabb", "aaba"
//        }));//cba

//        System.out.println(new AlienDictionary().alienOrder(new String[]{
//                "za",
//                "zb",
//                "ca",
//                "cb"
//        }));//abzc
    }

}

