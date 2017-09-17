package com.entity;

import javax.persistence.*;

@NamedQueries({@NamedQuery(
        name = "booksByAuthor",
        query = "FROM Book b WHERE b.author = :author"
)})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "books",
                query = "SELECT * FROM books",
                resultClass = Book.class
        )
})

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "date")
    private String date;
    @Column(name = "description")
    private String description;
    @Column(name = "picturePath")
    private String picturePath;

    public Book() {
    }

    public Book(String title, String author, String date, String description, String picturePath) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.description = description;
        this.picturePath = picturePath;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", picturePath='" + picturePath + '\'' +
                '}';
    }
}
