package com.hillel.objects;

public class Book {

    private String bookName;
    private String bookAuthor;
    private String bookPublishYear;

    public Book(String bookName, String bookAuthor, String bookPublishYear) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPublishYear = bookPublishYear;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPublishYear() {
        return bookPublishYear;
    }

    public void setBookPublishYear(String bookPublishYear) {
        this.bookPublishYear = bookPublishYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPublishYear='" + bookPublishYear + '\'' +
                '}';
    }
}
