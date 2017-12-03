package entering.MethodsForMenu;

import classes.Commit.Commit;
import classes.Dir.Dir;
import classes.OutFile.OurFile;
import classes.Type.Type;
import entering.HelpMenu.HelpMenu;

import java.util.Map;

public class MethodsForMenu extends HelpMenu {

    public MethodsForMenu() {}

    public void yourName() {

        String temp = scanner.nextLine();
        this.yourName = temp;
    }

    public void yourEmail() {

        String temp = scanner.nextLine();
        this.yourEmail = temp;
    }

    public void addFile() {
        System.out.println("Input Name of File: ");
        String temp = scanner.nextLine();
        Type file = new OurFile(temp);
        this.getCurrentDirV().addFile((OurFile)file);
    }

    public void addDir() {
        Type tree = new Dir();
        this.getCurrentDirV().addDir((Dir) tree);
        System.out.println("New Dir created");
    }

    public void getCurrentDir() {
        System.out.println("Current dir " + getCurrentDirV().getHashCode());
    }

    public void changeDir() {
        System.out.println("Input dir hashcode: ");
        String input = scanner.nextLine();
        boolean dirExist = getModifiedDir().contentDir(input);

        if(dirExist == true)
        {
            currentDirV = getModifiedDir().getDir(input);
            System.out.println("Current Dir was changed to " + input);
        }
        else
        {
            System.out.println("No such dir");
        }
    }

    public void changeDirToModified() {
        currentDirV = getModifiedDir();
        System.out.println("Current dir was changed to modified dir");
    }

    public void commit() {
        System.out.println("Input commit message: ");

        String message = scanner.nextLine();

        Commit commit;

        if(getCommits().isEmpty())
        {
            commit = new Commit(getModifiedDir().getHashCode(), message);
        }
        else
        {
            commit = new Commit(getModifiedDir().getHashCode(),getCommits().get(getCommits().size() - 1).getHashCode(), message);
        }

        getCommits().add(commit);

        commit.addMap(this.getCommitedFilesMap());

        getModifiedDir().addMap(this.getCommitedFilesMap());
    }

    public String getTypeC() {
        return "commit";
    }

    public void log() {
        for(Commit c: getCommits())
        {
            System.out.println(getTypeC() + c.getHashCode());
            System.out.println(c.getContent());
            System.out.println();
        }
    }

    public void outPutElement() {
        System.out.println("Input element hash code: ");

        String input = scanner.nextLine();

        if(getModifiedDir().getHashCode().equals(input))
        {
            System.out.println(getModifiedDir().getContent());
            return;
        }

        if(getModifiedDir().contentElement(input))
        {
            System.out.println(getModifiedDir().getElement(input).getContent());
        }
        else
        {
            System.out.println("No such element");
        }
    }

    public void outPutCommittedElement() {
        System.out.println("Input element hash code: ");

        String input = scanner.nextLine();

        for(Map.Entry<String , String> entry : getCommitedFilesMap().entrySet())
        {
            if(entry.getKey().equals(input))
            {
                System.out.println(entry.getValue());
                return;
            }
        }
        System.out.println("No such element");
    }

    public void outPutElements() {
        getModifiedDir().elemOutPut();
    }

    public void outPutCommittedElements() {
        for(Map.Entry<String,String> entry: getCommitedFilesMap().entrySet())
        {
            System.out.println("Element: ");
            System.out.println(entry.getKey() + "\n" + entry.getValue());
            System.out.println();
        }
    }
}
