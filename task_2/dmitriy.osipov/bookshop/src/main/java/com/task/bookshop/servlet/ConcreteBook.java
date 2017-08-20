package com.task.bookshop.servlet;

import com.task.bookshop.repository.BeansContainer;
import com.task.bookshop.repository.BookRepository;
import com.task.bookshop.repository.BookRepositoryImpl;
import com.task.bookshop.utils.HtmlPagePreparer;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/book")
public class ConcreteBook extends HttpServlet {

  private static final long serialVersionUID = 170820030000L;

  private transient BookRepository bookRepository;
  private transient HtmlPagePreparer pagePreparer;

  public ConcreteBook() {
    bookRepository = BeansContainer.getBean(BookRepositoryImpl.class);
    pagePreparer = BeansContainer.getBean(HtmlPagePreparer.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String response = pagePreparer.prepareForOneBook(
        bookRepository.getById(req.getParameter("id")));
    PrintWriter writer = resp.getWriter();
    writer.write(response);
  }
}
