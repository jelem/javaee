package com.gitapp.services;

import com.gitapp.RWParent;
import com.gitapp.control.ControlVersions;
import com.gitapp.entity.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ReadDB extends RWParent {
    private static final Logger log = LoggerFactory.getLogger(ReadDB.class);
    private List<Path> paths;

    public ReadDB() {
        super(CreateDB.getDBURL());
    }

    @Override
    protected List<Path> getPaths(String path) throws IOException {
        paths = new ArrayList<>();
        List<Version> versionList = ControlVersions.readVersionFile();
        List<String> sha1 = new ArrayList<>();
        if (!versionList.isEmpty()) {
            Version version = versionList.get(versionList.size() - 1);
            for (String sha : version.getSha1List()) {
                sha1.add(sha.substring(0, 2));
            }
        }
        File file = new File(path);
        paths = path(file, sha1);
        //TODO fix stream
        /*List<Path> paths = Files.walk(Paths.get(path))
                .filter(path1 -> !path1.toString().endsWith("\\VersionFile"))
                .peek(path1 -> System.out.println(path1.toString()))
                .filter(path1 -> {
                    for(String s: sha1){
                        if (path1.toString().startsWith(getDBURL() + "\\" + s)){
                            return true;
                        }
                    }
                    return false;
                })
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());*/
        return paths;
    }

    private List<Path> path(File file, List<String> sha1) {
        File[] files = file.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    for (String s : sha1) {
                        if (f.toPath().startsWith(CreateDB.getDBURL() + "\\" + s)) {
                            path(f, sha1);
                        }
                    }
                } else {
                    if (!f.toPath().endsWith("VersionFile")) {
                        paths.add(f.toPath());
                    }
                }
            }
        }
        return paths;
    }

    public void readDBBySha1(String sha1) throws IOException {
        paths = new ArrayList<>();
        List<String> sha = new ArrayList<>();
        sha.add(sha1.substring(0, 2));
        File file = new File(CreateDB.getDBURL());
        for (Path path : path(file, sha)) {
            byte[] bytes = Files.readAllBytes(path);
            String content = new String(bytes, Charset.defaultCharset());
            log.info(content);
        }
    }
}
