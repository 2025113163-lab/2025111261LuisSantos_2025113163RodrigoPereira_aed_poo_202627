package edu.ufp.inf.labirinto;

import java.io.Serializable;

// Registo do historico de um evento ou operação realizada por um robot
public class MissionLog implements Serializable {

    private final Date timestamp;
    private final String description;
    private final Robot robot;
    private final Artefact artefact;

    /**
     * Construtor do registo de missao associado a um robot
     *
     * @param timestamp - data/marca temporal do evento
     * @param description - descrição da acao executada
     * @param robot
     * @param artefact - artefacto envolvido na acao (pode ser {@code null})
     * @throws IllegalArgumentException
     */
    public MissionLog(Date timestamp, String description, Robot robot, Artefact artefact) {
        if (robot == null) {
            throw new IllegalArgumentException("Registo de missao tem de estar associado a um robot.");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Descricao do registo nao pode ser nula ou vazia.");
        }
        this.timestamp = (timestamp != null) ? new Date(timestamp) : new Date();
        this.description = description;
        this.robot = robot;
        this.artefact = artefact;
    }

    /**
     * Construtor do registo de missao sem artefacto associado
     *
     * @param timestamp - data/marca temporal do evento
     * @param description - descrição da acao executada
     * @param robot
     */
    public MissionLog(Date timestamp, String description, Robot robot) {
        this(timestamp, description, robot, null);
    }

    public Date getTimestamp() { return new Date(timestamp); }

    public String getDescription() { return description; }

    public Robot getRobot() { return robot; }

    public Artefact getArtefact() { return artefact; }

    @Override
    public String toString() {
        return "MissionLog{" + "timestamp=" + timestamp + ", robotId='" + robot.getId() + '\'' + ", artefactId=" + (artefact != null ? "'" + artefact.getId() + "'" : "none") + ", description='" + description + '\'' + '}';
    }
}