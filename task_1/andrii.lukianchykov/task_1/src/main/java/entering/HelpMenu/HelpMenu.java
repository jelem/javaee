package entering.HelpMenu;

import entering.MethodsForMenu.MethodsForMenu;
import entering.Proccessing.Proccessing;

import java.util.Scanner;

public class HelpMenu extends Proccessing {


    public HelpMenu() {}

    public void showMenuCommands() {

        System.out.println("Commands:" +"\n");


        System.out.println("Input 'Your Name' git --global user.name 'Your Name'");
        System.out.println("Input 'Your Email' git --global user.email 'Your Email'");
        System.out.println("Input 'new file' to create New file in a current dir");
        System.out.println("Input 'new dir' to create New folder in a current dir");
        System.out.println("Input 'current dir' to get current dir hash code");
        System.out.println("Input 'modified dir' to get modified dir hash code");
        System.out.println("Input 'change dir' to go to another dir");
        System.out.println("Input 'commit' to commit files");
        System.out.println("Input 'log' to see history of commits");
        System.out.println("Input 'read not committed element' to read certain element");
        System.out.println("Input 'read committed element' to read certain committed element");
        System.out.println("Input 'read not committed elements' to read all not committed elements");
        System.out.println("Input 'read committed elements' to read all committed elements");

        System.out.println("Type 'exit' to exit");
    }

    public void switching(String key) {

        MethodsForMenu methodsForMenu = new MethodsForMenu();
        this.key = key;

        switch (key) {
            case "Your Name":
                methodsForMenu.yourName();
                break;
            case "Your Email":
                methodsForMenu.yourEmail();
                break;
            case "new file":
                methodsForMenu.addFile();
                break;
            case "new dir":
                methodsForMenu.addDir();
                break;
            case "current dir":
                methodsForMenu.getCurrentDir();
                break;
            case "change Dir":
                methodsForMenu.changeDir();
                break;
            case "modified dir":
                methodsForMenu.changeDirToModified();
                break;
            case "commit":
                methodsForMenu.commit();
                break;
            case "log":
                methodsForMenu.log();
                break;
            case "read not committed element":
                methodsForMenu.outPutElement();
                break;
            case "read committed element":
                methodsForMenu.outPutCommittedElement();
                break;
            case "read not committed elements":
                methodsForMenu.outPutElements();
                break;
            case "read committed elements":
                methodsForMenu.outPutCommittedElements();
                break;
            case "exit":
                return;
            default:
                System.out.println("Incorrect input!");
        }
    }

    public void go() {

        showMenuCommands();

        Scanner scanner = new Scanner(System.in);

        while(true)
        {
            System.out.println(">>> ");
            while(!scanner.hasNextLine())
            {
               key = scanner.nextLine();
                switching(key);
            }
        }
    }

    public String getKey() {
        return key;
    }

}
