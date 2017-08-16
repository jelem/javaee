package com.greyslon.servlets.servlets;

import com.greyslon.servlets.models.Book;
import com.greyslon.servlets.repositories.BookRepository;
import com.greyslon.servlets.utils.HtmlContent;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books/add")
public class AddBookServlet extends HttpServlet {

  private BookRepository bookRepository = new BookRepository();
  private HtmlContent htmlContent = new HtmlContent();

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    PrintWriter writer = resp.getWriter();

    Book book = new Book(req.getParameter("author"), req.getParameter("bookName"),
        req.getParameter("published"));
    writer.write(htmlContent.renderNavigation());
    if (bookRepository.addBook(book)) {
      writer.write("Added");
    } else {
      writer.write("Not added");
    }
  }
}
