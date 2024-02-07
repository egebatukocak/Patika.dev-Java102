import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("Book 1", 30, "Author 1", LocalDate.of(2001, 6, 1)));
        books.add(new Book("Book 2", 566, "Author 2", LocalDate.of(2002, 7, 4)));
        books.add(new Book("Book 3", 178, "Author 3", LocalDate.of(2003, 8, 5)));
        books.add(new Book("Book 4", 789, "Author 4", LocalDate.of(2004, 9, 30)));
        books.add(new Book("Book 5", 44, "Author 5", LocalDate.of(2011, 1, 21)));
        books.add(new Book("Book 6", 100, "Author 6", LocalDate.of(2021, 3, 11)));
        books.add(new Book("Book 7", 170, "Author 7", LocalDate.of(2013, 2, 6)));
        books.add(new Book("Book 8", 80, "Author 8", LocalDate.of(2017, 4, 8)));
        books.add(new Book("Book 9", 600, "Author 9", LocalDate.of(2009, 6, 15)));
        books.add(new Book("Book 10", 300, "Author 10", LocalDate.of(2010, 12, 25)));


        Map<String,String> bookMap = books.stream().collect(Collectors.toMap(book -> book.getBookName(), book -> book.getAuthorName()));
        System.out.println("\nALL BOOKS:");
        books.forEach(book -> System.out.println(book.toStringAllInfo()));
        System.out.println("******************************");

        System.out.println("\nBOOKS WITH 100 OR MORE PAGES:");
        List<Book> filteredBookList = getTheBookListFilteredByPageCount(books, 101, Integer.MAX_VALUE);
        filteredBookList.forEach(book -> System.out.println(book.toString()));
        System.out.println("******************************");

        System.out.println("\nBOOKS WITH 100 OR LESS PAGES:");
        List<Book> filteredBookList2 = getTheBookListFilteredByPageCount(books, 0, 100);
        filteredBookList2.forEach(System.out::println);
        System.out.println("******************************");
    }
    private static List<Book> getTheBookListFilteredByPageCount(ArrayList<Book> mainList, int minPageCount, int maxPageCount){
        return mainList.stream().filter(book -> book.getPageCount() >= minPageCount && book.getPageCount() <= maxPageCount).toList();
    }
}