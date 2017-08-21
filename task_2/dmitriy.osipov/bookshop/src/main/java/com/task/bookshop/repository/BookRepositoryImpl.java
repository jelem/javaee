package com.task.bookshop.repository;

import com.task.bookshop.exceptions.BookAlreadyExistsException;
import com.task.bookshop.exceptions.BookNotExistsException;
import com.task.bookshop.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookRepositoryImpl implements BookRepository {

  private List<Book> bookList;

  public BookRepositoryImpl() {
    bookList = new ArrayList<>();
    demoData();
  }

  @Override
  public void save(Book book) throws BookAlreadyExistsException {
    if (bookList.contains(book)) {
      throw new BookAlreadyExistsException("Book is already added");
    }
    bookList.add(book);
  }

  @Override
  public void update(Book book) throws BookNotExistsException {
    Optional<Book> oldBook = bookList.stream()
        .filter(x -> x.getId().equals(book.getId())).findFirst();
    int index = bookList.indexOf(oldBook
        .orElseThrow(() -> new BookNotExistsException("There is no book with such ID")));
    bookList.set(index, book);
  }

  @Override
  public boolean exists(Book book) {
    return bookList.contains(book);
  }

  @Override
  public List<Book> findAll() {
    return bookList;
  }

  @Override
  public Book getById(String id) {
    return bookList.stream().filter(x -> x.getId().equals(id)).findFirst()
        .orElse(Book.getEmptyBook());
  }

  @Override
  public List<Book> findAllByAuthorLike(String author) {
    return bookList.stream().filter(x -> x.getAuthor().toLowerCase().contains(author.toLowerCase()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Book> findAllByYear(int year) {
    return bookList.stream().filter(x -> x.getYear() == year).collect(Collectors.toList());
  }

  @Override
  public List<Book> findAllByTitleLike(String title) {
    return bookList.stream().filter(x -> x.getTitle().toLowerCase().contains(title.toLowerCase()))
        .collect(Collectors.toList());
  }

  private void demoData() {
    bookList.add(new Book("Code Complete", "Steve McConnell", 2004));
    bookList.add(new Book("The Unified Software Development Process",
        "Grady Booch, Ivar Jacobson, James Rumbaugh", 1995));
    bookList.add(new Book("Java 8. The Complete Reference, 9th Edition",
        "Herbert Schildt", 2015));
    bookList.add(new Book("Java. Библиотека профессионала. Том 1. Основы",
        "Хорстманн К., Корнелл Г.", 2016));
    bookList.add(new Book("The Lord of the Rings", "J.R.R. Tolkien", 2017));
    bookList.add(new Book("The Hobbit", "J.R.R. Tolkien", 2016));
    bookList.add(new Book("The Chronicles of Amber", "Roger Zelazny", 2016));
    bookList.add(new Book("The Steel Rat", "Harry Harrison", 2015));
    bookList.add(new Book("The Steel Rat", "Harry Harrison", 2017));
  }
}
