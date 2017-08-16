package com.task.utils;

import com.task.model.IElement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileSystemUtility {

    String rootFolder;

    public FileSystemUtility(String rootFolder) {
        this.rootFolder = rootFolder;
        this.checkFolder("");
    }

    public FileSystemUtility() {
        this("results");
    }

    public void save(IElement element) {
        if (element == null) {
            return;
        }
        String hash = element.getHash();
        File folder = this.checkFolder(hash);
        this.saveObject(element, folder.getAbsolutePath().concat("\\").concat(hash));
    }

    private void saveObject(IElement element, String filename) {
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(element);
            oos.flush();
            oos.close();
        } catch (IOException ioExc) {
            ioExc.printStackTrace();
        }
    }

    private File checkFolder(String elementHash) {
        String path = (elementHash.length() > 2) ? rootFolder.concat("\\").concat(elementHash.substring(0, 2)) :
                rootFolder;
        File folder = new File(path);
        if (!Files.exists(Paths.get(path))) {
            folder.mkdir();
        }
        return folder;
    }
}
