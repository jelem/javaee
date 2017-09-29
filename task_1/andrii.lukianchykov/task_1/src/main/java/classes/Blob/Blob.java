package classes.Blob;

import classes.Tree.Tree;
import classes.Type.Type;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

public class Blob extends Type {

    private String blob = "blob";
    private String hashcode = null;
    private String content = null;

    public Blob() {}

    public Blob(String content, String hashcode) {

        this.content = content;
        this.hashcode = hashcode;

    }

    public String getHashCode()
    {
        return hashcode;
    }

    public String getHashCode(String key)
    {
        return hashcode = DigestUtils.sha1Hex(key);
    }

    @Override
    public String getContent() {
        return null;
    }

    public Blob  getBlobOb() {
        Blob blob = new Blob();
        return blob;
    }

    public String getType() {
        return blob;
    }

    @Override
    public void addMap(Map map) {
        map.put(this.getHashCode(),this.getContent());
    }
    @Override
    public void elemOutPut(){}

}
