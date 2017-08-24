package com.task.bookshop.servlet;

import com.task.bookshop.exceptions.BookAlreadyExistsException;
import com.task.bookshop.model.Book;
import com.task.bookshop.repository.BeansContainer;
import com.task.bookshop.repository.BookRepository;
import com.task.bookshop.repository.BookRepositoryImpl;

import java.io.IOException;
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
    Book newBook = new Book(req.getParameter("title"), req.getParameter("author"),
        Integer.parseInt(req.getParameter("year")));
    try {
      bookRepository.save(newBook);
    } catch (BookAlreadyExistsException exc) {
      exc.printStackTrace();
    }
    resp.sendRedirect("/bookshop/servlet/allbooks");
  }
}
