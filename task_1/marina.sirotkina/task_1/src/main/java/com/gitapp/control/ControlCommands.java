package com.gitapp.control;

import com.gitapp.services.CreateDB;
import com.gitapp.services.ReadDB;
import com.gitapp.services.ReadData;
import com.gitapp.services.SaveData;

import java.io.IOException;
import java.nio.file.StandardOpenOption;

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
            controlVersions.write(ControlVersions.getVersionFileURL(),
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
