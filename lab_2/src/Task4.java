import java.util.Arrays;

public class Task4 {
    public static int[][] rotateClockwise(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] rotated = new int[cols][rows];
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                rotated[j][rows - 1 - i] = matrix[i][j];
        return rotated;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2}, {3, 4}};
        int[][] rotated = rotateClockwise(matrix);
        for (int[] row : rotated)
            System.out.println(Arrays.toString(row));
    }
}
