package com.servlets;

import com.repository.BookDB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/books/search")
public class PrintBooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        PrintWriter out = resp.getWriter();
        req.setCharacterEncoding("UTF-8");

        out.print("<html>\n" +
                "<head>\n" +
                " <meta charset=\"utf-8\">" +
                "    <title>Some text</title>\n" +
                "</head>\n" +
                "<body>");

        out.print("<table>");
        BookDB.getInstance().getBookSet().forEach(p -> {
            out.print("<tr>");
            out.print("<td>".concat(p.getAuthor()).concat("</td>"));
            out.print("<td>".concat(p.getTitle()).concat("</td>"));
            out.print("<td>".concat(p.getDate()).concat("</td>"));
            out.print("</tr>");
        });
        out.print("</table>");
        out.print("</body>\n" +
                "</html>");
    }
}
