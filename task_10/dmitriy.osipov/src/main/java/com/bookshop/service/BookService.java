package com.bookshop.service;

import com.bookshop.exception.BookAlreadyExistsException;
import com.bookshop.exception.BookException;
import com.bookshop.exception.BookInvalidException;
import com.bookshop.exception.BookNotFoundException;
import com.bookshop.model.Author;
import com.bookshop.model.Book;
import com.bookshop.model.Genre;
import com.bookshop.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private AuthorService authorService;

  @Autowired
  private GenreService genreService;

  public List<Book> getAll() {
    return bookRepository.findAll();
  }

  public Book get(long id) throws BookNotFoundException {
    Book book = bookRepository.findOne(id);
    if (book == null) {
      throw new BookNotFoundException();
    }
    return book;
  }

  public List<Book> getByAuthors(List<Author> authors) {
    return bookRepository.findAllByAuthorsIn(authors);
  }

  public List<Book> getByAuthorsNames(List<String> authors) {
    return this.getByAuthors(authorService.getAuthors(authors));
  }

  public List<Book> getByAuthorsName(String author) {
    List<String> authors = new ArrayList<>();
    authors.add(author);
    return this.getByAuthorsNames(authors);
  }

  public List<Book> getByGenres(List<Genre> genres) {
    return bookRepository.findAllByGenresIn(genres);
  }

  public List<Book> getByGenre(String genre) {
    List<Genre> genres = genreService.findByName(genre);
    return this.getByGenres(genres);
  }

  public List<Book> getByYear(int year) {
    return bookRepository.findAllByYear(year);
  }

  public List<Book> getByTitle(String title) {
    return bookRepository.findAllByTitleLike(title);
  }

  public Book addNew(Book book) throws BookException {
    if (bookRepository.exists(book.getId())) {
      throw new BookAlreadyExistsException();
    }
    if (book.getAuthors() == null || book.getAuthors().isEmpty()
        || book.getGenres() == null || book.getGenres().isEmpty()
        || book.getTitle() == null || book.getTitle().isEmpty()
        || book.getYear() == 0) {
      throw new BookInvalidException();
    }
    return bookRepository.save(book);
  }

  public Book update(Book book) throws BookException {
    Book inDb = bookRepository.findOne(book.getId());
    if (inDb == null) {
      throw new BookNotFoundException();
    }
    if (book.getAuthors() == null || book.getAuthors().isEmpty()) {
      book.setAuthors(inDb.getAuthors());
    }
    if (book.getYear() == 0) {
      book.setYear(inDb.getYear());
    }
    if (book.getTitle() == null || book.getTitle().isEmpty()) {
      book.setTitle(inDb.getTitle());
    }
    if (book.getDescription() == null || book.getDescription().isEmpty()) {
      book.setTitle(inDb.getDescription());
    }
    if (book.getGenres() == null || book.getGenres().isEmpty()) {
      book.setGenres(inDb.getGenres());
    }
    return bookRepository.save(book);
  }

  public boolean delete(long id) throws BookException {
    if (!bookRepository.exists(id)) {
      throw new BookNotFoundException();
    }
    bookRepository.delete(id);
    return bookRepository.exists(id);
  }
}
