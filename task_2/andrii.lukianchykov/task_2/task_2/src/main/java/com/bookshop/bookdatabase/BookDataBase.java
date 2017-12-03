package com.bookshop.bookdatabase;

import static java.lang.System.out;

import com.bookshop.book.Book;
import com.bookshop.helpTests.ListArrayException;
import com.bookshop.helpTests.ListIsEmptyException;
import com.bookshop.helpTests.ListIsNotEmptyException;
import com.bookshop.response.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookDataBase {

    private static Scanner scanner = new Scanner(System.in);
    private List<Book> listOfBooks = new ArrayList<>();
    private Book book = new Book();
    private Response response = new Response();
    private int key;

    public void showBookList() {
        System.out.println("List of books: " + listOfBooks);
    }

    public void showBooks(){
        System.out.println("List of books: \n |" + book.getTitleBook() + "|" + book.getAuthorBook() + "|" + book.getYearBook() + "|");
    }

    public void showBooksServletOutput() {
        if(listOfBooks.isEmpty()) {
            for(Book b : listOfBooks){
                response.getMiddle(b.getTitleBook(),b.getAuthorBook(),b.getYearBook());
            }
        }else{
            response.getOutPutElse();
        }
    }

    public void defaultValuesListOfBooks() {

        if (listOfBooks != null) {
            out.println(
                "List is not empty.\nDo you want to add elements for included or to remove previous?\n Yes(add) - 1\n No(remove) - 2");

            out.println("Input your answer: ");

            if (scanner.hasNext() == false)
            {
                switch (key) {
                    case 1:
                        listOfBooks.add(new Book("Legend of Searcher", "Terry Goodkind", "2008"));
                        listOfBooks.add(new Book("Comics", "Overwatch", "2017"));
                        listOfBooks.add(new Book("Limited Collection of how to code right", "Java Enetrprise", "2017"));
                        break;
                    case 2:
                        removeList();
                        break;
                    default:
                        out.println("Incorrect key");
                        break;
                }

            } else{
                throw new ListArrayException("Something wrong with array");
            }
        }
    }

    public List<Book> findBookByAuthor(String author) {

        List<Book> books = new ArrayList<Book>();

        if (listOfBooks.isEmpty()) {
            for (Book b : listOfBooks) {
                if (b.getAuthorBook().equals(author)) {
                    response.getMiddle(b.getTitleBook(),b.getAuthorBook(),b.getYearBook());
                    books.add(book);
                }
            }
        }else{
            response.getSearchElse();
        }

        return books;
    }

    public void addNewBook() {

        if( listOfBooks.isEmpty()) {
            listOfBooks.add(book);
        } else {
            out.println("<p>Enter correct book with dates!</p>");
        }
    }

    public void removeList() {

        listOfBooks.remove(book);
        if(listOfBooks != null) {
            throw new ListIsNotEmptyException("List is not empty");
        }
        else{
            throw new ListArrayException("Something wrong with array");
        }
    }

    public List<Book> getListOfBooks() {
        return listOfBooks;
    }
}
