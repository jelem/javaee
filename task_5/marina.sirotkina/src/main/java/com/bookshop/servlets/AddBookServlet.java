package com.bookshop.servlets;

import com.bookshop.dao.BookDao;
import com.bookshop.domain.Book;
import com.bookshop.services.ResponseContent;
import com.bookshop.utils.CreateBlob;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddBookServlet extends HttpServlet {

  private String title;
  private String author;
  private String description;
  private String illustration;
  private BigDecimal price;
  private ResponseContent responseContent;
  private BookDao bookDao;
  private CreateBlob createBlob;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    if (validate(req.getParameter("title"), req.getParameter("author"),
        req.getParameter("description"), req.getParameter("illustration"),
        req.getParameter("price"))) {

      title = req.getParameter("title");
      author = req.getParameter("author");
      description = req.getParameter("description");
      illustration = req.getParameter("illustration");
      price = BigDecimal.valueOf(Double.valueOf(req.getParameter("price")));

      bookDao = new BookDao();
      createBlob = new CreateBlob();
      bookDao.save(
          new Book(title, author, description, createBlob.readImage(illustration), price));
      responseContent = new ResponseContent();
      out.println(responseContent.getHtmlContent(bookDao.getAll()));
    } else {
      out.println("<td>Can't create the book</td>");
      out.println("<td>Please enter the text in all fields</td>");
    }
  }

  private boolean validate(String... parameters) {
    int count = 0;
    for (String parameter : parameters) {
      if (parameter != null && parameter.length() != 0) {
        count++;
      }
    }
    return count == 5;
  }

}
