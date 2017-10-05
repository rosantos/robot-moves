package com.github.rosantos.robotmoves.entity;

import java.util.Objects;

public class Terrain extends Coordinate {

	public Terrain() {
		this(5, 5);
	}

	public Terrain(int limitX, int limitY) {
		super(limitX, limitY);
	}

	public void validPosition(Coordinate position) {
		Objects.requireNonNull(position, "Posicao nao pode ser nula");
		if (getX() < position.getX()) {
			throw new IllegalArgumentException("Coordenada X ultrapassa o limite do Terreno");
		}
		
		if (getY() < position.getY()) {
			throw new IllegalArgumentException("Coordenada Y ultrapassa o limite do Terreno");
		}
	}

}
