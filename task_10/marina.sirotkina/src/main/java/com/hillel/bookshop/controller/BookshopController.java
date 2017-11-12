package com.hillel.bookshop.controller;

import com.hillel.bookshop.model.books.Book;
import com.hillel.bookshop.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookshopController {

  @Autowired
  private BookService bookService;

  @RequestMapping(value = "/books/{author}", method = RequestMethod.GET)
  public List<Book> findBooksByAuthor(@PathVariable String author) {
    List<Book> books = bookService.getAllBooksByAuthor(author);
    if (books.isEmpty()) {
      throw new BooksNotFoundException();
    }
    return books;
  }

  @RequestMapping(value = "/newBook", method = RequestMethod.POST)
  public String saveNewBook(@RequestBody Book book) {
    try {
      bookService.saveNewBook(book);
    } catch (Exception ex) {
      ex.printStackTrace();
      return "saving the book is impossible";
    }
    return "saving is success";
  }

  @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
  public String updateBook(@PathVariable Long id, @RequestBody Book book) {
    try {
      bookService.updateBook(id, book);
    } catch (Exception ex) {
      ex.printStackTrace();
      return "updating the book is impossible";
    }
    return "updating is success";
  }

  @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
  public String deleteBook(@PathVariable Long id) {
    try {
      bookService.deleteBook(id);
    } catch (Exception ex) {
      ex.printStackTrace();
      return "deleting is impossible";
    }
    return "deleting is success";
  }
}
