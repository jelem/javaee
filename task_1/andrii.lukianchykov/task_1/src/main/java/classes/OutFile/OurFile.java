package classes.OutFile;

import classes.Type.Type;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

public class OurFile extends Type {

    private String hashcode = null;
    private String content;
    private String type = "blob";

    public OurFile() {}

    public OurFile(String content) {

        this.content = content;
        this.hashcode = getHashCode(content);
    }

    public String getHashCode() {
        return hashcode;
    }

    public String getHashCode(String key) {
        return hashcode = DigestUtils.sha1Hex(key);
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public void addMap(Map map) {

        map.put(this.getHashCode(),this.getContent());
    }

    public void elemOutPut() {

        System.out.println("File" + "\t" + hashcode);
        System.out.println();
    }
}