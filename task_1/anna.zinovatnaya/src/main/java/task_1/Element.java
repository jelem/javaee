package main.java;

import java.util.Map;

public interface Element {

    String getContent();

    String getHashCode();

    String getType();

    void addToMap(Map map);

}
