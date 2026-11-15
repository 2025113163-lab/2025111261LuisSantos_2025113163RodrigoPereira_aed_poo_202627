package edu.ufp.inf.management;

import edu.ufp.inf.labirinto.*;

import java.util.ArrayList;

public class TestDatabase {

    /**
     * Testa as operações de inserção, prevenção de duplicados, edição,
     * listagem e remoção sobre as SeparateChainingHashST (Requisito R2).
     */
    public static void testR1() {
        System.out.println("TABELAS DE SÍMBOLOS");

        Database db = new Database();
        Date d1 = new Date((short) 1, (short) 9, 2026);

        db.insertRobot(new ExplorerRobot("R1", "Scout-Alpha", 85, RobotStatus.ACTIVE, d1, 15.0));
        db.insertRobot(new CargoRobot("R2", "Hauler-Beta", 60, RobotStatus.IDLE, d1, 50.0));
        db.insertArtefact(new TechRelic("A1", 4.5, 90.0, 2, "Processador Antigo"));
        db.insertRoom(new Room("S1", ZoneType.SAFE, "SEC-A1", 1, d1, 0.2));
        db.insertStation(new MaintenanceStation("M1", 3));

        // Validar rejeição de ID duplicado
        try {
            db.insertRobot(new ExplorerRobot("R1", "Duplicado", 50, RobotStatus.IDLE, d1, 10.0));
        } catch (IllegalArgumentException e) {
            System.out.println("Exceção esperada ao inserir ID duplicado: " + e.getMessage());
        }

        // Editar e listar
        db.editRobot("R1", "Scout-Alpha-V2", 95, RobotStatus.ACTIVE);
        db.editStation("M1", 4, 3);
        System.out.println("--- Robôs registados ---");
        db.listRobots();
        System.out.println("--- Estações registadas ---");
        db.listStations();

        // Remover inexistente e existente
        System.out.println("Remover ID inexistente ('XX'): " + db.removeRobot("XX"));
        System.out.println("Remover estação 'M1': " + db.removeStation("M1"));
    }

    /**
     * Popula a Database com múltiplas entidades e regista logs de missão,
     */
    public static void testR2() {
        System.out.println("PESQUISAS EM REDBLACKBST");

        Database db = new Database();

        Date dJan = new Date((short) 15, (short) 1, 2026);
        Date dMay = new Date((short) 20, (short) 5, 2026);
        Date dSep = new Date((short) 10, (short) 9, 2026);
        Date dNov = new Date((short) 15, (short) 11, 2026);

        // Inserir Robôs
        Robot r1 = new ExplorerRobot("R1", "Vanguard-X", 90, RobotStatus.ACTIVE, dSep, 20.0);
        Robot r2 = new ExplorerRobot("R2", "Vanguard-Z", 35, RobotStatus.CHARGING, dJan, 12.0);
        Robot r3 = new CargoRobot("R3", "Titan-Cargo", 75, RobotStatus.ACTIVE, dMay, 100.0);
        db.insertRobot(r1);
        db.insertRobot(r2);
        db.insertRobot(r3);

        // Inserir Artefactos
        Artefact a1 = new TechRelic("A1", 5.0, 95.0, 2, "Relíquia de Navegação Estelar");
        Artefact a2 = new EnergyCrystal("A2", 2.5, 70.0, 3, "Cristal Energético Azul");
        Artefact a3 = new HazardousMaterial("A3", 12.0, 45.0, 5, "Contentor Radioativo Alfa");
        db.insertArtefact(a1);
        db.insertArtefact(a2);
        db.insertArtefact(a3);

        // Inserir Salas
        db.insertRoom(new Room("S1", ZoneType.SAFE, "LAB-01", 1, dJan, 0.5));
        db.insertRoom(new Room("S2", ZoneType.HAZARDOUS, "REA-09", 4, dSep, 8.7));
        db.insertRoom(new Room("S3", ZoneType.HAZARDOUS, "REA-12", 4, dNov, 9.2));

        // Registar Logs de Missão
        db.registerMissionLog(new MissionLog(dMay, "Patrulha no setor norte", r1));
        db.registerMissionLog(new MissionLog(dSep, "Recolha de relíquia tecnológica", r3, a1));
        db.registerMissionLog(new MissionLog(dNov, "Extração de cristal energético", r3, a2));

        // R3a: Robôs por tipo, intervalo de bateria e data de versão
        ArrayList<Robot> res3a = db.searchRobotsByBatteryAndVersion(ExplorerRobot.class, 50, 100, dMay, dNov);
        System.out.println("[R3a] ExplorerRobots com bateria [50..100] e versão [Mai..Nov]: " + res3a.size() + " -> " + res3a);

        // R3b: Robôs por substring de nome, modelo e estado
        ArrayList<Robot> res3b = db.searchRobotsByNameAndStatus("vanguard", "Explorer", RobotStatus.ACTIVE);
        System.out.println("[R3b] Robôs 'vanguard' ativos: " + res3b.size() + " -> " + res3b);

        // R3c: Artefactos por tipo, perigo, intervalo de peso e intervalo de valor científico
        ArrayList<Artefact> res3c = db.searchArtefactsByCriteria(TechRelic.class, 2, 1.0, 10.0, 80.0, 100.0);
        System.out.println("[R3c] TechRelics perigo=2, peso [1..10], valor [80..100]: " + res3c.size() + " -> " + res3c);

        // R3d: Artefactos por substring de descrição, subtipo e valor mínimo
        ArrayList<Artefact> res3d = db.searchArtefactsByDescAndValue("cristal", EnergyCrystal.class, 50.0);
        System.out.println("[R3d] EnergyCrystals com 'cristal' e valor >= 50: " + res3d.size() + " -> " + res3d);

        // R3e: Salas por zona, nível de segurança e intervalo de deteção
        ArrayList<Room> res3e = db.searchRoomsByZoneSecurityAndDate(ZoneType.HAZARDOUS, 4, dMay, dNov);
        System.out.println("[R3e] Salas HAZARDOUS seg=4 detetadas [Mai..Nov]: " + res3e.size() + " -> " + res3e);

        // R3f: Salas por substring de código, zona e intervalo de perigo ambiental
        ArrayList<Room> res3f = db.searchRoomsByCodeZoneAndHazard("REA", ZoneType.HAZARDOUS, 8.0, 10.0);
        System.out.println("[R3f] Salas 'REA' HAZARDOUS com perigo [8..10]: " + res3f.size() + " -> " + res3f);

        // R3g: Logs de missão por tipo de artefacto e intervalo temporal
        ArrayList<MissionLog> res3g = db.searchMissionLogsByArtefactAndDate(TechRelic.class, dJan, dNov);
        System.out.println("[R3g] Logs com recolha de TechRelic [Jan..Nov]: " + res3g.size() + " -> " + res3g);
    }

    /**
     * Testa a sincronização entre estruturas de dados aquando de edições e remoções,
     */
    public static void testR3() {
        System.out.println("CONSISTÊNCIA ENTRE ESTRUTURAS E ARQUIVO");

        Database db = new Database();
        Date d1 = new Date((short) 10, (short) 10, 2026);

        CargoRobot hauler = new CargoRobot("R10", "Carrier-One", 90, RobotStatus.ACTIVE, d1, 80.0);
        Artefact relic = new TechRelic("A10", 10.0, 99.0, 1, "Mapa Estelar");
        hauler.addArtefact(relic);

        db.insertRobot(hauler);
        db.insertArtefact(relic);

        // 1. Editar bateria de 90 para 15 e verificar atualização na RedBlackBST
        System.out.println("Antes da edição (bateria 80..100): "
                + db.searchRobotsByBatteryAndVersion(null, 80, 100, d1, d1).size());
        db.editRobot("R10", "Carrier-One", 15, RobotStatus.CHARGING);
        System.out.println("Após edição (bateria 80..100): "
                + db.searchRobotsByBatteryAndVersion(null, 80, 100, d1, d1).size());
        System.out.println("Após edição (bateria 0..20): "
                + db.searchRobotsByBatteryAndVersion(null, 0, 20, d1, d1).size());

        // 2. Remover artefacto e verificar remoção em cascata (BST + carga do robô + arquivo)
        db.removeArtefact("A10");
        System.out.println("Artefactos na BST após remoção: "
                + db.searchArtefactsByDescAndValue("", null, 0.0).size());
        System.out.println("Carga atual do Carrier-One após remoção de A10: " + hauler.getCurrentLoad() + " kg");

        // 3. Remover robô e verificar arquivo em memória
        db.removeRobot("R10");
        System.out.println("--- Entidades no arquivo (archivedEntities) ---");
        db.listArchivedEntities();
    }

    public static void main(String[] args) {
        testR1();
        testR2();
        testR3();
    }
}