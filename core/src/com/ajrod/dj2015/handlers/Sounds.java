package com.ajrod.dj2015.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class Sounds {
	
	public Sound c1, d, e, f, g, a, b, c2;
	private float volume, speed;
	
	public Sounds() {
		volume = 1.0f;
		speed = 1.0f;
		c1 = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_c.wav"));
		d = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_d.wav"));
		e = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_e.wav"));
		f = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_f.wav"));
		g = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_g.wav"));
		a = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_a.wav"));
		b = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_b.wav"));
		c2 = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_high_c.wav"));
	}
	
	public void changeSounds(int sel) {
		c1.stop();
		d.stop();
		e.stop();
		f.stop();
		g.stop();
		a.stop();
		b.stop();
		c2.stop();
		switch (sel) { // TODO: add more cases upon adding new packs
		case 0:
			c1 = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_c.wav"));
			d = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_d.wav"));
			e = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_e.wav"));
			f = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_f.wav"));
			g = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_g.wav"));
			a = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_a.wav"));
			b = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_b.wav"));
			c2 = Gdx.audio.newSound(Gdx.files.internal("bedsqeak_high_c.wav"));
			break;
		case 1:
			c1 = Gdx.audio.newSound(Gdx.files.internal("water_drop_c.wav"));
			d = Gdx.audio.newSound(Gdx.files.internal("water_drop_d.wav"));
			e = Gdx.audio.newSound(Gdx.files.internal("water_drop_e.wav"));
			f = Gdx.audio.newSound(Gdx.files.internal("water_drop_f.wav"));
			g = Gdx.audio.newSound(Gdx.files.internal("water_drop_g.wav"));
			a = Gdx.audio.newSound(Gdx.files.internal("water_drop_a.wav"));
			b = Gdx.audio.newSound(Gdx.files.internal("water_drop_b.wav"));
			c2 = Gdx.audio.newSound(Gdx.files.internal("water_drop_high_c.wav"));
			break;
		case 2:
			c1 = Gdx.audio.newSound(Gdx.files.internal("airhorn_c.wav"));
			d = Gdx.audio.newSound(Gdx.files.internal("airhorn_d.wav"));
			e = Gdx.audio.newSound(Gdx.files.internal("airhorn_e.wav"));
			f = Gdx.audio.newSound(Gdx.files.internal("airhorn_f.wav"));
			g = Gdx.audio.newSound(Gdx.files.internal("airhorn_g.wav"));
			a = Gdx.audio.newSound(Gdx.files.internal("airhorn_a.wav"));
			b = Gdx.audio.newSound(Gdx.files.internal("airhorn_b.wav"));
			c2 = Gdx.audio.newSound(Gdx.files.internal("airhorn_high_c.wav"));
			break;
		case 3:
			c1 = Gdx.audio.newSound(Gdx.files.internal("808_c.wav"));
			d = Gdx.audio.newSound(Gdx.files.internal("808_d.wav"));
			e = Gdx.audio.newSound(Gdx.files.internal("808_e.wav"));
			f = Gdx.audio.newSound(Gdx.files.internal("808_f.wav"));
			g = Gdx.audio.newSound(Gdx.files.internal("808_g.wav"));
			a = Gdx.audio.newSound(Gdx.files.internal("808_a.wav"));
			b = Gdx.audio.newSound(Gdx.files.internal("808_b.wav"));
			c2 = Gdx.audio.newSound(Gdx.files.internal("808_high_c.wav"));
			break;
		}
	}
	
	public float getVolume() { return volume; }
	public float getSpeed() { return speed; }
	public void setSpeed(float x) { speed = x; }
	public void setVolume(float x) { volume = x; }
}
