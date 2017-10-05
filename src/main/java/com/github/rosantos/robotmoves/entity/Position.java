package com.github.rosantos.robotmoves.entity;

public class Position {

	Coordinate coordinate;

	EnumDirection direction;

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

	public void moveToNorth(int step) {
		moveY(step);
	}

	public void moveToEast(int step) {
		moveX(step);
	}

	public void moveToSouth(int step) {
		moveY(-step);
	}

	public void moveToWest(int step) {
		moveX(-step);
	}

	public EnumDirection turnRight(EnumAxisMovement axisMovement, int moviments) {
		direction = nextDirection(direction, axisMovement, moviments);
		return direction;
	}

	@Override
	public String toString() {
		return String.format("(%x, %x, %s)", coordinate.getX(), coordinate.getY(), direction);
	}

	private EnumDirection nextDirection(EnumDirection current, EnumAxisMovement axisMoviment, int moviments) {
		EnumDirection newDirection = current;
		int realMoviments = moviments % EnumDirection.values().length;
		switch (axisMoviment) {
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
	

	private void moveY(int step) {
		coordinate.setY(coordinate.getY() + step);
	}

	private void moveX(int step) {
		coordinate.setX(coordinate.getX() + step);
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

}
