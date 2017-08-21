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

@WebServlet("/servlet/find/year")
public class FindByYear extends HttpServlet {

  private static final long serialVersionUID = 170820030003L;

  private transient BookRepository bookRepository;

  private transient HtmlPagePreparer pagePreparer;

  public FindByYear() {
    bookRepository = BeansContainer.getBean(BookRepositoryImpl.class);
    pagePreparer = BeansContainer.getBean(HtmlPagePreparer.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int year = Integer.parseInt(req.getParameter("year"));
    PrintWriter writer = resp.getWriter();
    writer.write(pagePreparer.prepareForTable(String.format("Books written at %d year", year),
        bookRepository.findAllByYear(year)));
  }
}
