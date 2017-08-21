package com.book;

import java.util.ArrayList;
import java.util.List;

public class BooksDB {
    private List<Book> bookList = new ArrayList<>();

    public List<Book> getBookList() {
        return this.bookList;
    }

    public void addBook(String name, String author, String dateOfPublishing) {
        this.bookList.add(new Book(name, author, dateOfPublishing));
    }
}
