package com.game.source.main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput3 implements MouseListener {
	
	//Classe che contiene il mouse input per la tabella degli Score
	 private Rectangle backbutton;
	 
    //Costruttore
    public MouseInput3(ScoreMenu scoremenu ) {
        this.backbutton=scoremenu.getBackbutton();

    }
    
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		//creo due valori che sono le coordinate del mouse quando viene cliccato Sx
				int mx = e.getX();
				int my = e.getY();
				
				if (Game.State == Game.STATE.SCORE){
					if (mx >= backbutton.getX() && mx <= (backbutton.getWidth() + backbutton.getX())) {
						if (my >= backbutton.getY() && my <= (backbutton.getY() + backbutton.getHeight())) {
							//ritorniamo al menù principale
							Game.State=Game.STATE.MENU;
							
						}
					}
				}
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
