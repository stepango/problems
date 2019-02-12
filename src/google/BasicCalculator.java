package google;

import java.util.Stack;

public class BasicCalculator {

    /**
     * Stack contains different objects
     */

    public int calculate(String s) {
        if (s.equals("")) return 0;
        Stack stack = new Stack();
        Integer tmpValue = null;
        for (char c : s.toCharArray()) {
            if (c == ' ') continue;
            if (c >= '0' && c <= '9') {
                tmpValue = (tmpValue == null ? 0 : tmpValue) * 10 + c - '0';
            } else {
                if (tmpValue != null) {
                    stack.add(tmpValue);
                    tmpValue = null;
                }
                if (c == '+' || c == '-') {
                    stack.add(c);
                } else if (c == '(') {
                    stack.add(c);
                } else if (c == ')') {
                    int total = 0;
                    while (!stack.isEmpty()) {
                        System.out.println("Total:" + total + "|" + stack);
                        Integer i = (Integer) stack.pop();
                        Character op = (Character) stack.pop();
                        if (op == '-') {
                            total -= i;
                        } else if (op == '+'){
                            total += i;
                        } else if (op == '('){
                            stack.push(total + i);
                            break;
                        }
                    }
                }
            }
        }
        if (tmpValue != null) stack.push(tmpValue);
        int total = 0;
        while (stack.size() > 1) {
            Integer i = (Integer) stack.pop();
            Character op = (Character) stack.pop();
            if (op == '-') {
                total -= i;
            } else {
                total += i;
            }
        }
        int num = 0;
        if (!stack.isEmpty()) num = (Integer) stack.pop();
        return total + num;
    }

    public static void main(String[] args) {
        System.out.println(new BasicCalculator().calculate("(1 34 + 23 - 57 - ((99)))"));
    }

}
