import org.owasp.esapi.ESAPI;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books/add/result")
public class AddBookResultServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    String name = ESAPI.encoder().canonicalize(req.getParameter("name"));
    String author = ESAPI.encoder().canonicalize(req.getParameter("author"));
    String year = ESAPI.encoder().canonicalize(req.getParameter("year"));

    out.println("<html>");
    out.println("<body>");
    out.println("<p>");
    out.println("Name: " + name);
    out.println("</p>");
    out.println("<p>");
    out.println("Author: " + author);
    out.println("</p>");
    out.println("<p>");
    out.println("Year: " + year);
    out.println("</p>");

    Book book = new Book(name, author, year);
    BooksDB.add(book);

    out.println("<p>");
    out.println("New book added!");
    out.println("</p>");

    out.println("<p>");
    out.println("<a href=\"/task_2-1.0-SNAPSHOT\">Return home</a>");
    out.println("</p>");

    out.println("</body>");
    out.println("</html>");
  }
}