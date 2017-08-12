package com.gitapp.control;

import com.gitapp.RWParent;
import com.gitapp.entity.Version;
import com.gitapp.services.CreateDB;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlVersions extends RWParent {

    private static String versionFileURL = CreateDB.getDBURL() + "\\VersionFile";
    private Version version;

    public ControlVersions() {
        super(versionFileURL);
        version = new Version();
    }

    public static List<Version> readVersionFile() throws IOException {
        List<Version> versionList = new ArrayList<>();
        if (Files.exists(Paths.get(versionFileURL))) {
            byte[] bytes = Files.readAllBytes(Paths.get(versionFileURL));
            if (bytes.length > 0) {
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

    @Override
    public void read() throws IOException {
        super.read();
    }

    public Version getVersion() throws IOException {
        version.setCommit(versionsCounter());
        version.setSha1List(sha1List);
        return version;
    }

    private int versionsCounter() throws IOException {
        List<Version> versionList = readVersionFile();
        int max = 0;
        if (versionList != null) {
            for (Version v : versionList) {
                if (v.getCommit() >= max) {
                    max = v.getCommit() + 1;
                }
            }
        }
        return max;
    }
}
