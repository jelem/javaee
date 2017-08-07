package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

import model.FileData;

@Service
public class VersionControlService {

  private File watchedFolder;
  private File VCSobjectsFolder;
  private final String VCSobjectsFolderName = "objects";
  private File VCSlogsFolder;
  private final String VCSlogsFolderName = "logs";
  private File VCSrootFolder;
  private final String VCSrootFolderName = ".VCS";
  private File VCSconfigFolder;
  private final String VCSconfigFolderName = "config";
  @Autowired
  private StructureService structureService;

  public void commit(String commitName) {
    Set<FileData> fileDataSet = structureService.getProjectStructure();
    for (FileData fileData : fileDataSet) {
      System.out.println(Paths.get(fileData.getParent(), fileData.getName()));
    }
  }

  public void log() {

  }

  public Object rebase() {

    return null;
  }

  public void init(String pathToWatch) throws IOException {
    this.watchedFolder = new File(pathToWatch);
    this.VCSrootFolder = new File(watchedFolder, VCSrootFolderName);

    structureService.setRootFile(watchedFolder);
    if (!VCSrootFolder.exists()) {
      Path VCSrootPath = Files.createDirectory(Paths.get(pathToWatch, VCSrootFolderName));
      Files.createDirectory(VCSrootPath.resolve(VCSobjectsFolderName));
      Files.createDirectory(VCSrootPath.resolve(VCSlogsFolderName));
      Files.createDirectory(VCSrootPath.resolve(VCSconfigFolderName));
    }
  }
}
