import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books")
public class BookShopServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    PrintWriter out = resp.getWriter();
    out.println("<html>");
    out.println("<body>");
    List<Book> tempList = BooksDB.getBooks();
    out.println("List of books");
    if(!tempList.isEmpty()) {
      for (Book b : tempList) {
        out.println("<p>");
        out.println(b.getName() + " " + b.getAuthor() + " " + b.getYear());
        out.println("</p>");
      }
    } else {
      out.println("<p>Empty</p>");
    }

    out.println("<p>");
    out.println("<a href=\"/task_2-1.0-SNAPSHOT\">Return home</a>");
    out.println("</p>");
    out.println("</body>");
    out.println("</html>");
  }

}