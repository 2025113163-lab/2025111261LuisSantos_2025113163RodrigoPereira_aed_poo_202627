package edu.ufp.inf.labirinto;

public class EnergyCrystal extends Artefact {

  public EnergyCrystal(String id, double weight, double scientificValue, int dangerLevel, String description) {
    super(id, weight, scientificValue, dangerLevel, description);
  }

  @Override
  public double calculateImpact() {
    return getWeight() * getDangerLevel() * 1.75;
  }

}