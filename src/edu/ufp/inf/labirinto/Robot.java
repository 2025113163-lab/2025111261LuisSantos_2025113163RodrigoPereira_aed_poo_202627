package edu.ufp.inf.labirinto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Representa um robot autónomo do labirinto.
 * Classe abstrata que guarda os dados comuns a todos os robots (nome, bateria, estado, versão do sistema e histórico de missões).
 *
 * @author Luis Santos & Rodrigo Pereira
 * @version 1.0
 */
public abstract class Robot extends Entity {
  /** Dados do Robo +*/
  private String nome;
  private int bateria;
  private RobotStatus estado;
  private Date dataVersaoSistema;
  private final List<MissionLog> missionLog;


  /**
   * @param id - Identificador unico do robo
   * @param nome - Nome
   * @param bateria - Nivel da bateria
   * @param dataVersaoSistema - Versao do sistema do robot
   * @param estado - Estado de operação
   * @throws IllegalArgumentException - se n tiverem dentro dos parametros de Nome, Bateria e ID
   */
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
/**
 * Acrescenta um registo ao histórico de missões do robot.
 **/
  public void addMissionLog(MissionLog log) {
    if (log != null) {
      this.missionLog.add(log);
    }
  }


/**
 * Devolve uma descrição textual do robot, com id, nome, bateria, estado e data da versão do sistema.
 **/
  @Override
  public String toString() {
    return "Robot{" + "id='" + getId() + '\'' + ", nome='" + nome + '\'' + ", bateria=" + bateria + ", estado=" + estado + ", dataVersao=" + dataVersaoSistema + '}';
    }

/**
 * Devolve o histórico de missões do robot.
 **/
  public List<MissionLog> getMissionLog() {
    return Collections.unmodifiableList(missionLog);
  }

/**
 * Devolve o nome do robot.
 **/
  public String getNome() { return nome; }

  /**
   * Altera o nome do robot.
   **/

  public void setNome(String nome) {
    if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome invalido");
    this.nome = nome;
  }
  /**
   * Devolve o nivel bateria do robot.
   **/
  public int getBateria() { return bateria; }

  /**
   * Altera o nivel de bateria o nome do robot.
   **/
  public void setBateria(int bateria) {
    if (bateria < 0 || bateria > 100) {
      throw new IllegalArgumentException("A carga da bateria deve estar entre 0 e 100");
    }
    this.bateria = bateria;
  }
  /**
   * Devolve o estado do robot.
   **/
  public RobotStatus getEstado() { return estado; }

  /**
   * Altera o estado do robot.
   **/
  public void setEstado(RobotStatus estado) {
    if (estado != null) this.estado = estado;
  }
  /**
   * Devolve a versao do robot.
   **/
  public Date getDataVersaoSistema() { return new Date(dataVersaoSistema); }

  /**
   * Altera a versao do robot.
   **/
  public void setDataVersaoSistema(Date dataVersaoSistema) {
    if (dataVersaoSistema != null) this.dataVersaoSistema = new Date(dataVersaoSistema);
  }
}