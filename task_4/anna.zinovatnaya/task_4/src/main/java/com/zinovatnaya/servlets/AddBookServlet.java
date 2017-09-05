package com.zinovatnaya.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/books/add")
public class AddBookServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    out.println("<html>");
    out.println("<body>");
    out.println("<form action=\"/task_2-1.0-SNAPSHOT/books/add/result\" method=\"POST\">");
    out.println("<table>");
    out.println("<tr>");
    out.println("<td>Name: </td>");
    out.println("<td><input type=\"text\" name=\"name\"/></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>Author: </td>");
    out.println("<td><input type=\"text\" name=\"author\"/></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>Year: </td>");
    out.println("<td><input type=\"text\" name=\"year\"/></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td></td>");
    out.println("<td><input type=\"submit\" value=\"Add\"/></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");

  }
}