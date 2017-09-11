package com;

import com.repository.BookDB;

public class TestDB {
    public static void main(String[] args) {
        BookDB bookDB = BookDB.getInstance();

//        Book book1 = new Book("1", "1", "1", "1");
//        Book book2 = new Book("2", "2", "2", "2");
//        bookDB.addBook(book1, "book1.jpg");
//        bookDB.addBook(book2, "book2.jpg");

        bookDB.getBooks().forEach(System.out::println);
    }
}
