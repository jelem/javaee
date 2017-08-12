package services;

import model.FileData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utils.ObjectsRepository;

import java.io.File;
import java.io.IOException;
import java.util.Set;


@Service
public class VersionControlService {

  private Logger logger = LogManager.getLogger();
  private File watchedFolder;
  @Autowired
  private StructureService structureService;
  @Autowired
  private ObjectsRepository objectsRepository;


  public void commit(String commitName) throws IOException {
    logger.traceEntry("Try to commit: " + commitName);
    Set<FileData> fileDataSet = structureService.getProjectStructure();
    objectsRepository.saveSnapshot(fileDataSet, commitName);
    logger.traceExit("Commited succesfully");
  }

  public String log() {
    logger.traceEntry("Getting log out");
    String log = objectsRepository.getLog();
    logger.traceExit("Log obtained successfully");
    return log;
  }

  public void revert(String commitName) throws IOException {
    logger.traceEntry("Start to revert");
    objectsRepository.revert(commitName);
    logger.traceExit("Reverted successfully");
  }

  public void init(String pathToWatch) throws IOException {
    logger.traceEntry("Initializing...");
    this.watchedFolder = new File(pathToWatch);

    structureService.setWatchedFolder(watchedFolder);
    objectsRepository.createStorage(watchedFolder);
    logger.traceExit(pathToWatch + " initialized");
  }
}
