package com.task.model;

import javax.naming.OperationNotSupportedException;

public interface IElementContent extends IElement {

    default String getContentString() throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
    void setContent(String object) throws OperationNotSupportedException;

    IElementLead getParent();
}
