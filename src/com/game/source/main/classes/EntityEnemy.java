/*
 * QUESTE INTERFACCE SERVONO PER INDICARE LA COLLISIONE DEI NEMICI CON LA NAVE O I PROIETTILI
 * DATO CHE POSSO SFRUTTARE IL METODO RECTANGLE PER IDENTIFICARE LE COLLISIONI TRA ELEMENTI DI
 * DIVERSE INTERFACCE E SFRUTTARE IL MIO CODICE DI CONSEGUENZA
 * 
 * Dopo una notte di programmazione non mi è venuto in mente un nome migliore per identificarle se non A e B
 * */

package com.game.source.main.classes;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface EntityEnemy {
	//funzioni per movimento e per render grafico dell'oggetto
	public void tick();
	public void render (Graphics g);
	public Rectangle getBounds();
	
	//funzioni per sapere dove si trova il mio oggetto lungo l'asse cartesiano
	public double getX();
	public double getY();
	public int getWidth();
	public int getHeight();
}