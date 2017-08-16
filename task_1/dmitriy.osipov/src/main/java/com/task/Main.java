package com.task;

import com.task.model.*;

public class Main {
    public static void main(String[] args) throws Exception {
        GitStruct gitStruct = new GitStruct();
        gitStruct.addCommit(Main.prepareData(1, gitStruct.getLastCommit()));
        gitStruct.addCommit(Main.prepareData(2, gitStruct.getLastCommit()));
        System.out.println(gitStruct.gitLog());
        System.out.println("--------------------------------------------");
        System.out.println(gitStruct.gitLogAll());

        gitStruct.gitSaveCommits();
    }

    private static Commit prepareData(int index, Commit parent) throws Exception {

        Commit c1 = new Commit(parent, "Commit "+ index, "C" + index);
        Tree t1 = new Tree(c1, String.format("T-%d-1", index));
        Blob b1 = new Blob(t1, String.format("B-%d-1", index));
        Tree t2 = new Tree(t1, String.format("T-%d-2", index));
        Blob b3 = new Blob(c1, String.format("B-%d-3", index));
        Tree t3 = new Tree(c1, String.format("T-%d-3", index));
        Blob b2 = new Blob(t3, String.format("B-%d-2", index));
        Tree t4 = new Tree(t3, String.format("T-%d-4", index));

        return c1;
    }
}
