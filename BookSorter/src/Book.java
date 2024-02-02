public class Book implements Comparable<Book> {
    private String title;
    private int pageNum;
    private String author;
    private String publishDate;

    public Book(String title, int pageNum, String author, String publishDate) {
        this.title = title;
        this.pageNum = pageNum;
        this.author = author;
        this.publishDate = publishDate;
    }

    @Override
    public int compareTo(Book otherBook) {
        return this.title.compareTo(otherBook.title);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + this.title + '\'' +
                ", pageCount=" + this.pageNum +
                ", author='" + this.author + '\'' +
                ", publishDate='" + this.publishDate + '\'' +
                '}';
    }

    public int getPageNum() {
        return this.pageNum;
    }
}
