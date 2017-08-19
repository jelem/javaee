package com.task.model;

import javax.naming.OperationNotSupportedException;

public interface ElementContent extends Element {

  default String getContentString() throws OperationNotSupportedException {
    throw new OperationNotSupportedException();
  }

  void setContent(String object) throws OperationNotSupportedException;

  ElementLead getParent();
}
