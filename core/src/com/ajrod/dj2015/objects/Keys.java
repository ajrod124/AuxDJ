package com.ajrod.dj2015.objects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Keys extends Box {

	private TextureRegion[] button;
	private TextureRegion[] glow;
	private TextureRegion noGlow;
	private float fadeTimer;
	private boolean fade;
	private Random rand;
	private int color;
	
	public Keys(float x, float y) {
		Texture tex = new Texture(Gdx.files.internal("thebuttons.png"));
		TextureRegion reg = new TextureRegion(tex);
		TextureRegion[][] temp = reg.split(95, 95);
		button = new TextureRegion[2];
		for (int i = 0; i < 2; i++) button[i] = temp[0][i];
		
		tex = new Texture(Gdx.files.internal("button_colors.png"));
		reg = new TextureRegion(tex);
		TextureRegion[][] temp2 = reg.split(115, 115);
		glow = new TextureRegion[10];
		noGlow = temp2[0][9];
		for (int i = 0; i < 9; i++) glow[i] = temp2[0][i];
		
		this.x = x;
		this.y = y;
		width = 95;
		height = 95;
		fadeTimer = 0;
		fade = false;
		rand = new Random();
	}

	public void update(float dt) {
		if (fade) {
			if (fadeTimer < 1.0f) {
				fadeTimer += dt*4;
				if (fadeTimer > 1.0f) {
					fadeTimer = 1.0f;
					fade = false;
				}
			}
		}
		else {
			if (fadeTimer > 0f) {
				fadeTimer -= dt*4;
				if (fadeTimer < 0f) {
					fadeTimer = 0;
				}
			}
		}
	}

	public void render(SpriteBatch sb) {
		if (!fade) {
			sb.draw(button[0], x - width/2f, y - width/2f, 95f, 95f);
		}
		else {
			sb.draw(button[1], x - width/2f, y - width/2f, 95f, 95f);
		}
		sb.setColor(1f, 1f, 1f, 1f - fadeTimer);
		sb.draw(noGlow, x - 10 -width/2f, y - 10 - width/2f, 115f, 115f);
		sb.setColor(1f, 1f, 1f, fadeTimer);
		sb.draw(glow[color], x - 10 - width/2f, y - 10 - width/2f, 115f, 115f);
		sb.setColor(1f, 1f, 1f, 1f);
	}

	public void onClick() {
		fade = true;
		fadeTimer = 0;
		color = rand.nextInt(9);
	}

}
