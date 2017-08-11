package com.game.source.main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.source.main.classes.EntityEnemy;
import com.game.source.main.classes.EntityFriendly;

public class Controller {
	//elenco di proiettili/nemici/roba varia in una lista per ciascuna entità
	private LinkedList<EntityFriendly> ef = new LinkedList<EntityFriendly>();
	private LinkedList<EntityEnemy> ee = new LinkedList<EntityEnemy>();
	
	EntityFriendly entf;
	EntityEnemy ente;
	Game game;
	Textures tex;
	Random r = new Random();
	
	/*	OGNI VOLTA che creo qualcosa lo passo come elemento dell'entità
	 * Così facendo chiamerò in modo automatico la sua funzione tick per il movimento, la sua funzione render per disegnarlo
	 * e le sue funzioni GetX, GetY per capire le sue coordinate
	 * 
	 * In sostanza visto che queste parti si ripetevano a iosa ho preferito creare una entità che le accogliesse e rendesse più ordinato il codice
	 * */
	
	
	public Controller(Game game, Textures tex) {
		//constructor
		this.game=game;
		this.tex=tex;
	}
	
	//aggiungo nemici in posizione casuale
	public void createEnemy(int enemy_count) {
		for(int i =0; i< enemy_count; i++) {
			//gli argomenti sono la posizione casuale sullo schermo, la posizione sull'asse Y e la texture da caricare
			addEntity(new Enemy(r.nextInt(Game.WIDTH*Game.SCALE), -10, tex, this, game));	
		}
	}

	public void tick() {
		//Per gli elementi di tipo Friendly
		for (int i = 0; i < ef.size(); i++) {
			entf = ef.get(i);
			
			entf.tick();
		}
		
		//Per gli elementi di tipo Enemy
		for (int i = 0; i < ee.size(); i++) {
			ente = ee.get(i);
			
			ente.tick();
		}
	}
	
	public void render (Graphics g) {
		//Per gli elementi di tipo Friendly
		for (int i = 0; i < ef.size(); i++) {
			entf = ef.get(i);
			
			entf.render(g);
		}
		
		//Per gli elementi di tipo Enemy
		for (int i = 0; i < ee.size(); i++) {
			ente = ee.get(i);
			
			ente.render(g);
		}
	}
	
	//lascio gli stessi nomi per semplicità tanto in base all''argomento passato il codice sa quale funzione chiamare
	
	//Metodi sulla lista degli elementi Friendly
	public void addEntity(EntityFriendly block) {
		ef.add(block);
	}
	
	public void removeEntity(EntityFriendly block) {
		ef.remove(block);
	}
	
	//Metodi sulla lista degli elementi Enemy
	public void addEntity(EntityEnemy block) {
		ee.add(block);
	}
	
	public void removeEntity(EntityEnemy block) {
		ee.remove(block);
	}
	
	public LinkedList<EntityFriendly> getEntityFriendly(){
		return ef;
	} 
	
	public LinkedList<EntityEnemy> getEntityEnemy(){
		return ee;
	} 
}