import java.util.HashMap;
import java.util.Map;

public class Task1 {
    public static String longestUniqueSubstring(String s) {
        int start = 0, maxLen = 0, maxStart = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0; end < s.length(); end++) {
            char c = s.charAt(end);
            if (map.containsKey(c) && map.get(c) >= start) {
                start = map.get(c) + 1;
            }
            map.put(c, end);
            if (end - start + 1 > maxLen) {
                maxLen = end - start + 1;
                maxStart = start;
            }
        }
        return s.substring(maxStart, maxStart + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(longestUniqueSubstring("abcabcbb")); // abc
    }
}
