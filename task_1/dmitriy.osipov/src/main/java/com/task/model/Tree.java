package com.task.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.naming.OperationNotSupportedException;

public class Tree implements ElementLead, ElementContent {

  private Set<ElementLead> parents;
  private Map<String, ElementContent> contentMap;
  private String name;

  public Tree(ElementLead parent, String... name) {
    this.addParent(parent);
    this.name = (name.length > 0) ? name[0] : "Tree";
    this.contentMap = new HashMap<>();
    parent.addContent(this);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object object) {
    if ((object == null) || (!(object instanceof Tree))) {
      return false;
    }
    Tree another = (Tree) object;
    return (this == object) || this.getHash().equals(another.getHash());
  }

  @Override
  public int hashCode() {
    return this.getHash().hashCode();
  }

  @Override
  public Map<String, ElementContent> getContent() {
    return this.contentMap;
  }

  private void addParent(ElementLead element)
      throws IndexOutOfBoundsException, IllegalArgumentException {
    if (parents == null) {
      parents = new HashSet<>();
    }
    if (parents.size() == 1) {
      throw new IndexOutOfBoundsException("Trees can't take more than 1 parents");
    }
    if (!parents.contains(element)) {
      parents.add(element);
    }
  }

  @Override
  public ElementLead getParent() {
    return parents.stream().findFirst().orElse(null);
  }

  @Override
  public Set<ElementLead> getParents() {
    return parents;
  }

  @Override
  public void setContent(String object) throws OperationNotSupportedException {
    throw new OperationNotSupportedException("Trees can't get any String content");
  }
}
