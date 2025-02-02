import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите натуральное число: ");
        int n = scanner.nextInt();
        scanner.close();

        int count = 0;
        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            }
            else {
                n = 3 * n + 1;
            }
            count++;
        }

        System.out.println(count);
    }
}
