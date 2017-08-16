package com.task;

import com.task.model.*;

public class Main {
    public static void main(String[] args) throws Exception {
        GitStruct gitStruct = Main.prepareData();
        System.out.println(gitStruct.gitLog());
        System.out.println("--------------------------------------------");
        System.out.println(gitStruct.gitLogAll());
    }

    private static GitStruct prepareData() throws Exception {
        GitStruct data = new GitStruct();
        Commit commit = new Commit(null, "Init commit");
        Tree tree = new Tree(commit);
        Blob blob = new Blob(tree);
        blob.setContent("test blob");
        tree.addContent(blob);
        commit.addContent(tree);
        data.addCommit(commit);

        Tree innerTree = new Tree(tree);
        tree.addContent(innerTree);

        Tree commitInnerTree = new Tree(commit);
        Blob innerCommitTreeBlob = new Blob(commitInnerTree);
        commitInnerTree.addContent(blob);
        innerCommitTreeBlob.setContent("another test blob");

        Tree secondInner = new Tree(commitInnerTree);

        Blob commitBlob = new Blob(commit);
        commit.addContent(commitBlob);
        commitBlob.setContent("it's under the commit");
        return data;
    }
}
