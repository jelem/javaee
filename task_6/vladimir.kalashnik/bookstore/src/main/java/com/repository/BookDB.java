package com.repository;

import com.entity.Book;
import com.utilies.HibernateUtilities;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class BookDB {
    private SessionFactory sessionFactory = HibernateUtilities.getSessionFactory();
    private static final BookDB BOOK_DB = new BookDB();

    private BookDB() {

    }

    public static BookDB getInstance() {
        return BOOK_DB;
    }

    public List<Book> getBooks() {
        List<Book> books;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createNamedQuery("books");
            books = query.getResultList();
        }
        return books;
    }

    public void addBook(Book book) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tr = session.beginTransaction();
            session.save(book);
            tr.commit();
        }
    }

    public List<Book> getBooksByAuthor(String name) {
        List<Book> books;
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createNamedQuery("booksByAuthor");
            query.setParameter("author", name);
            books = query.getResultList();
        }
        return books;
    }

    public void closeSessionFactory() {
        sessionFactory.close();
    }
}
