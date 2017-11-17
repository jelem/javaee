package com.repository;

import com.entity.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BookDBTest {
    private Set<Book> bookSet;
    @Mock
    private BookDB bookDB;


    @Before
    public void setup() {
        bookDB = mock(BookDB.class);
        bookSet = new HashSet<>();
    }

    @Test
    public void addBookTest() {
        Book book = new Book("", "", "");
        Book book1 = new Book("1", "", "");
        bookSet.add(book);

        when(bookDB.getBooks()).thenReturn(bookSet);

        assertTrue(bookDB.getBooks().contains(book));
        assertFalse(bookDB.getBooks().contains(book1));
    }

    @Test
    public void getBookSet() {
        Set<Book> bookSet1 = new HashSet<>();

        when(bookDB.getBooks()).thenReturn(bookSet);

        assertTrue(bookDB.getBooks() == bookSet);
        assertFalse(bookDB.getBooks() == bookSet1);
    }
}
