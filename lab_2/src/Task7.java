import java.util.Arrays;

public class Task7 {
    public static int[] maxInEachRow(int[][] matrix) {
        int[] result = new int[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int max = matrix[i][0];
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] > max)
                    max = matrix[i][j];
            }
            result[i] = max;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 5, 3}, {4, 2, 6}};
        System.out.println(Arrays.toString(maxInEachRow(matrix))); // [5, 6]
    }
}
