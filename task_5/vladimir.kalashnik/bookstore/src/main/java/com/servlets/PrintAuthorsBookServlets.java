package com.servlets;

import com.repository.BookDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet("/books/search/author")
public class PrintAuthorsBookServlets extends HttpServlet {
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
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        req.setCharacterEncoding("UTF-8");
        BookDB bookDB = BookDB.getInstance();

        String author = req.getParameter("author");

        bookDB.getBooks().stream()
                .filter(p -> p.getAuthor().equals(author)).
                forEach(p -> {
                    printWriter.println(p.getTitle());
                    printWriter.println(p.getAuthor());
                    printWriter.println(p.getDate());
                    printWriter.println(p.getDescription());
                    printWriter.println("<img src=\"data:image/jpeg;base64,".concat(p.getPicture()).concat("\"/>"));
                });
    }
}
