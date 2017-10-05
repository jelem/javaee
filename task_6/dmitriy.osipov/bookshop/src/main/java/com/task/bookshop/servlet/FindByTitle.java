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

@WebServlet("/servlet/find/title")
public class FindByTitle extends HttpServlet {

  private static final long serialVersionUID = 170820030002L;

  private transient BookRepository bookRepository;

  public FindByTitle() {
    bookRepository = BeansContainer.getBean(BookRepositoryImpl.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String bookTitle = req.getParameter("title");
    String title;
    if (bookTitle != null) {
      req.setAttribute("books", bookRepository.findAllByTitleLike(bookTitle));
      title = StringConverter.convertString("Книги, название которых содержит '",
          Charset.defaultCharset().name(), "UTF-8") + bookTitle + "'";
    } else {
      req.setAttribute("books", bookRepository.findAll());
      title = StringConverter
          .convertString("Книг не найдено. Просмотрите полный список книг.",
              Charset.defaultCharset().name(), "UTF-8");
    }
    req.setAttribute("pageTitle", title);
    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/booklist.jsp");
    dispatcher.forward(req, resp);
  }
}
