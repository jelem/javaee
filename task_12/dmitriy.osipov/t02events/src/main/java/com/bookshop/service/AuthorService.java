package com.bookshop.service;

import com.bookshop.exception.AuthorAlreadyExistsException;
import com.bookshop.exception.AuthorException;
import com.bookshop.exception.AuthorInvalidException;
import com.bookshop.exception.AuthorNotFoundException;
import com.bookshop.model.Author;
import com.bookshop.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

  @Autowired
  private AuthorRepository authorRepository;

  public Author addNew(Author author) throws AuthorException {
    if (authorRepository.findOne(author.getId()) != null) {
      throw new AuthorAlreadyExistsException();
    }
    if ((author.getName() == null || author.getName().isEmpty())) {
      throw new AuthorInvalidException();
    }
    return authorRepository.save(author);
  }

  public Author update(Author author) throws AuthorException {
    Author inDb = authorRepository.findOne(author.getId());
    if (inDb == null) {
      throw new AuthorNotFoundException();
    }
    if (author.getName() == null || author.getName().isEmpty()) {
      return inDb;
    }
    return authorRepository.save(author);
  }

  public boolean delete(long id) throws AuthorException {
    if (authorRepository.getOne(id) == null) {
      throw new AuthorNotFoundException();
    }
    authorRepository.delete(id);
    return authorRepository.getOne(id) == null;
  }

  public Author get(long id) throws AuthorNotFoundException {
    Author author = authorRepository.findOne(id);
    if (author == null) {
      throw new AuthorNotFoundException();
    }
    return author;
  }

  public List<Author> getAuthors(String name) {
    return authorRepository.findAllByNameLike(name);
  }

  public List<Author> getAuthors(List<String> names) {
    Set<Author> results = new HashSet<>();
    names.forEach(name -> {
      results.addAll(this.getAuthors(name));
    });
    return new ArrayList<>(results);
  }

  public List<Author> getAll() {
    return authorRepository.findAll();
  }
}
