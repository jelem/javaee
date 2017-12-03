package com.bookshop.servlets;

import com.bookshop.response.Response;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "/books/search")
public class SearchBookBAServlet extends HttpServlet {

    private Response response = new Response();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter outPut = resp.getWriter();
        response.gerSearch();
    }
}