package com.repository;

import com.entity.Book;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

public class BookDB {
    private Connection connection;
    private static final BookDB BOOK_DB = new BookDB();

    private BookDB() {
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream("/db.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("username"),
                    properties.getProperty("password")
            );
            System.out.println("Connection successful");
        } catch (SQLException | IOException e) {
            System.err.println("Can't connection to database");
        }
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String dateOfPublishing = resultSet.getString("date");
                String description = resultSet.getString("description");

                Blob picture = resultSet.getBlob("picture");
                byte[] pictureBytes = Base64.getEncoder().encode(picture.getBytes(1, (int) picture.length()));
                String encodedPictureBytes = new String(pictureBytes);

                Book book = new Book(title, author, dateOfPublishing, description, encodedPictureBytes);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public void addBook(Book books, String pictureName) {
        String sql = "INSERT INTO books(title, author, date, description, picture) VALUES(?, ?, ?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
             BufferedInputStream input = new BufferedInputStream(getClass().getResourceAsStream("/picture/" + pictureName))) {

            preparedStatement.setString(1, books.getTitle());
            preparedStatement.setString(2, books.getAuthor());
            preparedStatement.setString(3, books.getDate());
            preparedStatement.setString(4, books.getDescription());
            preparedStatement.setBinaryStream(5, input);

            preparedStatement.executeUpdate();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static BookDB getInstance() {
        return BOOK_DB;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
