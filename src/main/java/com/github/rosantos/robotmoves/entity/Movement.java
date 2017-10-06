package com.github.rosantos.robotmoves.entity;

/**
 * @author Robson Ortega dos Santos
 *
 *         Movimento válido a ser executado pelo Robo, o mesmo possui o movimento e o numero de
 *         vezes que deve executar o mesmo
 */
public class Movement {
  private EnumMovement enumMovement;

  private int steps = 0;

  /**
   * @param enumMovement Tipo de Movimento
   * @param steps Numero de passos do Movimento
   */
  public Movement(EnumMovement enumMovement, int steps) {
    this.enumMovement = enumMovement;
    this.steps = steps;
  }

  public EnumMovement getEnumMovement() {
    return enumMovement;
  }

  public void setAxisMovement(EnumMovement enumMovement) {
    this.enumMovement = enumMovement;
  }

  public int getSteps() {
    return steps;
  }

  public void setSteps(int steps) {
    this.steps = steps;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((enumMovement == null) ? 0 : enumMovement.hashCode());
    result = prime * result + steps;
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Movement other = (Movement) obj;
    if (enumMovement != other.enumMovement)
      return false;
    if (steps != other.steps)
      return false;
    return true;
  }



}
