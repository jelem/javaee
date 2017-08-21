package com.book;

public class Book {
    private String name;
    private String author;
    private String dateOfPublishing;

    public Book(String name, String author, String dateOfPublishing) {
        this.name = name;
        this.author = author;
        this.dateOfPublishing = dateOfPublishing;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDateOfPublishing() {
        return dateOfPublishing;
    }

    @Override
    public String toString() {
        return
                "name='" + name + '\'' +
                        ", author='" + author + '\'' +
                        ", dateOfPublishing='" + dateOfPublishing;
    }
}
