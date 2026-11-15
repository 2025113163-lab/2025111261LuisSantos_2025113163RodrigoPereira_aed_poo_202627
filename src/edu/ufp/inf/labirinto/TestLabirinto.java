package edu.ufp.inf.labirinto;

// R1: Testes para as classes base
public class TestLabirinto {

    // Verifica o polimorfismo dos artefactos, limites dos robots, carregamento nas estacoes e datas.
    public static void testR1() {
        System.out.println("Inicializacao do Teste... \n");

        // polimorfismo em Artifact.calculateImpact()
        Artefact relic = new TechRelic("A1", 10.0, 95.0, 3, "Núcleo Quântico");
        Artefact crystal = new EnergyCrystal("A2", 10.0, 80.0, 3, "Cristal de Plasma");
        Artefact hazard = new HazardousMaterial("A3", 10.0, 40.0, 3, "Isótopo Instável");

        System.out.println("Impacto de um TechRelic com peso=10 e perigo=3: " + relic.calculateImpact());
        System.out.println("Impacto de um EnergyCrystal com peso=10 e perigo=3: " + crystal.calculateImpact());
        System.out.println("Impacto de um HazardousMaterial com peso=10 e perigo=3: " + hazard.calculateImpact());

        // capacidade max nos CagoRobots
        Date v1 = new Date((short) 10, (short) 9, 2026);
        CargoRobot cargo = new CargoRobot("R-CARGO", "Atlas", 45, RobotStatus.ACTIVE, v1, 15.0);

        System.out.println("Adicionar A1 (10kg / cap 15kg): " + cargo.addArtefact(relic));   // true
        System.out.println("Adicionar A2 (10kg -> total 20kg): " + cargo.addArtefact(crystal)); // false (excede)
        System.out.println("Carga atual do Atlas: " + cargo.getCurrentLoad() + " kg");

        // estacoes de carregamento
        MaintenanceStation station = new MaintenanceStation("ST-01", 1);
        System.out.println("Recarregar Atlas (1 slot livre): " + station.rechargeRobot(cargo) + " | Bateria: " + cargo.getBateria() + "% | Estado: " + cargo.getEstado());

        ExplorerRobot explorer = new ExplorerRobot("R-EXP", "Scout", 20, RobotStatus.IDLE, v1, 25.0);
        System.out.println("Recarregar Scout (0 slots livres): " + station.rechargeRobot(explorer)); // false

        station.releaseBay(cargo);
        System.out.println("Slots livres depois de libertar Atlas: " + station.getAvailableBays());

        // date
        Date d1 = new Date((short) 1, (short) 9, 2026);
        Date d2 = new Date((short) 15, (short) 10, 2026);
        System.out.println(d1 + " antes de " + d2 + "? " + d1.beforeDate(d2));
    }


    // Executar o TestLabirinto
    public static void main(String[] args) {
        testR1();
    }
}
