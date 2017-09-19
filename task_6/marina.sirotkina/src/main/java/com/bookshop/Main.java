package com.bookshop;

import com.bookshop.utils.HibernateUtils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

import javax.persistence.Query;

public class Main {

  public static void main(String[] args) {

    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    try (Session session = sessionFactory.openSession()) {

      final Transaction tr = session.beginTransaction();
      BooksCreator booksCreator = new BooksCreator();
      booksCreator.book1(session);
      booksCreator.book2(session);
      booksCreator.book3(session);

      Query query = session.createNamedQuery("bookDescriptionByLanguage");
      query.setParameter("language", "russian");
      List resultList = query.getResultList();
      System.out.println(resultList);

      Query query1 = session.createNamedQuery("categoryDescriptionByTitle");
      query1.setParameter("category_title", "Компьютерная литература");
      List resultList1 = query1.getResultList();
      System.out.println(resultList1);
      tr.commit();
    }

    sessionFactory.close();
  }

}
