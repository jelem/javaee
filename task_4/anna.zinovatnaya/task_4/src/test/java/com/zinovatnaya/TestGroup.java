package com.zinovatnaya;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  BookTest.class,
  BooksDBTest.class
}
)
public class TestGroup {
}