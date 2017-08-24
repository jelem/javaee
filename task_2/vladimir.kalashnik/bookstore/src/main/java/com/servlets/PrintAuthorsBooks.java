package com.servlets;

import com.book.IBookDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/books/search/author")
public class PrintAuthorsBooks extends HttpServlet implements IBookDB {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        out.println("<html>\n" +
                "<head>\n" +
                "    <title>Some text</title>\n" +
                "</head>\n" +
                "<body>");
        out.println("<form action=\"/art-1.0-SNAPSHOT/books/search/author\" method=\"post\">\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <td>author: </td>\n" +
                "            <td><input type=\"text\" name=\"author\"> </td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td></td>\n" +
                "            <td><input type=\"submit\" value=\"Register\"/></td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</form>");
        out.println("</body>\n" +
                "</html>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();

        String author = req.getParameter("author");
        IBookDB.getBookDB().getBookList().stream()
                .filter(p -> p.getAuthor().equals(author)).
                forEach(printWriter::println);
    }
}
