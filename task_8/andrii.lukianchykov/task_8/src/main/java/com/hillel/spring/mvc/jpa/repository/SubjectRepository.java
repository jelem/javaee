package com.hillel.spring.mvc.jpa.repository;

import com.hillel.spring.mvc.jpa.model.Subject;

import org.springframework.stereotype.Repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class SubjectRepository {

  @PersistenceContext
  EntityManager entityManager;

  public List<Subject> getSubjects() {
    Query query = entityManager.createQuery("from Subject");
    return query.getResultList();
  }
}
