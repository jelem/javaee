package com.bookshop.repository;

import com.bookshop.model.Genre;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {

  public List<Genre> findByNameLike(String name);
}
