package edu.ufp.inf.labirinto;

public class TechRelic extends Artefact {

  public TechRelic(double weight, double scientificValue, int dangerLevel, String description) {
    super(weight, scientificValue, dangerLevel, description);
  }

  public double calculateImpact() {
  return 0.0;
  }

}