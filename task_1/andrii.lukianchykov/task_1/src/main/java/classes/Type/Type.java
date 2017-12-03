package classes.Type;

import classes.Blob.Blob;
import classes.Commit.Commit;
import classes.OutFile.OurFile;
import classes.Parent.Parent;
import classes.Tree.Tree;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

public abstract class Type {

    private String hashcode = null;
    private String content = null;

    public Type(){}

    public Type ( String hashcode, String content) {

        this.hashcode = hashcode;
        this.content = content;
    }
    public abstract String getContent();

    public  abstract String getType();

    public abstract void addMap(Map map);

    public  abstract void elemOutPut();

    public abstract String getHashCode();

    public abstract String getHashCode(String key);


}

