package edu.ufp.inf.labirinto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExplorerRobot extends Robot {

  private double sensorRange;

  /**
   * Cria um robot explorador.
   *
   * @param id - identificador único
   * @param nome - nome do robot
   * @param bateria - nível de bateria (0 a 100)
   * @param estado - estado inicial
   * @param dataVersaoSistema - versão do sistema
   * @param sensorRange - alcance dos sensores
   * @throws IllegalArgumentException - se algum valor for inválido ou o alcance não for superior a zero
   */
  public ExplorerRobot(String id, String nome, int bateria, RobotStatus estado, Date dataVersaoSistema, double sensorRange) {
    super(id, nome, bateria, dataVersaoSistema, estado);
    if (sensorRange <= 0) {
        throw new IllegalArgumentException("A sensorRange deve ser superior a zero");
    }
    this.sensorRange = sensorRange;
  }
  /**
   * Devolve o alcance dos sensores.
   */
  public double getSensorRange() { return sensorRange; }
  /**
   * Altera o alcance dos sensores.
   *
   * @param sensorRange - novo alcance
   * @throws IllegalArgumentException - se não for superior a zero
   */
  public void setSensorRange(double sensorRange) {
    if (sensorRange <= 0) {
        throw new IllegalArgumentException("A sensorRange deve ser superior a zero");
    }
    this.sensorRange = sensorRange;
  }
}