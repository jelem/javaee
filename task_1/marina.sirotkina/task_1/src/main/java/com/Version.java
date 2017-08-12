package com;

import java.util.List;

/**
 * Created by Марина on 12.08.2017.
 */
public class Version {
    List<String> sha1List;
    private Integer commit;

    public Version() {
    }

    public Version(Integer commit, List<String> sha1List) {
        this.commit = commit;
        this.sha1List = sha1List;
    }

    public Integer getCommit() {
        return commit;
    }

    public void setCommit(Integer commit) {
        this.commit = commit;
    }

    public List<String> getSha1List() {
        return sha1List;
    }

    public void setSha1List(List<String> sha1List) {
        this.sha1List = sha1List;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(commit);
        sb.append(sha1List);
        return sb.toString();
    }
}