package edu.ufp.inf.management;


import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.ufp.inf.labirinto.*;

public class Database implements ManageEntities, ManageSearches {

  private SeparateChainingHashST<String, Robot> robots;

  private SeparateChainingHashST<String, Artefact> artefacts;

  private SeparateChainingHashST<String, Room> Rooms;

  private SeparateChainingHashST<String, MaintenanceStation> Stations;


  public Database(){
    robots = new SeparateChainingHashST<>();
    artefacts = new SeparateChainingHashST<>();
    Rooms = new SeparateChainingHashST<>();
    Stations = new SeparateChainingHashST<>();
  }
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
      if (r == null) { throw new IllegalArgumentException("Robot cannot be null"); }
      if (robots.contains(r.getId())) {  throw new IllegalArgumentException("Robot already exists"); }
      robots.put(r.getId(), r);

  }

  public Robot removeRobot(String id) {
    if (!robots.contains(id)) {
      return null;
    }
    Robot r = robots.get(id);
    robots.delete(id);

    return r;
  }

  public boolean editRobot(String id, String nome, int bateria, RobotStatus estado) {
    if (!robots.contains(id)) {
      return false;
    }
    Robot r = robots.get(id);
    r.setNome(nome);
    r.setBateria(bateria);
    r.setEstado(estado);
    return true;
  }

  public void listRobots() {
    for (String key : robots.keys()) {
      Robot r = robots.get(key);
      System.out.println(r);
    }
  }

  public void insertArtefact(Artefact a) {
    if (a == null) { throw new IllegalArgumentException("Artefact cannot be null"); }
    if (artefacts.contains(a.getId())) {  throw new IllegalArgumentException("Artefact already exists"); }
    artefacts.put(a.getId(), a);
  }

  public Artefact removeArtefact(String id) {
    if (!artefacts.contains(id)) { return null; }
    Artefact a = artefacts.get(id);
    artefacts.delete(id);
    return a;
  }

  public boolean editArtefact(String id, double weight, double scientificValue, int dangerLevel, String description) {
    if (!artefacts.contains(id)) { return false; }
    Artefact a = artefacts.get(id);
    a.setWeight(weight);
    a.setScientificValue(scientificValue);
    a.setDangerLevel(dangerLevel);
    a.setDescription(description);
    return true;
  }

  public void listArtefacts() {
    for (String key : artefacts.keys()) {
      System.out.println(artefacts.get(key));
    }
  }

  public void insertRoom(Room r) {
    if (r == null) { throw new IllegalArgumentException("Room cannot be null"); }
    if (Rooms.contains(r.getId())) {  throw new IllegalArgumentException("Room already exists"); }
    Rooms.put(r.getId(), r);
  }

  public Room removeRoom(String id) {
    if  (!Rooms.contains(id)) { return null; }
    Room room = Rooms.get(id);
    Rooms.delete(id);
    return room;
  }

  public boolean editRoom(String id, ZoneType zone, String code, int securityLevel, Date detectionDate, double hazardIndex) {
    if  (!Rooms.contains(id)) { return false; }
    Room room = Rooms.get(id);
    room.setZone(zone);
    room.setCode(code);
    room.setSecurityLevel(securityLevel);
    room.setDetectionDate(detectionDate);
    room.setHazardIndex(hazardIndex);
    return true;
  }

    public void listRooms() {
    for (String key : Rooms.keys()) {
      System.out.println(Rooms.get(key));
    }
  }

  public void insertStation(MaintenanceStation s) {
    if (s == null) { throw new IllegalArgumentException("Station cannot be null"); }
    if (Stations.contains(s.getId())) { throw new IllegalArgumentException("Station already exists"); }
    Stations.put(s.getId(), s);
  }

  public MaintenanceStation removeStation(String id) {
    if (!Stations.contains(id)) { return null; }
    MaintenanceStation s = Stations.get(id);
    Stations.delete(id);
    return s;
  }

  public boolean editStation(String id, int capacity, int availableBays) {
    if (!Stations.contains(id)) { return false; }
    MaintenanceStation s = Stations.get(id);
    s.setCapacity(capacity);          // primeiro a capacidade...
    s.setAvailableBays(availableBays); // ...depois os bays livres
    return true;
  }

  public void listStations() {
    for (String key : Stations.keys()) {
      System.out.println(Stations.get(key));
    }
  }

}