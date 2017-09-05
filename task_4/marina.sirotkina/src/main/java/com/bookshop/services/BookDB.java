package com.bookshop.services;

import com.bookshop.entity.Book;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public enum BookDB implements Serializable {

  INSTANCE;

  private List<Book> bookList;

  BookDB() {
    bookList = new ArrayList<>();
    bookList.add(new Book("The Martian", "Andy Weir", "2011"));
    bookList.add(new Book("The Alchemist", "Paulo Coelho", "1988"));
    bookList.add(new Book("The Great Gatsby", "Scott Fitzgerald", "1925"));
  }

  public List<Book> getBookList() {
    return bookList;
  }

  public void updateBookList(Book book) {
    if (book != null && !bookList.contains(book)) {
      bookList.add(book);
    }
  }

  public List<Book> searchBook(String author) {
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
