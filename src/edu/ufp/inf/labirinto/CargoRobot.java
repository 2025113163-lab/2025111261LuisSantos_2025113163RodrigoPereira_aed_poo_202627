package edu.ufp.inf.labirinto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CargoRobot extends Robot {

  private double capacidadeMaxima;
  private final List<Artefact> artifacts;

  public CargoRobot(String id, String nome, int bateria, RobotStatus estado, Date dataVersaoSistema, double capacidadeMaxima) {
    super(id, nome, bateria, dataVersaoSistema, estado);
    if (capacidadeMaxima <= 0) {
      throw new IllegalArgumentException("A capacidade maxima deve ser superior a zero");
    }
    this.capacidadeMaxima = capacidadeMaxima;
    this.artifacts = new ArrayList<>();
  }

  public double getCurrentLoad() {
    double total = 0.0;
    for (Artefact a : artifacts) {
      total += a.getWeight();
    }
    return total;
  }

  public boolean addArtefact(Artefact a) {
    if (a == null) return false;
    if (getCurrentLoad() + a.getWeight() > capacidadeMaxima) {
      return false;
    }
    return artifacts.add(a);
  }

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