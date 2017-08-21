package com.task.model;

import com.task.utils.FileSystemUtility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GitStruct {

  private Map<String, Commit> commitMap;
  private String lastKey;

  public GitStruct() {
    commitMap = new HashMap<>();
    lastKey = null;
  }

  public void addCommit(Commit newCommit) {
    this.lastKey = newCommit.getHash();
    this.commitMap.put(lastKey, newCommit);
  }

  public Commit getLastCommit() {
    return this.commitMap.get(lastKey);
  }

  public String gitLog() {
    StringBuilder builder = new StringBuilder();
    walkOnCommits(builder, new HashSet<Commit>(), commitMap.get(lastKey));
    return builder.toString();
  }

  public String gitLogAll() {
    Set<ElementLead> walked = new HashSet<>();
    StringBuilder builder = new StringBuilder();
    getCommitsData(builder, walked, this.commitMap.get(lastKey));

    return builder.toString();
  }

  public void gitSaveCommits() {
    FileSystemUtility fileSystemUtility = new FileSystemUtility();
    for (Commit commit : commitMap.values()) {
      fileSystemUtility.save(commit);
    }
  }

  public void gitSaveToDiffFiles() {
    FileSystemUtility fileSystemUtility = new FileSystemUtility("resultsDF");
    Set<Element> elements = new HashSet<>();
    for (Commit commit : this.commitMap.values()) {
      commit.getElements(elements, commit);
    }
    for (Element element : elements) {
      fileSystemUtility.save(element);
    }
  }

  private void walkOnCommits(StringBuilder builder, Set<Commit> walked, Commit lastCommit) {
    if (lastCommit == null || walked.contains(lastCommit)) {
      return;
    }
    walked.add(lastCommit);
    builder.append(String.format("%s  -  %s%n", lastCommit.getHash(), lastCommit.getMessage()));
    for (Commit parent : lastCommit.getParents()) {
      walkOnCommits(builder, walked, parent);
    }
  }

  private void getCommitsData(StringBuilder info, Set<ElementLead> walked, Commit lastCommit) {
    if (walked.contains(lastCommit)) {
      return;
    }
    walked.add(lastCommit);
    Set<ElementLead> walkedInner = new HashSet<>();
    lastCommit.showContent(info, walkedInner, lastCommit);
    info.append("\n--------------------------------\n");
    walked.addAll(walkedInner);
    for (Commit parent : lastCommit.getParents()) {
      getCommitsData(info, walked, parent);
    }
    info.append("\n--------------------------------\n");
  }
}
