import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        Set<Book> titleSortedSet = new TreeSet<>();
        titleSortedSet.add(new Book("The Tempest", 272, "William Shakespeare", "1611"));
        titleSortedSet.add(new Book("Blindness", 352, "José Saramago", "1995"));
        titleSortedSet.add(new Book("Animal Farm", 144, "George Orwell", "1945"));
        titleSortedSet.add(new Book("Notes from Underground", 208, "Fyodor Dostoevsky", "1864"));
        titleSortedSet.add(new Book("Crime and Punishment", 430, "Fyodor Dostoevsky", "1866"));

        System.out.println("Kitapları A'dan Z'ye isme göre sırala:");
        for (Book book : titleSortedSet) {
            System.out.println(book);
        }

        Set<Book> pageCountSortedSet = new TreeSet<>((book1, book2) -> Integer.compare(book1.getPageNum(), book2.getPageNum()));
        pageCountSortedSet.addAll(titleSortedSet); // A'dan Z'ye sıralı küme üzerinden ekler

        System.out.println("\nKitapları sayfa sayısına göre sırala:");
        for (Book book : pageCountSortedSet) {
            System.out.println(book);
        }
    }
}