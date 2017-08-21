package com.hillel.data;

import com.hillel.objects.Book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

public class DataBase {

  private TreeMap<String, Book> bookList = new TreeMap();
  private final String fileName = "books.txt";

  private static DataBase dataBase;

  private DataBase() throws IOException {
    this.bookList = readFromFile();
  }

  public static DataBase getInstance() throws IOException {
    if (dataBase == null) {
      dataBase = new DataBase();
    }
    return dataBase;
  }

  public TreeMap<String, Book> getBookList() throws IOException {
    return bookList;
  }

  private TreeMap<String, Book> readFromFile() throws IOException {

    TreeMap<String, Book> bookListOut = new TreeMap();
    ClassLoader classLoader = getClass().getClassLoader();
    BufferedReader bufferedReader = new BufferedReader(
        new FileReader(classLoader.getResource(fileName).getFile()));
    String str = "";

    while ((str = bufferedReader.readLine()) != null) {
      String[] bookProperties = str.split(":");

      bookListOut.put(bookProperties[0].trim(),
          new Book(bookProperties[0].trim(), bookProperties[1].trim(), bookProperties[2].trim()));
    }

    bufferedReader.close();
    TreeMap<String, Book> bookTreeMapUTF8 = getStringBookTreeMapUTF8(bookListOut);

    return bookTreeMapUTF8;
  }

  private TreeMap<String, Book> getStringBookTreeMapUTF8(TreeMap<String, Book> bookListOut)
      throws UnsupportedEncodingException {
    TreeMap<String, Book> bookTreeMapUTF8 = new TreeMap();
    for (Map.Entry<String, Book> bookEntry : bookListOut.entrySet()) {
      String bookName = setEncodingToUTF8(bookEntry.getKey());
      String bookAuthor = setEncodingToUTF8(bookEntry.getValue().getBookAuthor());
      String bookPublishYear = setEncodingToUTF8(bookEntry.getValue().getBookPublishYear());
      bookTreeMapUTF8.put(bookName, new Book(bookName, bookAuthor, bookPublishYear));
    }
    return bookTreeMapUTF8;
  }

  public TreeMap<String, Book> findBooksOwnerAuthor(String nameAuthorParametr) throws IOException {

    TreeMap<String, Book> bookMap = DataBase.getInstance().getBookList();
    TreeMap<String, Book> bookMapOneAuthor = new TreeMap<String, Book>();
    for (Map.Entry<String, Book> bookEntry : bookMap.entrySet()) {
      if (bookEntry.getValue().getBookAuthor().equalsIgnoreCase(nameAuthorParametr)) {
        bookMapOneAuthor.put(bookEntry.getKey(), bookEntry.getValue());
      }
    }

    if (bookMapOneAuthor.size() == 0) {
      bookMapOneAuthor.put("Empty_MAP", null);
    }

    return bookMapOneAuthor;
  }

  private Boolean writeToFile() throws IOException {
    TreeMap<String, Book> bookListOut = new TreeMap();

    fillCycleBookListWithKeyByName(bookListOut);
    Boolean writtedFlag = false;

    BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("books2.txt"));
    String str = "";

    for (Map.Entry<String, Book> bookEntryForWritting : bookListOut.entrySet()) {

      str = bookEntryForWritting.getKey() + ":" + bookEntryForWritting.getValue().getBookAuthor()
          + ":" + bookEntryForWritting.getValue().getBookPublishYear();
      bufferedWriter.write(str);
    }

    return writtedFlag;
  }

  private void fillCycleBookListWithKeyByName(TreeMap<String, Book> bookListOut) {
    for (Map.Entry<String, Book> bookEntry : bookList.entrySet()) {
      bookListOut.put(bookEntry.getValue().getBookName(), bookEntry.getValue());
    }
  }

  public String setEncodingToUTF8(String stringParametr) throws UnsupportedEncodingException {
    return new String(stringParametr.getBytes(Charset.defaultCharset()), "UTF-8");
  }


  public Boolean addToBookList(TreeMap<String, Book> treeMapParamentr) {
    this.bookList.putAll(treeMapParamentr);
    return this.bookList.containsKey(treeMapParamentr.firstKey());
  }
}




