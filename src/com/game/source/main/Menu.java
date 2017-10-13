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
	public Rectangle helpbutton = new Rectangle(Game.WIDTH/2 +25, 250, 130, 50);
	public Rectangle scorebutton = new Rectangle(Game.WIDTH/2 + 175, 250, 150, 50);
	public Rectangle quitbutton = new Rectangle(Game.WIDTH/2 + 120, 350, 100, 50);
	public Rectangle player2 = new Rectangle(Game.WIDTH/2 + 300, 150, 130, 50);
	public Rectangle player1 = new Rectangle(Game.WIDTH/2 -100, 150, 130, 50);


	//Funzione con la quale disegno il men�
	public void render(Graphics g) {
		
		//Cast a Graphics2D per disegnare il rettangolo
		Graphics2D g2d = (Graphics2D) g;
		
		//creo il font come mi pare e piace
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		//lo imposto come standard e imposto anche il colore
		g.setFont(fnt0);
		g.setColor(Color.white);
		
		//scrivo ci� che ci sar� nel mio men�
		g.drawString("SPAZIO GIOCO", Game.WIDTH / 2, 100);

		//Aggiungo le scritte e disegno le voci del mio menu
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		// riempio le voci del mio men� modificando le x e le y perch� andrebbe a scrivere nell'angolo in alto a destra
		g.drawString("Gioca", playbutton.x + 11, playbutton.y +35);
		g.drawString("Aiuto", helpbutton.x + 30, helpbutton.y + 35);
		g.drawString("Punteggi", scorebutton.x + 10, scorebutton.y + 35);
		g.drawString("Esci", quitbutton.x + 19, quitbutton.y + 35);

		g.drawString("Player1", player1.x + 15, player1.y +35);
		g.drawString("Player2", player2.x + 15, player2.y + 35);

		//disegno i bordi delle voci del men�
		g2d.draw(playbutton);
		g2d.draw(helpbutton);
		g2d.draw(scorebutton);
		g2d.draw(quitbutton);
		g2d.draw(player1);
		g2d.draw(player2);

		//controllo il  flagplayer per evidenzare all'utente in quale modalità sta giocando

		//impostazione di dafault : il pulsante player1 è attivo(Colore Rosso)
		if(Game.flagplayer==1){
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			g.setFont(fnt2);
			g.setColor(Color.RED);
			g.fillRect(Game.WIDTH/2 -100, 150, 130, 50);
			g.setColor(Color.WHITE);
			g.drawString("Player1", player1.x + 15, player1.y +35);
			g2d.draw(player1);

		}
		//se l'utente preme player2 il pulsante si attivera (colore rosso) e l'utente giochera in modalita 2 giocatori
		if(Game.flagplayer==0){
			Font fnt2 = new Font("arial", Font.BOLD, 30);
			g.setFont(fnt2);
			g.setColor(Color.RED);
			g.fillRect(Game.WIDTH/2 + 300, 150, 130, 50);
			g.setColor(Color.WHITE);
			g.drawString("Player2", player2.x + 15, player2.y + 35);
			g2d.draw(player2);


		}
		
	}

	public void Paint(Graphics g,double x,double y){
		Graphics2D g2d =(Graphics2D) g;
		g.drawString("Player1", player1.x + 15, player1.y +35);
		g2d.draw(player1);
		g2d.setColor(Color.RED);


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
	
	public Rectangle getScorebutton() {
		return scorebutton;
	}

	public void setScorebutton(Rectangle scorebutton) {
		this.scorebutton = scorebutton;
	}

	public Rectangle getPlayer1() {return player1;}
	public void setPlayer1(Rectangle player1) {this.player1 = player1;}

	public Rectangle getPlayer2() {return player2;}
	public void setPlayer2(Rectangle player2) {this.player2 = player2;}


}
