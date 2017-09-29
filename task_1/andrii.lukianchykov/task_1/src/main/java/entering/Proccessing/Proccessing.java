package entering.Proccessing;

import classes.Commit.Commit;
import classes.Dir.Dir;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Proccessing {

    protected Scanner scanner = new Scanner(System.in);
    protected Dir currentDirV;
    protected Dir modifiedDir;
    protected String key = null;
    protected Map<String, String> commitedFilesMap;
    protected ArrayList<Commit> commits;
    protected String yourName;
    protected String yourEmail;

    public Proccessing() {

        this.currentDirV = new Dir();
        this.modifiedDir = currentDirV;
        this.commitedFilesMap = new HashMap<>();
        this.commits = new ArrayList<>();
        this.yourName = getYourName();
        this.yourEmail = getYourEmail();
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Dir getCurrentDirV() {
        return currentDirV;
    }

    public void setCurrentDirV(Dir currentDirV) {
        this.currentDirV = currentDirV;
    }

    public Dir getModifiedDir() {
        return modifiedDir;
    }

    public void setModifiedDir(Dir modifiedDir) {
        this.modifiedDir = modifiedDir;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, String> getCommitedFilesMap() {
        return commitedFilesMap;
    }

    public void setCommitedFilesMap(Map<String, String> commitedFilesMap) {
        this.commitedFilesMap = commitedFilesMap;
    }

    public ArrayList<Commit> getCommits() {
        return commits;
    }

    public void setCommits(ArrayList<Commit> commits) {
        this.commits = commits;
    }

    public String getYourName() {
        return yourName;
    }

    public void setYourName(String yourName) {
        this.yourName = yourName;
    }

    public String getYourEmail() {
        return yourEmail;
    }

    public void setYourEmail(String yourEmail) {
        this.yourEmail = yourEmail;
    }
}
