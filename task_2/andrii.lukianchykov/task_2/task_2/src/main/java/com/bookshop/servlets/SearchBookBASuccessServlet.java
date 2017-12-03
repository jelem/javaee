package com.bookshop.servlets;

import com.bookshop.bookdatabase.BookDataBase;
import com.bookshop.response.Response;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/books/search/success")
public class SearchBookBASuccessServlet extends HttpServlet {

    public Response response = new Response();
    public BookDataBase bookDataBase = new BookDataBase();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter outPut = resp.getWriter();

        String author = req.getParameter("author");
        response.getSearchHeadWA(author);
        bookDataBase.findBookByAuthor(author);
        response.getEndSearchSuccess();
    }
}
