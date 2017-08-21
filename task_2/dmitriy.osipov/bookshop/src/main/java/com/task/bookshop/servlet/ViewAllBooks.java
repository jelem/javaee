package com.task.bookshop.servlet;

import com.task.bookshop.model.Book;
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

@WebServlet("/servlet/allbooks")
public class ViewAllBooks extends HttpServlet {

  private static final long serialVersionUID = 170820030005L;

  private transient HtmlPagePreparer pagePreparer;
  private transient BookRepository bookRepository;

  public ViewAllBooks() {
    pagePreparer = BeansContainer.getBean(HtmlPagePreparer.class);
    bookRepository = BeansContainer.getBean(BookRepositoryImpl.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter writer = resp.getWriter();
    writer.write(pagePreparer.prepareForTable("Books in our shop", bookRepository.findAll()));
  }
}
