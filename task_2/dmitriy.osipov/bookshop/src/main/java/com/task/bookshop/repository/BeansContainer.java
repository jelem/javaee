package com.task.bookshop.repository;

import java.util.HashMap;
import java.util.Map;

public class BeansContainer {

  private static Map<Class, Object> objectMap = new HashMap<>();

  public static <T> T getBean(Class<T> typeClass) {
    if (!objectMap.containsKey(typeClass)) {
      T newBean = null;
      try {
        newBean = typeClass.newInstance();
      } catch (InstantiationException | IllegalAccessException exc) {
        exc.printStackTrace();
        throw new RuntimeException("Dependency creation exception: " + typeClass.getName(), exc);
      }
      objectMap.put(typeClass, newBean);
    }
    return ((T) objectMap.get(typeClass));
  }
}
