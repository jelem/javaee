package com.task.bookshop.repository;

import com.task.bookshop.exceptions.BookAlreadyExistsException;
import com.task.bookshop.exceptions.BookNotExistsException;
import com.task.bookshop.model.Book;

import java.util.List;

public interface BookRepository {

  void save(Book book) throws BookAlreadyExistsException;

  void update(Book book) throws BookNotExistsException;

  List<Book> findAll();

  Book getById(Long id);

  List<Book> findAllByAuthorLike(String author);

  List<Book> findAllByYear(int year);

  List<Book> findAllByTitleLike(String title);

  boolean exists(Book book);

  boolean isEmpty();

}
