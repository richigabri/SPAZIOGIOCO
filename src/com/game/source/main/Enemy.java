package com.game.source.main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.source.main.classes.EntityEnemy;
import com.game.source.main.classes.EntityFriendly;

public class Enemy extends GameObject implements EntityEnemy{
	
	//coordinate e texture + RANDOM per il riposizionamento casuale
	private double x, y;
	Random r = new Random();
	//game e controller per controllare se il nemico viene colpito
	private Game game;
	private Controller c;
	int score=0;
	//variabile speed per la velocitï¿½ dei nemici casuale
	private int speed = r.nextInt(3)+ 1;
	
	private Textures tex;
	
	public Enemy(double x, double y, Textures tex, Controller c, Game game) {
		//creatore
		super(x,y);	//USO I dati del costruttore in GameObject
		//inizializzo la nave a una posizione random dentro alla mia griglia di gioco
		this.x = r.nextInt(Game.WIDTH * Game.SCALE - 40);
		this.tex=tex;
		this.c=c;
		this.game=game;
	}
	
	public void tick() {
		//movimento del nemico TEST
		y += speed;
		
		if(y > (Game.HEIGHT * Game.SCALE)) {
			y = 0;
			//una volta che ripartono li rallento ma continuo a farli andare a una velocità random
			speed = r.nextInt(2)+ 1;
			//DA CONTROLLARE PER FAR STARE TUTTA LA NAVE DENTRO ALLO SCHERMO
			x = r.nextInt(Game.WIDTH * Game.SCALE - 40);	
		}
		
	
		for (int i = 0; i < game.ef.size(); i++) {
			
			//Creo una entità temporanea e controllo che non collida col nemico
			EntityFriendly tempef = game.ef.get(i);
			
			//se il nemico tocca un friendly (proietile o nave) allora lo rimuovo
			if (Physics.Collision(this, tempef)) {
				
					Sounds.playerDeath.play(); //suono quando il nemico muore
					
				//RIMUOVO sia il proiettile che il nemico se collidono
				c.removeEntity(tempef);
				c.removeEntity(this);
				
				//AGGIORNO le variabili di conseguenza
				game.setEnemy_killed(game.getEnemy_killed() + 1);

			} 
					
			//aggiorno lo score quando un nemico viene colpito dal proiettile
			if (Physics.Collision(this, tempef)){
				
				game.setScore(Game.getScore()+50);
			}
		
			
		}
		
	}
	
	public void render(Graphics g) {
		g.drawImage(tex.enemy, (int)x, (int)y, null);
	}
	
	
	//per le collisioni, funzione che crea il bordo
	public Rectangle getBounds() {
		
		//DA CONTROLLARE PER LE DIMENSIONI NON FISSATE DEGLI OGGETTI - ok funziona
		return new Rectangle((int)x, (int)y, getWidth(), getHeight());
	}
	
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	//getter delle dimensioni
	public int getWidth() {
		return tex.enemy.getWidth();
	}
		
	public int getHeight() {
		return tex.enemy.getHeight();
	}
	
}