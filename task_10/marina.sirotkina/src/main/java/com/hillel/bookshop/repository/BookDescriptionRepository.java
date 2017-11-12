package com.hillel.bookshop.repository;

import com.hillel.bookshop.model.books.BookDescription;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDescriptionRepository extends JpaRepository<BookDescription, Long> {

  List<BookDescription> findAllByBookAuthor(String author);
}