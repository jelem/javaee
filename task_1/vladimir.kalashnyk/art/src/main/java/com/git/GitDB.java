package com.git;

import com.git.gitObjects.Blob;
import com.git.gitObjects.Commit;
import com.git.gitObjects.GitObject;
import com.git.gitObjects.Tree;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by User on 13.08.2017.
 */
public class GitDB {
    private Map<String, GitObject> gitDB = new HashMap<>();
    private Map<String, GitObject> stageDB = new HashMap<>();
    private String lastCommit;
    private String rootDirectory;

    private String calculateKey(GitObject gitObject) {
        StringBuilder hashObject = new StringBuilder();
        for (String key : gitObject.getLinks()) {
            hashObject.append(key);
        }
        return DigestUtils.sha1Hex(hashObject.toString());
    }

    public void addBlobIntoStageDB(Blob blob, Tree... trees) {
        String blobHash = DigestUtils.sha1Hex(blob.getText());

        if (!gitDB.containsKey(blobHash) && !stageDB.containsKey(blobHash)) {
            stageDB.put(blobHash, blob);
        }
        if (!trees[trees.length - 1].getLinks().contains(blobHash)) {
            removeOldKeys(trees);
            linkWithNewKey(trees, blobHash);
        }
    }

    private void linkWithNewKey(Tree[] trees, String hash) {
        trees[trees.length - 1].getLinks().add(hash);
        for (int i = trees.length - 1; i >= 0; i--) {
            hash = calculateKey(trees[i]);
            stageDB.put(hash, trees[i]);
            if (i != 0) {
                trees[i - 1].getLinks().add(hash);
            } else {
                rootDirectory = hash;
            }
        }
    }

    private void removeOldKeys(Tree[] trees) {
        ArrayList<String> oldLinks = new ArrayList<>();
        String key;
        for (int i = trees.length - 1; i >= 0; i--) {
            key = calculateKey(trees[i]);
            stageDB.remove(key);
            oldLinks.add(key);
        }
        for (int i = trees.length - 2; i >= 0; i--) {
            trees[i].getLinks().remove(oldLinks.get(trees.length - i - 2));
        }
    }

    public void commit() {
        if (!stageDB.isEmpty()) {
            linkNewLastCommit();
            gitDB.putAll(stageDB);

            stageDB = new HashMap<>();
            rootDirectory = null;
        }
    }

    private void linkNewLastCommit() {
        Commit commit = new Commit();
        commit.addLink(rootDirectory);
        if (lastCommit != null) {
            commit.addLink(lastCommit);
        }
        lastCommit = calculateKey(commit);
        stageDB.put(lastCommit, commit);
    }

    public void log() {
        for (Map.Entry<String, GitObject> e : gitDB.entrySet()) {
            if (e.getValue() instanceof Commit) {
                System.out.println(e.getKey() + "\t" + e.getValue().getClass().getSimpleName());
            }
        }
    }

    public void catFile(String key) {
        if (gitDB.containsKey(key)) {
            GitObject gitObject = gitDB.get(key);
            if (gitObject instanceof Blob) {
                System.out.println(((Blob) gitObject).getText());
            } else {
                for (String k : gitObject.getLinks()) {
                    System.out.println(k + "\t" + gitDB.get(k).getClass().getSimpleName());

                }
            }
        }
    }

    public void printCommit(String commitHashObject) {
        GitObject gitObject = gitDB.get(commitHashObject);
        if (gitObject instanceof Commit) {
            ArrayList<String> links = gitObject.getLinks();
            ArrayList<String> res = new ArrayList<>();

            listCommit(links, res);
            for (String s : res) {
                System.out.println(s);
            }
        }
    }

    private void listCommit(ArrayList<String> links, ArrayList<String> res) {
        for (String link : links) {
            GitObject gitO = gitDB.get(link);

            if (gitO instanceof Tree) {
                res.add(link + "\t" + gitO.getClass().getSimpleName());
                listCommit(gitO.getLinks(), res);
            } else {
                res.add(link + "\t" + gitO.getClass().getSimpleName());
            }
        }
    }


    public void printStageDb() {
        stageDB.forEach((key, value) -> System.out.println(key + "\t" + value.getClass().getSimpleName()));
    }

    public void printGitDb() {
        gitDB.forEach((key, value) -> System.out.println(key + "\t" + value.getClass().getSimpleName()));
    }
}
