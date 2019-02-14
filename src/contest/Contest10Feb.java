package contest;

public class Contest10Feb {

    char[] map = new char[26];

    public boolean equationsPossible(String[] equations) {
        for (char i = 0; i < 26; i++) {
            map[i] = i;
        }

        for (int i = 0, equationsLength = equations.length; i < equationsLength; i++) {
            String equation = equations[i];
            if (equation.charAt(1) == '=') {
                char a = equation.charAt(0);
                char b = equation.charAt(3);
                union(a, b);
            }
        }

        for (int i = 0, equationsLength = equations.length; i < equationsLength; i++) {
            String equation = equations[i];
            if (equation.charAt(1) == '!') {
                char a = equation.charAt(0);
                char b = equation.charAt(3);
                if (a == b) return false;
                if (find(a - 'a') == find(b - 'a')) return false;
            }
        }
        return true;
    }

    void union(char a, char b) {
        int ia = find(a - 'a');
        int ib = find(b - 'a');
        if (ia != ib) map[ib] = (char) ia;
    }

    char find(int v) {
        while (v != map[v]) v = map[v];
        return (char) v;
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
//        System.out.println(new Contest10Feb().equationsPossible(new String[]{"a==b", "b!=c", "c==a"}));
        System.out.println(new Contest10Feb().equationsPossible(new String[]{"b!=f", "c!=e", "f==f", "d==f", "b==f", "a==f"}));
    }
}
