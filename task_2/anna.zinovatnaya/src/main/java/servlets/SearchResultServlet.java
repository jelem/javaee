import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books/search/author")
public class SearchResultServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    String author = req.getParameter("author");

    out.println("<html>");
    out.println("<body>");
    out.println("Author: " + author);
    List<Book> books = BooksDB.getBooks();
    if (!books.isEmpty()) {
      for (Book b : books) {
        out.println("<p>");
        out.println(b.getName() + " " + b.getAuthor() + " " + b.getYear());
        out.println("</p>");
      }
    } else {
      out.println("<p>");
      out.println("No books of this author");
      out.println("</p>");
    }

    out.println("<p>");
    out.println("<a href=\"/task_2-1.0-SNAPSHOT\">Return home</a>");
    out.println("</p>");

    out.println("</body>");
    out.println("</html>");
  }
}