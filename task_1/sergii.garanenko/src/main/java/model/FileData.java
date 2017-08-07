package model;

import java.io.Serializable;
import java.util.Objects;

public class FileData implements Serializable {

  private String hash;
  private String name;
  private String parent;

  public FileData(String hash, String name, String parent) {
    this.hash = hash;
    this.name = name;
    this.parent = parent;
  }

  public String getHash() {
    return hash;
  }

  public String getName() {
    return name;
  }

  public String getParent() {
    return parent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileData fileData = (FileData) o;
    return Objects.equals(hash, fileData.hash) &&
        Objects.equals(name, fileData.name) &&
        Objects.equals(parent, fileData.parent);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hash, name, parent);
  }

  @Override
  public String toString() {
    return "FileData{" +
        "hash='" + hash + '\'' +
        ", name='" + name + '\'' +
        ", parent='" + parent + '\'' +
        '}';
  }
}
