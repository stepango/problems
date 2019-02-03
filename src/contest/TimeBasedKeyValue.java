package contest;

import java.util.*;

public class TimeBasedKeyValue {
    static class TimeMap {

        HashMap<String, TreeMap<Integer, String>> map = new HashMap<>();

        /**
         * Initialize your data structure here.
         */
        public TimeMap() {

        }

        public void set(String key, String value, int timestamp) {
            TreeMap<Integer, String> values = map.get(key);
            if (values == null) {
                values = new TreeMap<>();
                map.put(key, values);
            }
            values.put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> tree = this.map.get(key);
            if (tree == null) return "";
            else {
                Map.Entry<Integer, String> entry = tree.ceilingEntry(timestamp);
                if (entry == null) {
                    return "";
                } else {
                    return entry.getValue();
                }
            }
        }
    }

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("1", "foo", 1);
        System.out.println(timeMap.get("1", 1));
        System.out.println(timeMap.get("1", 2));
        timeMap.set("1", "foo", 3);
        System.out.println(timeMap.get("1", 2));

        //[null,null,"bar","bar",null,"bar2","bar2"]
    }
}
