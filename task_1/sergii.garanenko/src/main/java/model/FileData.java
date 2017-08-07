package model;

public class FileData {

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
  public String toString() {
    return "FileData{" +
        "hash='" + hash + '\'' +
        ", name='" + name + '\'' +
        ", parent='" + parent + '\'' +
        '}';
  }
}
