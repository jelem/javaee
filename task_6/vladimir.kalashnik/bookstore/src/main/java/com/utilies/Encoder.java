package com.utilies;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Base64;

public class Encoder {
    private static final Encoder INSTANCE = new Encoder();

    private Encoder() {

    }

    public static String encoding(String pictureName) {
        return INSTANCE.encodePicture(pictureName);
    }

    private String encodePicture(String pictureName) {
        String picture = null;
        try (InputStream input =
                     new BufferedInputStream(getClass().getResourceAsStream("/picture/" + pictureName))) {
            int b;
            byte[] bytes = new byte[input.available()];
            for (int i = 0; (b = input.read()) != -1; i++) {
                bytes[i] = (byte) b;
            }

            bytes = Base64.getEncoder().encode(bytes);
            picture = new String(bytes);

        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return picture;
    }
}
