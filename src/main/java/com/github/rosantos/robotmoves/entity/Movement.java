package com.github.rosantos.robotmoves.entity;

public class Movement {
  private EnumMovement axisMovement;
  
  private int steps = 0;
  
  public Movement(EnumMovement axisMovement, int steps) {
    this.axisMovement = axisMovement;
    this.steps = steps;
  }

  public EnumMovement getAxisMovement() {
    return axisMovement;
  }

  public void setAxisMovement(EnumMovement axisMovement) {
    this.axisMovement = axisMovement;
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
    result = prime * result + ((axisMovement == null) ? 0 : axisMovement.hashCode());
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
    if (axisMovement != other.axisMovement)
      return false;
    if (steps != other.steps)
      return false;
    return true;
  }

  
  
}
