package com.task.bookshop.servlet;

import com.task.bookshop.model.Book;
import com.task.bookshop.repository.BeansContainer;
import com.task.bookshop.repository.BookRepository;
import com.task.bookshop.repository.BookRepositoryImpl;
import com.task.bookshop.utils.HtmlPagePreparer;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.RequestDispatcher;
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
    String year = req.getParameter("year");
    String title;
    if (year != null) {
      req.setAttribute("books", bookRepository.findAllByYear(Integer.parseInt(year)));
      title = "Книги, написанные в " + year + " году";
    } else {
      req.setAttribute("books", bookRepository.findAll());
      title = "Книг не найдено. Просмотрите полный список книг.";
    }
    req.setAttribute("pageTitle",
        new String(title.getBytes(Charset.defaultCharset()), Charset.forName("UTF-8")));
    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/booklist.jsp");
    dispatcher.forward(req, resp);
  }
}
