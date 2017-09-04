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

@WebServlet("/servlet/book")
public class ConcreteBook extends HttpServlet {

  private static final long serialVersionUID = 170820030000L;

  private transient BookRepository bookRepository;

  public ConcreteBook() {
    bookRepository = BeansContainer.getBean(BookRepositoryImpl.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String id = req.getParameter("id");
    req.setAttribute("oneBook", bookRepository.getById(id));
    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/onebook.jsp");
    dispatcher.forward(req, resp);
  }
}
