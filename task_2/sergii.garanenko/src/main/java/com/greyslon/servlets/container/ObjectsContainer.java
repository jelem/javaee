package com.greyslon.servlets.container;

import java.util.HashMap;
import java.util.Map;

public class ObjectsContainer {

  private static Map<Class, Object> classObjectMap = new HashMap<>();

  public static <T> T getObject(Class<T> clazz) {
    try {
      if (classObjectMap.containsKey(clazz)) {
        return ((T) classObjectMap.get(clazz));
      }
      T object = clazz.newInstance();
      classObjectMap.put(clazz, object);
      return ((T) object);
    } catch (ReflectiveOperationException ex) {
      ex.printStackTrace();
      throw new RuntimeException("Error on creating instance of " + clazz.getName(), ex);
    }
  }
}
