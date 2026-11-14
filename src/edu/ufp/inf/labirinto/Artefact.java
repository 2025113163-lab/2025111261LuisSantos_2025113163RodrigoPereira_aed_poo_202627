package edu.ufp.inf.labirinto;

import Integer;
import String;

public abstract class Artefact extends Entity {

  public double weight;

  public double scientificValue;

  public Integer dangerLevel;

  public String description;

  
  public double calculateImpact() {
  return 0.0;
  }

}