package com.game.source.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//controlli da tastiera
public class KeyInput extends KeyAdapter{
	
	Game game;
	
	//colleghiamo tastiera al gioco
	public KeyInput(Game game) {
		this.game=game;
	}
	
	//comunica al gioco il tasto
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}

}
