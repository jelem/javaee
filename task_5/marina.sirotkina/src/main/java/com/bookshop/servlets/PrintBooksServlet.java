package com.bookshop.servlets;

import com.bookshop.dao.BookDao;
import com.bookshop.domain.Book;
import com.bookshop.services.ResponseContent;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books")
public class PrintBooksServlet extends HttpServlet {

  private ResponseContent responseContent;
  private BookDao bookDao;
  private List<Book> books;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("text/html; charset=UTF-8");
    bookDao = new BookDao();
    responseContent = new ResponseContent();
    PrintWriter out = resp.getWriter();
    if (!getBooks().isEmpty()) {
      out.println(responseContent.getHtmlContent(getBooks()));
    } else {
      out.println("There no books");
    }
  }

  public List<Book> getBooks() {
    books = bookDao.getAll();
    return books;
  }
}
