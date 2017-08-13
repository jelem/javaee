package main.java;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

public class File implements Element {

    private String hash;
    private String content;

    public File(String content){
        this.content = content;
        this.hash = DigestUtils.sha1Hex(content);
    }

    public String getContent(){
        return content;
    }

    public String getHashCode(){
        return hash;
    }

    public String getType(){
        return "blob";
    }

    public void addToMap(Map map){
        map.put(this.getHashCode(), this.getContent());
    }

    public void printElement(){
        System.out.print("file" + "\t" + hash);
        System.out.println();
    }
}
