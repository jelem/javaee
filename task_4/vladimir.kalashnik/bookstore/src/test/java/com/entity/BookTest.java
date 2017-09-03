package com.entity;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BookTest {
    private Book book;

    @Before
    public void setUp() {
        book = new Book("title", "author", "date");
    }

    @Test
    public void getTitleTest() {
        assertThat("title", is(book.getTitle()));
    }

    @Test
    public void getAuthorTest() {
        assertThat("author", is(book.getAuthor()));
    }

    @Test
    public void getDateTest() {
        assertThat("date", is(book.getDate()));
    }

    @Test
    public void expectedEqualsObjectTest() {
        Book expectedBook = new Book("title", "author", "date");

        assertTrue(expectedBook.equals(book));
    }

    @Test
    public void expectedNotEqualsObjectTest() {
        Book expectedBookTitle = new Book("title_1", "author", "date");
        Book expectedBookAuthor = new Book("title", "author_1", "date");
        Book expectedBookDate = new Book("title", "author", "date_1");

        assertFalse(expectedBookTitle.equals(book));
        assertFalse(expectedBookAuthor.equals(book));
        assertFalse(expectedBookDate.equals(book));
    }

    @Test
    public void expectedEqualHashcode() {
        Book book1 = new Book("title", "author", "date");

        assertThat(book1.hashCode(), is(book.hashCode()));
    }

    @Test
    public void expectedNotEqualHashcode() {
        Book book1 = new Book("title_1", "author", "date");

        assertThat(book1.hashCode(), not(book.hashCode()));
    }

    @Test
    public void toStringTest() {
        Book book1 = new Book("title", "author", "date");

        assertThat(book.toString(), is(book1.toString()));
    }
}
