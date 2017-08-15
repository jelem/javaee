package main.java;

import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map;

public class Commit implements Element {

  private String hash;
  private String parent;
  private String tree;
  private String message;
  private LocalDate date;

  public Commit(String treeHash, String parentHash, String message) {
    this.tree = treeHash;
    this.parent = parentHash;

    String content = "tree " + treeHash;
    content += "\nparent " + parentHash;

    this.hash = DigestUtils.sha1Hex(content);

    this.message = message;
    this.date = LocalDate.now();
  }

  public Commit(String treeHash, String message) {
    this.tree = treeHash;
    this.parent = null;

    String content = "tree " + treeHash;

    this.hash = DigestUtils.sha1Hex(content);
    this.message = message;
    this.date = LocalDate.now();
  }

  public String getContent() {
    String temp = "tree " + tree;

    if (parent != null) {
      temp += "\nparent " + parent;
    }

    temp += "\n" + message;

    SimpleDateFormat ft =
        new SimpleDateFormat("dd.MM.yyyy hh:mm:ss a");

    temp += "\n" + ft.format(this.date);

    return temp;
  }

  public String getHashCode() {
    return hash;
  }

  public String getType() {
    return "commit";
  }

  public void addToMap(Map map) {
    map.put(this.getHashCode(), this.getContent());
  }
}

