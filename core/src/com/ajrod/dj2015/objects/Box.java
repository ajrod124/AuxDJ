package com.ajrod.dj2015.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Box {
	
	protected float x, y, width, height;
	
	public boolean contains(float x, float y) {
		return x > this.x - width/2 &&
				x < this.x + width/2 &&
				y > this.y - height/2 &&
				y < this.y + height/2;
	}
	
	public abstract void update (float dt);
	public abstract void render (SpriteBatch sb);
}

