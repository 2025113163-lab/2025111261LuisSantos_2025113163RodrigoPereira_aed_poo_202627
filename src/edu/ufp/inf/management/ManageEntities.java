package edu.ufp.inf.management;

import edu.ufp.inf.labirinto.RobotStatus;
import edu.ufp.inf.labirinto.Room;
import edu.ufp.inf.labirinto.Robot;
import edu.ufp.inf.labirinto.Artefact;
import edu.ufp.inf.labirinto.*;

public interface ManageEntities {

  public void insertRobot(Robot r);

  public Robot removeRobot(String id);

  public boolean editRobot(String id, String nome, int bateria, RobotStatus estado);

  public void listRobots();

  public void insertArtefact(Artefact a);

  public Artefact removeArtefact(String id);

  public boolean editArtefact(String id, double weight, double scientificValue, int dangerLevel, String description);

  public void listArtefacts();

  public void insertRoom(Room r);

  public Room removeRoom(String id);

  public boolean editRoom(String id, ZoneType zone, String code, int securityLevel, Date detectionDate, double hazardIndex);

  public void listRooms();

  public void insertStation(MaintenanceStation s);

  public MaintenanceStation removeStation(String id);

  public boolean editStation(String id, int capacity, int availableBays);

  public void listStations();

}