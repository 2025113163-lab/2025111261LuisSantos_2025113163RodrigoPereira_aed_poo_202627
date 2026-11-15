package edu.ufp.inf.management;

import edu.ufp.inf.labirinto.*;

public class TestDatabase {
    public static void main(String[] args) {
        Database db = new Database();
        System.out.println("Database criada");
        db.insertRobot(new ExplorerRobot("R1", "Explorador 1", 80, RobotStatus.ACTIVE, new Date(), 10.0));
        db.insertRobot(new CargoRobot("R2", "Carga 1", 60, RobotStatus.IDLE, new Date(), 50.0));
        db.listRobots();
        Robot removido = db.removeRobot("R1");
        System.out.println("Removido: " + removido);
        db.listRobots();                          // só deve aparecer o R2
        System.out.println(db.removeRobot("XX")); // id inexistente: deve dar null
        db.insertArtefact(new TechRelic("A1", 5.0, 80.0, 2, "Relic"));
        db.insertRoom(new Room("S1", ZoneType.SAFE, "S-01", 1, new Date(), 0.5));
        db.insertStation(new MaintenanceStation("M1", 4));
        db.listArtefacts();
        db.listRooms();
        db.listStations();
        System.out.println(db.editArtefact("A1", 6.0, 90.0, 3, "Relic v2"));  // true
        System.out.println(db.removeRoom("S1") != null);                      // true
        System.out.println(db.removeStation("XX"));                           // null
    }
}