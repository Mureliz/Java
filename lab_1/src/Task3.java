import java.util.Objects;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int x_treasure = scanner.nextInt();
        int y_treasure = scanner.nextInt();
        scanner.nextLine(); // для очистки буфера

        // Начальные координаты
        int x = 0, y = 0;
        int steps = 0;

        while (true) {
            String direction = scanner.nextLine();

            if (direction.equals("стоп")) {
                break;
            }

            int distance = scanner.nextInt();
            scanner.nextLine();

            switch (direction) {
                case "север" -> y += distance;
                case "юг" -> y -= distance;
                case "восток" -> x += distance;
                case "запад" -> x -= distance;
            }

            if (x != x_treasure || y != y_treasure) {
                steps++;
            }
        }

        System.out.println(steps);
        scanner.close();
    }
}

