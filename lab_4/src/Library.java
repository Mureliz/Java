import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private Set<String> authors;
    private Map<String, Integer> authorStatistics;

    public Library() {
        books = new ArrayList<>();
        authors = new HashSet<>();
        authorStatistics = new HashMap<>();
    }

    public void addBook(Book book) {
        books.add(book);
        authors.add(book.getAuthor());
        authorStatistics.put(book.getAuthor(), 
            authorStatistics.getOrDefault(book.getAuthor(), 0) + 1);
    }

    public void removeBook(Book book) {
        if (books.remove(book)) {
            // Проверяем, есть ли еще книги этого автора
            boolean authorHasMoreBooks = books.stream()
                .anyMatch(b -> b.getAuthor().equals(book.getAuthor()));
            
            if (!authorHasMoreBooks) {
                authors.remove(book.getAuthor());
            }
            
            // Обновляем статистику
            int count = authorStatistics.getOrDefault(book.getAuthor(), 0) - 1;
            if (count <= 0) {
                authorStatistics.remove(book.getAuthor());
            } else {
                authorStatistics.put(book.getAuthor(), count);
            }
        }
    }

    public List<Book> findBooksByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }

    public List<Book> findBooksByYear(int year) {
        return books.stream()
                .filter(book -> book.getYear() == year)
                .collect(Collectors.toList());
    }

    public void printAllBooks() {
        System.out.println("Все книги в библиотеке:");
        books.forEach(System.out::println);
    }

    public void printUniqueAuthors() {
        System.out.println("Уникальные авторы в библиотеке:");
        authors.forEach(System.out::println);
    }

    public void printAuthorStatistics() {
        System.out.println("Статистика по авторам:");
        authorStatistics.forEach((author, count) -> 
            System.out.println(author + ": " + count + " книг(и)"));
    }
}