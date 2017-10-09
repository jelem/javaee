package com.zinovatnaya.servlets;

import com.zinovatnaya.Author;
import com.zinovatnaya.Book;
import com.zinovatnaya.BooksDB;
import org.owasp.esapi.ESAPI;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addresult")
public class AddBookResultServlet extends HttpServlet {

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    BooksDB.getInstance().addBook(new Book(req.getParameter("name"), 
        new Author(req.getParameter("author")), req.getParameter("description"), 
        req.getParameter("image")));

    out.println("<p>");
    out.println("New book added!");
    out.println("</p>");

    out.println("<p>");
    out.println("<a href=\"/bookshop\">Return home</a>");
    out.println("</p>");

    out.println("</body>");
    out.println("</html>");
  }
}