package edu.ufp.inf.management;

import edu.ufp.inf.labirinto.*;
import java.util.ArrayList;

// Interface que define as operacoes de gestao e arquivo das entidades do sistema
public interface ManageEntities {

    void insertRobot(Robot r);
    Robot removeRobot(String id);
    boolean editRobot(String id, String nome, int bateria, RobotStatus estado);
    void listRobots();

    void insertArtefact(Artefact a);
    Artefact removeArtefact(String id);
    boolean editArtefact(String id, double weight, double scientificValue, int dangerLevel, String description);
    void listArtefacts();

    void insertRoom(Room r);
    Room removeRoom(String id);
    boolean editRoom(String id, ZoneType zone, String code, int securityLevel, Date detectionDate, double hazardIndex);
    void listRooms();

    void insertStation(MaintenanceStation s);
    MaintenanceStation removeStation(String id);
    boolean editStation(String id, int capacity, int availableBays);
    void listStations();

    void registerMissionLog(MissionLog log);
    ArrayList<Entity> getArchivedEntities();
    void listArchivedEntities();
}