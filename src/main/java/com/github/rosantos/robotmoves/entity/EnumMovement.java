package com.github.rosantos.robotmoves.entity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Robson Ortega dos Santos
 * 
 *         Possiveis Movimentos de um Robo
 *
 */
public enum EnumMovement {
  /**
   * Movimento: MOVE/Movimentar Para Frente, Movimento Inverso: Não Possui
   */
  MOVE("M", null),
  /**
   * Movimento: RIGHT/Rotacionar para a Direita, Movimento Inverso: Left/Esquerda
   */
  RIGHT("R", "L"),
  /**
   * Movimento: LEFT/Rotacionar para a Esquerda, Movimento Inverso: Rigth/Direita
   */
  LEFT("L", "R");

  private String label;
  private String reverseLabel;

  /**
   * Tipo de Movimento
   * 
   * @param label Abreviação do Movimento
   * @param reverseLabel Abreviação do Movimento Inverso
   */
  private EnumMovement(String label, String reverseLabel) {
    this.label = label;
    this.reverseLabel = reverseLabel;
  }

  /**
   * Abreviação do Movimento
   * 
   * @return Abreviação do Movimento
   */
  public String getLabel() {
    return label;
  }

  /**
   * Recupera o tipo do Movimento através da abreviatura do movimento
   * 
   * @param label Abreviatura do Movimento
   * @return Tipo do Movimento
   */
  public static EnumMovement getMovement(String label) {
    return Arrays.asList(EnumMovement.values()).stream()
        .filter(moviment -> moviment.getLabel().equalsIgnoreCase(label)).findFirst().get();
  }


  /**
   * Recupera as abreviaturas dos movimentos
   * 
   * @return As Abreviaturas dos Movimentos
   */
  public static List<String> getLabels() {
    return Arrays.asList(EnumMovement.values()).stream().map(EnumMovement::getLabel)
        .collect(Collectors.toList());
  }

  /**
   * Tipo de Movimento Inverso ao Movimento passado por parâmetro
   * 
   * @param movement Movimento
   * @return {@link EnumMovement} Movimento Inverso
   */
  public static EnumMovement getInverseAxisMovement(EnumMovement movement) {
    return Objects.nonNull(movement.reverseLabel) ? getMovement(movement.reverseLabel) : null;
  }

  /**
   * Movimentos e seus respectivos movimentos inversos
   * 
   * @return Tipos de Movimentos e seus respectivos Tipos de Movimentos
   */
  public static Map<EnumMovement, EnumMovement> getAxisAndReverses() {
    Map<EnumMovement, EnumMovement> map = new HashMap<>();
    Arrays.asList(EnumMovement.values()).stream()
        .filter(axisMovement -> Objects.nonNull(axisMovement.reverseLabel))
        .forEach(axisMovement -> map.put(axisMovement, getInverseAxisMovement(axisMovement)));
    return map;
  }

}
