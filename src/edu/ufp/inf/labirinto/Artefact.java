package edu.ufp.inf.labirinto;

/**
 * Artefacto recolhível do labirinto (classe abstrata; cada tipo calcula o seu impacto).
 *
 * @author Luis Santos &amp; Rodrigo Pereira
 * @version 1.0
 */
public abstract class Artefact extends Entity {

  /** Peso, valor cientifico, Nivel de perigo e descrição do Artefacto **/
  private double weight;
  private double scientificValue;
  private int dangerLevel;
  private String description;

  /**
     * Cria um artefacto.
     *
     * @param id - identificador único
     * @param weight - peso
     * @param scientificValue - valor científico
     * @param dangerLevel - nível de perigo
     * @param description - descrição (se null, fica vazia)
     * @throws IllegalArgumentException - se o id for inválido ou algum valor numérico for negativo
     */
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

/**
 * Calcula o impacto operacional do artefacto.
 **/
  public abstract double calculateImpact();


  /**
          * Devolve o peso. Altera o peso
     **/
    public double getWeight() { return weight; }
    public void setWeight(double weight) {
        if (weight < 0) throw new IllegalArgumentException("Weight invalido");
        this.weight = weight;
    }

/**
 * Devolve o valor científico. Altera o valor cientifico
 **/
    public double getScientificValue() { return scientificValue; }
    public void setScientificValue(double scientificValue) {
        if (scientificValue < 0) throw new IllegalArgumentException("ScientificValue invalido");
        this.scientificValue = scientificValue;
    }

    /**
     * Devolve o nivel de perigo. Altera o nivel de perigo
     **/
    public int getDangerLevel() { return dangerLevel; }
    public void setDangerLevel(int dangerLevel) {
        if (dangerLevel < 0) throw new IllegalArgumentException("DangerLevel invalido");
        this.dangerLevel = dangerLevel;
    }

    /**
     * Devolve a descricao. Altera a descricao
     **/
    public String getDescription() { return description; }
    public void setDescription(String description) {
        this.description = (description != null) ? description : "";
    }
}