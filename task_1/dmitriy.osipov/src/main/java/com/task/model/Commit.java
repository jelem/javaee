package com.task.model;

import com.task.exceptions.ElementAlreadyExistsException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Commit implements IElementLead {

    private Set<Commit> parents;
    private Map<String, IElementContent> contentMap;

    @Override
    public boolean equals(Object object) {
        if ((object == null) || (!(object instanceof Commit))) {
            return false;
        }
        Commit another = (Commit)object;
        return (this == object) || this.getHash().equals(another.getHash());
    }

    @Override
    public void addParent(IElementLead element) throws IndexOutOfBoundsException, ElementAlreadyExistsException,
            IllegalArgumentException {
        if (parents == null) {
            parents = new HashSet<>();
        }
        if (parents.size() == 2) {
            throw new IndexOutOfBoundsException("Commits can't take more than 2 parents");
        }
        if (!(element instanceof Commit)) {
            throw new IllegalArgumentException("Commits can't take element with any another type like a parent");
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
    public Map<String, IElementContent> getContent() {
        return this.contentMap;
    }

    @Override
    public void addContent(IElementContent content) {
        if (this.contentMap == null) {
            contentMap = new HashMap<>();
        }
        this.contentMap.put(content.getHash(), content);
    }
}
