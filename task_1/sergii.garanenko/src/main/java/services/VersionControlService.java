package services;

import model.FileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.ObjectsRepository;

import java.io.File;
import java.io.IOException;
import java.util.Set;


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

  public void revert(String commitName) throws IOException {
    objectsRepository.revert(commitName);
  }

  public void init(String pathToWatch) throws IOException {
    this.watchedFolder = new File(pathToWatch);

    structureService.setWatchedFolder(watchedFolder);
    objectsRepository.createStorage(watchedFolder);
  }
}
