package edu.ufp.inf.labirinto;

// Representa um artefact recolhivel no labirinto
public abstract class Artefact extends Entity {

  private double weight;
  private double scientificValue;
  private int dangerLevel;
  private String description;

  public Artefact(String id, double weight, double scientificValue, int dangerLevel, String description) {
      super(id);
      if (weight < 0 || scientificValue < 0 || dangerLevel < 0) {
          throw new IllegalArgumentException("Weight / scientificValue / dangerLevel não podem ser negativos");
      }
      this.weight = weight;
      this.scientificValue = scientificValue;
      this.dangerLevel = dangerLevel;
      this.description = (description != null) ? description : "";
  }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id='" + getId() + "', weight=" + weight
                + ", scientificValue=" + scientificValue + ", dangerLevel=" + dangerLevel
                + ", description='" + description + "'}";
    }

  public abstract double calculateImpact();

    public double getWeight() { return weight; }
    public void setWeight(double weight) {
        if (weight < 0) throw new IllegalArgumentException("Weight invalido");
        this.weight = weight;
    }

    public double getScientificValue() { return scientificValue; }
    public void setScientificValue(double scientificValue) {
        if (scientificValue < 0) throw new IllegalArgumentException("ScientificValue invalido");
        this.scientificValue = scientificValue;
    }

    public int getDangerLevel() { return dangerLevel; }
    public void setDangerLevel(int dangerLevel) {
        if (dangerLevel < 0) throw new IllegalArgumentException("DangerLevel invalido");
        this.dangerLevel = dangerLevel;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = (description != null) ? description : "";
    }
}