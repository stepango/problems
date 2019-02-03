package google;

public class KDistinctCharacters {

    static int counter = 1;

    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0) return 0;
        int size = s.length();
        if (size <= k) return s.length();
        int result = 1;
        int i = 0, j = 0;
        int[] entries = new int[256];
        entries[s.charAt(0)] = 1;
        while (j < size - 1) {
            if (k < counter) {
                char key = s.charAt(i);
                int integer = entries[key];
                if (integer == 1) {
                    entries[key] = 0;
                    counter--;
                } else {
                    entries[key] = integer - 1;
                }
                i++;
            }
            j++;
            addToMap(s, entries, j);
            if (k >= counter){
                result = j - i + 1;
            }
            System.out.println(i + " " + j + " size:" + counter);
        }
        return result;
    }

    private static void addToMap(String s, int[] entries, int j) {
        char key = s.charAt(j);
        int integer = entries[key];
        if (integer == 0) {
            entries[key] = 1;
            counter++;
        } else {
            entries[key] = integer + 1;
        }
    }

    public static void main(String[] args) {
//        System.out.println(lengthOfLongestSubstringKDistinct("", 0));
//        System.out.println(lengthOfLongestSubstringKDistinct("", 1));
//        System.out.println(lengthOfLongestSubstringKDistinct("a", 1));
//        System.out.println(lengthOfLongestSubstringKDistinct("aa", 1));
//        System.out.println(lengthOfLongestSubstringKDistinct("aa", 3));
//        System.out.println(lengthOfLongestSubstringKDistinct("aba", 1));
//        System.out.println(lengthOfLongestSubstringKDistinct("aba", 2));
        System.out.println(lengthOfLongestSubstringKDistinct("ccaabbb", 2));
    }

}
