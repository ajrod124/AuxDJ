package com.ajrod.dj2015;

import com.ajrod.dj2015.handlers.Pack;
import com.ajrod.dj2015.handlers.Sounds;
import com.ajrod.dj2015.states.GSM;
import com.ajrod.dj2015.states.MainState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Dj2015 extends ApplicationAdapter {
	
	public static final String TITLE = "DJ2015";
	public static final int WIDTH = 540;
	public static final int HEIGHT = 960;
	public static final int TOTAL_SAMPLES = 4; // TODO: add 1 when new pack is added
	public static Sounds sounds;
	public static BitmapFont font;
	public static Pack[] sample;
	public static int[] selected;
	public static Preferences prefs;
	
	private GSM gsm;
	private SpriteBatch sb;
	
	@Override
	public void create () {
		prefs = Gdx.app.getPreferences("Dj2015");
		sample = new Pack[TOTAL_SAMPLES];
		selected = new int[3];
		for (int i = 0; i < TOTAL_SAMPLES; i++) {
			if (!prefs.contains("vol" + i))
				prefs.putFloat("vol" + i, 1.0f);
			if (!prefs.contains("pitch" + i))
				prefs.putFloat("pitch" + i, 1.0f);
			sample[i] = new Pack(i, prefs.getFloat("vol" + i, 1.0f), prefs.getFloat("pitch" + i, 1.0f));
			if (i < 3) {
				if (!prefs.contains("selected" + i))
					prefs.putInteger("selected" + i, i);
				selected[i] = prefs.getInteger("selected" + i, i);
			}
		}
		prefs.flush();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		sb = new SpriteBatch();
		gsm = new GSM();
		sounds = new Sounds();
		gsm.push(new MainState(gsm));
		font = new BitmapFont(Gdx.files.internal("flipps.fnt"), false);
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(sb);
	}
	
	public void dispose() {
		prefs.flush();
		super.dispose();
	}
}
