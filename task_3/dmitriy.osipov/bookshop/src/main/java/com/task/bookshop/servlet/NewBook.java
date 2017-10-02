package com.task.bookshop.servlet;

import com.task.bookshop.exceptions.BookAlreadyExistsException;
import com.task.bookshop.model.Book;
import com.task.bookshop.repository.BeansContainer;
import com.task.bookshop.repository.BookRepository;
import com.task.bookshop.repository.BookRepositoryImpl;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlet/newbook")
public class NewBook extends HttpServlet {

  private static final long serialVersionUID = 170820030004L;

  private transient BookRepository bookRepository;

  public NewBook() {
    bookRepository = BeansContainer.getBean(BookRepositoryImpl.class);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Book newBook = new Book(
        new String(req.getParameter("title")
            .getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8")),
        new String(req.getParameter("author")
        .getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8")),
        Integer.parseInt(req.getParameter("year")));
    try {
      bookRepository.save(newBook);
    } catch (BookAlreadyExistsException exc) {
      exc.printStackTrace();
    }
    resp.sendRedirect("/bookshop/servlet/allbooks");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("newBookForm", createForm());
    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/newbook.jsp");
    dispatcher.forward(req, resp);
  }

  private String createForm() {
    return "<form action=\"/bookshop/servlet/newbook\" method=\"post\" class=\"requestForm\">\n"
        + "\t<h2>Author:</h2>"
        + "<input type=\"text\" name=\"author\" class=\"formEntry\">\n"
        + "\t<h2>Title:</h2>"
        + "<input type=\"text\" name=\"title\" class=\"formEntry\">\n"
        + "\t<h2>Year:</h2>"
        + "<input type=\"number\" name=\"year\" value=\"2017\" class=\"formEntry\">\n"
        + "\t<input type=\"submit\" value=\"Add\" class=\"formEntry\">\n"
        + "</form>";
  }
}
