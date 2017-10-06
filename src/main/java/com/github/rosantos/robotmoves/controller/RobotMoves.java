package com.github.rosantos.robotmoves.controller;

import java.util.List;
import com.github.rosantos.robotmoves.entity.EnumMovement;
import com.github.rosantos.robotmoves.entity.Movement;
import com.github.rosantos.robotmoves.entity.Position;
import com.github.rosantos.robotmoves.entity.Terrain;
import com.github.rosantos.robotmoves.util.MovementsUtil;

/**
 * @author Robson Ortega dos Santos
 * 
 *         Controle de Movimentos do Robo em marte, a mesma controla a posição do Robo no Terreno em
 *         que foi vinculado o robo
 *
 */
public class RobotMoves {

  private Position position = new Position();

  private Terrain terrain = new Terrain();

  /**
   * Movimenta o Robô sobre a Terreno configurado
   * 
   * @param steps Numero de Passos que o robo irá se deslocar
   * @return {@link Position} Posição e Direção do robô após seu deslocamento
   * @exception IllegalArgumentException caso o mesmo ultrapasse o limite do terreno
   */
  private Position move(int steps) {
    switch (position.getDirection()) {
      case NORTH:
        position.moveToNorth(steps);
        break;
      case EAST:
        position.moveToEast(steps);
        break;
      case SOUTH:
        position.moveToSouth(steps);
        break;
      case WEST:
        position.moveToWest(steps);
        break;
      default:
        break;
    }
    terrain.validPosition(position.getCoordinate());
    return position;
  }

  /**
   * Movimenta e Rotaciona o robo pelos comandos enviados pelos engenheiros.
   * 
   * @param commands Sequencia de Movimentos e Rotações que o Robo irá executar
   * @return {@link Position} Posição após o deslocamento do robo
   * @exception IllegalArgumentException Caso os comandos não sejam interpretados ou o movimento
   *            ultrapasse o limite do terreno retorna Movimenta o Robô
   */
  public Position execute(String commands) {
    List<Movement> movements = MovementsUtil.convertStringToMovements(commands);
    movements.stream().forEach(movement -> {
      if (EnumMovement.MOVE.equals(movement.getEnumMovement())) {
        move(movement.getSteps());
      } else {
        position.rotate(movement.getEnumMovement(), movement.getSteps());
      }
    });

    return position;
  }

}
