package com.task.model;

import javax.naming.OperationNotSupportedException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Tree implements IElementLead, IElementContent {

    private Set<IElementLead> parents;
    private Map<String, IElementContent> contentMap;
    private String name = "Tree";

    public Tree(IElementLead parent, String... name) {
        this.addParent(parent);
        if (name.length > 0) {
            this.name = name[0];
        }
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
        Tree another = (Tree)object;
        return (this == object) || this.getHash().equals(another.getHash());
    }

    @Override
    public Map<String, IElementContent> getContent() {
        return this.contentMap;
    }

    @Override
    public void addContent(IElementContent content) {
        if (contentMap == null) {
            contentMap = new HashMap<>();
        }
        contentMap.put(content.getHash(), content);
    }

    private void addParent(IElementLead element) throws IndexOutOfBoundsException, IllegalArgumentException {
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
    public IElementLead getParent() {
        return parents.stream().findFirst().get();
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
