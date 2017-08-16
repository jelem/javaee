package com.greyslon.servlets.repositories;

import com.greyslon.servlets.models.Book;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BookRepository implements Serializable {

  private static Set<Book> bookSet = new TreeSet<>((Book book1, Book book2) -> {
    int result = book1.getAuthor().compareToIgnoreCase(book2.getAuthor());
    return result == 0
        ? book1.getPublishedYear().compareToIgnoreCase(book2.getPublishedYear())
        : result;
  });

  public BookRepository() {
    bookSet.add(new Book("Horstmann", "Java For Everyone: Late Objects", "2011"));
    bookSet.add(new Book("Horstmann", "Big Java: Early Objects", "2013"));
  }

  public boolean addBook(Book book) {
    return bookSet.add(book);
  }

  public Set<Book> getBooks() {
    return Collections.unmodifiableSet(bookSet);
  }

  public Set<Book> getBooks(String author) {
    if (author == null) {
      return Collections.emptySet();
    }
    return bookSet
        .stream()
        .filter(book -> author.equals(book.getAuthor()))
        .collect(Collectors.toSet());
  }
}
