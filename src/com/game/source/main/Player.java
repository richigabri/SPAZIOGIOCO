package com.game.source.main;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.source.main.classes.EntityEnemy;
import com.game.source.main.classes.EntityFriendly;

public class Player extends GameObject implements EntityFriendly{
	
	//assi direzionali e velocit�
	private double velX=0;
	private double velY=0;
	private Textures tex;
	private Game game;
	private Controller controller;


	public Player(double x, double y, Textures tex, Game game, Controller controller) {
	//costruttore
		super(x,y);	//USO I dati del costruttore in GameObject
		this.tex=tex;
		this.game=game;
		this.controller=controller;
	}
	
	public void tick() {

		x += velX;
		y += velY;
		
		if (x <= 0) {
			x = 0;
		}
		if (x >= 640 - 90) {	//lunghezza nave per collisioni
			x = 640 - 90;
		}
		if (y <= 0) {
			y = 0;
		}
		if (y >= 400 - 0) {	//altezza nave per collisioni
			y = 400 - 0;
		}
		
		//ciclo per far calare la barra dei punti vita
		//controllo per ogni nemico che non abbia toccato la mia navicella
		for (int i = 0; i < game.ee.size(); i++) {
			//creo una variabile temporale per controllare la collisione
			EntityEnemy tempee = game.ee.get(i);
			//se collidono rimuovo il nemico e abbasso la vita
			if(Physics.Collision(this, tempee)) {
				Sounds.playerDeath.play();//suono quando la nave colpisce i nemici
				controller.removeEntity(tempee);
				Game.health -= 10;
				

							
				//aumento il contatore di nemici uccidi
				game.setEnemy_killed(game.getEnemy_killed() + 1);
			}
		}
		
	}
	
	//Calcolo il bordo della texture
	public Rectangle getBounds() {	
		//DA CONTROLLARE PER LE DIMENSIONI NON FISSATE DEGLI OGGETTI
		return new Rectangle((int)x, (int)y, getWidth(), getHeight());
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.player, (int)x, (int)y, null);
	}
	
	//GETTER & SETTER
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {	
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	//getter delle dimensioni della texture della navicella
	public int getWidth() {
		return tex.player.getWidth();
	}
	
	public int getHeight() {
		return tex.player.getHeight();
	}
}