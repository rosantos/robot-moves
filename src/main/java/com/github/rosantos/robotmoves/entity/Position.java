package com.github.rosantos.robotmoves.entity;

/**
 * @author Robson Ortega dos Santos
 * 
 *         Posicao do Robo, possui as coordenadas e a direção que ele se encontra
 */
public class Position {

  Coordinate coordinate;

  EnumDirection direction;

  /**
   * Posição padrão do Robo, coordenadas (0, 0) e em direção ao Norte
   */
  public Position() {
    this(0, 0, EnumDirection.NORTH);
  }

  public Position(int x, int y, EnumDirection direction) {
    this.coordinate = new Coordinate(x, y);
    this.direction = direction;
  }

  public EnumDirection getDirection() {
    return direction;
  }

  /**
   * Movimenta o Robo para o Norte
   * 
   * @param step Número de Passos
   */
  public void moveToNorth(int step) {
    moveY(step);
  }

  /**
   * Movimenta o Robo para o Leste
   * 
   * @param step Número de Passos
   */
  public void moveToEast(int step) {
    moveX(step);
  }

  /**
   * Movimenta o Robo para o Sul
   * 
   * @param step Número de Passos
   */
  public void moveToSouth(int step) {
    moveY(-step);
  }

  /**
   * Movimenta o Robo para o Oeste
   * 
   * @param step Número de Passos
   */
  public void moveToWest(int step) {
    moveX(-step);
  }

  public EnumDirection rotate(EnumMovement axisMovement, int steps) {
    direction = nextDirection(direction, axisMovement, steps);
    return direction;
  }

  /**
   * Rotaciona o número de vezes para a direção solicitada
   * 
   * @param current Direção atual
   * @param enumMoviment Tipo de Movimento {@link EnumMovement} LEFT/RIGHT
   * @param steps Numero de Iterações
   * @return Retorna nova posição
   */
  @SuppressWarnings("incomplete-switch")
  private EnumDirection nextDirection(EnumDirection current, EnumMovement enumMoviment, int steps) {
    EnumDirection newDirection = current;
    int realMoviments = steps % EnumDirection.values().length;
    switch (enumMoviment) {
      case LEFT:
        for (int i = 0; i < realMoviments; i++) {
          // Se primeiro item da lista a mesma vai para o final da lista
          if (newDirection.ordinal() == 0) {
            newDirection = EnumDirection.values()[EnumDirection.values().length - 1];
          } else {
            newDirection = EnumDirection.values()[newDirection.ordinal() - 1];
          }
        }
        break;

      case RIGHT:
        for (int i = 0; i < realMoviments; i++) {
          // Se ultimo item da lista a direção vai para o inicio da lista
          if (newDirection.ordinal() == EnumDirection.values().length - 1) {
            newDirection = EnumDirection.values()[0];
          } else {
            newDirection = EnumDirection.values()[newDirection.ordinal() + 1];
          }
        }
        break;
    }
    return newDirection;
  }


  /**
   * Movimentos nas direções NORTH/SOUTH
   * 
   * @param step número de iterações
   */
  private void moveY(int step) {
    coordinate.setY(coordinate.getY() + step);
  }

  /**
   * Movimentos nas direções EAST/WEST
   * 
   * @param step número de iterações
   */
  private void moveX(int step) {
    coordinate.setX(coordinate.getX() + step);
  }

  public Coordinate getCoordinate() {
    return coordinate;
  }

  @Override
  public String toString() {
    return String.format("(%x, %x, %s)", coordinate.getX(), coordinate.getY(), direction);
  }

}
