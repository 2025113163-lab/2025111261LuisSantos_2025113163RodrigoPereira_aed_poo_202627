package edu.ufp.inf.labirinto;

public class HazardousMaterial extends Artefact {

  public HazardousMaterial(double weight, double scientificValue, int dangerLevel, String description) {
    super(weight, scientificValue, dangerLevel, description);
  }

  public double calculateImpact() {
  return 0.0;
  }

}