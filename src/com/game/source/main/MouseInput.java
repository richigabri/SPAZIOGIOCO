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

	
	//Costruttore
	public MouseInput(Menu menu) {
		this.playbutton = menu.getPlaybutton();
		this.helpbutton = menu.getHelpbutton();
		this.quitbutton = menu.getQuitbutton();

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
                    JOptionPane.showMessageDialog(null,"Distruggi tutti gli alieni! \n Muoviti con le frecce o WASD \n Spara con la barra spaziatrice \n SCALA LA CLASSIFICA!","INFORMAZIONI",3);


				}
			}
		}


	}



    public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
