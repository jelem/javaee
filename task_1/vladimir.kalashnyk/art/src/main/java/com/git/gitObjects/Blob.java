package com.git.gitObjects;

/**
 * Created by User on 09.08.2017.
 */
public class Blob extends GitObject {
    private String text = "";

    public Blob(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}