package com.github.rosantos.robotmoves.entity;

/**
 * @author Robson Ortega dos Santos
 * Coordenadas, onde apenas são permitidos valores positivos para X e Y  
 *
 */
public class Coordinate {

  protected int x = 0;
  protected int y = 0;

  public Coordinate(int x, int y) {
    setX(x);
    setY(y);
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    if (x < 0) {
      throw new IllegalArgumentException("Coordenada X deve ser maior que 0");
    }
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    if (y < 0) {
      throw new IllegalArgumentException("Coordenada Y deve ser maior que 0");
    }
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}
