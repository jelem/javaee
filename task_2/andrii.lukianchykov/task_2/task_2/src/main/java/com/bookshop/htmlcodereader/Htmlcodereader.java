package com.bookshop.htmlcodereader;

import java.net.URL;
import java.util.Scanner;

public class Htmlcodereader {

    public static void encode(String...strings) throws Exception {

        URL u; // web-site "Example"
        Scanner s;

        u = new URL(strings[0]);
        s = new Scanner(u.openStream());

        while(s.hasNext())
        {
            System.out.println(s.nextLine());
        }
    }

}
