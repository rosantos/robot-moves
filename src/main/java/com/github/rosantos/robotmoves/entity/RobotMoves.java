package com.github.rosantos.robotmoves.entity;

public class RobotMoves {

	private Position position = new Position();
	
	private Terrain terrain = new Terrain();

	public Position move(int i) {
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

}
