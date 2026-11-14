package edu.ufp.inf.labirinto;

public class TechRelic extends Artefact {

  public TechRelic(String id, double weight, double scientificValue, int dangerLevel, String description) {
    super(id, weight, scientificValue, dangerLevel, description);
  }

  @Override
  public double calculateImpact() {
    return getWeight() * getDangerLevel() * 2;
  }

}