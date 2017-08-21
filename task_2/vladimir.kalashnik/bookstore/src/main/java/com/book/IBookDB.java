package com.book;

public interface IBookDB {
    final static BooksDB bookDB = new BooksDB();

    public static BooksDB getBookDB() {
        return bookDB;
    }
}
