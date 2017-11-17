package com.entity;

public class Book {
    private String title;
    private String author;
    private String date;

    public Book(String title, String author, String dateOfPublishing) {
        this.title = title;
        this.author = author;
        this.date = dateOfPublishing;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        return date != null ? date.equals(book.date) : book.date == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return title.concat("\t")
                .concat(author).concat("\t")
                .concat(date);
    }
}
