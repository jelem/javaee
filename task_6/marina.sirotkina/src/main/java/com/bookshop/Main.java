package com.bookshop;

import com.bookshop.utils.HibernateUtils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {

  public static void main(String[] args) {

    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    try (Session session = sessionFactory.openSession()) {

      Transaction tr = session.beginTransaction();

      tr.commit();
    }

    sessionFactory.close();
  }

}
