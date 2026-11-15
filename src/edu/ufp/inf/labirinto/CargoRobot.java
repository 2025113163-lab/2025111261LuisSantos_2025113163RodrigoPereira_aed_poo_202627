package edu.ufp.inf.labirinto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CargoRobot extends Robot {

  private double capacidadeMaxima;
  private final List<Artefact> artifacts;


  /**
   * Cria um robot de carga.
   *
   * @param id - identificador único
   * @param nome - nome do robot
   * @param bateria - nível de bateria (0 a 100)
   * @param estado - estado inicial
   * @param dataVersaoSistema - versão do sistema
   * @param capacidadeMaxima - peso máximo transportável
   * @throws IllegalArgumentException - se algum valor for inválido ou a capacidade não for superior a zero
   */
  public CargoRobot(String id, String nome, int bateria, RobotStatus estado, Date dataVersaoSistema, double capacidadeMaxima) {
    super(id, nome, bateria, dataVersaoSistema, estado);
    if (capacidadeMaxima <= 0) {
      throw new IllegalArgumentException("A capacidade maxima deve ser superior a zero");
    }
    this.capacidadeMaxima = capacidadeMaxima;
    this.artifacts = new ArrayList<>();
  }
  /**
   * Calcula a carga atual.
   */
  public double getCurrentLoad() {
    double total = 0.0;
    for (Artefact a : artifacts) {
      total += a.getWeight();
    }
    return total;
  }
  /**
   * Adiciona um artefacto à carga.
   *
   * @param a - artefacto a adicionar
   */
  public boolean addArtefact(Artefact a) {
    if (a == null) return false;
    if (getCurrentLoad() + a.getWeight() > capacidadeMaxima) {
      return false;
    }
    return artifacts.add(a);
  }
  /**
   * Remove um artefacto da carga.
   *
   * @param a - artefacto a remover
   */
  public boolean removeArtefact(Artefact a) {
    return artifacts.remove(a);
  }

  public List<Artefact> getArtifacts() {
    return Collections.unmodifiableList(artifacts);
  }

  public double getCapacidadeMaxima() { return capacidadeMaxima; }
  public void setCapacidadeMaxima(double capacidadeMaxima) {
    if (capacidadeMaxima < getCurrentLoad()) {
      throw new IllegalArgumentException("Nova capacidade inferior a carga atual");
    }
    this.capacidadeMaxima = capacidadeMaxima;
  }
}