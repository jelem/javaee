package com.entity;

public class Book {
    private String title;
    private String author;
    private String date;
    private String description;
    private String picture;

    public Book(String title, String author, String dateOfPublishing, String description) {
        this.title = title;
        this.author = author;
        this.date = dateOfPublishing;
        this.description = description;
    }

    public Book(String title, String author, String dateOfPublishing, String description,String picture) {
        this(title, author, dateOfPublishing, description);
        this.picture = picture;
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

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
