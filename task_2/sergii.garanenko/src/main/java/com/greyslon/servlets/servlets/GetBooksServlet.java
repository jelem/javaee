package com.greyslon.servlets.servlets;

import com.greyslon.servlets.container.ObjectsContainer;
import com.greyslon.servlets.models.Book;
import com.greyslon.servlets.repositories.BookRepository;
import com.greyslon.servlets.utils.HtmlContent;
import com.greyslon.servlets.utils.HtmlHelper;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books/all")
public class GetBooksServlet extends HttpServlet {

  private BookRepository bookRepository;
  private HtmlContent htmlContent;
  private HtmlHelper htmlHelper;

  public GetBooksServlet() {
    bookRepository = ObjectsContainer.getObject(BookRepository.class);
    htmlContent = ObjectsContainer.getObject(HtmlContent.class);
    htmlHelper = ObjectsContainer.getObject(HtmlHelper.class);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter writer = resp.getWriter();
    Set<Book> books = bookRepository.getBooks();
    writer.write(htmlContent.renderNavigation());
    if (books.isEmpty()) {
      writer.write("No books found");
    } else {
      for (Book book : books) {
        writer.write(htmlContent.renderContent(htmlHelper.divWrap(book.toString(), "book")));
      }
    }
  }
}
