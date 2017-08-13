package com.game.source.main;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sounds {
	//creo le variabili che si associano a CIASCUNO dei suoni qui presenti
	public static final Sounds playerShoot = new Sounds("/Audio/shoot.wav");
	public static final Sounds playerDeath = new Sounds("/Audio/bossdeath.wav");
	public static final Sounds enemyDeath = new Sounds("/Audio/death.wav");
	public static final Sounds gameMusic = new Sounds("/Audio/gamemusic.wav");
	public static final Sounds levelUp = new Sounds("/Audio/levelup.wav");
	public static final Sounds powerup = new Sounds("/Audio/powerup.wav");
	
	private AudioClip clip;
	
	//Costruttore della classe Sounds, associa alle variabili l'audio associato e ha un controllo in caso di errore
	private Sounds(String name) {
		try {
			clip = Applet.newAudioClip(Sounds.class.getResource(name));
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	//riproduco l'audio associato alla variabile dichiarata prima nel codice con un controllo nel caso ci siano errori
	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
