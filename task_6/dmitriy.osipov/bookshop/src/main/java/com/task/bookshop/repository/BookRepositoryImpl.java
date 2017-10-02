package com.task.bookshop.repository;

import com.task.bookshop.HibernateUtilities;
import com.task.bookshop.exceptions.BookAlreadyExistsException;
import com.task.bookshop.exceptions.BookNotExistsException;
import com.task.bookshop.exceptions.DBConnectionException;
import com.task.bookshop.model.Book;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {

  private SessionFactory sessionFactory;

  public BookRepositoryImpl() {
    sessionFactory = HibernateUtilities.getSessionFactory();
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  @Override
  public void save(Book book) throws BookAlreadyExistsException {
    if (exists(book)) {
      throw new BookAlreadyExistsException("This book already exist in DB");
    }
    try (Session session = sessionFactory.openSession()) {
      session.save(book);
    } catch (HibernateException hibExc) {
      throw new DBConnectionException(hibExc.getMessage());
    }
  }

  @Override
  public void update(Book book) throws BookNotExistsException {
    if (!exists(book)) {
      throw new BookNotExistsException("Such book doesn't exist  in DB");
    }
    try (Session session = sessionFactory.openSession()) {
      session.save(book);
    } catch (HibernateException hibExc) {
      throw new DBConnectionException(hibExc.getMessage());
    }
  }

  @Override
  public List<Book> findAll() {
    try (Session session = sessionFactory.openSession()) {
      Query<Book> query = session.createQuery("select book from Book book");
      return query.getResultList();
    } catch (HibernateException hibExc) {
      throw new DBConnectionException(hibExc.getMessage());
    }
  }

  @Override
  public Book getById(Long id) {
    try (Session session = sessionFactory.openSession()) {
      Query<Book> query = session.createQuery("select book from Book book "
          + "where book.id = :id");
      query.setParameter("id", id);
      return query.getSingleResult();
    } catch (HibernateException hibExc) {
      throw new DBConnectionException(hibExc.getMessage());
    }
  }

  @Override
  public List<Book> findAllByAuthorLike(String author) {
    try (Session session = sessionFactory.openSession()) {
      Query<Book> query = session.createQuery("select book from Book book "
          + "where book.author like :author");
      query.setParameter("author", "%" + author + "%");
      return query.getResultList();
    } catch (HibernateException hibExc) {
      throw new DBConnectionException(hibExc.getMessage());
    }
  }

  @Override
  public List<Book> findAllByYear(int year) {
    try (Session session = sessionFactory.openSession()) {
      Query<Book> query = session.createQuery("select book from Book book "
          + "where book.year = :year");
      query.setParameter("year", year);
      return query.getResultList();
    } catch (HibernateException hibExc) {
      throw new DBConnectionException(hibExc.getMessage());
    }
  }

  @Override
  public List<Book> findAllByTitleLike(String title) {
    try (Session session = sessionFactory.openSession()) {
      Query<Book> query = session.createQuery("select book from Book book "
          + "where book.title like :title");
      query.setParameter("title", "%" + title + "%");
      return query.getResultList();
    } catch (HibernateException hibExc) {
      throw new DBConnectionException(hibExc.getMessage());
    }
  }

  @Override
  public boolean exists(Book book) {
    try (Session session = sessionFactory.openSession()) {
      Query<Long> query = session.createQuery("select count(book) from Book book "
          + "where book.author = :author and "
          + "book.title = :title and "
          + "book.year = :year");
      query.setParameter("title", book.getTitle());
      query.setParameter("author", book.getAuthor());
      query.setParameter("year", book.getYear());
      return (query.getSingleResult().compareTo(0L) != 0);
    } catch (HibernateException hibExc) {
      throw new DBConnectionException(hibExc.getMessage());
    }
  }

  @Override
  public boolean isEmpty() {
    try (Session session = sessionFactory.openSession()) {
      Query<Long> query = session.createQuery("select count(book) from Book book");
      return query.getSingleResult().compareTo(0L) == 0;
    } catch (HibernateException hibExc) {
      throw new DBConnectionException(hibExc.getMessage());
    }
  }
}
