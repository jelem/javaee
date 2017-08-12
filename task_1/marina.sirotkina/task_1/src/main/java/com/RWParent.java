package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Марина on 12.08.2017.
 */
public class RWParent {

    private static final Logger log = LoggerFactory.getLogger(RWParent.class);

    private static String directoryPath;
    protected static List<Data> fileList;
    protected static List<String> sha1List;


    public RWParent(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    private static String toSHA1(byte[] bytes) {
        return DigestUtils.md5DigestAsHex(bytes);
    }

    protected void read() throws IOException {
        fileList = new ArrayList<>();
        sha1List = new ArrayList<>();
        List<Path> pathList = getPaths(directoryPath);
        for (Path path : pathList) {
            byte[] bytes = Files.readAllBytes(path);
            String content = new String(bytes, Charset.defaultCharset());
            String keySHA1 = toSHA1(bytes);
            fileList.add(new Data(keySHA1, content));
            sha1List.add(keySHA1);
        }
    }

    protected void write(String fileURL, String content, OpenOption openOption) {
        BufferedWriter writer = null;
        try {
            writer = Files.newBufferedWriter(Paths.get(fileURL), openOption);
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected List<Path> getPaths(String path) throws IOException {
        return Files.walk(Paths.get(path))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
    }

    protected void printData() {
        for (Data data : fileList) {
            log.info(data.toString());
        }
    }

}
