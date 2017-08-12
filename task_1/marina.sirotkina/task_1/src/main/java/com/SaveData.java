package com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by Марина on 12.08.2017.
 */
public class SaveData extends RWParent {

    private String directoryPath;

    public SaveData(String directoryPath) {
        super(directoryPath + "\\db");
        this.directoryPath = directoryPath;
    }

    public void saveData() throws IOException {
        for (Data data : fileList) {
            createDirectory(data);
            createFile(data);
            write(getFileURL(data), data.getContent(), StandardOpenOption.WRITE);
        }
    }

    private String getFileURL(Data data) {
        return directoryPath + "\\db\\" + parseDirTitle(data.getKeySHA1()) + "\\"
                + parseFileName(data.getKeySHA1());
    }

    private void createFile(Data data) throws IOException {
        String fileURL = directoryPath + "\\db\\" + parseDirTitle(data.getKeySHA1()) + "\\"
                + parseFileName(data.getKeySHA1());
        if (!Files.exists(Paths.get(fileURL))) {
            Files.createFile(Paths.get(fileURL));
        }
    }

    private void createDirectory(Data data) throws IOException {
        String dirURL = directoryPath + "\\db\\" + parseDirTitle(data.getKeySHA1());
        if (!Files.exists(Paths.get(dirURL))) {
            Files.createDirectory(Paths.get(dirURL));
        }
    }

    private String parseDirTitle(String sha1) {
        return sha1.substring(0, 2);
    }

    private String parseFileName(String sha1) {
        return sha1.substring(2);
    }

}
