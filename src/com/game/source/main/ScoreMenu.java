package com.game.source.main;
	
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public class ScoreMenu {
	
	private Rectangle backbutton = new Rectangle(500, 400, 80, 25);
	
	public void render (Graphics g) {
		//cast a Graphics2D per disegnare il menù
		Graphics2D g2 = (Graphics2D) g;
		//array con i miei high score
		HighScore[] h=HighScore.getHighScores();
		
		g2.setColor(Color.white);
		g2.setFont(new Font("Arial", Font.BOLD, 30));
		g2.drawString("HIGH SCORES", 200 , 40);
		g2.setFont(new Font("Arial", Font.BOLD, 24));
		g2.drawString("Nome", 80 , 80);
		g2.drawString("Punteggio", 300, 80);
		g2.drawLine(0, 100, 600, 100);
		g2.setFont(new Font("Arial", Font.BOLD, 22));
		for (int i=0; i<h.length; i++)
			if (h[i].getScore()>0)
			{
				g2.drawString(new Integer(i+1).toString()+".", 15, 120+i*45);
				g2.drawString(h[i].getName(), 80, 120+i*45);
				g2.drawString(new Integer(h[i].getScore()).toString(), 300, 120+i*45);
			}
		
		//Bottone per tornare indietro
		g2.setFont(new Font("Arial", Font.BOLD, 20));
		g2.drawString("indietro", backbutton.x + 5, backbutton.y + 20);
		g2.draw(backbutton);
	}	
		
	public void mousePressed(MouseEvent e) 
	{
		//creo due valori che sono le coordinate del mouse quando viene cliccato Sx
		int mx = e.getX();
		int my = e.getY();
		
		if (mx >= backbutton.getX() && mx <= (backbutton.getWidth() + backbutton.getX())) {
			if (my >= backbutton.getY() && my <= (backbutton.getY() + backbutton.getHeight())) {
				//accediamo alla tabella dei punteggi
				Game.State=Game.STATE.MENU;
			}
		}
	}

	public Rectangle getBackbutton() {
		return backbutton;
	}

	public void setBackbutton(Rectangle backbutton) {
		this.backbutton = backbutton;
	}
	
}
