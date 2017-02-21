package com.ajrod.dj2015.states;

import com.ajrod.dj2015.Dj2015;
import com.ajrod.dj2015.objects.LongButton;
import com.ajrod.dj2015.objects.Voice;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SampleListState extends State {

	private LongButton button;
	private Voice voice[];
	private int counter;
	private int selections[];
	
	protected SampleListState(GSM gsm) {
		super(gsm);
		selections = new int[3];
		counter = 0;
		voice = new Voice[Dj2015.TOTAL_SAMPLES];
		button = new LongButton(false, Dj2015.WIDTH/2, 60f);
		for (int i = 0; i < voice.length; i++)
			voice[i] = new Voice(i, 58f + 106f*i, (Dj2015.HEIGHT - 315.5f) - (116*(i/5)), false);
	}

	public void update(float dt) { 
		if (counter == 3) {
			for (int i = 0; i < 3; i++) {
				Dj2015.selected[i] = selections[i];
				Dj2015.prefs.putInteger("selected" + i, selections[i]);
			}
			gsm.set(new MainState(gsm));
		}
		handleInput(); 
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		for (int i = 0; i < voice.length; i++)
			voice[i].render(sb);
		button.render(sb);
		Dj2015.font.draw(sb, "Choose Any 3:", 10f, Dj2015.HEIGHT - 175f);
		sb.setColor(1, 1, 1, 0);
		//Dj2015.font.draw(sb, "More", 150f, Dj2015.HEIGHT - 500f);
		//Dj2015.font.draw(sb, "Coming", 190f, Dj2015.HEIGHT - 530f);
		//Dj2015.font.draw(sb, "Soon!!!", 260f, Dj2015.HEIGHT - 560f);
		sb.setColor(1, 1, 1, 1);
		sb.end();
	}

	public void handleInput() {
		if (Gdx.input.justTouched()) {
			mouse.x = Gdx.input.getX();
			mouse.y = Gdx.input.getY();
			cam.unproject(mouse);
			if (button.contains(mouse.x, mouse.y))
				gsm.set(new MainState(gsm));
			for (int i = 0; i < voice.length; i++) {
				if (voice[i].contains(mouse.x, mouse.y) && !voice[i].isOn()) {
					selections[counter++] = i;
					voice[i].onClick();
				}
			}
		}
	}

}
