package com.task.model;

import com.task.exceptions.ElementAlreadyExistsException;

import javax.naming.OperationNotSupportedException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tree implements IElementLead, IElementContent {

    private Set<IElementLead> parents;
    private Map<String, IElementContent> contentMap;

    @Override
    public boolean equals(Object object) {
        if ((object == null) || (!(object instanceof Tree))) {
            return false;
        }
        Tree another = (Tree)object;
        return (this == object) || this.getHash().equals(another.getHash());
    }

    @Override
    public Map<String, IElementContent> getContent() {
        return this.contentMap;
    }

    @Override
    public void addContent(IElementContent content) {
        this.contentMap.put(content.getHash(), content);
    }

    @Override
    public void addParent(IElementLead element) throws IndexOutOfBoundsException, ElementAlreadyExistsException,
            IllegalArgumentException {
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
    public Set<IElementLead> getParents() {
        return parents;
    }

    @Override
    public void setContent(String object) throws OperationNotSupportedException {
        throw new OperationNotSupportedException("Trees can't get any String content");
    }
}
