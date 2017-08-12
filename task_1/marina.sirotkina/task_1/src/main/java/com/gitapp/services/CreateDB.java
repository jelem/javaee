package com.gitapp.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CreateDB {

    private static String DBURL;
    private String directoryPath;

    public CreateDB(String directoryPath) {
        this.directoryPath = directoryPath;
        DBURL = directoryPath + "\\db";
    }

    public static String getDBURL() {
        return DBURL;
    }

    public void createDBDirectory() throws IOException {
        if (!Files.exists(Paths.get(getDBURL()))) {
            Files.createDirectory(Paths.get(getDBURL()));
            System.out.println("DB was created");
        }
    }

    public void createVersionFile() throws IOException {
        if (!Files.exists(Paths.get(getDBURL() + "\\VersionFile"))) {
            Files.createFile(Paths.get(getDBURL() + "\\VersionFile"));
            System.out.println("VersionFile was created");
        }
    }
}
