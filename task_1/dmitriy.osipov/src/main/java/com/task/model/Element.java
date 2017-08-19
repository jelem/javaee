package com.task.model;

import com.task.utils.DataUtility;

import java.io.Serializable;
import java.util.Set;

public interface Element extends Serializable {

  Set<? extends ElementLead> getParents();

  String getName();

  default String getHash() {
    return DataUtility.getHash(this);
  }

  default String showParents() {
    StringBuilder builder = new StringBuilder("");
    Set<? extends ElementLead> parents = this.getParents();
    for (ElementLead parent : parents) {
      builder.append(parent.getHash()).append(";  ");
    }
    return (parents.size() > 0) ? builder.toString() : "root";
  }

  default String getInfo() {
    return String.format("Type: %10s;\t\tName: %10s;\t\tKey: %s;\t\tParents: %s;",
        this.getClass().getSimpleName(), this.getName(), this.getHash(), this.showParents());
  }
}
