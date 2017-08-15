package com.task.model;

import com.task.exceptions.ElementAlreadyExistsException;

import java.util.HashSet;
import java.util.Set;

public class Blob implements IElementContent {

    private Set<IElementLead> parents;

    private String content;

    @Override
    public void addParent(IElementLead element) throws IndexOutOfBoundsException, ElementAlreadyExistsException,
            IllegalArgumentException {
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
    public void setContent(String object) {
        content = object;
    }

    @Override
    public String getContent() {
        return content;
    }
}
