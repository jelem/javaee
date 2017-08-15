package com.task.model;

import javax.naming.OperationNotSupportedException;

public interface IElementContent extends IElement {

    Object getContent();
    void setContent(String object) throws OperationNotSupportedException;
}
