package com.github.rosantos.robotmoves.entity;

import java.util.Objects;

/**
 * @author Robson Ortega dos Santos
 * 
 *         Terreno de Marte, onde são delimitadas os limites válidos para o Robo
 */
public class Terrain extends Coordinate {

  /**
   * Terreno Padrão Superfície 5x5
   */
  public Terrain() {
    this(5, 5);
  }

  public Terrain(int limitX, int limitY) {
    super(limitX, limitY);
  }

  /**
   * Valida se a posição é válida de acordo com os limites do Terreno, em caso de falha retorn
   * {@link IllegalArgumentException} devido as cordenadas enviadas pelo engenheiro da NASA não são
   * válidas neste terreno
   * 
   * @param position Posição a qual o Robo se deslocaria
   */
  public void validPosition(Coordinate position) {
    Objects.requireNonNull(position, "Posicao nao pode ser nula");
    if (getX() <= position.getX()) {
      throw new IllegalArgumentException("Coordenada X ultrapassa o limite do Terreno");
    }

    if (getY() <= position.getY()) {
      throw new IllegalArgumentException("Coordenada Y ultrapassa o limite do Terreno");
    }
  }

}
