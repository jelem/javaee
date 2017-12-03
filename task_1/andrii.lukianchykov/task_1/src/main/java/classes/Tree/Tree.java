package classes.Tree;

import classes.Commit.Commit;
import classes.Type.Type;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Map;

public class Tree extends Type {
    private String content;
    private String hashcode = null;
    private String parent;
    private String tree;

    public Tree(){}

    public Tree(String content,String parentHashCode) {

        this.content = content;
        this.parent = getHashCode(parentHashCode);
        this.hashcode = getHashCodeTree();
        this.tree = getType();
    }

    @Override
    public String getContent() {
        return null;
    }


    public String getTree() {
        return tree;
    }

    public Tree  getTreeOb() {
        Tree tree = new Tree();
        return tree;
    }

    public String getType() {
        return "tree";
    }

    @Override
    public void addMap(Map map) {
        map.put(this.getHashCode(),this.getContent());
    }

    @Override
    public void elemOutPut() {}

    public String getTypeT() {
        return "tree";
    }

    @Override
    public String getHashCode() {
        return hashcode;
    }

    public String getHashCode(String key) {
        return hashcode = DigestUtils.sha1Hex(key);
    }


    public String getHashCodeTree() {
        return hashcode = DigestUtils.sha1Hex(content);
    }

}
