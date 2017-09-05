package com.task.bookshop.repository;

import static org.junit.Assert.assertTrue;

import com.task.bookshop.utils.LinkGenerator;
import org.junit.Test;

public class BeansContainerTest {

  @Test
  public void beanContainerShouldReturnTheSameObject() {
    LinkGenerator linkGenerator1 = BeansContainer.getBean(LinkGenerator.class);
    LinkGenerator linkGenerator2 = BeansContainer.getBean(LinkGenerator.class);
    assertTrue(linkGenerator1 == linkGenerator2);
  }
}
