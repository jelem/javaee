package com.task.model;

import java.util.HashMap;
import java.util.Map;

public class GitStruct {

    private Map<String, Commit> commitMap;

    private String lastKey;

    public GitStruct() {
        commitMap = new HashMap<>();
        lastKey = null;
    }

    
}
