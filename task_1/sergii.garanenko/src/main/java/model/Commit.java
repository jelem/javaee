package model;

import java.util.Objects;

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

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Commit commit = (Commit) obj;
    return Objects.equals(commitName, commit.commitName)
        && Objects.equals(commitSHA1, commit.commitSHA1);
  }

  @Override
  public int hashCode() {
    return Objects.hash(commitName, commitSHA1);
  }
}
