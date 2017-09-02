package com.git.gitObjects;

import java.util.ArrayList;

/**
 * Created by User on 09.08.2017.
 */
public abstract class GitObject {
    private ArrayList<String> links = new ArrayList<>();

    abstract String getText();

    public ArrayList<String> getLinks() {
        return links;
    }

    public void addLink(String link) {
        this.links.add(link);
    }
}
