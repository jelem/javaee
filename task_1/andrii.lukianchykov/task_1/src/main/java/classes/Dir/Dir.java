package classes.Dir;

import classes.Blob.Blob;
import classes.OutFile.OurFile;
import classes.Type.Type;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Dir extends Type {

    private String hashcode = null;
    private String content = null;
    private Blob blob = new Blob();

    private List<Dir> dirs;
    private List<OurFile> files; // like Files

    public  Dir() {

        this.dirs = new ArrayList<>();
        this.files = new ArrayList<>();

        this.content = "";
        this.hashcode = getHashCode(content);
    }

    public void addDir(Dir dir) {

        dirs.add(dir);
        content += getType() + dir.getHashCode() + "\n";
        hashcode = getHashCode(content);
    }
    public void addFile(OurFile file) {

        files.add(file);
        content += blob.getType()+ file.getHashCode() + "\n";
        hashcode = getHashCode(content);
    }

    public List<Dir> getDirs() {
        return dirs;
    }

    public List<OurFile> getFiles() {
        return files;
    }

    public boolean contentDir(String dirHashCode) {

        for(Dir dir: dirs)
        {
            if(dir.getHashCode().equals(dirHashCode))
            {
                return  true;
            }
        }
        for(Dir dir: dirs)
        {
            if(dir.contentDir(dirHashCode))
            {
                return true;
            }
        }
        return false;
    }

    public Dir getDir(String dirHashCode) {

        for(Dir d: dirs)
        {
            if(d.getHashCode().equals(dirHashCode)) {
                return d;
            }
        }
        for(Dir d: dirs)
        {
            if(d.contentDir(dirHashCode))
            {
                return d.getDir(dirHashCode);
            }
        }
        return null;
    }
    public boolean contentElement(String elementHashCode) {

        for(Type t: dirs)
        {
            if(t.getHashCode().equals(elementHashCode))
            {
                return true;
            }
        }
        for(Type t: files)
        {
            if(t.getHashCode().equals(elementHashCode))
            {
                return true;
            }
        }
        for(Dir d: dirs)
        {
            if(d.contentElement(elementHashCode))
            {
                return  true;
            }
        }
        return  false;
    }
    public Type getElement(String elementHashCode) {

        for(Type t: dirs)
        {
            if(t.getHashCode().equals(elementHashCode))
            {
                return t;
            }
        }
        for(Type t: files)
        {
            if(t.getHashCode().equals(elementHashCode))
            {
                return t;
            }
        }
        for(Dir d: dirs)
        {
            if(d.contentElement(elementHashCode))
            {
                return d.getElement(elementHashCode);
            }
        }
        return null;
    }

    public String getType() {

        return  "tree";
    }

    public String getContent() {
        return content;
    }

    public void addMap(Map map) {

        map.put(this.getHashCode(), this.getContent());
        for(Dir d: dirs)
        {
            d.addMap(map);
        }
        for(OurFile f: files)
        {
            f.addMap(map);
        }
    }

    public void elemOutPut() {

        System.out.println("dir\t");
        System.out.println(hashcode);
        System.out.println();

        for(Dir d: dirs)
        {
            d.elemOutPut();
        }
        for(OurFile f: files)
        {
            f.elemOutPut();
        }
    }

    @Override
    public String getHashCode() {
        return hashcode;
    }

    @Override
    public String getHashCode(String key) {
        return hashcode = DigestUtils.sha1Hex(key);
    }
}
