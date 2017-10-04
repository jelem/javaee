package com.task.bookshop.servlet;

import com.task.bookshop.repository.BeansContainer;
import com.task.bookshop.repository.BookRepository;
import com.task.bookshop.repository.BookRepositoryImpl;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/allbooks")
public class ViewAllBooks extends HttpServlet {

  private static final long serialVersionUID = 170820030005L;

  private transient BookRepository bookRepository;

  public ViewAllBooks() {
    bookRepository = BeansContainer.getBean(BookRepositoryImpl.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    req.setAttribute("books", bookRepository.findAll());
    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/booklist.jsp");
    dispatcher.forward(req, resp);
  }
}
