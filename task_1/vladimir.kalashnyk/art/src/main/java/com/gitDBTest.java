package com;

import com.git.GitDB;
import com.git.gitObjects.Blob;
import com.git.gitObjects.Tree;

/**
 * Created by User on 13.08.2017.
 */
public class gitDBTest {
    public static void main(String[] args) {
        GitDB gitDB = new GitDB();

        Tree project = new Tree();
        Tree languages = new Tree();

        Blob c = new Blob("I know C++");
        Blob java = new Blob("I know Java");
        Blob python = new Blob("I know Python");
        Blob skills = new Blob("I know Java");
        //===================== commit 1 ===============================================
        // 2 tree 3 blob
        gitDB.addBlobIntoStageDB(c, project, languages);
        gitDB.addBlobIntoStageDB(java, project, languages);
        gitDB.addBlobIntoStageDB(python, project, languages);
        gitDB.addBlobIntoStageDB(skills, project);

        System.out.println("log:");
        gitDB.commit();
        gitDB.log();
        System.out.println("commit 1:");
        gitDB.printCommit("6d3234ad4f7b3a897bafd059ab76258964c63fe3");
        System.out.println("-----------------------------");
        //===================== commit 2 ===============================================
        // 4 tree 2 links of blob
        Tree project_1 = new Tree();
        Tree languages_1 = new Tree();
        Tree firstFolder = new Tree();
        Tree secondFolder = new Tree();
        gitDB.addBlobIntoStageDB(java, project_1, languages_1, firstFolder);
        gitDB.addBlobIntoStageDB(c, project_1, languages_1, firstFolder, secondFolder);

        gitDB.commit();
        System.out.println("log:");
        gitDB.log();
        System.out.println("commit 1:");
        gitDB.printCommit("6d3234ad4f7b3a897bafd059ab76258964c63fe3");
        System.out.println("commit 2:");
        gitDB.printCommit("a89e6b5eb4a922d6c94b8960a3c7a31adbf0cf66");

        System.out.println("---------- all data ---------");
        gitDB.printGitDb();
        System.out.println("-----------------------------");
    }
}
