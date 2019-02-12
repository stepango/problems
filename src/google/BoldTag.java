package google;

import java.util.Arrays;

/**
 * public class BoldTag {
 * <p>
 * Write a function that adds bold tags (<b> and </b>) to the input string where the specified substrings match.  Substrings may repeat or overlap.
 * <p>
 * String function emboldenString(String input, String[] toBold) {
 * ...
 * }
 * <p>
 * emboldenString("'Hello my friendly dog", ["Hello", "llo", “dog”])
 * result: “<b>Hello</b> my friendly <b>dog</b>”
 * <p>
 * emboldenString("aaaabbbcccccaa", ["aaa", "c"])
 * result: "<b>aaaa</b>bbb<b>ccccc</b>aa"
 * <p>
 * emboldenString("aaaaaabbbcccccaa", ["aaa", "ab"])
 * result: "<b>aaaaaab</b>bbcccccaa"
 * <p>
 * <p>
 * Create prefix tree
 * iterate trough string - find match in ptrfix tree
 * remember if index of closing tag if next word starts at same positino - merge tags
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * }
 */

public class BoldTag {
    static String emboldenString(String input, String[] toBold) {
        boolean[] mask = new boolean[input.length()];
        for (int i = 0; i < toBold.length; i++) {
            String word = toBold[i];
            int startIndex = input.indexOf(word);
            while (startIndex != -1) {
                for (int j = startIndex; j < startIndex + word.length(); j++) {
                    mask[j] = true;
                }
                startIndex = input.indexOf(word, startIndex + 1);
            }
        }
        System.out.println(Arrays.toString(mask));
        boolean lastMask = false;
        boolean m = false;
        StringBuilder sb = new StringBuilder();
        for (int i = 0, maskLength = mask.length; i < maskLength; i++) {
            m = mask[i];
            char c = input.charAt(i);
            if (!lastMask && m) {
                sb.append("<b>");
            } else if (lastMask && !m) {
                sb.append("</b>");
            }
            sb.append(c);
            lastMask = m;
        }
        if (m) {
            sb.append("</b>");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(emboldenString("abcxyz123", new String[]{"abc", "123"}));
    }
}
