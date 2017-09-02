package com.task.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Commit implements ElementLead {

  private Set<Commit> parents;
  private Map<String, ElementContent> contentMap;
  private String name;
  private String message;

  public Commit(Commit parent, String message, String... name) {
    this(parent, null, message, name);
  }

  public Commit(Commit parentFirst, Commit parentSecond, String message, String... name) {
    this.addParent(parentFirst);
    this.addParent(parentSecond);
    this.message = message;
    this.name = (name.length > 0) ? name[0] : "Commit";
    contentMap = new HashMap<>();
  }

  @Override
  public String getName() {
    return name;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public boolean equals(Object object) {
    if ((object == null) || (!(object instanceof Commit))) {
      return false;
    }
    Commit another = (Commit) object;
    return (this == object) || this.getHash().equals(another.getHash());
  }

  @Override
  public int hashCode() {
    return this.getHash().hashCode();
  }

  private void addParent(ElementLead element)
      throws IndexOutOfBoundsException, IllegalArgumentException {
    if (parents == null) {
      parents = new HashSet<>();
    }
    if (element == null) {
      return;
    }
    if (parents.size() == 2) {
      throw new IndexOutOfBoundsException("Commits can't take more than 2 parents");
    }
    if (!(element instanceof Commit)) {
      throw new IllegalArgumentException(
          "Commits can't take element with any another type like a parent");
    }
    if (!parents.contains(element)) {
      parents.add((Commit) element);
    }
  }

  @Override
  public Set<Commit> getParents() {
    return this.parents;
  }

  @Override
  public Map<String, ElementContent> getContent() {
    return this.contentMap;
  }

  @Override
  public String getInfo() {
    return String.format("Type: %10s;\t\tName: %10s;\t\tKey: %s;\t\tParents: %s;\t\tMessage: %s",
        this.getClass().getSimpleName(), this.getName(), this.getHash(), this.showParents(),
        this.getMessage());
  }
}
