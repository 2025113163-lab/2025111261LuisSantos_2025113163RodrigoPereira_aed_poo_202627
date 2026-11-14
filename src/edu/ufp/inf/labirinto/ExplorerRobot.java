package edu.ufp.inf.labirinto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExplorerRobot extends Robot {

  private double sensorRange;

  public ExplorerRobot(String id, String nome, int bateria, RobotStatus estado, Date dataVersaoSistema, double sensorRange) {
    super(id, nome, bateria, dataVersaoSistema, estado);
    if (sensorRange <= 0) {
        throw new IllegalArgumentException("A sensorRange deve ser superior a zero");
    }
    this.sensorRange = sensorRange;
  }

  public double getSensorRange() { return sensorRange; }
  public void setSensorRange(double sensorRange) {
    if (sensorRange <= 0) {
        throw new IllegalArgumentException("A sensorRange deve ser superior a zero");
    }
    this.sensorRange = sensorRange;
  }
}