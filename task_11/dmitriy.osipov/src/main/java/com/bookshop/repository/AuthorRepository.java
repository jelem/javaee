package com.bookshop.repository;

import com.bookshop.model.Author;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

  public List<Author> findAllByNameLike(String name);
}
