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
    private BookDB bookDB = BookDB.getInstance();

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

        out.println("Test -  - -  - -  - - - 1");

        out.println("<table>");
        bookDB.getBooks().forEach(p -> {
            out.println("<tr>");
            out.println("<td>".concat(p.getAuthor()).concat("</td>"));
            out.println("<td>".concat(p.getTitle()).concat("</td>"));
            out.println("<td>".concat(p.getDate()).concat("</td>"));
            out.println("<td>".concat(p.getDescription()).concat("</td>"));
            out.println("<td><img src=\"data:image/jpeg;base64,".concat(p.getPicture()).concat("/></td>"));
            out.println("</tr>");
        });
        out.println("</table>");

        out.println("Test -  - -  - -  - - - 1");

        out.println("</body>\n" +
                "</html>");
    }
}
