package com.bookshop.controller;

import com.bookshop.exception.BookException;
import com.bookshop.exception.BookNotFoundException;
import com.bookshop.model.Book;
import com.bookshop.model.Genre;
import com.bookshop.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

  @Autowired
  private BookService bookService;

  @RequestMapping(value = "/all", method = RequestMethod.GET)
  public List<Book> getAll() throws BookException {
    return bookService.getAll();
  }

  @RequestMapping(value = "/bytitle", method = RequestMethod.GET)
  public List<Book> findByTitle(@RequestParam(name = "value") String title) {
    return bookService.getByTitle(title);
  }

  @RequestMapping(value = "/byyear", method = RequestMethod.GET)
  public List<Book> findByYear(@RequestParam(name = "value") int year) {
    return bookService.getByYear(year);
  }

  @RequestMapping(value = "/byauthor", method = RequestMethod.GET)
  public List<Book> findByAuthors(@RequestParam(name = "value") String author) {
    return bookService.getByAuthorsName(author);
  }

  @RequestMapping(value = "/byauthors", method = RequestMethod.GET)
  public List<Book> findByAuthors(@RequestBody List<String> authors) {
    return bookService.getByAuthorsNames(authors);
  }

  @RequestMapping(value = "/bygenre", method = RequestMethod.GET)
  public List<Book> findByGenres(@RequestParam(name = "value") String genre) {
    return bookService.getByGenre(genre);
  }

  @RequestMapping(value = "/bygenres", method = RequestMethod.GET)
  public List<Book> findByGenres(@RequestBody List<Genre> genres) {
    return bookService.getByGenres(genres);
  }

  @RequestMapping("/{id}")
  public Book get(@PathVariable(name = "id") long id) throws BookNotFoundException {
    return bookService.get(id);
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public Book add(@RequestBody Book book) throws BookException {
    return bookService.addNew(book);
  }

  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public Book update(@RequestBody Book book) throws BookException {
    return bookService.update(book);
  }

  @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
  public boolean delete(@PathVariable(name = "id") long id) throws BookException {
    return bookService.delete(id);
  }
}
