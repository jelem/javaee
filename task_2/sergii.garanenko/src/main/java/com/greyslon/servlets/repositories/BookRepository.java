package com.greyslon.servlets.repositories;

import com.greyslon.servlets.models.Book;
import com.greyslon.servlets.utils.Encoder;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BookRepository implements Serializable {

  private Encoder encoder = new Encoder();
  private static Set<Book> bookSet = new TreeSet<>((Book book1, Book book2) -> {
    int result = book1.getAuthor().compareToIgnoreCase(book2.getAuthor());
    return result == 0
        ? book1.getPublishedYear().compareToIgnoreCase(book2.getPublishedYear())
        : result;
  });

  public BookRepository() {
    bookSet.add(new Book(
        encoder.encodeToUTF8("Horstmann", Charset.defaultCharset()),
        encoder.encodeToUTF8("Java For Everyone: Late Objects", Charset.defaultCharset()),
        encoder.encodeToUTF8("2011", Charset.defaultCharset())));
    bookSet.add(new Book(
        encoder.encodeToUTF8("Horstmann", Charset.defaultCharset()),
        encoder.encodeToUTF8("Big Java: Early Objects", Charset.defaultCharset()),
        encoder.encodeToUTF8("2013", Charset.defaultCharset())));
    bookSet.add(new Book(
        encoder.encodeToUTF8("Неизвестный автор", Charset.defaultCharset()),
        encoder.encodeToUTF8("Джава", Charset.defaultCharset()),
        encoder.encodeToUTF8("2013г", Charset.defaultCharset())));
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
        .filter(book -> author.toLowerCase().equals(book.getAuthor().toLowerCase()))
        .collect(Collectors.toSet());
  }
}
