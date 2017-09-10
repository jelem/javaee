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

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    bookDao = new BookDao();
    responseContent = new ResponseContent();
    PrintWriter out = resp.getWriter();
    List<Book> bookList = bookDao.getAll();
    if (!bookList.isEmpty()) {
      out.println(responseContent.getHtmlContent(bookDao.getAll()));
    } else {
      out.println("There no books");
    }
  }

}
