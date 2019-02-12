package google;

import java.util.HashMap;
import java.util.Map;

class AllOne {

    Map<String, Node> map = new HashMap<>();
    Node head;
    Node tail;

    class Node {
        Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        String key;
        Integer value;
        Node next;
        Node prev;

        @Override
        public String toString() {
            return "[key:" + key + ", value" + value + "]";
        }
    }

    /**
     * Initialize your data structure here.
     */
    public AllOne() {
    }

    /**
     * Inserts a new key <Key> with value 1. Or increments an existing key by 1.
     */
    public void inc(String key) {
        Node node = map.get(key);
        if (node == null) {
            node = new Node(key, 1);
            if (tail != null) {
                incertAfter(tail, node);
                tail = node;
            }
            map.put(key, node);
        } else {
            node.value += 1;
        }
        if (head == null) head = node;
        if (tail == null) tail = node;
        // move node
        while (node.prev != null && !inOrder(node)) {
            swap(node.prev, node);
        }
        moveHead();
        moveTail();
    }

    /**
     * Decrements an existing key by 1. If Key's value is 1, remove it from the data structure.
     */
    public void dec(String key) {
        Node node = map.get(key);
        if (node == null) return;
        node.value -= 1;
        if (node.value == 0) {
            if (tail == node)
                tail = node.prev;
            if (head == node) {
                head = node.next;
            }
            remove(node);
            map.remove(key);
        } else {
            while (node.next != null && !inOrder(node)) {
                swap(node, node.next);
            }
            if (node.value < tail.value) {
                tail = node;
            }
            if (node.value > head.value) {
                head = node;
            }
        }
        moveHead();
        moveTail();
    }

    void moveHead() {
        while (head.prev != null) {
            head = head.prev;
        }
    }

    void moveTail() {
        while (tail.next != null) {
            tail = tail.next;
        }
    }

    void swap(Node n1, Node n2) {
//        System.out.println(n1 + " <> " + n2);
        remove(n2);
        incertBefore(n1, n2);
    }

    void incertBefore(Node target, Node node) {
        Node tp = target.prev;
        if (tp != null) {
            tp.next = node;
            node.prev = tp;
        }
        target.prev = node;
        node.next = target;
    }

    void incertAfter(Node target, Node node) {
//        System.out.println("IAfter: " + target + " - " + node);
        Node tn = target.next;
        if (tn != null) {
            tn.prev = node;
            node.next = target;
        }
        target.next = node;
        node.prev = target;
    }

    private void remove(Node n) {
        if (n.next != null)
            n.next.prev = n.prev;
        if (n.prev != null)
            n.prev.next = n.next;
        n.prev = null;
        n.next = null;
    }

    private boolean inOrder(Node n) {
        if (n.prev != null && n.prev == n.next) throw new IllegalArgumentException();
        Integer v = n.value;
        return (n.prev == null || n.prev.value >= v) && (n.next == null || n.next.value <= v);
    }

    /**
     * Returns one of the keys with maximal value.
     */
    public String getMaxKey() {
        if (head != null) {
            return head.key;
        } else {
            return "";
        }
    }

    /**
     * Returns one of the keys with Minimal value.
     */
    public String getMinKey() {
        if (tail != null) {
            return tail.key;
        } else {
            return "";
        }
    }

    void print() {
        Node n = head;
        while (n != null) {
            System.out.print(n.key + "-" + n.value + "->");
            n = n.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        AllOne allOne = new AllOne();
//        System.out.println(allOne.getMaxKey() + " - Max");
//        System.out.println(allOne.getMinKey() + " - Min");
//        allOne.inc("1");
//        allOne.inc("1");
//        allOne.inc("1");
//        allOne.inc("1");
//        allOne.inc("2");
//        System.out.println(allOne.getMaxKey() + " - Max");
//        System.out.println(allOne.getMinKey() + " - Min");
//        "inc","inc","inc","inc","inc","inc","dec","dec","getMinKey","dec","getMaxKey","getMinKey"]
//        ["a"],["b"],["b"],["c"],["c"],["c"],["b"],["b"],[],         ["a"],[],         []]
//        allOne = new AllOne();
//        System.out.println(allOne.getMaxKey() + " - Max");
//        System.out.println(allOne.getMinKey() + " - Min");
//        allOne.inc("a");
//        allOne.print();
//        allOne.inc("b");
//        allOne.print();
//        allOne.inc("b");
//        allOne.print();
//        allOne.inc("c");
//        allOne.print();
//        allOne.inc("c");
//        allOne.print();
//        allOne.inc("c");
//        allOne.print();
//        allOne.dec("b");
//        allOne.print();
//        allOne.dec("b");
//        allOne.print();
//        allOne.dec("a");
//        allOne.print();
//        System.out.println(allOne.getMinKey() + " - Min");
        allOne.inc("hello");allOne.print();
        allOne.inc("world");allOne.print();
        allOne.inc("leet");allOne.print();
        allOne.inc("code");allOne.print();
        allOne.inc("DS");allOne.print();
        allOne.inc("leet");allOne.print();
        System.out.println(allOne.getMaxKey());
        allOne.inc("DS");allOne.print();
        allOne.dec("leet");allOne.print();
        System.out.println(allOne.getMaxKey());

//        "inc",     "inc",    "inc",   "inc",   "inc", "inc", "getMaxKey","inc","dec","getMaxKey","dec","inc","getMaxKey","inc","inc","dec","dec","dec","dec","getMaxKey","inc","inc","inc","inc","inc","inc","getMaxKey","getMinKey"]
//        ["hello"],["world"],["leet"],["code"],["DS"],["leet"],[],       ["DS"],["leet"],[],["DS"],["hello"],[],["hello"],["hello"],["world"],["leet"],["code"],["DS"],[],["new"],["new"],["new"],["new"],["new"],["new"],[],[]]
    }
}
