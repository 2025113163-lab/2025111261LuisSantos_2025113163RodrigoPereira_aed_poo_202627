package edu.ufp.inf.labirinto;

public class HazardousMaterial extends Artefact {

  /**
   * Cria um material perigoso.
   *
   * @param id - identificador único
   * @param weight - peso
   * @param scientificValue - valor científico
   * @param dangerLevel - nível de perigo
   * @param description - descrição
   * @throws IllegalArgumentException - se algum valor for inválido (ver Artefact)
   */
  public HazardousMaterial(String id, double weight, double scientificValue, int dangerLevel, String description) {
    super(id, weight, scientificValue, dangerLevel, description);
  }

  @Override
  public double calculateImpact() {
    return getWeight() * getDangerLevel() * 1.5;
  }

}