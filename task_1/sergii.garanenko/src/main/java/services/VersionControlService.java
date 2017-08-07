package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import model.FileData;
import utils.ObjectsRepository;

@Service
public class VersionControlService {

  private File watchedFolder;
  @Autowired
  private StructureService structureService;
  @Autowired
  private ObjectsRepository objectsRepository;


  public void commit(String commitName) throws IOException {
    Set<FileData> fileDataSet = structureService.getProjectStructure();
    objectsRepository.saveSnapshot(fileDataSet, commitName);
  }

  public String log() {
    return objectsRepository.getLog();
  }

  public Object rebase() {

    return null;
  }

  public void init(String pathToWatch) throws IOException {
    this.watchedFolder = new File(pathToWatch);

    structureService.setWatchedFolder(watchedFolder);
    objectsRepository.createStorage(watchedFolder);
  }
}
