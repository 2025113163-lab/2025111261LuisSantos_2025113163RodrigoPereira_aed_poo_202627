package edu.ufp.inf.labirinto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class Robot extends Entity {

  private String nome;
  private int bateria;
  private RobotStatus estado;
  private Date dataVersaoSistema;
  private final List<MissionLog> missionLog;

  public Robot(String id, String nome, int bateria, Date dataVersaoSistema, RobotStatus estado) {
    super(id);
    if (nome == null || nome.isBlank()) {
      throw new IllegalArgumentException("O robot não pode ter o nome vazio");
    }
    setBateria(bateria);
    this.nome = nome;
    this.estado = (estado != null) ? estado : RobotStatus.IDLE;
    this.dataVersaoSistema = (dataVersaoSistema != null) ? new Date(dataVersaoSistema) : new Date();
    this.missionLog = new ArrayList<>();
  }

  public void addMissionLog(MissionLog log) {
    if (log != null) {
      this.missionLog.add(log);
    }
  }

  public List<MissionLog> getMissionLog() {
    return Collections.unmodifiableList(missionLog);
  }

  public String getNome() { return nome; }
  public void setNome(String nome) {
    if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome invalido");
    this.nome = nome;
  }

  public int getBateria() { return bateria; }
  public void setBateria(int bateria) {
    if (bateria < 0 || bateria > 100) {
      throw new IllegalArgumentException("A carga da bateria deve estar entre 0 e 100");
    }
    this.bateria = bateria;
  }

  public RobotStatus getEstado() { return estado; }
  public void setEstado(RobotStatus estado) {
    if (estado != null) this.estado = estado;
  }

  public Date getDataVersaoSistema() { return new Date(dataVersaoSistema); }
  public void setDataVersaoSistema(Date dataVersaoSistema) {
    if (dataVersaoSistema != null) this.dataVersaoSistema = new Date(dataVersaoSistema);
  }
}