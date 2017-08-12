package com.gitapp.entity;

public class Data {
    private String keySHA1;
    private String content;

    public Data() {
    }

    public Data(String keySHA1, String content) {
        this.keySHA1 = keySHA1;
        this.content = content;
    }

    public String getKeySHA1() {
        return keySHA1;
    }

    public void setKeySHA1(String keySHA1) {
        this.keySHA1 = keySHA1;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("com.gitapp.entity.Data: ");
        sb.append("keySHA1= ").append(keySHA1);
        sb.append(", content= ").append(content);
        return sb.toString();
    }
}