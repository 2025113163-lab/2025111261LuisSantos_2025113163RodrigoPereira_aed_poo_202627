package edu.ufp.inf.management;


import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.ST;
import edu.ufp.inf.labirinto.*;

public class Database implements ManageEntities, ManageSearches {

  private ST<String, Robot> robots;

  private ST<String, Artefact> artefacts;

  private ST<String, Room> Rooms;

  private ST<String, MaintenanceStation> Stations;

    /**
    * 
    *
   */
  public List<Entity> entity;

  public ArrayList<Robot> searchRobotsByBatteryAndVersion(Class type, int minBat, int maxBat, Date start, Date end) {
  return null;
  }

  public ArrayList<Robot> searchRobotsByNameAndStatus(String subName, String model, RobotStatus status) {
  return null;
  }

  public ArrayList<Artefact> searchArtefactsByCriteria(Class type, int danger, double minWeight, double maxWeight, double minVal, double maxVal) {
  return null;
  }

  public ArrayList<Artefact> searchArtefactsByDescAndValue(String subDesc, Class type, double minVal) {
  return null;
  }

  public ArrayList<Room> searchRoomsByZoneSecurityAndDate(ZoneType zone, int securityLevel, Date start, Date end) {
  return null;
  }

  public ArrayList<Room> searchRoomsByCodeZoneAndHazard(String subCode, ZoneType zone, double minHazard, double maxHazard) {
  return null;
  }

  public ArrayList<MissionLog> searchMissionLogsByArtefactAndDate(Class artefactType, Date start, Date end) {
  return null;
  }

  public void insertRobot(Robot r) {
  }

  public Robot removeRobot(String id) {
  return null;
  }

  public void listRobots() {
  }

  public void insertArtefact(Artefact a) {
  }

  public Artefact removeArtefact(String id) {
  return null;
  }

  public void listArtefacts() {
  }

  public void insertRoom(Room r) {
  }

  public Room removeRoom(String id) {
  return null;
  }

  public void listRooms() {
  }

}