package contest;

public class StrWithout3a3b {
    static public String strWithout3a3b(int A, int B) {
        int as = 0;
        int bs = 0;
        StringBuffer buffer = new StringBuffer(A + B);
        while (A + B > 0) {
            if (A > B) {
                if (as < 2) {
                    buffer.append('a');
                    A--;
                    as++;
                    bs = 0;
                } else {
                    buffer.append('b');
                    B--;
                    bs++;
                    as = 0;
                }
            } else {
                if (bs < 2) {
                    buffer.append('b');
                    B--;
                    bs++;
                    as = 0;
                } else {
                    buffer.append('a');
                    A--;
                    as++;
                    bs = 0;
                }
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        System.out.println(strWithout3a3b(4, 1));
        System.out.println(strWithout3a3b(4, 4));
        System.out.println(strWithout3a3b(5, 3));
        System.out.println(strWithout3a3b(1, 3));
        System.out.println(strWithout3a3b(4, 7));
    }
}
