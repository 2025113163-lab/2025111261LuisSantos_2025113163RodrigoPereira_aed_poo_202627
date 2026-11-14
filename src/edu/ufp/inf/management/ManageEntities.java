package edu.ufp.inf.management;

import edu.ufp.inf.labirinto.Room;
import edu.ufp.inf.labirinto.Robot;
import String;
import edu.ufp.inf.labirinto.Artefact;

public interface ManageEntities {

  public void insertRobot(Robot r);

  public Robot removeRobot(String id);

  public void listRobots();

  public void insertArtefact(Artefact a);

  public Artefact removeArtefact(String id);

  public void listArtefacts();

  public void insertRoom(Room r);

  public Room removeRoom(String id);

  public void listRooms();

}