package model;

public class Commit {

  private String commitName;
  private String commitSHA1;

  public Commit(String commitSHA1, String commitName) {
    this.commitName = commitName;
    this.commitSHA1 = commitSHA1;
  }

  public String getCommitName() {
    return commitName;
  }

  public String getCommitSHA1() {
    return commitSHA1;
  }
}
