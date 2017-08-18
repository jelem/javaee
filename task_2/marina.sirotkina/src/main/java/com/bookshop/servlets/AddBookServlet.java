package com.bookshop.servlets;

import com.bookshop.entity.Book;
import com.bookshop.services.ResponseContent;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddBookServlet extends HttpServlet {

  private String title;
  private String author;
  private String year;
  private ResponseContent responseContent;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    if (validate(req.getParameter("title"), req.getParameter("author"),
        req.getParameter("year"))) {
      title = req.getParameter("title");
      author = req.getParameter("author");
      year = req.getParameter("year");
      responseContent = new ResponseContent(getNewBook());
      out.println(responseContent.getHTMLContent());
    } else {
      out.println("<td>Can't create the book</td>");
      out.println("<td>Please enter the text in all fields</td>");
    }
  }

  private boolean validate(String... parameters) {
    int i = 0;
    for (String parameter : parameters) {
      if (parameter != null && parameter.length() != 0) {
        i++;
      }
    }
    return i == 3;
  }

  private Book getNewBook() {
    return new Book(title, author, year);
  }
}
