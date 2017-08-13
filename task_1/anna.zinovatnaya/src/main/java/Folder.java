package main.java;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Folder implements Element{

    private String hash;

    private List<Folder> folders;
    private List<File> files;

    private String content;

    public Folder() {
        this.folders = new ArrayList<>();
        this.files = new ArrayList<>();

        this.content = "";

        this.hash = DigestUtils.sha1Hex(content);
    }



    public void addFolder(Folder folder){
        folders.add(folder);
        content += "tree " + folder.getHashCode() + "\n";
        hash = DigestUtils.sha1Hex(content);
    }



    public void addFile(File file){
        files.add(file);
        content += "blob " + file.getHashCode() + "\n";
        hash = DigestUtils.sha1Hex(content);
    }



    public String getContent(){
        return content;
    }



    public String getHashCode(){
        return hash;
    }



    public String getType(){
        return "tree";
    }



    public List<File> getFiles() {
        return files;
    }



    public List<Folder> getFolders() {
        return folders;
    }



    public boolean containsFolder(String folderHash){

        for(Folder f: folders){
            if(f.getHashCode().equals(folderHash)){
                return true;
            }
        }

        for(Folder f: folders){
            if(f.containsFolder(folderHash)){
                return true;
            }
        }

        return false;
    }



    public Folder getFolder(String folderHash){

        for(Folder f: folders){
            if(f.getHashCode().equals(folderHash)){
                return f;
            }
        }

        for(Folder f: folders){
            if(f.containsFolder(folderHash)){
                return f.getFolder(folderHash);
            }
        }

        return null;
    }



    public boolean containsElement(String elementHash){

        for(Element f: folders){
            if(f.getHashCode().equals(elementHash)){
                return true;
            }
        }

        for(Element f: files){
            if(f.getHashCode().equals(elementHash)){
                return true;
            }
        }

        for(Folder f: folders){
            if(f.containsElement(elementHash)){
                return true;
            }
        }

        return false;
    }



    public Element getElement(String elementHash){

        for(Element f: folders){
            if(f.getHashCode().equals(elementHash)){
                return f;
            }
        }

        for(Element f: files){
            if(f.getHashCode().equals(elementHash)){
                return f;
            }
        }


        for(Folder f: folders){
            if(f.containsElement(elementHash)){
                return f.getElement(elementHash);
            }
        }

        return null;
    }



    public void addToMap(Map map){

        map.put(this.getHashCode(), this.getContent());

        for(Folder f: folders){
            f.addToMap(map);
        }

        for(File f: files){
            f.addToMap(map);
        }
    }



    public void printElements(){

        System.out.print("folder\t");
        System.out.println(hash);
        System.out.println();

        for(Folder f: folders){
            f.printElements();
        }

        for(File f: files){
            f.printElement();
        }
    }
}

