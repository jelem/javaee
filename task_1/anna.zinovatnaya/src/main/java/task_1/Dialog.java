package main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

public class Dialog {

  /*
  Files and Folders represent real files that are under git. If two files are equal they are still stored two times
   */
  private Folder currentFolder;
  private Folder rootFolder; // modified area

  /*
  Map stores committed elements (files, folders and commits). If two elements are equal, they are stored only once.
   */
  private Map<String, String> commitedFilesMap; // committed area

  /*
  Vector stores history of commits
  */
  private Vector<Commit> commits;



  public Dialog() {
    this.currentFolder = new Folder();
    this.rootFolder = currentFolder;
    this.commitedFilesMap = new HashMap<>();
    this.commits = new Vector<>();
  }



  void start() {

    String option = "0";

    Scanner scanner = new Scanner(System.in, "UTF-8");

    System.out.println("Commands:");
    System.out.println("");
    System.out.println("Type 'new file' to create new file in a current folder");
    System.out.println("Type 'new folder' to create new folder in a current folder");

    System.out.println("Type 'current folder' to get current folder hash code");
    System.out.println("Type 'root folder' to get root folder hash code");
    System.out.println("Type 'change folder' to go to another folder");

    System.out.println("Type 'commit' to commit files");

    System.out.println("Type 'log' to see history of commits");

    System.out.println("Type 'read not committed element' to read certain element");
    System.out.println("Type 'read committed element' to read certain committed element");

    System.out.println("Type 'read not committed elements' to read all not committed elements");
    System.out.println("Type 'read committed elements' to read all committed elements");

    System.out.println("Type 'exit' to exit");


    boolean endOfDialog = false;

    while (endOfDialog == false) {

      System.out.print("> ");

      while (!scanner.hasNextLine()) {}

      option = scanner.nextLine();

      switch (option) {
        case "new file":
          this.addFile();
          break;
        case "new folder":
          this.addFolder();
          break;
        case "current folder":
          this.getCurrentFolder();
          break;
        case "change folder":
          this.changeFolder();
          break;
        case "root folder":
          this.changeFolderToRoot();
          break;
        case "commit":
          this.commit();
          break;
        case "log":
          this.log();
          break;
        case "read not committed element":
          this.readElement();
          break;
        case "read committed element":
          this.readCommittedElement();
          break;
        case "read not committed elements":
          this.readElements();
          break;
        case "read committed elements":
          this.readCommittedElements();
          break;
        case "exit":
          endOfDialog = true;
          break;
        default:
          System.out.println("Incorrect input!");
      }
    }
  }



  void addFile() {

    Scanner scanner = new Scanner(System.in, "UTF-8");
    System.out.println("Type your text:");

    String temp = scanner.nextLine();

    Element file = new File(temp);

    this.currentFolder.addFile((File) file);
  }



  void addFolder() {

    Element tree = new Folder();

    this.currentFolder.addFolder((Folder) tree);

    System.out.println("New folder created");
  }



  void getCurrentFolder() {
    System.out.println("Current folder " + currentFolder.getHashCode());
  }



  void changeFolder() {
    Scanner scanner = new Scanner(System.in, "UTF-8");
    System.out.println("Type folder hash code:");
    String input = scanner.nextLine();


    boolean folderExists = rootFolder.containsFolder(input);

    if (folderExists) {
      currentFolder = rootFolder.getFolder(input);
      System.out.println("Current folder was changed to " + input);
    } else {
      System.out.println("No such folder");
    }

  }



  void changeFolderToRoot() {

    currentFolder = rootFolder;
    System.out.println("Current folder was changed to root folder");

  }



  void commit() {
    Commit commit;

    Scanner scanner = new Scanner(System.in, "UTF-8");
    System.out.println("Type commit message:");
    String message = scanner.nextLine();


    if (commits.isEmpty()) {
      commit = new Commit(rootFolder.getHashCode(), message);
    } else {
      commit = new Commit(rootFolder.getHashCode(), commits.get(commits.size() - 1).getHashCode(), message);
    }

    commits.add(commit);

    commit.addToMap(this.commitedFilesMap); // adding commit to committed area

    rootFolder.addToMap(this.commitedFilesMap); // adding all files from modified area to committed area

  }



  void log() {

    for (Commit c: commits) {
      System.out.println("commit " + c.getHashCode());
      System.out.println(c.getContent());
      System.out.println();
    }
  }



  void readElement() {
    Scanner scanner = new Scanner(System.in, "UTF-8");

    System.out.println("Type element hash code:");

    String input = scanner.nextLine();

    if (rootFolder.getHashCode().equals(input)) {
      System.out.println(rootFolder.getContent());
      return;
    }

    if (rootFolder.containsElement(input)) {
      System.out.println(rootFolder.getElement(input).getContent());
    } else {
      System.out.println("No such element");
    }
  }



  void readCommittedElement() {
    Scanner scanner = new Scanner(System.in, "UTF-8");

    System.out.println("Type element hash code:");

    String input = scanner.nextLine();

    for (Map.Entry<String, String> entry: commitedFilesMap.entrySet()) {

      if (entry.getKey().equals(input)) {
        System.out.println(entry.getValue());
        return;
      }
    }

    System.out.println("No such element");
  }



  void readElements() {
    rootFolder.printElements();
  }



  void readCommittedElements() {

    for (Map.Entry<String, String> entry: commitedFilesMap.entrySet()) {

      System.out.println("Element:");
      System.out.println(entry.getKey() + "\n" + entry.getValue());
      System.out.println();

    }
  }
}

