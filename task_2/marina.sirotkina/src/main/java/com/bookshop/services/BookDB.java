package com.bookshop.services;

import com.bookshop.entity.Book;
import java.util.ArrayList;
import java.util.List;

public class BookDB {

  private static List<Book> bookList;

  static {
    bookList = new ArrayList<>();
    bookList.add(new Book("The Martian", "Andy Weir", "2011"));
    bookList.add(new Book("The Alchemist", "Paulo Coelho", "1988"));
    bookList.add(new Book("The Great Gatsby", "Scott Fitzgerald", "1925"));
  }

  private Book book;
  private String author;

  public BookDB(Book book) {
    this.book = book;
  }

  public BookDB(String author) {
    this.author = author;
  }

  public static List<Book> getBookList() {
    return bookList;
  }

  public void updateBookList() {
    if (book != null) {
      bookList.add(book);
    }
  }

  public List<Book> searchBook() {
    List<Book> books = new ArrayList<>();
    for (Book book : bookList) {
      if (author.equals(book.getAuthor())) {
        books.add(book);
      }
    }
    return books;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("BookDB: ");
    sb.append("bookList=").append(bookList);
    return sb.toString();
  }
}
