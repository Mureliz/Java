import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class Task5 {
    public static int[] findPairWithSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int complement = target - num;
            if (map.containsKey(complement)) {
                return new int[]{complement, num};
            }
            map.put(num, 1);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] result = findPairWithSum(new int[]{2, 7, 11, 15}, 13);
        System.out.println(result != null ? Arrays.toString(result) : "null");
    }
}
