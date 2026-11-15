package edu.ufp.inf.management;


import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.SeparateChainingHashST;
import edu.ufp.inf.labirinto.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;


public class Database implements ManageEntities, ManageSearches, Serializable {

  private static final String ARCHIVE_FILE_PATH = "data/archive_log.txt";

  // R2: ST
  private final SeparateChainingHashST<String, Robot> robots;
  private final SeparateChainingHashST<String, Artefact> artefacts;
  private final SeparateChainingHashST<String, Room> rooms;
  private final SeparateChainingHashST<String, MaintenanceStation> stations;

  // R3: RedBlacks
  private final RedBlackBST<Integer, ArrayList<Robot>> robotsByBattery;
  private final RedBlackBST<Date, ArrayList<Robot>> robotsByVersionDate;
  private final RedBlackBST<Double, ArrayList<Artefact>> artefactsByValue;
  private final RedBlackBST<Double, ArrayList<Artefact>> artefactsByWeight;
  private final RedBlackBST<Date, ArrayList<Room>> roomsByDetectionDate;
  private final RedBlackBST<Date, ArrayList<MissionLog>> missionLogsByDate;

  // R4
  private final SeparateChainingHashST<String, Entity> archivedEntities;

  // Inicializacao de todas as estruturas da base de dados
  public Database(){
    this.robots = new SeparateChainingHashST<>();
    this.artefacts = new SeparateChainingHashST<>();
    this.rooms = new SeparateChainingHashST<>();
    this.stations = new SeparateChainingHashST<>();

    this.robotsByBattery = new RedBlackBST<>();
    this.robotsByVersionDate = new RedBlackBST<>();
    this.artefactsByValue = new RedBlackBST<>();
    this.artefactsByWeight = new RedBlackBST<>();
    this.roomsByDetectionDate = new RedBlackBST<>();
    this.missionLogsByDate = new RedBlackBST<>();

    this.archivedEntities = new SeparateChainingHashST<>();
  }


  // R3 e R4: Metodos auxiliares para redblacks
  private <K extends Comparable<K>, V> void addToBST(RedBlackBST<K, ArrayList<V>> bst, K key, V value) {
    if (key == null || value == null) return;
    if (!bst.contains(key)) {
      bst.put(key, new ArrayList<>());
    }
    ArrayList<V> bucket = bst.get(key);
    if (!bucket.contains(value)) {
      bucket.add(value);
    }
  }

  private <K extends Comparable<K>, V> void removeFromBST(RedBlackBST<K, ArrayList<V>> bst, K key, V value) {
    if (key == null || value == null || !bst.contains(key)) return;
    ArrayList<V> bucket = bst.get(key);
    bucket.remove(value);
    if (bucket.isEmpty()) {
      bst.delete(key);
    }
  }

  private void archiveEntity(Entity e) {
    if (e == null) return;
    archivedEntities.put(e.getId(), e);
    try {
      File file = new File(ARCHIVE_FILE_PATH);
      if (file.getParentFile() != null) {
        file.getParentFile().mkdirs();
      }
      try (PrintWriter pw = new PrintWriter(new FileWriter(file, true))) {
        pw.println("[ARCHIVED " + new Date() + "] " + e);
      }
    } catch (IOException ex) {
      System.err.println("Aviso: nao foi possivel escrever no ficheiro de arquivo: " + ex.getMessage());
    }
  }


  // R2 e R4: Gestao de robots
  @Override
  public void insertRobot(Robot r) {
    if (r == null) throw new IllegalArgumentException("Robot cannot be null");
    if (robots.contains(r.getId())) throw new IllegalArgumentException("Robot already exists: " + r.getId());

    robots.put(r.getId(), r);
    addToBST(robotsByBattery, r.getBateria(), r);
    addToBST(robotsByVersionDate, r.getDataVersaoSistema(), r);

    for (MissionLog log : r.getMissionLog()) {
      addToBST(missionLogsByDate, log.getTimestamp(), log);
    }
  }

  @Override
  public Robot removeRobot(String id) {
    if (id == null || !robots.contains(id)) return null;

    Robot r = robots.get(id);
    robots.delete(id);
    removeFromBST(robotsByBattery, r.getBateria(), r);
    removeFromBST(robotsByVersionDate, r.getDataVersaoSistema(), r);
    archiveEntity(r);
    return r;
  }

  @Override
  public boolean editRobot(String id, String nome, int bateria, RobotStatus estado) {
    if (id == null || !robots.contains(id)) return false;

    Robot r = robots.get(id);
    int oldBateria = r.getBateria();

    r.setNome(nome);
    r.setBateria(bateria);
    r.setEstado(estado);

    if (oldBateria != bateria) {
      removeFromBST(robotsByBattery, oldBateria, r);
      addToBST(robotsByBattery, bateria, r);
    }
    return true;
  }

  @Override
  public void listRobots() {
    for (String key : robots.keys()) {
      System.out.println(robots.get(key));
    }
  }


  // R2 e R4: Gestao de Artefactos
  @Override
  public void insertArtefact(Artefact a) {
    if (a == null) throw new IllegalArgumentException("Artefact cannot be null");
    if (artefacts.contains(a.getId())) throw new IllegalArgumentException("Artefact already exists: " + a.getId());

    artefacts.put(a.getId(), a);
    addToBST(artefactsByValue, a.getScientificValue(), a);
    addToBST(artefactsByWeight, a.getWeight(), a);
  }

  @Override
  public Artefact removeArtefact(String id) {
    if (id == null || !artefacts.contains(id)) return null;

    Artefact a = artefacts.get(id);
    artefacts.delete(id);
    removeFromBST(artefactsByValue, a.getScientificValue(), a);
    removeFromBST(artefactsByWeight, a.getWeight(), a);

    // Remover artefacto de qualquer CargoRobot
    for (String robotId : robots.keys()) {
      Robot r = robots.get(robotId);
      if (r instanceof CargoRobot cargoRobot) {
        cargoRobot.removeArtefact(a);
      }
    }

    archiveEntity(a);
    return a;
  }

  @Override
  public boolean editArtefact(String id, double weight, double scientificValue, int dangerLevel, String description) {
    if (id == null || !artefacts.contains(id)) return false;

    Artefact a = artefacts.get(id);
    double oldWeight = a.getWeight();
    double oldValue = a.getScientificValue();

    a.setWeight(weight);
    a.setScientificValue(scientificValue);
    a.setDangerLevel(dangerLevel);
    a.setDescription(description);

    if (Double.compare(oldWeight, weight) != 0) {
      removeFromBST(artefactsByWeight, oldWeight, a);
      addToBST(artefactsByWeight, weight, a);
    }
    if (Double.compare(oldValue, scientificValue) != 0) {
      removeFromBST(artefactsByValue, oldValue, a);
      addToBST(artefactsByValue, scientificValue, a);
    }
    return true;
  }

  @Override
  public void listArtefacts() {
    for (String key : artefacts.keys()) {
      System.out.println(artefacts.get(key));
    }
  }


  // R2 e R4: Gestao de rooms
  @Override
  public void insertRoom(Room r) {
    if (r == null) throw new IllegalArgumentException("Room cannot be null");
    if (rooms.contains(r.getId())) throw new IllegalArgumentException("Room already exists: " + r.getId());

    rooms.put(r.getId(), r);
    addToBST(roomsByDetectionDate, r.getDetectionDate(), r);
  }

  @Override
  public Room removeRoom(String id) {
    if (id == null || !rooms.contains(id)) return null;

    Room room = rooms.get(id);
    rooms.delete(id);
    removeFromBST(roomsByDetectionDate, room.getDetectionDate(), room);
    archiveEntity(room);
    return room;
  }

  @Override
  public boolean editRoom(String id, ZoneType zone, String code, int securityLevel, Date detectionDate, double hazardIndex) {
    if (id == null || !rooms.contains(id)) return false;

    Room room = rooms.get(id);
    Date oldDate = room.getDetectionDate();

    room.setZone(zone);
    room.setCode(code);
    room.setSecurityLevel(securityLevel);
    room.setDetectionDate(detectionDate);
    room.setHazardIndex(hazardIndex);

    Date newDate = room.getDetectionDate();
    if (!oldDate.equals(newDate)) {
      removeFromBST(roomsByDetectionDate, oldDate, room);
      addToBST(roomsByDetectionDate, newDate, room);
    }
    return true;
  }

  @Override
  public void listRooms() {
    for (String key : rooms.keys()) {
      System.out.println(rooms.get(key));
    }
  }


  // R2 e R4: Gestao estacoes de manutencao
  @Override
  public void insertStation(MaintenanceStation s) {
    if (s == null) throw new IllegalArgumentException("Station cannot be null");
    if (stations.contains(s.getId())) throw new IllegalArgumentException("Station already exists: " + s.getId());
    stations.put(s.getId(), s);
  }

  @Override
  public MaintenanceStation removeStation(String id) {
    if (id == null || !stations.contains(id)) return null;
    MaintenanceStation s = stations.get(id);
    stations.delete(id);
    archiveEntity(s);
    return s;
  }

  @Override
  public boolean editStation(String id, int capacity, int availableBays) {
    if (id == null || !stations.contains(id)) return false;
    MaintenanceStation s = stations.get(id);
    s.setCapacity(capacity);
    s.setAvailableBays(availableBays);
    return true;
  }

  @Override
  public void listStations() {
    for (String key : stations.keys()) {
      System.out.println(stations.get(key));
    }
  }


  // R3: Logs
  @Override
  public void registerMissionLog(MissionLog log) {
    if (log == null) throw new IllegalArgumentException("MissionLog cannot be null");
    Robot r = log.getRobot();
    if (!robots.contains(r.getId())) {
      throw new IllegalArgumentException("O robô associado ao log não está registado na base de dados: " + r.getId());
    }
    r.addMissionLog(log);
    addToBST(missionLogsByDate, log.getTimestamp(), log);
  }

  @Override
  public ArrayList<Entity> getArchivedEntities() {
    ArrayList<Entity> archived = new ArrayList<>();
    for (String key : archivedEntities.keys()) {
      archived.add(archivedEntities.get(key));
    }
    return archived;
  }

  @Override
  public void listArchivedEntities() {
    for (String key : archivedEntities.keys()) {
      System.out.println(archivedEntities.get(key));
    }
  }


  // R3: Pesquisa com RedBlacks / filtros
  @Override
  public ArrayList<Robot> searchRobotsByBatteryAndVersion(Class type, int minBat, int maxBat, Date start, Date end) {
    ArrayList<Robot> results = new ArrayList<>();
    if (minBat > maxBat || robotsByBattery.isEmpty()) return results;

    for (Integer bat : robotsByBattery.keys(minBat, maxBat)) {
      for (Robot r : robotsByBattery.get(bat)) {
        boolean matchesType = (type == null || type.isInstance(r));
        boolean matchesStart = (start == null || r.getDataVersaoSistema().compareTo(start) >= 0);
        boolean matchesEnd = (end == null || r.getDataVersaoSistema().compareTo(end) <= 0);
        if (matchesType && matchesStart && matchesEnd) {
          results.add(r);
        }
      }
    }
    return results;
  }

  @Override
  public ArrayList<Robot> searchRobotsByNameAndStatus(String subName, String model, RobotStatus status) {
    ArrayList<Robot> results = new ArrayList<>();
    for (String id : robots.keys()) {
      Robot r = robots.get(id);
      boolean matchesName = (subName == null || subName.isBlank()
              || r.getNome().toLowerCase().contains(subName.toLowerCase()));
      boolean matchesModel = (model == null || model.isBlank()
              || r.getClass().getSimpleName().toLowerCase().contains(model.toLowerCase()));
      boolean matchesStatus = (status == null || r.getEstado() == status);

      if (matchesName && matchesModel && matchesStatus) {
        results.add(r);
      }
    }
    return results;
  }

  @Override
  public ArrayList<Artefact> searchArtefactsByCriteria(Class type, int danger, double minWeight, double maxWeight, double minVal, double maxVal) {
    ArrayList<Artefact> results = new ArrayList<>();
    if (minVal > maxVal || minWeight > maxWeight || artefactsByValue.isEmpty()) return results;

    for (Double val : artefactsByValue.keys(minVal, maxVal)) {
      for (Artefact a : artefactsByValue.get(val)) {
        boolean matchesType = (type == null || type.isInstance(a));
        boolean matchesDanger = (danger < 0 || a.getDangerLevel() == danger);
        boolean matchesWeight = (a.getWeight() >= minWeight && a.getWeight() <= maxWeight);

        if (matchesType && matchesDanger && matchesWeight) {
          results.add(a);
        }
      }
    }
    return results;
  }

  @Override
  public ArrayList<Artefact> searchArtefactsByDescAndValue(String subDesc, Class type, double minVal) {
    ArrayList<Artefact> results = new ArrayList<>();
    if (artefactsByValue.isEmpty()) return results;

    double maxKey = artefactsByValue.max();
    if (minVal > maxKey) return results;

    for (Double val : artefactsByValue.keys(minVal, maxKey)) {
      for (Artefact a : artefactsByValue.get(val)) {
        boolean matchesType = (type == null || type.isInstance(a));
        boolean matchesDesc = (subDesc == null || subDesc.isBlank()
                || a.getDescription().toLowerCase().contains(subDesc.toLowerCase()));

        if (matchesType && matchesDesc) {
          results.add(a);
        }
      }
    }
    return results;
  }

  @Override
  public ArrayList<Room> searchRoomsByZoneSecurityAndDate(ZoneType zone, int securityLevel, Date start, Date end) {
    ArrayList<Room> results = new ArrayList<>();
    if (start == null || end == null || start.afterDate(end) || roomsByDetectionDate.isEmpty()) {
      return results;
    }

    for (Date d : roomsByDetectionDate.keys(start, end)) {
      for (Room room : roomsByDetectionDate.get(d)) {
        boolean matchesZone = (zone == null || room.getZone() == zone);
        boolean matchesSec = (securityLevel < 0 || room.getSecurityLevel() == securityLevel);

        if (matchesZone && matchesSec) {
          results.add(room);
        }
      }
    }
    return results;
  }

  @Override
  public ArrayList<Room> searchRoomsByCodeZoneAndHazard(String subCode, ZoneType zone, double minHazard, double maxHazard) {
    ArrayList<Room> results = new ArrayList<>();
    if (minHazard > maxHazard) return results;

    for (String id : rooms.keys()) {
      Room room = rooms.get(id);
      boolean matchesCode = (subCode == null || subCode.isBlank()
              || room.getCode().toLowerCase().contains(subCode.toLowerCase()));
      boolean matchesZone = (zone == null || room.getZone() == zone);
      boolean matchesHazard = (room.getHazardIndex() >= minHazard && room.getHazardIndex() <= maxHazard);

      if (matchesCode && matchesZone && matchesHazard) {
        results.add(room);
      }
    }
    return results;
  }

  @Override
  public ArrayList<MissionLog> searchMissionLogsByArtefactAndDate(Class artefactType, Date start, Date end) {
    ArrayList<MissionLog> results = new ArrayList<>();
    if (start == null || end == null || start.afterDate(end) || missionLogsByDate.isEmpty()) {
      return results;
    }

    for (Date d : missionLogsByDate.keys(start, end)) {
      for (MissionLog log : missionLogsByDate.get(d)) {
        if (log.getArtefact() != null && (artefactType == null || artefactType.isInstance(log.getArtefact()))) {
          results.add(log);
        }
      }
    }
    return results;
  }
}