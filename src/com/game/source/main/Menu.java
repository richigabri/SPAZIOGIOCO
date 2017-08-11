package com.game.source.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {
	
	/* aggiungiamo dei rettangoli che saranno i componenti del nostro menu
	 * I VALORI DENTRO A RECTANGLE SONO LE DIMENSIONI E LA POSIZIONE LUNGO GLI ASSI DEI MIEI BOTTONI 
	 */
	public Rectangle playbutton = new Rectangle(Game.WIDTH/2 + 120, 150, 100, 50);
	public Rectangle helpbutton = new Rectangle(Game.WIDTH/2 + 120, 250, 100, 50);
	public Rectangle quitbutton = new Rectangle(Game.WIDTH/2 + 120, 350, 100, 50);
	
	//Funzione con la quale disegno il menù
	public void render(Graphics g) {
		
		//Cast a Graphics2D per disegnare il rettangolo
		Graphics2D g2d = (Graphics2D) g;
		
		//creo il font come mi pare e piace
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		//lo imposto come standard e imposto anche il colore
		g.setFont(fnt0);
		g.setColor(Color.white);
		
		//scrivo ciò che ci sarà nel mio menù
		g.drawString("SPAZIO GIOCO", Game.WIDTH / 2, 100);
		
		//Aggiungo le scritte e disegno le voci del mio menu
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		// riempio le voci del mio menù modificando le x e le y perchè andrebbe a scrivere nell'angolo in alto a destra
		g.drawString("Gioca", playbutton.x + 11, playbutton.y +35);
		g.drawString("Aiuto", helpbutton.x + 15, helpbutton.y + 35);
		g.drawString("Esci", quitbutton.x + 19, quitbutton.y + 35);
		//disegno i bordi delle voci del menù
		g2d.draw(playbutton);
		g2d.draw(helpbutton);
		g2d.draw(quitbutton);
		
	}
	
	//Getters & Setters
	
	public Rectangle getPlaybutton() {
		return playbutton;
	}

	public void setPlaybutton(Rectangle playbutton) {
		this.playbutton = playbutton;
	}

	public Rectangle getHelpbutton() {
		return helpbutton;
	}

	public void setHelpbutton(Rectangle helpbutton) {
		this.helpbutton = helpbutton;
	}

	public Rectangle getQuitbutton() {
		return quitbutton;
	}

	public void setQuitbutton(Rectangle quitbutton) {
		this.quitbutton = quitbutton;
	}
	
}
