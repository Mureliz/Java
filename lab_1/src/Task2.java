import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество чисел: ");
        int n = scanner.nextInt();
        double sum = 0;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                sum += scanner.nextDouble();
            }
            else {
                sum -= scanner.nextDouble();
            }
        }

        System.out.println(sum);
    }
}
