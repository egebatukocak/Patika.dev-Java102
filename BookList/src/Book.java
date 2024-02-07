import java.time.LocalDate;

public class Book {
    private String bookName, authorName;
    private int pageCount;
    private LocalDate publicationDate;

    public Book(String bookName, int pageCount, String authorName, LocalDate publicationDate) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.pageCount = pageCount;
        this.publicationDate = publicationDate;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    @Override
    public String toString() {
        return bookName + ", " + authorName;
    }

    public String toStringAllInfo() {
        return bookName + ", " + authorName + "(Page :  " + pageCount + "- Year :  " + publicationDate.getYear() + ")";
    }
}
