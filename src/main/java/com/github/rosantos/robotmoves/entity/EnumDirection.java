package com.github.rosantos.robotmoves.entity;

public enum EnumDirection {
  NORTH("N"), EAST("E"), SOUTH("S"), WEST("W");

  private String label;

  private EnumDirection(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return label;
  }
}
