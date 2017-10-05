package com.github.rosantos.robotmoves;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.rosantos.robotmoves.entity.EnumAxisMovement;
import com.github.rosantos.robotmoves.entity.EnumDirection;
import com.github.rosantos.robotmoves.entity.RobotMoves;
import com.github.rosantos.robotmoves.entity.Terrain;
import com.github.rosantos.robotmoves.entity.Position;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RobotMovesApplicationTests {

	@Test
	public void contextLoads() {
	}
		
	@Test
	public void checkInitialPosition() {
		Position position = new Position();
		Assert.assertEquals("(0, 0, N)", position.toString());
	}
	
	@Test
	public void moveToNorth() {
		Position position = new Position(0,0,EnumDirection.NORTH);
		position.moveToNorth(1);
		Assert.assertEquals("(0, 1, N)", position.toString());
	}

	@Test
	public void moveToEast() {
		Position position = new Position(0,0,EnumDirection.EAST);
		position.moveToEast(1);
		Assert.assertEquals("(1, 0, E)", position.toString());
	}
	
	@Test
	public void moveToSouth() {
		Position position = new Position(0,1,EnumDirection.SOUTH);
		position.moveToSouth(1);
		Assert.assertEquals("(0, 0, S)", position.toString());
	}
	
	@Test
	public void moveToWest() {
		Position position = new Position(1,0,EnumDirection.WEST);
		position.moveToWest(1);
		Assert.assertEquals("(0, 0, W)", position.toString());
	}

	@Test
	public void turnRight() {
		Position position = new Position(0,0,EnumDirection.NORTH);
		position.turnRight(EnumAxisMovement.RIGHT,1);
		Assert.assertEquals("(0, 0, E)", position.toString());
	}
	
	@Test
	public void turnRight7Movements() {
		Position position = new Position(0,0,EnumDirection.NORTH);
		position.turnRight(EnumAxisMovement.RIGHT,7);
		Assert.assertEquals("(0, 0, W)", position.toString());
	}
	
	@Test
	public void turnLeft() {
		Position position = new Position(0,0,EnumDirection.NORTH);
		position.turnRight(EnumAxisMovement.LEFT,1);
		Assert.assertEquals("(0, 0, W)", position.toString());
	}
	
	@Test
	public void turnLeft7Movements() {
		Position position = new Position(0,0,EnumDirection.NORTH);
		position.turnRight(EnumAxisMovement.LEFT,7);
		Assert.assertEquals("(0, 0, E)", position.toString());
	}
	
	@Test
	public void checkInitialTerrain() {
		Terrain terrain= new Terrain();
		Assert.assertEquals("(5, 5)", terrain.toString());
	}
	
	@Test
	public void checkValidMovement() {
		RobotMoves robotMoves = new RobotMoves();
		Position position = robotMoves.move(1);
		Assert.assertEquals("(0, 1, N)", position.toString());
	}
	
	@Test
	public void checkInvalidMovement() {
		RobotMoves robotMoves = new RobotMoves();
		try {
			robotMoves.move(6);			
		} catch (IllegalArgumentException e) {
			Assert.assertTrue(true);
		}
	}

}
