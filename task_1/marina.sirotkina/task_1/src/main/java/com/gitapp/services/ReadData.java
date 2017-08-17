package com.gitapp.services;

import com.gitapp.RWParent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class ReadData extends RWParent {

    private String directoryPath;

    public ReadData(String directoryPath) {
        super(directoryPath);
        this.directoryPath = directoryPath;
    }

    @Override
    public List<Path> getPaths(String path) throws IOException {
        return Files.walk(Paths.get(directoryPath))
                .filter(path1 -> !path1.toString().startsWith(directoryPath + "\\db"))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
    }

}
