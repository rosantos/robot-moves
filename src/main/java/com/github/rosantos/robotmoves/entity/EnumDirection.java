package com.github.rosantos.robotmoves.entity;

/**
 * @author Robson Ortega dos Santos
 * 
 *         Possíveis direções do Robo, as mesmas estão registradas em sentido horário para melhor
 *         eficiência no calculo de rotação
 *
 */
public enum EnumDirection {
  /**
   * Direção: North/Norte, Abreviação: "N"
   */
  NORTH("N"),
  /**
   * Direção: East/Leste, Abreviação: "E"
   */
  EAST("E"),
  /**
   * Direção: SOUTH/Sul, Abreviação: "S"
   */
  SOUTH("S"),
  /**
   * Direção: West/Oeste, Abreviação: "W"
   */
  WEST("W");

  private String label;

  /**
   * Direção
   * 
   * @param label Abreviação da Direção do Robo
   */
  private EnumDirection(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    // Sobrescrito para que exiba o valor requisitado e não o nome da direção
    return label;
  }
}
