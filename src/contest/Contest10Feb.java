package contest;

import kotlin.Pair;

import java.util.*;

public class Contest10Feb {

    public boolean equationsPossible(String[] equations) {
        DisjointUnionSet set = new DisjointUnionSet();

        for (int i = 0, equationsLength = equations.length; i < equationsLength; i++) {
            String equation = equations[i];
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            if (equation.charAt(1) == '=') {
                set.union(a, b);
            }
        }

        for (int i = 0, equationsLength = equations.length; i < equationsLength; i++) {
            String equation = equations[i];
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            if (equation.charAt(1) == '!') {
                if (a == b) return false;
                if (set.isSameUnion(a, b)) return false;
            }
        }
        return true;
    }

    class DisjointUnionSet {
        int[] map = new int[26];
        int counter = 1;

        void union(char a, char b) {
            int ia = a - 'a';
            int ib = b - 'a';
            int i = map[ia];
            int j = map[ib];
            if (i == 0 && j == 0) {
                counter++;
                map[ia] = counter;
                map[ib] = counter;
            } else if (j == 0) {
                map[ib] = i;
            } else if (i == 0) {
                map[ia] = j;
            } else {
                if (i != j) {
                    if (i > j) {
                        for (int mi = 0; mi < map.length; mi++) {
                            if (map[mi] == j) map[mi] = i;
                        }
                    } else {
                        for (int mi = 0; mi < map.length; mi++) {
                            if (map[mi] == i) map[mi] = j;
                        }
                    }
                }
            }
        }

        boolean isSameUnion(char a, char b) {
            int ia = map[a - 'a'];
            int ib = map[b - 'a'];
            return ia != 0 && ib != 0 && ia == ib;
        }
    }

    public static void main(String[] args) {
//        new Contest10Feb().addToArrayForm(new int[]{1, 3, 0, 0}, 100).forEach(System.out::println);
//        new Contest10Feb().addToArrayForm(new int[]{9, 9, 0, 0}, 100).forEach(System.out::println);
//        new Contest10Feb().addToArrayForm(new int[]{2, 7, 4}, 181).forEach(System.out::println);
//        System.out.println(new Contest10Feb().equationsPossible(new String[]{"a==b", "b!=a"}));
//        System.out.println(new Contest10Feb().equationsPossible(new String[]{"a==a", "b!=b"}));
//        System.out.println(new Contest10Feb().equationsPossible(new String[]{"a==a"}));
//        System.out.println(new Contest10Feb().equationsPossible(new String[]{"a==b"}));
//        System.out.println(new Contest10Feb().equationsPossible(new String[]{"a==b", "b==c"}));
//        System.out.println(new Contest10Feb().equationsPossible(new String[]{"a==b", "b==c", "c!=a"}));
        System.out.println(new Contest10Feb().equationsPossible(new String[]{"a==b", "b!=c", "c==a"}));
    }
}
