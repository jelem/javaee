package com.bookshop.service;

import com.bookshop.exception.GenreAlreadyExistsException;
import com.bookshop.exception.GenreException;
import com.bookshop.exception.GenreInvalidException;
import com.bookshop.exception.GenreNotFoundException;
import com.bookshop.model.Genre;
import com.bookshop.repository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {

  @Autowired
  private GenreRepository genreRepository;

  public List<Genre> getAll() {
    return genreRepository.findAll();
  }

  public Genre addNew(Genre genre) throws GenreException {
    if (genreRepository.exists(genre.getId())) {
      throw new GenreAlreadyExistsException();
    }
    if (genre.getName() == null) {
      throw new GenreInvalidException();
    }
    return genreRepository.save(genre);
  }

  public Genre update(Genre genre) throws GenreException {
    Genre inDb = genreRepository.findOne(genre.getId());
    if (inDb == null) {
      throw new GenreNotFoundException();
    }
    if (genre.getName() == null) {
      return inDb;
    }
    return genreRepository.save(genre);
  }

  public Genre get(long id) throws GenreNotFoundException {
    Genre genre = genreRepository.findOne(id);
    if (genre == null) {
      throw new GenreNotFoundException();
    }
    return genre;
  }

  public List<Genre> findByName(String name) {
    return genreRepository.findByNameLike(name);
  }

  public boolean delete(long id) throws GenreNotFoundException {
    if (!genreRepository.exists(id)) {
      throw new GenreNotFoundException();
    }
    genreRepository.delete(id);
    return genreRepository.exists(id);
  }
}
