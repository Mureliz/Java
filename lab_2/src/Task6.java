public class Task6 {
    public static int sum2DArray(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix)
            for (int val : row)
                sum += val;
        return sum;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2}, {3, 4}};
        System.out.println(sum2DArray(matrix)); // 10
    }
}
