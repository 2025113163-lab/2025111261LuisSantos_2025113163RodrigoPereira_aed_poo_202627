package edu.ufp.inf.labirinto;

public class EnergyCrystal extends Artefact {

  /**
   * Cria uma cristal energético.
   *
   * @param id - identificador único
   * @param weight - peso
   * @param scientificValue - valor científico
   * @param dangerLevel - nível de perigo
   * @param description - descrição
   * @throws IllegalArgumentException - se algum valor for inválido (ver Artefact)
   */
  public EnergyCrystal(String id, double weight, double scientificValue, int dangerLevel, String description) {
    super(id, weight, scientificValue, dangerLevel, description);
  }

  @Override
  public double calculateImpact() {
    return getWeight() * getDangerLevel() * 1.75;
  }

}