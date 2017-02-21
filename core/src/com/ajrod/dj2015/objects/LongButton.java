package com.ajrod.dj2015.objects;

import com.ajrod.dj2015.Dj2015;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class LongButton extends Box {

	private boolean main;
	private TextureRegion button;
	
	public LongButton (boolean b, float x, float y) {
		Texture tex;
		tex = new Texture(Gdx.files.internal("long_button.png"));
		
		this.x = x;
		this.y = y;
		width = 520;
		height = 100;
		main = b;
		button = new TextureRegion(tex);
	}
	
	public void update(float dt) {
		/*Nothing*/
	}

	public void render(SpriteBatch sb) {
		
		sb.draw(button, x - width/2f, y - height/2f, width, height);
		if (main)
			Dj2015.font.draw(sb, "Edit Sample Packs", x - 170f, y + 15f);
		else
			Dj2015.font.draw(sb, "Go Back", x - 80f, y + 15f);
	}

}
