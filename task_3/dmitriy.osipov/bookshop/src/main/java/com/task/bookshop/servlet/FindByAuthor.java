package com.task.bookshop.servlet;

import com.task.bookshop.repository.BeansContainer;
import com.task.bookshop.repository.BookRepository;
import com.task.bookshop.repository.BookRepositoryImpl;
import com.task.bookshop.utils.HtmlPagePreparer;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/find/author")
public class FindByAuthor extends HttpServlet {

  private static final long serialVersionUID = 170820030001L;

  private transient BookRepository bookRepository;
  private transient HtmlPagePreparer pagePreparer;

  public FindByAuthor() {
    bookRepository = BeansContainer.getBean(BookRepositoryImpl.class);
    pagePreparer = BeansContainer.getBean(HtmlPagePreparer.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String author = req.getParameter("author");
    String title;
    if (author != null) {
      req.setAttribute("books", bookRepository.findAllByAuthorLike(author));
      title = "Книги, написанные " + author;
    } else {
      req.setAttribute("books", bookRepository.findAll());
      title = "Книг этого автора не найдено. Просмотрите полный список книг.";
    }
    req.setAttribute("pageTitle",
        new String(title.getBytes(Charset.defaultCharset()), Charset.forName("UTF-8")));
    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/booklist.jsp");
    dispatcher.forward(req, resp);
    /*
    String author = req.getParameter("author");
    PrintWriter writer = resp.getWriter();
    writer.write(pagePreparer.prepareForTable(String.format("Books written by %s", author),
        bookRepository.findAllByAuthorLike(author)));
    */
  }
}
