package com;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Марина on 12.08.2017.
 */
public class Main {
    private static ControlCommands controlCommands = new ControlCommands("D:\\git-test");

    public static void main(String[] args) throws IOException {


        //controlCommands.init();
        //controlCommands.commit();
        //controlCommands.pull();
        //controlCommands.add();
        //controlCommands.catfile("3ba3f56b3a0d3c4a97eadc1da557397a");

        init();
    }

    public static void select() {

        System.out.println("Select an action:" + "\n"
                + "add" + "\n"
                + "pull" + "\n"
                + "catfile" + "\n"
                + "commit" + "\n"
                + "exit");
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            command = scanner.nextLine();
            switch (command) {
                case "add":
                    controlCommands.add();
                    break;
                case "pull":
                    controlCommands.pull();
                    break;
                case "catfile":
                    System.out.println("Sha:");
                    String sha = scanner.nextLine();
                    //String wrongLine = scanner.nextLine();
                    controlCommands.catfile(sha);
                case "commit":
                    controlCommands.commit();
                    break;
                case "exit":
                    System.exit(0);
                    break;
            }
        } while (!"exit".equals(command));
    }

    public static void init() {
        System.out.println("Select an action:" + "\n"
                + "init - initialize DB"
                + "exit");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        switch (command) {
            case "init":
                controlCommands.init();
                select();
            case "exit":
                break;
            default: System.exit(0);
        }
    }
}