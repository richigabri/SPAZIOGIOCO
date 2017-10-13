package com.game.source.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Textures {
	//texture che verranno usate dal gioco
	public BufferedImage player,player2, bullet,bullet2, enemy,background,background2;
	BufferedImageLoader loader = new BufferedImageLoader();
	
	public Textures(Game game) {
		
		getTextures();
		
	}
	
	private void getTextures() {
		//carico immagine giocatore
		try {
			player =  loader.loadImage("/playerShip3_blue.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		//carico immagine secondo giocatore
		try {
			player2 =  loader.loadImage("/playerShip3_red.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

		//carico immagine missile  primo giocatore
		try {
			bullet =  loader.loadImage("/Lasers/laserBlue01.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//carico immagine missile per il secondo giocatore
		try {
			bullet2 =  loader.loadImage("/Lasers/laserGreen11.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		//carico immagine nemico
		
		try {
			enemy =  loader.loadImage("/Enemies/enemyGreen4.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			background =  loader.loadImage("/Background/spacebackground.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			background2 =  loader.loadImage("/Background/Parallax100.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
