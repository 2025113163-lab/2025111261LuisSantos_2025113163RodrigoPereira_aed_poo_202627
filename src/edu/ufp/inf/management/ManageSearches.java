package edu.ufp.inf.management;

import edu.ufp.inf.labirinto.*;
import java.util.ArrayList;

public interface ManageSearches {

  public ArrayList<Robot> searchRobotsByBatteryAndVersion(Class type, int minBat, int maxBat, Date start, Date end);

  public ArrayList<Robot> searchRobotsByNameAndStatus(String subName, String model, RobotStatus status);

  public ArrayList<Artefact> searchArtefactsByCriteria(Class type, int danger, double minWeight, double maxWeight, double minVal, double maxVal);

  public ArrayList<Artefact> searchArtefactsByDescAndValue(String subDesc, Class type, double minVal);

  public ArrayList<Room> searchRoomsByZoneSecurityAndDate(ZoneType zone, int securityLevel, Date start, Date end);

  public ArrayList<Room> searchRoomsByCodeZoneAndHazard(String subCode, ZoneType zone, double minHazard, double maxHazard);

  public ArrayList<MissionLog> searchMissionLogsByArtefactAndDate(Class artefactType, Date start, Date end);

}