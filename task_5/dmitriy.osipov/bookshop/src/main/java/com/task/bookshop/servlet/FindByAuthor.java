package com.task.bookshop.servlet;

import com.task.bookshop.repository.BeansContainer;
import com.task.bookshop.repository.BookRepository;
import com.task.bookshop.repository.BookRepositoryImpl;
import com.task.bookshop.utils.StringConverter;

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

  public FindByAuthor() {
    bookRepository = BeansContainer.getBean(BookRepositoryImpl.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    String author = req.getParameter("author");
    String title;
    if (author != null) {
      req.setAttribute("books", bookRepository.findAllByAuthorLike(author));
      title = StringConverter.convertString("Книги, написанные '%",
          Charset.defaultCharset().name(), "UTF-8") + author + "%'";
    } else {
      req.setAttribute("books", bookRepository.findAll());
      title = StringConverter
          .convertString("Книг такого автора не найдено. Просмотрите полный список книг.",
              Charset.defaultCharset().name(), "UTF-8");
    }
    req.setAttribute("pageTitle", title);
    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/booklist.jsp");
    dispatcher.forward(req, resp);
  }
}
