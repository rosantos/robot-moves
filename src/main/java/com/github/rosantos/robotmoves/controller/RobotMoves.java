package com.github.rosantos.robotmoves.controller;

import java.util.List;
import com.github.rosantos.robotmoves.entity.EnumMovement;
import com.github.rosantos.robotmoves.entity.Movement;
import com.github.rosantos.robotmoves.entity.Position;
import com.github.rosantos.robotmoves.entity.Terrain;
import com.github.rosantos.robotmoves.util.MovementsUtil;

public class RobotMoves {
  
  private Position position = new Position();

  private Terrain terrain = new Terrain();

  private Position move(int i) {
    switch (position.getDirection()) {
      case NORTH:
        position.moveToNorth(i);
        break;
      case EAST:
        position.moveToEast(i);
        break;
      case SOUTH:
        position.moveToSouth(i);
        break;
      case WEST:
        position.moveToWest(i);
        break;
      default:
        break;
    }
    terrain.validPosition(position.getCoordinate());
    return position;
  }

  public Position execute(String commands) {
    List<Movement> movements = MovementsUtil.convertStringToMovements(commands);
    movements.stream().forEach(movement -> {
      if (EnumMovement.MOVE.equals(movement.getAxisMovement())) {
        move(movement.getSteps());
      } else {
        position.rotate(movement.getAxisMovement(), movement.getSteps());
      }
    });
    
    return position;
  }

}
