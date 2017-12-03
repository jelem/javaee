package com.bookshop.response;

import static java.lang.System.out;

public class Response {
    public void getRegistrationResultStart(String title, String author, String year) {
        out.println(
                "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Register new Book</title>\n" +
                        "<style type=\"some text/css\">\n" +
                        "        body {\n" +
                        "            background-color: #98FB98;\n" +
                        "        }\n" +
                        "    </style>" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <table>\n" +
                        "        <tr>\n" +
                        "            <td><p>Title: " + title + "</p></td>" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "             <td><p>Author: " + author + "</p></td>" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "             <td><p>Year: " + year + "</p></td>" +
                        "        </tr>\n");
    }

    public void getRegistrationResultEnd() {
        out.println("<tr>");
        out.println("<td><h1 align = \"center\" New book successfully added!</h1></td>");
        out.println("</tr>");
        out.println("<tr>\n" +
                "            <td><a href=\"/index.html\">Return home</a></td>\n" +
                "        </tr>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    public void getRegistration() {
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Register new Book</title>\n" +
                "<style type=\"some text/css\">\n" +
                "        body {\n" +
                "            background-color: #98FB98;\n" +
                "        }\n" +
                "    </style>" +
                "</head>\n" +
                "<body>\n" +
                "<form action=\"/registration.html\" method=\"post\"></form>\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <td>Input Title :</td>\n" +
                "            <td><input type=\"text\" name=\"Title\"></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>Input Author:</td>\n" +
                "            <td><input type=\"text\" name=\"Author\"></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td>Input Year:</td>\n" +
                "            <td><input type=\"text\" name=\"Year\"></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td><input type=\"submit\" value=\"Registration\"/></td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "            <td><a href=\"/SuccessRegistration.html\">Check Registration</a></td>\n" +
                "        </tr>" +
                "            <td><a href=\"/index.html\">Return home</a></td>\n" +
                "        </tr>\n" +
                "    </table>\n" +
                "</body>\n" +
                "</html>");
    }

    public void getOutPutHead() {
        out.println("<html lang=\"en\"");
        out.println("<head>");
        out.println("<title>\"Books List\" ");
        out.println("<style type=\"some text/css\">\n" +
                "        body {\n" +
                "            background-color: #FDF5E6;\n" +
                "        }\n" +
                "    </style>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action=\"/books.html\" method=\"get\"></form>" +
                "        <h1 align=\"center\">Book List<br /></h1>\n" +
                "    <table>\n");
    }

    public void getOutPutElse() {
        out.println("<tr><td><p>Empty</p></td></tr>");
    }

    public void getMiddle(String title, String author, String year) {
        out.println("<tr>");
        out.println("<td><p>|Title : |" + title + " |Author : | " + author + " |Year: | " + year + "</p></td>");
        out.println("</td>");
    }

    public void getOutPutEnd()
    {
        out.println(" <tr>\n" +
                "           <td><a href=\"/index.html\">Return home</a></td>\n" +
                "    </tr>\n");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    public void getSearchHeadWA(String author) {
        out.println(
                "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Success Search</title>\n" +
                        "    <style type=\"some text/css\">\n" +
                        "        body {\n" +
                        "            background-color: #6A5ACD;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>" +
                        "<body>\n" +
                        "<form action=\"/SuccessSearch.html\" method=\"post\"></form>\n" +
                        "    <table>" +
                        "        <tr>\n" +
                        "           <td><p>Author: " + author + "</p></td>" +
                        "        </tr>");
    }

    public void getSearchElse() {
        out.println("<tr><td><p>No such book in our Shop</p></td></tr>");
    }

    public void getEndSearchSuccess()
    {
        out.println("<tr>");
        out.println("<td><a href= Return main </a></td>");
        out.println("</tr>");
        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    public void gerSearch() {
        out.println(
                "<html lang=\"en\">\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Search Author</title>\n" +
                        "    <style type=\"some text/css\">\n" +
                        "        body {\n" +
                        "            background-color: #6A5ACD;\n" +
                        "        }\n" +
                        "    </style>\n" +
                        "</head>" +
                        "<body>\n" +
                        "<form action=\"/search.html\" method=\"get\">\n" +
                        "    <table>\n" +
                        "        <tr>\n" +
                        "            <td> Input Author:</td>\n" +
                        "            <td><input type=\"text\" name=\"Author\"></td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td><input type=\"submit\" value=\"Search\"/></td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td><a href=\"/SearchSuccess.html\">Check Search</a></td>\n" +
                        "        </tr>\n" +
                        "        <tr>\n" +
                        "            <td><a href=\"/index.html\">Return home</a></td>\n" +
                        "        </tr>\n" +
                        "    </table>\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");
    }
}
