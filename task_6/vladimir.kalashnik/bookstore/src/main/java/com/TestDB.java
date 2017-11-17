package com;

import com.entity.Book;
import com.repository.BookDB;

public class TestDB {
    public static void main(String[] args) {
        BookDB bookDB = BookDB.getInstance();

        Book book1 = new Book("1", "1", "1", "1", "book1.jpg");
        Book book2 = new Book("2", "2", "2", "2", "book2.jpg");
        bookDB.addBook(book1);
        bookDB.addBook(book2);

        bookDB.getBooks().forEach(System.out::println);
        bookDB.getBooksByAuthor("1").forEach(System.out::println);
        bookDB.closeSessionFactory();

    }
}
