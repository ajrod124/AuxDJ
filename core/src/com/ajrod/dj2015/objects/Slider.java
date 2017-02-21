package com.ajrod.dj2015.objects;

import com.ajrod.dj2015.Dj2015;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Slider extends Box {

	private TextureRegion slider, bar, filler;
	private float leftBound, rightBound;
	private final float SLOPE;
	private boolean pitch;

	public Slider (float y, boolean b) {
		Texture tex;
		TextureRegion reg;
		
		tex = new Texture(Gdx.files.internal("slider.png"));
		reg = new TextureRegion(tex);
		slider = reg;
		
		tex = new Texture(Gdx.files.internal("pitch_bar.png"));
		reg = new TextureRegion(tex);
		bar = reg;
		
		tex = new Texture(Gdx.files.internal("bar_color.png"));
		reg = new TextureRegion(tex);
		filler = reg;
		
		this.y = y;
		width = 100;
		height = 100;
		leftBound = 21;
		rightBound = 519;
		pitch = b;
		if (b) {
			SLOPE = 1.5f/518f;
			x = 0.5f/SLOPE;
		}
		else {
			SLOPE = 1f/518f;
			x = 1f/SLOPE;
		}
	}

	public void update(float dt) {}

	public void render(SpriteBatch sb) {
		sb.draw(filler, 21f, y - height/2f, x - 21f, 25f);
		sb.draw(bar, 20f, y - height/2f, 500f, 25f);
		if (pitch) {
			Dj2015.font.draw(sb, "PITCH/SPEED", 277, y - 55f);
			Dj2015.font.draw(sb, String.format("%.2f", Dj2015.sounds.getSpeed()), 21, y - 55f);
		}
		else {
			Dj2015.font.draw(sb, "VOLUME", 375, y - 55f);
			Dj2015.font.draw(sb, String.format("%.2f", Dj2015.sounds.getVolume()), 21, y - 55f);
		}
		sb.draw(slider, x - width/2f, y - width/2f, 100f, 100f);
	}

	public void onClick(float x) {
		if (x >= leftBound && x <= rightBound) {
			this.x = x;
			if (pitch) setPitch();
			else setVolume();
		}
	}
	
	private void setPitch() {
		Dj2015.sounds.setSpeed(SLOPE*x + 0.5f);
	}
	
	private void setVolume() {
		Dj2015.sounds.setVolume(SLOPE*x);
	}
	
	public void setParams(float vVolume, float vPitch) {
		if (pitch) {
			Dj2015.sounds.setSpeed(vPitch);
			this.x = (vPitch - 0.5f)/SLOPE;
		}
		else {
			Dj2015.sounds.setVolume(vVolume);
			this.x = vVolume/SLOPE;
		}
	}
}
