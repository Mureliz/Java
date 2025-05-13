public class LibraryTest {
    public static void main(String[] args) {
        Library library = new Library();

        // Создаем книги
        Book book1 = new Book("Война и мир", "Лев Толстой", 1869);
        Book book2 = new Book("Анна Каренина", "Лев Толстой", 1877);
        Book book3 = new Book("Преступление и наказание", "Фёдор Достоевский", 1866);
        Book book4 = new Book("Идиот", "Фёдор Достоевский", 1869);
        Book book5 = new Book("1984", "Джордж Оруэлл", 1949);
        Book book6 = new Book("Скотный двор", "Джордж Оруэлл", 1945);

        // Добавляем книги в библиотеку
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);
        library.addBook(book6);

        // Выводим все книги
        library.printAllBooks();
        System.out.println();

        // Выводим уникальных авторов
        library.printUniqueAuthors();
        System.out.println();

        // Выводим статистику по авторам
        library.printAuthorStatistics();
        System.out.println();

        // Ищем книги по автору
        System.out.println("Книги Льва Толстого:");
        library.findBooksByAuthor("Лев Толстой").forEach(System.out::println);
        System.out.println();

        // Ищем книги по году издания
        System.out.println("Книги 1869 года:");
        library.findBooksByYear(1869).forEach(System.out::println);
        System.out.println();

        // Удаляем книгу и проверяем изменения
        System.out.println("Удаляем книгу 'Идиот'");
        library.removeBook(book4);
        library.printAllBooks();
        System.out.println();

        // Проверяем обновленную статистику
        library.printAuthorStatistics();
    }
}