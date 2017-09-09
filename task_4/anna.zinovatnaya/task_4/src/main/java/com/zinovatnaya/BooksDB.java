package com.zinovatnaya;

import java.util.ArrayList;
import java.util.List;

public class BooksDB {

  public static List<Book> getBooks() {
    return books;
  }

  public static void add(Book book) {
    books.add(book);
  }

  private static List<Book> books = new ArrayList<>();

  public static void clear() {
    books.clear();
  }
}
