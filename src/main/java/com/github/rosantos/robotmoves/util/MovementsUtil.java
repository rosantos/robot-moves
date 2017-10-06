package com.github.rosantos.robotmoves.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import com.github.rosantos.robotmoves.entity.EnumMovement;
import com.github.rosantos.robotmoves.entity.Movement;

public class MovementsUtil {

  public static List<Movement> convertStringToMovements(String commands) {
    commands = commands.toUpperCase();
    
    if (!commands.matches(getCommandsValids())) {
      throw new IllegalArgumentException("Comando invalido");
    }

    for (Entry<EnumMovement, EnumMovement> entry : EnumMovement.getAxisAndReverses()
        .entrySet()) {
      commands = commands
          .replace(entry.getKey().getLabel().concat(entry.getValue().getLabel()), "");
    }

    List<Movement> result = new ArrayList<>();
    Movement movement = null;
    for (int i = 0; i < commands.length(); i++) {
      String command = String.valueOf(commands.charAt(i));
      EnumMovement current = EnumMovement.getAxisMovement(command);

      if (Objects.nonNull(current)) {
        if (Objects.nonNull(movement) && Objects.equals(movement.getAxisMovement(), current)) {
          movement.setSteps(movement.getSteps() + 1);
        } else {
          movement = new Movement(current, 1);
          result.add(movement);
        }
      }
    }

    return result;
  }

  private static String getCommandsValids() {
    return String.format("[%s]*",
        EnumMovement.getLabels().stream().reduce((a, b) -> a + b).get());
  }

}
