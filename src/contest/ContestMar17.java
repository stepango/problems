package contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ContestMar17 {

    public int bitwiseComplement(int N) {
        int result = 0;
        char[] chars = Integer.toString(N, 2).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            result = result << 1;
            if (chars[i] == '0') result += 1;
        }
        return result;
    }

    public int numPairsDivisibleBy60(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i1 = 0, timeLength = time.length; i1 < timeLength; i1++) {
            int i = time[i1];
            int v = i % 60;
            Integer integer = map.get(v);
            if (integer == null) {
                map.put(v, 1);
            } else {
                map.put(v, 1 + integer);
            }
        }
        System.out.println(map.entrySet());
        int result = 0;
        for (int i = 0; i < time.length; i++) {
            int v = 60 - (time[i] % 60);
            if (v == 60) v = 0;
            Integer integer = map.get(v);
            System.out.println(v + " " + integer);
            if (integer != null) {
                result += integer;
            }
            System.out.println(result);
            if (v == 30 || v == 0) {
                result--;
            }
            System.out.println(result);
        }
        return result / 2;
    }

    public int shipWithinDays(int[] weights, int D) {
        int[] capacity = new int[weights.length];
        int minCapacity = weights[0];
        int tmpCap = 0;
        for (int i = 0; i < capacity.length; i++) {
            int weight = weights[i];
            tmpCap += weight;
            capacity[i] = tmpCap;
            minCapacity = Math.max(minCapacity, weight);
        }
        System.out.println(Arrays.toString(capacity));
        int result = 0;
        for (int i = 0; i < capacity.length; i++) {
            int c = capacity[i];
            int days = 0;
            if (c < minCapacity) continue;
            int tmpW = 0;
            for (int weight : weights) {
                tmpW += weight;
                if (tmpW > c) {
                    days++;
                    tmpW = c;
                }
            }
            if (days == D) {
                result = c;
                break;
            }
        }
        int days = D;
        while (days == D) {
            int tmpW = 0;
            for (int weight : weights) {
                tmpW += weight;
                if (tmpW > result) {
                    days++;
                }
            }
            result--;
        }
        return result + 1;
    }

    public static void main(String[] args) {
//        System.out.println(new ContestMar17().numPairsDivisibleBy60(new int[]{
//                30, 20, 150, 100, 40
//        }));

        System.out.println(new ContestMar17().numPairsDivisibleBy60(new int[]{
                60, 60, 60
        }));
    }

}
