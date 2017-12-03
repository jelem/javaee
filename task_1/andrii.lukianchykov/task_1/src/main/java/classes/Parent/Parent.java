package classes.Parent;

import classes.Blob.Blob;
import classes.Tree.Tree;
import classes.Type.Type;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

public class Parent extends Type {

    private String content;
    private String hashcode = null;
    private String parent;

    public Parent() {}

    public Parent(String content,Blob blob) {

        this.content = content;
        this.hashcode = getHashCode(); //get HashCode();
        this.parent = getType();

    }

    @Override
    public String getContent() {
        return null;
    }

    public String getType() {
        return "parent";
    }

    @Override
    public void addMap(Map map) {
        map.put(this.getHashCode(),this.getContent());
    }

    @Override
    public void elemOutPut() {

    }

    public Parent getParentOb() {
        Parent parent = new Parent();
        return parent;
    }

    public String getHashCode() {
        return hashcode;
    }

    @Override
    public String getHashCode(String key) {
        return null;
    }

}
