package com.servlets;

import com.entity.Book;
import com.repository.BookDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/books")
public class AddNewBookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("UTF-8");

        out.println("<html>\n" +
                "<head>\n" +
                " <meta charset=\"utf-8\">" +
                "    <title>Some text</title>\n" +
                "</head>\n" +
                "<body>");
        out.println("<form action=\"/art-1.0-SNAPSHOT/books\" method=\"post\">\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <td>title:</td>\n" +
                "            <td><input type=\"text\" name=\"title\"></td>\n" +
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
                "            <td>description: </td>\n" +
                "            <td><input type=\"text\" name=\"description\"> </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>picture: </td>\n" +
                "            <td><input type=\"text\" name=\"pictureName\"> </td>\n" +
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
        resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("UTF-8");

        BookDB bookDB = BookDB.getInstance();

        String bookName = req.getParameter("title");
        String author = req.getParameter("author");
        String dateOfPublishing = req.getParameter("dateOfPublishing");
        String description = req.getParameter("description");
        String pictureName = req.getParameter("pictureName");

        Book book = new Book(bookName, author, dateOfPublishing, description);


        bookDB.addBook(book, pictureName);

        resp.sendRedirect(req.getContextPath());
    }
}
