package com.task.model;

import com.task.exceptions.ElementAlreadyExistsException;
import com.task.utils.DataUtility;

import java.util.Set;

public interface IElement {

    void addParent(IElementLead element) throws IndexOutOfBoundsException, ElementAlreadyExistsException,
            IllegalArgumentException;
    Set<? extends IElementLead> getParents();
    
    default String getHash() {
        return DataUtility.getHash(this);
    }
}
