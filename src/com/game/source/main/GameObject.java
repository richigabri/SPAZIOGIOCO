package com.game.source.main;

import java.awt.Rectangle;

public class GameObject {
	//ogni oggetto di gioco avrà coordinate X e Y, questa classe le focalizza
	public double x,y;

	public GameObject(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	//Creo un rettangolo intorno a ogni oggetto per capire le eventuali collisioni in Physics
	public Rectangle getBounds(int width, int height) {
		//DA CONTROLLARE PER LE DIMENSIONI NON FISSATE DEGLI OGGETTI
		return new Rectangle((int)x, (int)y, width, height);
	}
}