package com.greyslon.servlets.servlets;

import com.greyslon.servlets.container.ObjectsContainer;
import com.greyslon.servlets.models.Book;
import com.greyslon.servlets.repositories.BookRepository;
import com.greyslon.servlets.utils.HtmlContent;
import com.greyslon.servlets.utils.HtmlHelper;
import com.greyslon.servlets.utils.QueryParamGetter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books/search")
public class GetBooksByAuthorServlet extends HttpServlet {

  private QueryParamGetter paramGetter;
  private BookRepository bookRepository;
  private HtmlContent htmlContent;
  private HtmlHelper htmlHelper;

  public GetBooksByAuthorServlet() {
    bookRepository = ObjectsContainer.getObject(BookRepository.class);
    htmlContent = ObjectsContainer.getObject(HtmlContent.class);
    htmlHelper = ObjectsContainer.getObject(HtmlHelper.class);
    paramGetter = ObjectsContainer.getObject(QueryParamGetter.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String queryString = req.getQueryString();
    PrintWriter writer = resp.getWriter();

    Optional<String> author = paramGetter.getParam(queryString, "author");
    if (author.isPresent()) {
      Set<Book> books = bookRepository.getBooks(author.get());
      writer.write(htmlContent.renderNavigation());
      if (books.isEmpty()) {
        writer
            .write(htmlContent.renderContent("Nothing was found by the author - " + author.get()));
      } else {
        for (Book book : books) {
          writer.write(htmlContent.renderContent(htmlHelper.divWrap(book.toString(), "book")));
        }
      }
    } else {
      writer.write(htmlContent.renderContent("No author was selected"));
    }
  }
}
