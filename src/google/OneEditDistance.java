package google;

public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        int sl = s.length();
        int tl = t.length();
        if (s.equals(t)) return false;
        if (sl == 0 && tl == 0) return false;
        if (sl == 1 && tl == 0) return true;
        if (sl == 0 && tl == 1) return true;
        int delta = Math.abs(sl - tl);
        if (delta >= 2) return false;
        int noMatch = 0;
        if (delta == 0) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) noMatch++;
                if (noMatch > 1) return false;
            }
        } else {
            String l;
            String sh;
            if (sl > tl) {
                l = s;
                sh = t;
            } else {
                sh = s;
                l = t;
            }
            System.out.println(l +" "+ sh);
            for (int i = 0; i < sh.length(); i++) {
                if (i > sh.length() - 1) noMatch++;
                else {
                    if (sh.charAt(i) != l.charAt(i + noMatch)) { noMatch++; i--;}
                    System.out.println(sh.charAt(i) + "<>" + l.charAt(i + noMatch) + "-" + noMatch);
                }
                if (noMatch > 1) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new OneEditDistance().isOneEditDistance("ab", "acb"));
    }
}
