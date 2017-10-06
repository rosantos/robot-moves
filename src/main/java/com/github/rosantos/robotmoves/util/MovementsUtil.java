package com.github.rosantos.robotmoves.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import com.github.rosantos.robotmoves.entity.EnumMovement;
import com.github.rosantos.robotmoves.entity.Movement;

/**
 * @author Robson Ortega dos Santos
 *
 *         Classe utilitária para a conversão dos Comandos em uma Lista de Movimentos, otimizando as
 *         Rotações para que o se consuma o minimo de energia do Robo e que não faça movimento
 *         desnecessário
 */
public class MovementsUtil {

  /**
   * Converte a sequencia de comandos em Movimentos e repetições do Movimento, otimizando comandos
   * do RL/LR sequenciais onde o resultado da direção manteria o mesmo, evitando o consumo
   * desnecessário de energia
   * 
   * @param commands Cadeia de Comandos
   * @return Lista de Movimentos
   * @exception IllegalArgumentException em caso de algum comando não ser válido
   */
  public static List<Movement> convertStringToMovements(String commands) {
    commands = commands.toUpperCase();

    if (!commands.matches(getCommandsValids())) {
      throw new IllegalArgumentException("Comando invalido");
    }

    for (Entry<EnumMovement, EnumMovement> entry : EnumMovement.getAxisAndReverses().entrySet()) {
      commands =
          commands.replace(entry.getKey().getLabel().concat(entry.getValue().getLabel()), "");
    }

    List<Movement> result = new ArrayList<>();
    Movement movement = null;
    for (int i = 0; i < commands.length(); i++) {
      String command = String.valueOf(commands.charAt(i));
      EnumMovement current = EnumMovement.getMovement(command);

      if (Objects.nonNull(current)) {
        if (Objects.nonNull(movement) && Objects.equals(movement.getEnumMovement(), current)) {
          movement.setSteps(movement.getSteps() + 1);
        } else {
          movement = new Movement(current, 1);
          result.add(movement);
        }
      }
    }

    return result;
  }

  /**
   * Retorna a lista de Comandos válidos
   * 
   * @return
   */
  private static String getCommandsValids() {
    return String.format("[%s]*", EnumMovement.getLabels().stream().reduce((a, b) -> a + b).get());
  }

}
