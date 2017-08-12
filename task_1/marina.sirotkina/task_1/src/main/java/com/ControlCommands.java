package com;

import java.io.IOException;
import java.nio.file.StandardOpenOption;

import static com.ControlVersions.getVersionFileURL;

/**
 * Created by Марина on 12.08.2017.
 */
public class ControlCommands {

    private CreateDB createDB;
    private ReadData readData;
    private SaveData saveData;
    private ControlVersions controlVersions;
    private ReadDB readDB;

    public ControlCommands(String path) {
        createDB = new CreateDB(path);
        readData = new ReadData(path);
        saveData = new SaveData(path);
        controlVersions = new ControlVersions();
        readDB = new ReadDB();
    }

    public void add() {
        try {
            readData.read();
        } catch (IOException e) {
            System.out.println("Something odd happened in add() (:");
            e.printStackTrace();
        }
        readData.printData();
    }

    public void catfile(String sha1) {
        try {
            readDB.readDBBySha1(sha1);
        } catch (IOException e) {
            System.out.println("Something odd happened in catfile() (:");
            e.printStackTrace();
        }
    }

    public void pull() {
        try {
            readDB.read();
        } catch (IOException e) {
            System.out.println("Something odd happened in pull() (:");
            e.printStackTrace();
        }
        readDB.printData();
    }

    public void commit() {
        try {
            readData.read();
            controlVersions.write(getVersionFileURL(),
                    controlVersions.getVersion().toString() + "*",
                    StandardOpenOption.APPEND);
            saveData.saveData();
        } catch (IOException e) {
            System.out.println("Something odd happened in commit() (:");
            e.printStackTrace();
        }
        System.out.println("Ok!");
    }

    public void init() {
        try {
            createDB.createDBDirectory();
            createDB.createVersionFile();
        } catch (IOException e) {
            System.out.println("Something odd happened in init() (:");
            e.printStackTrace();
        }
    }
}
