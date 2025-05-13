import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

// Главный класс приложения
public class CinemaTicketSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Cinema> cinemas = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static User currentUser = null;
    
    // Предопределенные пользователи для тестирования
    static {
        users.add(new User("admin", "admin123", true));
        users.add(new User("user", "user123", false));
    }

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в билетную систему кинотеатров!");
        
        // Основной цикл программы
        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                if (currentUser.isAdmin()) {
                    showAdminMenu();
                } else {
                    showUserMenu();
                }
            }
        }
    }
    
    // Меню входа/регистрации
    private static void showLoginMenu() {
        System.out.println("\n1. Вход");
        System.out.println("2. Регистрация");
        System.out.println("3. Выход");
        System.out.print("Выберите действие: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // очистка буфера
        
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.out.println("До свидания!");
                System.exit(0);
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
    
    // Меню администратора
    private static void showAdminMenu() {
        System.out.println("\n=== АДМИНИСТРАТОР ===");
        System.out.println("1. Добавить кинотеатр");
        System.out.println("2. Добавить зал в кинотеатр");
        System.out.println("3. Создать сеанс");
        System.out.println("4. Просмотреть все кинотеатры");
        System.out.println("5. Выход из системы");
        System.out.print("Выберите действие: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                addCinema();
                break;
            case 2:
                addHall();
                break;
            case 3:
                createSession();
                break;
            case 4:
                listCinemas();
                break;
            case 5:
                currentUser = null;
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
    
    // Меню пользователя
    private static void showUserMenu() {
        System.out.println("\n=== ПОЛЬЗОВАТЕЛЬ ===");
        System.out.println("1. Найти ближайший сеанс");
        System.out.println("2. Купить билет");
        System.out.println("3. Просмотреть план зала");
        System.out.println("4. Просмотреть все кинотеатры");
        System.out.println("5. Выход из системы");
        System.out.print("Выберите действие: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                findNearestSession();
                break;
            case 2:
                buyTicket();
                break;
            case 3:
                showHallLayout();
                break;
            case 4:
                listCinemas();
                break;
            case 5:
                currentUser = null;
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
        }
    }
    
    // Вход в систему
    private static void login() {
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                currentUser = user;
                System.out.println("Вход выполнен успешно!");
                return;
            }
        }
        
        System.out.println("Неверный логин или пароль.");
    }
    
    // Регистрация нового пользователя
    private static void register() {
        System.out.print("Введите новый логин: ");
        String login = scanner.nextLine();
        
        // Проверка на существующий логин
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                System.out.println("Пользователь с таким логином уже существует.");
                return;
            }
        }
        
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        
        users.add(new User(login, password, false));
        System.out.println("Регистрация завершена успешно!");
    }
    
    // Добавление кинотеатра (только для администратора)
    private static void addCinema() {
        System.out.print("Введите название кинотеатра: ");
        String name = scanner.nextLine();
        System.out.print("Введите адрес кинотеатра: ");
        String address = scanner.nextLine();
        
        cinemas.add(new Cinema(name, address));
        System.out.println("Кинотеатр успешно добавлен!");
    }
    
    // Добавление зала в кинотеатр (только для администратора)
    private static void addHall() {
        if (cinemas.isEmpty()) {
            System.out.println("Сначала добавьте кинотеатр.");
            return;
        }
        
        listCinemas();
        System.out.print("Выберите кинотеатр (номер): ");
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор кинотеатра.");
            return;
        }
        
        System.out.print("Введите название зала: ");
        String hallName = scanner.nextLine();
        System.out.print("Введите количество рядов: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество мест в ряду: ");
        int seatsPerRow = scanner.nextInt();
        scanner.nextLine();
        
        CinemaHall hall = new CinemaHall(hallName, rows, seatsPerRow);
        cinemas.get(cinemaIndex).addHall(hall);
        System.out.println("Зал успешно добавлен в кинотеатр!");
    }
    
    // Создание сеанса (только для администратора)
    private static void createSession() {
        if (cinemas.isEmpty()) {
            System.out.println("Нет доступных кинотеатров.");
            return;
        }
        
        listCinemas();
        System.out.print("Выберите кинотеатр (номер): ");
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор кинотеатра.");
            return;
        }
        
        Cinema cinema = cinemas.get(cinemaIndex);
        if (cinema.getHalls().isEmpty()) {
            System.out.println("В этом кинотеатре нет залов.");
            return;
        }
        
        System.out.println("Доступные залы:");
        List<CinemaHall> halls = cinema.getHalls();
        for (int i = 0; i < halls.size(); i++) {
            System.out.println((i + 1) + ". " + halls.get(i).getName());
        }
        
        System.out.print("Выберите зал (номер): ");
        int hallIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (hallIndex < 0 || hallIndex >= halls.size()) {
            System.out.println("Неверный выбор зала.");
            return;
        }
        
        System.out.print("Введите название фильма: ");
        String movieName = scanner.nextLine();
        System.out.print("Введите продолжительность фильма (в минутах): ");
        int duration = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите дату и время сеанса (формат: dd.MM.yyyy HH:mm): ");
        String dateTimeStr = scanner.nextLine();
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);
            
            MovieSession session = new MovieSession(movieName, duration, dateTime);
            halls.get(hallIndex).addSession(session);
            System.out.println("Сеанс успешно создан!");
        } catch (Exception e) {
            System.out.println("Неверный формат даты и времени.");
        }
    }
    
    // Поиск ближайшего сеанса (для пользователя)
    private static void findNearestSession() {
        System.out.print("Введите название фильма: ");
        String movieName = scanner.nextLine();
        
        MovieSession nearestSession = null;
        Cinema nearestCinema = null;
        CinemaHall nearestHall = null;
        LocalDateTime now = LocalDateTime.now();
        
        for (Cinema cinema : cinemas) {
            for (CinemaHall hall : cinema.getHalls()) {
                for (MovieSession session : hall.getSessions()) {
                    if (session.getMovieName().equalsIgnoreCase(movieName) 
                            && session.getDateTime().isAfter(now)
                            && session.hasAvailableSeats()) {
                        
                        if (nearestSession == null || session.getDateTime().isBefore(nearestSession.getDateTime())) {
                            nearestSession = session;
                            nearestCinema = cinema;
                            nearestHall = hall;
                        }
                    }
                }
            }
        }
        
        if (nearestSession != null) {
            System.out.println("\nБлижайший сеанс:");
            System.out.println("Фильм: " + nearestSession.getMovieName());
            System.out.println("Кинотеатр: " + nearestCinema.getName() + " (" + nearestCinema.getAddress() + ")");
            System.out.println("Зал: " + nearestHall.getName());
            System.out.println("Время: " + nearestSession.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")));
            System.out.println("Длительность: " + nearestSession.getDuration() + " мин");
            System.out.println("Свободных мест: " + nearestSession.getAvailableSeatsCount());
        } else {
            System.out.println("Нет доступных сеансов для этого фильма.");
        }
    }
    
    // Покупка билета (для пользователя)
    private static void buyTicket() {
        listCinemas();
        System.out.print("Выберите кинотеатр (номер): ");
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор кинотеатра.");
            return;
        }
        
        Cinema cinema = cinemas.get(cinemaIndex);
        if (cinema.getHalls().isEmpty()) {
            System.out.println("В этом кинотеатре нет залов.");
            return;
        }
        
        System.out.println("Доступные залы:");
        List<CinemaHall> halls = cinema.getHalls();
        for (int i = 0; i < halls.size(); i++) {
            System.out.println((i + 1) + ". " + halls.get(i).getName());
        }
        
        System.out.print("Выберите зал (номер): ");
        int hallIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (hallIndex < 0 || hallIndex >= halls.size()) {
            System.out.println("Неверный выбор зала.");
            return;
        }
        
        CinemaHall hall = halls.get(hallIndex);
        if (hall.getSessions().isEmpty()) {
            System.out.println("В этом зале нет сеансов.");
            return;
        }
        
        System.out.println("Доступные сеансы:");
        List<MovieSession> sessions = hall.getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            MovieSession session = sessions.get(i);
            System.out.println((i + 1) + ". " + session.getMovieName() + " - " + 
                    session.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + 
                    " (свободных мест: " + session.getAvailableSeatsCount() + ")");
        }
        
        System.out.print("Выберите сеанс (номер): ");
        int sessionIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (sessionIndex < 0 || sessionIndex >= sessions.size()) {
            System.out.println("Неверный выбор сеанса.");
            return;
        }
        
        MovieSession session = sessions.get(sessionIndex);
        if (!session.hasAvailableSeats()) {
            System.out.println("На этот сеанс нет свободных мест.");
            return;
        }
        
        // Показываем план зала
        session.printSeatLayout();
        
        System.out.print("Выберите ряд: ");
        int row = scanner.nextInt();
        System.out.print("Выберите место: ");
        int seat = scanner.nextInt();
        scanner.nextLine();
        
        if (session.bookSeat(row - 1, seat - 1)) {
            System.out.println("Билет успешно куплен! Ряд " + row + ", место " + seat);
        } else {
            System.out.println("Не удалось купить билет. Возможно, место уже занято или не существует.");
        }
    }
    
    // Показать план зала (для пользователя)
    private static void showHallLayout() {
        listCinemas();
        System.out.print("Выберите кинотеатр (номер): ");
        int cinemaIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (cinemaIndex < 0 || cinemaIndex >= cinemas.size()) {
            System.out.println("Неверный выбор кинотеатра.");
            return;
        }
        
        Cinema cinema = cinemas.get(cinemaIndex);
        if (cinema.getHalls().isEmpty()) {
            System.out.println("В этом кинотеатре нет залов.");
            return;
        }
        
        System.out.println("Доступные залы:");
        List<CinemaHall> halls = cinema.getHalls();
        for (int i = 0; i < halls.size(); i++) {
            System.out.println((i + 1) + ". " + halls.get(i).getName());
        }
        
        System.out.print("Выберите зал (номер): ");
        int hallIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (hallIndex < 0 || hallIndex >= halls.size()) {
            System.out.println("Неверный выбор зала.");
            return;
        }
        
        CinemaHall hall = halls.get(hallIndex);
        if (hall.getSessions().isEmpty()) {
            System.out.println("В этом зале нет сеансов.");
            return;
        }
        
        System.out.println("Доступные сеансы:");
        List<MovieSession> sessions = hall.getSessions();
        for (int i = 0; i < sessions.size(); i++) {
            MovieSession session = sessions.get(i);
            System.out.println((i + 1) + ". " + session.getMovieName() + " - " + 
                    session.getDateTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")) + 
                    " (свободных мест: " + session.getAvailableSeatsCount() + ")");
        }
        
        System.out.print("Выберите сеанс (номер): ");
        int sessionIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        
        if (sessionIndex < 0 || sessionIndex >= sessions.size()) {
            System.out.println("Неверный выбор сеанса.");
            return;
        }
        
        sessions.get(sessionIndex).printSeatLayout();
    }
    
    // Список всех кинотеатров
    private static void listCinemas() {
        if (cinemas.isEmpty()) {
            System.out.println("Нет доступных кинотеатров.");
            return;
        }
        
        System.out.println("\nСписок кинотеатров:");
        for (int i = 0; i < cinemas.size(); i++) {
            Cinema cinema = cinemas.get(i);
            System.out.println((i + 1) + ". " + cinema.getName() + " (" + cinema.getAddress() + ")");
            System.out.println("   Залы: " + cinema.getHalls().size());
        }
    }
}

// Класс пользователя
class User {
    private String login;
    private String password;
    private boolean isAdmin;
    
    public User(String login, String password, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    
    public String getLogin() {
        return login;
    }
    
    public String getPassword() {
        return password;
    }
    
    public boolean isAdmin() {
        return isAdmin;
    }
}

// Класс кинотеатра
class Cinema {
    private String name;
    private String address;
    private List<CinemaHall> halls = new ArrayList<>();
    
    public Cinema(String name, String address) {
        this.name = name;
        this.address = address;
    }
    
    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public List<CinemaHall> getHalls() {
        return halls;
    }
    
    public void addHall(CinemaHall hall) {
        halls.add(hall);
    }
}

// Класс кинозала
class CinemaHall {
    private String name;
    private int rows;
    private int seatsPerRow;
    private List<MovieSession> sessions = new ArrayList<>();
    
    public CinemaHall(String name, int rows, int seatsPerRow) {
        this.name = name;
        this.rows = rows;
        this.seatsPerRow = seatsPerRow;
    }
    
    public String getName() {
        return name;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getSeatsPerRow() {
        return seatsPerRow;
    }
    
    public List<MovieSession> getSessions() {
        return sessions;
    }
    
    public void addSession(MovieSession session) {
        session.initializeSeats(rows, seatsPerRow);
        sessions.add(session);
    }
}

// Класс киносеанса
class MovieSession {
    private String movieName;
    private int duration; // в минутах
    private LocalDateTime dateTime;
    private boolean[][] seats; // матрица мест: true - занято, false - свободно
    
    public MovieSession(String movieName, int duration, LocalDateTime dateTime) {
        this.movieName = movieName;
        this.duration = duration;
        this.dateTime = dateTime;
    }
    
    public String getMovieName() {
        return movieName;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void initializeSeats(int rows, int seatsPerRow) {
        seats = new boolean[rows][seatsPerRow];
    }
    
    // Проверка наличия свободных мест
    public boolean hasAvailableSeats() {
        if (seats == null) return false;
        
        for (boolean[] row : seats) {
            for (boolean seat : row) {
                if (!seat) return true;
            }
        }
        return false;
    }
    
    // Количество свободных мест
    public int getAvailableSeatsCount() {
        if (seats == null) return 0;
        
        int count = 0;
        for (boolean[] row : seats) {
            for (boolean seat : row) {
                if (!seat) count++;
            }
        }
        return count;
    }
    
    // Бронирование места
    public boolean bookSeat(int row, int seat) {
        if (seats == null || row < 0 || row >= seats.length || seat < 0 || seat >= seats[0].length) {
            return false;
        }
        
        if (seats[row][seat]) {
            return false; // место уже занято
        }
        
        seats[row][seat] = true;
        return true;
    }
    
    // Печать плана зала
    public void printSeatLayout() {
        if (seats == null) {
            System.out.println("План зала не доступен.");
            return;
        }
        
        System.out.println("\nПлан зала (X - занято, O - свободно):");
        System.out.print("   ");
        for (int i = 1; i <= seats[0].length; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();
        
        for (int i = 0; i < seats.length; i++) {
            System.out.printf("%2d ", i + 1);
            for (int j = 0; j < seats[i].length; j++) {
                System.out.print(seats[i][j] ? " X " : " O ");
            }
            System.out.println();
        }
    }
}