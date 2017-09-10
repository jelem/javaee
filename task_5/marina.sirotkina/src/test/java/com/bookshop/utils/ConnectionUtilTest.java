package com.bookshop.utils;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class ConnectionUtilTest {

  @InjectMocks
  private ConnectionUtil connectionUtil;

  @Mock
  private Connection mockConnection;
  @Mock
  private Statement mockStatement;

  @Before
  public void setUp() throws Exception {
    connectionUtil = new ConnectionUtil();
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void tearDown() throws Exception {
    connectionUtil.closeConnection();
  }

  @Test
  public void getConnection() throws Exception {
    Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
    Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
    int value = executeQuery("");
    assertEquals(value, 1);
    Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
  }

  private int executeQuery(String query) throws SQLException {
    return connectionUtil.getConnection().createStatement().executeUpdate(query);
  }

}