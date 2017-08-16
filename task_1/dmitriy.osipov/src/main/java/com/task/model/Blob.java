package com.task.model;

import java.util.HashSet;
import java.util.Set;

public class Blob implements IElementContent {

  private Set<IElementLead> parents;

  private String name = "Blob";

  private String content;

  public Blob(IElementLead parent, String... name) {
    this.addParent(parent);
    if (name.length > 0) {
      this.name = name[0];
    }
    parent.addContent(this);
  }

  @Override
  public String getName() {
    return name;
  }

  private void addParent(IElementLead element)
      throws IndexOutOfBoundsException, IllegalArgumentException {
    if (parents == null) {
      parents = new HashSet<>();
    }
    if (parents.size() == 1) {
      throw new IndexOutOfBoundsException("Blobs can't take more than 1 parents");
    }
    if (!parents.contains(element)) {
      parents.add(element);
    }
  }

  @Override
  public Set<IElementLead> getParents() {
    return parents;
  }

  @Override
  public IElementLead getParent() {
    return parents.stream().findFirst().get();
  }

  @Override
  public void setContent(String object) {
    content = object;
  }

  @Override
  public String getContentString() {
    return content;
  }

  @Override
  public String getInfo() {
    return String.format("Type: %10s;\t\tName: %10s;\t\tKey: %s;\t\tParents: %s;\t\tValue:%s",
        this.getClass().getSimpleName(), this.getName(), this.getHash(), this.showParents(),
        this.getContentString());
  }
}
