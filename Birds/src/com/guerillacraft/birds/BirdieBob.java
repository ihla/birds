package com.guerillacraft.birds;

import com.badlogic.gdx.math.Vector2;

public class BirdieBob {
	private Vector2 initPosition = new Vector2();
	private Vector2 speed = new Vector2();
	private Vector2 position = new Vector2();
	
	public BirdieBob(Vector2 initPosition, Vector2 speed) {
		this.initPosition.set(initPosition);
		this.position.set(initPosition);
		this.speed.set(speed);
	}
	
	public void update(float deltaTime) {
		
	}

}
