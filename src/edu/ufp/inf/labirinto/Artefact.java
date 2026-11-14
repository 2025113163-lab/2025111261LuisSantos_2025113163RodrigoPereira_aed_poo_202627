package edu.ufp.inf.labirinto;


public abstract class Artefact extends Entity {

  public double weight;

  public double scientificValue;

  public int dangerLevel;

  public String description;

    public Artefact(double weight, double scientificValue, int dangerLevel, String description) {
        this.weight = weight;
        this.scientificValue = scientificValue;
        this.dangerLevel = dangerLevel;
        this.description = description;
    }



    public double calculateImpact() {
  return 0.0;
  }

}