package com.bookshop.book;

public class Book {

    private String titleBook;
    private String authorBook;
    private String yearBook;

    public Book() {}
    public Book(String tittleBook, String authorBook, String yearBook) {

        this.titleBook = tittleBook;
        this.authorBook = authorBook;
        this.yearBook = yearBook;
    }

    public String getTitleBook() {
        return titleBook;
    }

    public String getAuthorBook() {
        return authorBook;
    }

    public String getYearBook() {
        return yearBook;
    }

    public Book getBook() {

        Book book = new Book();
        return book;
    }
}
