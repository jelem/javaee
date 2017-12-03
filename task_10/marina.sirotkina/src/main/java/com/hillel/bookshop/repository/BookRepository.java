package com.hillel.bookshop.repository;

import com.hillel.bookshop.model.books.Book;
import com.hillel.bookshop.model.books.BookDescription;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findBookByBookDescription(List<BookDescription> description);
}
