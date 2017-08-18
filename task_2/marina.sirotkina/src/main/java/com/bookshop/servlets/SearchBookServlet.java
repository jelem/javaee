package com.bookshop.servlets;

import com.bookshop.entity.Book;
import com.bookshop.services.BookDB;
import com.bookshop.services.ResponseContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/search/result")
public class SearchBookServlet extends HttpServlet{
    private BookDB bookDB;
    private String author;
    private ResponseContent responseContent;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        responseContent = new ResponseContent();
        author = req.getParameter("author");
        PrintWriter out = resp.getWriter();
        out.println(responseContent.getHTMLContent(getBookByAuthor()));
    }

    private List<Book> getBookByAuthor(){
        bookDB = new BookDB(author);
        return bookDB.searchBook();
    }
}
