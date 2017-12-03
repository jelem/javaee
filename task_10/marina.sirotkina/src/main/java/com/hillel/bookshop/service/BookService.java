package com.hillel.bookshop.service;

import com.hillel.bookshop.model.books.Book;
import com.hillel.bookshop.model.books.BookDescription;
import com.hillel.bookshop.repository.BookDescriptionRepository;
import com.hillel.bookshop.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

  @Autowired
  private BookDescriptionRepository bookDescriptionRepository;

  @Autowired
  private BookRepository bookRepository;

  public List<Book> getAllBooksByAuthor(String author) {
    List<BookDescription> bookDescription = bookDescriptionRepository.findAllByBookAuthor(author);
    return bookRepository.findBookByBookDescription(bookDescription);
  }

  public Book getById(Long id) {
    return bookRepository.findOne(id);
  }

  public void saveNewBook(Book book) {
    bookRepository.save(book);
  }

  public void updateBook(Long id, Book book) {
    Book bookFromDb = bookRepository.findOne(id);
    bookFromDb.setBookDescription(book.getBookDescription());
    bookFromDb.setBookIllustrations(book.getBookIllustrations());
    bookFromDb.setCategory(book.getCategory());
    bookFromDb.setPrice(book.getPrice());
    bookFromDb.setIsbn(book.getIsbn());

    bookRepository.save(bookFromDb);
  }

  public void deleteBook(Long id) {
    bookRepository.delete(id);
  }
}
