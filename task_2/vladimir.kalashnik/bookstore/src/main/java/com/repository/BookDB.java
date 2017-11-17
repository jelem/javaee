package com.repository;

import com.beans.BookBean;
import com.entity.Book;

import java.util.Set;

public class BookDB {
    private BookDB() {

    }

    public Set<Book> getBookSet() {
        return BookBean.BOOKS;
    }

    public void addBook(Book book) {
        BookBean.BOOKS.add(book);
    }

    public static BookDB getInstance() {
        return new BookDB();
    }
}
