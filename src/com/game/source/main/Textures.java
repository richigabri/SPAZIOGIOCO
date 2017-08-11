package com.game.source.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Textures {
	//texture che verranno usate dal gioco
	public BufferedImage player, bullet, enemy;
	BufferedImageLoader loader = new BufferedImageLoader();
	
	public Textures(Game game) {
		
		getTextures();
		
	}
	
	private void getTextures() {
		//carico immagine giocatore
		try {
			player =  loader.loadImage("/playerShip1_blue.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//carico immagine missile 
		try {
			bullet =  loader.loadImage("/Lasers/laserBlue01.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//carico immagine nemico
		try {
			enemy =  loader.loadImage("/Enemies/enemyBlack2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
