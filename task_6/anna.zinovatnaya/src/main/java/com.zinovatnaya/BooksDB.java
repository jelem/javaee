package com.zinovatnaya;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;
import javax.persistence.Query;

public class BooksDB {
  private static BooksDB instance = null;

  public static synchronized BooksDB getInstance() {
    if (instance == null) {
      instance = new BooksDB();
    }
    return instance;
  }

  private BooksDB() {
  }

  public synchronized void addBook(Book book) {

    SessionFactory sessionFactory = HibernateUtilities.getSessionFactory();

    try (Session session = sessionFactory.openSession()) {

      Transaction tx;
      tx = session.beginTransaction();

      Query query = session.createQuery("select " 
                                        + "new com.zinovatnaya.Author(a.id, a.name) " 
                                        + "from Author a " 
                                        + "where a.name = :authorname");

      query.setParameter("authorname", book.getAuthor().getName());

      List<Author> authors =  query.getResultList();

      if (authors.isEmpty()) {
        session.save(book.getAuthor());

      } else {
        book.setAuthor(authors.get(0));
      }
      session.save(book);

      tx.commit();
    }

  }

  public synchronized List<Book> getBooks() {
    List<Book> books;
    SessionFactory sessionFactory = HibernateUtilities.getSessionFactory();

    try (Session session = sessionFactory.openSession()) {

      Transaction tx = session.beginTransaction();

      Query query = session.createQuery("select " 
                                       + "new com.zinovatnaya.Book(b.name, b.author, b.description, b.imagePath) " 
                                       + "from Book b");

      books =  query.getResultList();

      tx.commit();
    }

    return books;
  }

  public synchronized List<Book> getBooksByAuthor(String authorName) {
    List<Book> books;
    SessionFactory sessionFactory = HibernateUtilities.getSessionFactory();

    try (Session session = sessionFactory.openSession()) {

      Transaction tx = session.beginTransaction();

      Query query = session.createQuery("select " 
                                       + "new com.zinovatnaya.Book(b.name, b.author, b.description, b.imagePath) " 
                                       + "from Book b " 
                                       + "where b.author.name=:author");

      query.setParameter("author", authorName);

      books =  query.getResultList();

      tx.commit();
    }

    return books;
  }
}