package classes.Commit;

import classes.Type.Type;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;

public class Commit extends Type {

    private String tree = "tree";
    private String parent = "parent";
    private String hashcode;
    private String message;
    private LocalDate date;

    public Commit() {}

    public Commit(String treeHashCode,String parentHashCode, String message) {

        this.tree = getHashCode(treeHashCode);
        this.parent = getHashCode(parentHashCode);

        String content = tree + getHashCode();
        content += "\n" + parent +  getHashCode();

        this.hashcode = getHashCode(content);

        this.message = message;
        this.date = LocalDate.now();
    }

    public Commit(String treeHashCode,String message) {

        this.tree = getHashCode(treeHashCode);
        this.parent = null;

        String content = tree+ getHashCode(treeHashCode);

        this.hashcode = getHashCode(content);

        this.message = message;
        this.date = LocalDate.now();
    }

    public String getContent() {

        String temp = tree + tree;

        if(parent != null)
        {
            temp += "\nparent" + parent;
        }

        temp += "\n" + message;

        SimpleDateFormat ft = new SimpleDateFormat("dd.MM..yyyy hh:mm:ss a");

        temp += "\n" + ft.format(this.date);

        return temp;
    }

    @Override
    public String getType() {
        return "commit";
    }

    @Override
    public void addMap(Map map) {
        map.put(this.getHashCode(),this.getContent());
    }

    @Override
    public void elemOutPut(){}

    @Override
    public String getHashCode() {
        return hashcode;
    }

    @Override
    public String getHashCode(String key) {
        return hashcode = DigestUtils.sha1Hex(key);
    }


}
