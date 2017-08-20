import java.util.ArrayList;
import java.util.List;

class BooksDB {
  static private List<Book> books = new ArrayList<>();

  static public List<Book> getBooks() {
    return books;
  }

  static public void add(Book book) {
    books.add(book);
  }
}
