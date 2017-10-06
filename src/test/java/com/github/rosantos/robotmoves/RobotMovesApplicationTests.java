package com.github.rosantos.robotmoves;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.github.rosantos.robotmoves.controller.RobotMoves;
import com.github.rosantos.robotmoves.entity.EnumDirection;
import com.github.rosantos.robotmoves.entity.EnumMovement;
import com.github.rosantos.robotmoves.entity.Movement;
import com.github.rosantos.robotmoves.entity.Position;
import com.github.rosantos.robotmoves.entity.Terrain;
import com.github.rosantos.robotmoves.util.MovementsUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RobotMovesApplicationTests {

  @Test
  public void contextLoads() {}

  @Test
  public void checkInitialPosition() {
    Position position = new Position();
    Assert.assertEquals("(0, 0, N)", position.toString());
  }

  @Test
  public void moveToNorth() {
    Position position = new Position(0, 0, EnumDirection.NORTH);
    position.moveToNorth(1);
    Assert.assertEquals("(0, 1, N)", position.toString());
  }

  @Test
  public void moveToEast() {
    Position position = new Position(0, 0, EnumDirection.EAST);
    position.moveToEast(1);
    Assert.assertEquals("(1, 0, E)", position.toString());
  }

  @Test
  public void moveToSouth() {
    Position position = new Position(0, 1, EnumDirection.SOUTH);
    position.moveToSouth(1);
    Assert.assertEquals("(0, 0, S)", position.toString());
  }

  @Test
  public void moveToWest() {
    Position position = new Position(1, 0, EnumDirection.WEST);
    position.moveToWest(1);
    Assert.assertEquals("(0, 0, W)", position.toString());
  }

  @Test
  public void turnRight() {
    Position position = new Position(0, 0, EnumDirection.NORTH);
    position.rotate(EnumMovement.RIGHT, 1);
    Assert.assertEquals("(0, 0, E)", position.toString());
  }

  @Test
  public void turnRight7Movements() {
    Position position = new Position(0, 0, EnumDirection.NORTH);
    position.rotate(EnumMovement.RIGHT, 7);
    Assert.assertEquals("(0, 0, W)", position.toString());
  }

  @Test
  public void turnLeft() {
    Position position = new Position(0, 0, EnumDirection.NORTH);
    position.rotate(EnumMovement.LEFT, 1);
    Assert.assertEquals("(0, 0, W)", position.toString());
  }

  @Test
  public void turnLeft7Movements() {
    Position position = new Position(0, 0, EnumDirection.NORTH);
    position.rotate(EnumMovement.LEFT, 7);
    Assert.assertEquals("(0, 0, E)", position.toString());
  }

  @Test
  public void checkInitialTerrain() {
    Terrain terrain = new Terrain();
    Assert.assertEquals("(5, 5)", terrain.toString());
  }

  @Test
  public void getValidCommands() {
    List<String> validMoviments = EnumMovement.getLabels();
    String[] expecteds = {"M", "R", "L"};
    Assert.assertArrayEquals(expecteds, validMoviments.toArray());
  }

  @Test
  public void checkReverseRight() {
    Assert.assertEquals(EnumMovement.LEFT,
        EnumMovement.getInverseAxisMovement(EnumMovement.RIGHT));
  }

  @Test
  public void checkReverseLeft() {
    Assert.assertEquals(EnumMovement.RIGHT,
        EnumMovement.getInverseAxisMovement(EnumMovement.LEFT));
  }

  @Test
  public void moveToForward3() {
    List<Movement> movements = MovementsUtil.convertStringToMovements("MRLMMLR");
    Movement[] expecteds = {new Movement(EnumMovement.MOVE, 3)};
    Assert.assertArrayEquals(expecteds, movements.toArray());
  }

  @Test
  public void moveToForward3Left2() {
    List<Movement> movements = MovementsUtil.convertStringToMovements("MRLMMLRLMLRM");
    Movement[] expecteds = {new Movement(EnumMovement.MOVE, 3),
        new Movement(EnumMovement.LEFT, 1), new Movement(EnumMovement.MOVE, 2)};
    Assert.assertArrayEquals(expecteds, movements.toArray());
  }

  @Test
  public void moveToForward3Right2() {
    List<Movement> movements = MovementsUtil.convertStringToMovements("MRLMMRRLMLRM");
    Movement[] expecteds = {new Movement(EnumMovement.MOVE, 3),
        new Movement(EnumMovement.RIGHT, 1), new Movement(EnumMovement.MOVE, 2)};
    Assert.assertArrayEquals(expecteds, movements.toArray());
  }
  
  @Test
  public void invalidCommands() {
    try {
      MovementsUtil.convertStringToMovements("MRLA");      
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }
  
  /** Testes do descritivo do problema **/
  
  /**
   * Movimento com rotações para direita: 
   * Entrada: curl -s --request POST http://localhost:8080/rest/mars/MMRMMRMM
   * Saída esperada: (2, 0, S)
   */
  @Test 
  public void movimentoComRotacoesParaDireita() {
    RobotMoves robotMoves = new RobotMoves();
    String expected = "(2, 0, S)";
    Assert.assertEquals(expected, robotMoves.execute("MMRMMRMM").toString());
  }
  
  /**
   * Movimento para esquerda:
   * Entrada: curl -s --request POST http://localhost:8080/rest/mars/MML
   * Saída esperada: (0, 2, W)
   */
  @Test 
  public void movimentoParaEsquerda() {
    RobotMoves robotMoves = new RobotMoves();
    String expected = "(0, 2, W)";
    Assert.assertEquals(expected, robotMoves.execute("MML").toString());
  }

  /**
   * Repetição da requisição com movimento para esquerda:
   * Entrada: curl -s --request POST http://localhost:8080/rest/mars/MML
   * Saída esperada: (0, 2, W)
   */
  @Test 
  public void repeticaoMovimentoParaEsquerda() {
    RobotMoves robotMoves = new RobotMoves();
    String expected = "(0, 2, W)";
    Assert.assertEquals(expected, robotMoves.execute("MML").toString());
  }
  
  /**
   * Comando inválido:
   * Entrada: curl -s --request POST http://localhost:8080/rest/mars/AAA
   * Saída esperada: 400 Bad Request (IllegalArgumentException)
   */
  @Test 
  public void ComandoInvalido() {
    try {
    RobotMoves robotMoves = new RobotMoves();
    robotMoves.execute("AAA");
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }
  
  /**
   * Posição inválida:
   * Entrada curl -s --request POST http://localhost:8080/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM
   * Saída esperada: 400 Bad Request (IllegalArgumentException)
   */
  @Test 
  public void PosicaoInvalida() {
    try {
    RobotMoves robotMoves = new RobotMoves();
    robotMoves.execute("MMMMMMMMMMMMMMMMMMMMMMMM");
    } catch (IllegalArgumentException e) {
      Assert.assertTrue(true);
    }
  }
  
}
