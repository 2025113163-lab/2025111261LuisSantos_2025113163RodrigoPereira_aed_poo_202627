package edu.ufp.inf.labirinto;


import java.util.List;


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