package com.bookshop.repository;

import com.bookshop.model.Author;
import com.bookshop.model.Book;
import com.bookshop.model.Genre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

  public List<Book> findAllByYear(int year);

  public List<Book> findAllByAuthorsIn(List<Author> authors);

  public List<Book> findAllByTitleLike(String title);

  public List<Book> findAllByGenresIn(List<Genre> genres);
}
