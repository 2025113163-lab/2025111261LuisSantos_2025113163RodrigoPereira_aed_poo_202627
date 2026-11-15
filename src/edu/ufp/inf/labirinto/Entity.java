package edu.ufp.inf.labirinto;

import java.io.Serializable;
import java.util.Objects;

// Superclass abstrata que representa qualquer entidade do dominio
public abstract class Entity implements Serializable {

  private final String id;

  /**
   * Constroi uma entidade com um id unico
   *
   * @param id - identificador unico (não nulo e sempre prenchido)
   * @throws IllegalArgumentException - se o id for nulo ou estiver em branco
   */

  public Entity(String id) throws IllegalArgumentException {
    if (id == null || id.isBlank()) {
      throw new IllegalArgumentException("O id da entidade não pode ser nulo ou vazio.");
    }
    this.id = id;
  }

  public String getId() { return id; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Entity entity = (Entity) o;
    return Objects.equals(id, entity.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}