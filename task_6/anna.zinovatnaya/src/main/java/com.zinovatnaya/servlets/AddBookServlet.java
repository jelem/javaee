package com.zinovatnaya.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class AddBookServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter out = resp.getWriter();

    out.println("<html>");
    out.println("<body>");
    out.println("<form action=\"/bookshop/addresult\" method=\"POST\">");
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
    out.println("<td>Description: </td>");
    out.println("<td><input type=\"text\" name=\"description\"/></td>");
    out.println("<td>Image: </td>");
    out.println("<td><input type=\"text\" name=\"image\"/></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td></td>");
    out.println("<td><input type=\"submit\" value=\"Add\"/></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("</form>");
    out.println("<p>");
    out.println("<a href=\"/bookshop\">Return home</a>");
    out.println("</p>");
    out.println("</body>");
    out.println("</html>");

  }
}