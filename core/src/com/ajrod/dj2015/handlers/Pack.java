package com.ajrod.dj2015.handlers;

public class Pack {
	public boolean continuous;
	public int selSample;
	public float savedVol;
	public float savedPitch;
	
	public Pack(int samp, float vol, float pitch) {
		selSample = samp;
		savedVol = vol;
		savedPitch = pitch;
		switch (samp) { // TODO: add more exceptions if applicable to added sound packs
		case 0:
			continuous = false;
			break;
		case 2:
			continuous = false;
			break;
		case 3:
			continuous = false;
			break;
		default:
			continuous = true;
			break;
		}
	}
}
