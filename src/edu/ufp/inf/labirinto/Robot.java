package edu.ufp.inf.labirinto;

import Integer;
import java.util.List;
import String;

public abstract class Robot extends Entity {

  public String nome;

  public Integer bateria;

  public RobotStatus estado;

  public Date dataVersaoSistema;

    /**
    * 
    *
   */
  public List<MissionLog> missionLog;

}