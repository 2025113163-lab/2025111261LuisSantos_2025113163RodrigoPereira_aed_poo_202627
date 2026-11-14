package edu.ufp.inf.labirinto;

// Representa uma estação de manutenção e recarga de robot no labirinto
public class MaintenanceStation extends Entity {

  private int capacity;
  private int availableBays;

  /**
   * Construtor da estacao de manutencao com capacidade e slots/bays determinadas
   *
   * @param id - identificador unico da estação
   * @param capacity - capacidade maxima de slots/bays (>0)
   * @param availableBays - numero de slots atualmente disponiveis
   * @throws IllegalArgumentException
   */
  public MaintenanceStation(String id, int capacity, int availableBays) {
    super(id);
    if (capacity <= 0) {
      throw new IllegalArgumentException("A capacidade da estacao deve ser superior a zero");
    }
    if (availableBays < 0 || availableBays > capacity) {
      throw new IllegalArgumentException("O numero de slots/bays disponiveis deve estar entre 0 e " + capacity + ".");
    }
    this.capacity = capacity;
    this.availableBays = availableBays;
  }

  /**
   * Construtor de estacao de manutencao com todas as slots/bays livres
   *
   * @param id - identificador unico da estação
   * @param capacity capacidade maxima de slots/bays
   */
  public MaintenanceStation(String id, int capacity) {
    this(id, capacity, capacity);
  }

  /**
   * Recarrega a bateria de um robot até 100%, ocupando uma slot/bay disponivel
   *
   * @param r - robot
   * @return {@code true} - se houver slot livre e a recarga for iniciada; {@code false} caso contrario
   */
  public boolean rechargeRobot(Robot r) {
    if (r == null || availableBays <= 0) {
      return false;
    }
    availableBays--;
    r.setEstado(RobotStatus.CHARGING);
    r.setBateria(100);
    return true;
  }

  /**
   * Liberta uma slot de carregamento e coloca o robot em estado de espera
   *
   * @param r - robot
   * @return {@code true} se a slot for libertada com sucesso; {@code false} caso contrario
   */
  public boolean releaseBay(Robot r) {
    if (r == null || availableBays >= capacity) {
      return false;
    }
    availableBays++;
    if (r.getEstado() == RobotStatus.CHARGING || r.getEstado() == RobotStatus.MAINTENANCE) {
      r.setEstado(RobotStatus.IDLE);
    }
    return true;
  }

  public int getCapacity() { return capacity; }
  public void setCapacity(int capacity) {
    if (capacity <= 0 || capacity < (this.capacity - this.availableBays)) {
      throw new IllegalArgumentException("Nova capacidade invalida face às slots atualmente ocupadas");
    }
    int occupiedBays = this.capacity - this.availableBays;
    this.capacity = capacity;
    this.availableBays = capacity - occupiedBays;
  }

  public int getAvailableBays() { return availableBays; }
  public void setAvailableBays(int availableBays) {
    if (availableBays < 0 || availableBays > capacity) {
      throw new IllegalArgumentException("Slots disponiveis devem estar entre 0 e " + capacity);
    }
    this.availableBays = availableBays;
  }

  @Override
  public String toString() {
    return "MaintenanceStation{" + "id='" + getId() + '\'' + ", availableBays=" + availableBays + "/" + capacity + '}';
  }
}