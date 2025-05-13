import java.util.Arrays;

public class Task2 {
    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            result[k++] = (a[i] < b[j]) ? a[i++] : b[j++];
        }
        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];
        return result;
    }

    public static void main(String[] args) {
        int[] result = mergeSortedArrays(new int[]{1, 3, 5}, new int[]{2, 4, 6});
        System.out.println(Arrays.toString(result));
    }
}
