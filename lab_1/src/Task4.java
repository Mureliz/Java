import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int roads_count = scanner.nextInt();
        int best_road = -1;
        int max_height = 0;

        for (int i = 1; i <= roads_count; i++) {
            int tunnels_count = scanner.nextInt();
            int min_height = 1000000;

            for (int j = 0; j < tunnels_count; j++) {
                int tunnel_height = scanner.nextInt();
                if (tunnel_height < min_height) {
                    min_height = tunnel_height;
                }
            }

            if (min_height > max_height) {
                max_height = min_height;
                best_road = i;  // фиксируем дорогу с наибольшей минимальной высотой
            }
        }

        System.out.println(best_road + " " + max_height);
    }
}
