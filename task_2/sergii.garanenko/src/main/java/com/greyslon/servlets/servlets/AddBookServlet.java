package com.greyslon.servlets.servlets;

import com.greyslon.servlets.container.ObjectsContainer;
import com.greyslon.servlets.models.Book;
import com.greyslon.servlets.repositories.BookRepository;
import com.greyslon.servlets.utils.HtmlContent;
import com.greyslon.servlets.utils.QueryParamGetter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books/add")
public class AddBookServlet extends HttpServlet {

  private BookRepository bookRepository;
  private HtmlContent htmlContent;
  private QueryParamGetter paramGetter;

  public AddBookServlet() {
    bookRepository = ObjectsContainer.getObject(BookRepository.class);
    htmlContent = ObjectsContainer.getObject(HtmlContent.class);
    paramGetter = ObjectsContainer.getObject(QueryParamGetter.class);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");
    String queryString = req.getReader().readLine();
    if (queryString == null) {
      return;
    }
    String postParams = URLDecoder.decode(queryString, "UTF-8");
    Book book = new Book(
        paramGetter.getParam(postParams, "author").orElse(""),
        paramGetter.getParam(postParams, "bookName").orElse(""),
        paramGetter.getParam(postParams, "published").orElse("")
    );
    PrintWriter writer = resp.getWriter();
    writer.write(htmlContent.renderNavigation());
    if (bookRepository.addBook(book)) {
      writer.write("Added");
    } else {
      writer.write("Not added");
    }
  }
}
