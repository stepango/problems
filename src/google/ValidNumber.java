package google;

public class ValidNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean sign = false;
        boolean integer = false;
        boolean dec = false;
        boolean decInt = false;
        boolean exp = false;
        boolean esign = false;
        boolean expi = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '+' || c == '-') {
                if (exp) {
                    if (esign || expi) {
                        return false;
                    } else {
                        esign = true;
                    }
                } else if (sign || integer || dec) {
                    return false;
                } else {
                    sign = true;
                }
            } else if (c >= '0' && c <= '9') {
                if (!integer) {
                    integer = true;
                } else if (exp && !expi) {
                    expi = true;
                } else if (dec && !decInt) {
                    decInt = true;
                }
            } else if (c == 'e') {
                if (exp || !integer){
                    return false;
                } else {
                    exp = true;
                }
            } else if (c == '.') {
                if (dec || exp) {
                    return false;
                } else {
                    dec = true;
                }
            } else {
                return false;
            }
        }
        if (sign && !integer) return false;
        if ((esign || exp) && !expi) return false;
        return integer;
    }

    public static void main(String[] args) {
        System.out.println(new ValidNumber().isNumber("46.e3"));
    }
}
