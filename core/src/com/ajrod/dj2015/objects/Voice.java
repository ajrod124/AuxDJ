package com.ajrod.dj2015.objects;

import com.ajrod.dj2015.Dj2015;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Voice extends Box {

	private int id;
	private TextureRegion[] button;
	private boolean on;

	public Voice (int sel, float x, float y, boolean b) {
		Texture tex; ;
		TextureRegion reg;
		TextureRegion[][] temp; 

		this.x = x;
		this.y = y;
		if (b) {
			width = 140;
			height = 140;
		}
		else {
			width = 96;
			height = 96;
		}
		id = sel;
		button = new TextureRegion[2];
		
		switch (id) { // TODO: add more cases upon adding more sound packs
		case 0:
			tex = new Texture(Gdx.files.internal("bedbutton.png"));
			reg = new TextureRegion(tex);
			temp = reg.split(140, 140);
			if (b && id == Dj2015.selected[0])
				on = true;
			else
				on = false;
			break;
		case 1:
			tex = new Texture(Gdx.files.internal("waterdropbutton.png"));
			reg = new TextureRegion(tex);
			temp = reg.split(140, 140);
			if (b && id == Dj2015.selected[0])
				on = true;
			else
				on = false;
			break;
			
		case 2:
			tex = new Texture(Gdx.files.internal("airhornbutton.png"));
			reg = new TextureRegion(tex);
			temp = reg.split(140, 140);
			if (b && id == Dj2015.selected[0])
				on = true;
			else
				on = false;
			break;
		case 3:
			tex = new Texture(Gdx.files.internal("808_button.png"));
			reg = new TextureRegion(tex);
			temp = reg.split(140, 140);
			if (b && id == Dj2015.selected[0])
				on = true;
			else
				on = false;
			break;
		default:
			tex = new Texture(Gdx.files.internal("bedbutton.png"));
			reg = new TextureRegion(tex);
			temp = reg.split(140, 140);
			if (b && id == Dj2015.selected[0])
				on = true;
			else
				on = false;
			break;
		}
		
		for (int i = 0; i < 2; i++) button[i] = temp[0][i];
		
	}
	
	public void update(float dt) { /* Do Nothing */ }

	public void render(SpriteBatch sb) {
		if (on)
			sb.draw(button[0], x - width/2f, y - width/2f, width, width);
		else
			sb.draw(button[1], x - width/2f, y - width/2f, width, width);
	}

	public void onClick() {
		if (!on)
			on = true;
	}
	
	public void turnOff() { on = false; }
	public void turnOn() { on = true; }
	public boolean isOn() { return on; }
}
