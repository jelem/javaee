package com;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.CreateDB.getDBURL;

/**
 * Created by Марина on 12.08.2017.
 */
public class ControlVersions extends RWParent {

    private static String versionFileURL = getDBURL() + "\\VersionFile";
    private Version version;

    public ControlVersions() {
        super(versionFileURL);
        version = new Version();
    }

    @Override
    protected void read() throws IOException {
        super.read();
    }

    public static List<Version> readVersionFile() throws IOException {
        List<Version> versionList = new ArrayList<>();
        if (Files.exists(Paths.get(versionFileURL))) {
            byte[] bytes = Files.readAllBytes(Paths.get(versionFileURL));
            if (bytes.length > 0){
                String versionContent = new String(bytes, Charset.defaultCharset());
                String[] string = versionContent.split("\\*");
                for (String str : string) {
                    String[] strings = str.split("\\[");
                    Integer commit = Integer.valueOf(strings[0]);
                    String[] sha1Str = strings[1].replaceAll("]", "").split(", ");
                    versionList.add(new Version(commit, Arrays.asList(sha1Str)));
                }
            } else {
                versionList = null;
            }
        }
        return versionList;
    }

    public static String getVersionFileURL() {
        return versionFileURL;
    }

    public Version getVersion() throws IOException {
        version.setCommit(versionsCounter());
        version.setSha1List(sha1List);
        return version;
    }

    private int versionsCounter() throws IOException {
        List<Version> versionList = readVersionFile();
        int max = 0;
        if (versionList != null){
            for (Version v : versionList) {
                if (v.getCommit() >= max) {
                    max = v.getCommit() + 1;
                }
            }
        }
        return max;
    }
}
