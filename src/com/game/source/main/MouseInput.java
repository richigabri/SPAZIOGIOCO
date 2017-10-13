package com.game.source.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
//GESTSICE IL MENU PRINCIPALE
public class MouseInput implements MouseListener {
	
	//variabili per dimensioni bottoni
	private Rectangle playbutton;
	private Rectangle quitbutton;
    private Rectangle helpbutton;
    private Rectangle scorebutton;

    private Rectangle player1button;
    private Rectangle player2button;


	
	//Costruttore
	public MouseInput(Menu menu) {
		this.playbutton = menu.getPlaybutton();
		this.helpbutton = menu.getHelpbutton();
		this.quitbutton = menu.getQuitbutton();
		this.player1button=menu.getPlayer1();
		this.player2button=menu.getPlayer2();
		this.scorebutton=menu.getScorebutton();
	}


	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) 
	{
		//creo due valori che sono le coordinate del mouse quando viene cliccato Sx
		int mx = e.getX();
		int my = e.getY();
		
		/*
		 * PER CAPIRE LE COORDINATE DEI BOTTONI
		public Rectangle playbutton = new Rectangle(Game.WIDTH/2 + 120, 150, 100, 50);
		public Rectangle helpbutton = new Rectangle(Game.WIDTH/2 + 120, 250, 100, 50);
		public Rectangle quitbutton = new Rectangle(Game.WIDTH/2 + 120, 350, 100, 50);
		*/
		
		//Se mi trovo nel men� controllo i bottoni da premere
		if (Game.State == Game.STATE.MENU) {
			//Clicco il tasto Gioca SE SONO ALL'INTERNO DELLE SUE COORDINATE X E Y
			if (mx >= playbutton.getX() && mx <= (playbutton.getWidth() + playbutton.getX())) {
				if (my >= playbutton.getY() && my <= (playbutton.getY() + playbutton.getHeight())) {
					//Cambiamo lo stato e premiamo il bottone
					Game.State = Game.STATE.GAME;
				}
			}
			
			//Clicco il tasto Esci SE SONO ALL'INTERNO DELLE SUE COORDINATE X E Y
			if (mx >= quitbutton.getX() && mx <= (quitbutton.getWidth() + quitbutton.getX())) {
				if (my >= quitbutton.getY() && my <= (quitbutton.getY() + quitbutton.getHeight())) {
					//Usciamo dal gioco con il codice di errore					
					System.exit(1);
				}
			}
			//Clicco il tasto Aiuto SE SONO ALL'INTERNO DELLE SUE COORDINATE X E Y
			if (mx >= helpbutton.getX() && mx <= (helpbutton.getWidth() + helpbutton.getX())) {
				if (my >= helpbutton.getY() && my <= (helpbutton.getY() + helpbutton.getHeight())) {
					//aggungiamo delle informazioni e premiamo il bottone
                    JOptionPane.showMessageDialog(null,"Distruggi tutti gli alieni! \n- Muoviti con le frecce e Spara con la barra spaziatrice per Player 1\n- Muoviti con WASD e Spara con R per Player 2 \n SCALA LA CLASSIFICA!","INFORMAZIONI",3);


				}
			}
			//clicco su punteggi
			if (mx >= scorebutton.getX() && mx <= (scorebutton.getWidth() + scorebutton.getX())) {
				if (my >= scorebutton.getY() && my <= (scorebutton.getY() + scorebutton.getHeight())) {
					//accediamo alla tabella dei punteggi
					Game.State=Game.STATE.SCORE;
				}
			}
			
			
			
			if (mx >= player1button.getX() && mx <= (player1button.getWidth() + player1button.getX())) {
				if (my >= player1button.getY() && my <= (player1button.getY() + player1button.getHeight())) {

					Game.flagplayer=1; //se 1 c'è un giocatore
				}
			}
			if (mx >= player2button.getX() && mx <= (player2button.getWidth() + player2button.getX())) {
				if (my >= player2button.getY() && my <= (player2button.getY() + player2button.getHeight())) {
					Game.flagplayer=0;
				}
			}

		}


	}



    public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
