package com.servlets;

import com.book.IBookDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/books")
public class AddNewBookServlet extends HttpServlet implements IBookDB {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>\n" +
                "<head>\n" +
                "    <title>Some text</title>\n" +
                "</head>\n" +
                "<body>");
        out.println("<form action=\"/art-1.0-SNAPSHOT/books\" method=\"post\">\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <td>book name:</td>\n" +
                "            <td><input type=\"text\" name=\"book name\"></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>author: </td>\n" +
                "            <td><input type=\"text\" name=\"author\"> </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>date of publishing: </td>\n" +
                "            <td><input type=\"text\" name=\"dateOfPublishing\"> </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td></td>\n" +
                "            <td><input type=\"submit\" value=\"Register\"/></td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</form>");

        out.println("</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("book name");
        String author = req.getParameter("author");
        String dateOfPublishing = req.getParameter("dateOfPublishing");

        IBookDB.getBookDB().addBook(bookName, author, dateOfPublishing);
        resp.sendRedirect(req.getContextPath());

    }
}
