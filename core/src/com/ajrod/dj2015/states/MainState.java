package com.ajrod.dj2015.states;

import com.ajrod.dj2015.Dj2015;
import com.ajrod.dj2015.objects.Keys;
import com.ajrod.dj2015.objects.LongButton;
import com.ajrod.dj2015.objects.Slider;
import com.ajrod.dj2015.objects.Voice;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainState extends State {

	//private LongButton button;
	private Keys keys[];
	private Voice voice[];
	private Slider slider[];
	private TextureRegion[] bg;
	private TextureRegion currentFrame;
	private float stateTime;
	private Animation ani;
	private int selVoice;

	public MainState (GSM gsm) {
		super(gsm);
		keys = new Keys[8];
		voice = new Voice[3];
		bg = new TextureRegion[60];
		slider = new Slider[2];
		
		//button = new LongButton(true, Dj2015.WIDTH/2, 770f);
		Dj2015.sounds.changeSounds(Dj2015.selected[0]);
		slider[0] = new Slider(355f, true);
		slider[0].setParams(Dj2015.sample[Dj2015.selected[0]].savedVol, Dj2015.sample[Dj2015.selected[0]].savedPitch);
		slider[1] = new Slider(500f, false);
		slider[1].setParams(Dj2015.sample[Dj2015.selected[0]].savedVol, Dj2015.sample[Dj2015.selected[0]].savedPitch);
		stateTime = 0;
		selVoice = 0;
		
		for (int i = 0; i < 8; i++) {
			keys[i] = new Keys(73.5f + (131f * (i%4)), 205f - (131f * (i/4)));
			if (i < 3) {
				voice[i] = new Voice(Dj2015.selected[i], 100f + (170f * i), 635f, true);
			}
		}
		
		Texture tex = new Texture(Gdx.files.internal("dj_bg.png"));
		TextureRegion reg = new TextureRegion(tex);
		TextureRegion[][] tmp = reg.split(Dj2015.WIDTH/4, Dj2015.HEIGHT/4);
		int k = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 10; j++) {
				bg[k++] = tmp[i][j];
			}
		}
		
		ani = new Animation(0.1f, bg);
	}

	@Override
	public void update(float dt) {
		handleInput();
		stateTime += (Dj2015.sounds.getSpeed()/2)*0.1f;
		if (stateTime >= 6f) stateTime = 0;
		for (int i = 0; i < 8; i++) {
			keys[i].update(dt);
			if (i < 3) {
				voice[i].update(dt);
			}
		}
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		currentFrame = ani.getKeyFrame(stateTime, true);
		sb.draw(currentFrame, 0, 0, Dj2015.WIDTH, Dj2015.HEIGHT);
		for (int i = 0; i < 8; i++) {
			keys[i].render(sb);
			if (i < 3) {
				voice[i].render(sb);
			}
		}
		//button.render(sb);
		slider[0].render(sb);
		slider[1].render(sb);
		sb.end();
	}

	@Override
	public void handleInput() {
		if (Gdx.input.justTouched()) {
			mouse.x = Gdx.input.getX();
			mouse.y = Gdx.input.getY();
			cam.unproject(mouse);
			for (int i = 0; i < 8; i++) {
				if (keys[i].contains(mouse.x, mouse.y)) {
					play(i);
				}
				if (i < 3) {
					if (voice[i].contains(mouse.x, mouse.y)) {
						if (voice[i].isOn())
							play(0);
						else {
							Dj2015.sounds.changeSounds(Dj2015.selected[i]);
							switch (i) {
							case 0:
								selVoice = 0;
								voice[0].onClick();
								voice[1].turnOff();
								voice[2].turnOff();
								break;
							case 1:
								selVoice = 1;
								voice[1].onClick();
								voice[0].turnOff();
								voice[2].turnOff();
								break;
							case 2:
								selVoice = 2;
								voice[2].onClick();
								voice[0].turnOff();
								voice[1].turnOff();
								break;
							}
							slider[0].setParams(Dj2015.sample[Dj2015.selected[i]].savedVol, Dj2015.sample[Dj2015.selected[i]].savedPitch);
							slider[1].setParams(Dj2015.sample[Dj2015.selected[i]].savedVol, Dj2015.sample[Dj2015.selected[i]].savedPitch);
							play(0);
						}
					}
				}
			}
			//if (button.contains(mouse.x, mouse.y))
				//gsm.set(new SampleListState(gsm));
		}
		for (int i =0; i < 3; i++) {
			if (Gdx.input.isTouched(i)) {
				mouse.x = Gdx.input.getX();
				mouse.y = Gdx.input.getY();
				cam.unproject(mouse);
				if (slider[0].contains(mouse.x, mouse.y)) {
					slider[0].onClick(mouse.x);
					Dj2015.sample[Dj2015.selected[selVoice]].savedPitch = Dj2015.sounds.getSpeed();
					Dj2015.prefs.putFloat("pitch" + Dj2015.selected[selVoice], Dj2015.sounds.getSpeed());
				}
				else if (slider[1].contains(mouse.x, mouse.y)) {
					slider[1].onClick(mouse.x);
					Dj2015.sample[Dj2015.selected[selVoice]].savedVol = Dj2015.sounds.getVolume();
					Dj2015.prefs.putFloat("vol" + Dj2015.selected[selVoice], Dj2015.sounds.getVolume());
				}
			}
		}
	}
	
	private void play(int i) {
		if (!Dj2015.sample[Dj2015.selected[selVoice]].continuous) {
			Dj2015.sounds.c1.stop();
			Dj2015.sounds.d.stop();
			Dj2015.sounds.e.stop();
			Dj2015.sounds.f.stop();
			Dj2015.sounds.g.stop();
			Dj2015.sounds.a.stop();
			Dj2015.sounds.b.stop();
			Dj2015.sounds.c2.stop();
		}
		switch (i) {
		case 0:
			Dj2015.sounds.c1.play(Dj2015.sounds.getVolume(), Dj2015.sounds.getSpeed(), 0);
			break;
		case 1:
			Dj2015.sounds.d.play(Dj2015.sounds.getVolume(), Dj2015.sounds.getSpeed(), 0);
			break;
		case 2:
			Dj2015.sounds.e.play(Dj2015.sounds.getVolume(), Dj2015.sounds.getSpeed(), 0);
			break;
		case 3:
			Dj2015.sounds.f.play(Dj2015.sounds.getVolume(), Dj2015.sounds.getSpeed(), 0);
			break;
		case 4:
			Dj2015.sounds.g.play(Dj2015.sounds.getVolume(), Dj2015.sounds.getSpeed(), 0);
			break;
		case 5:
			Dj2015.sounds.a.play(Dj2015.sounds.getVolume(), Dj2015.sounds.getSpeed(), 0);
			break;
		case 6:
			Dj2015.sounds.b.play(Dj2015.sounds.getVolume(), Dj2015.sounds.getSpeed(), 0);
			break;
		case 7:
			Dj2015.sounds.c2.play(Dj2015.sounds.getVolume(), Dj2015.sounds.getSpeed(), 0);
			break;
		}
		keys[i].onClick();
	}
	
}
