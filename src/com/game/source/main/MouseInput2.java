package com.game.source.main;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//GESTISCE IL GAME OVER MENU
public class MouseInput2  implements MouseListener  {
    private Rectangle replaybutton;
    private Rectangle quitbuttontomenu;

    //Costruttore
    public MouseInput2(GameOver gameover ) {
        this.replaybutton = gameover.getReplaybutton();
        this.quitbuttontomenu = gameover.getQuitbuttontomenu();

    }



    @Override
     public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
     //creo due valori che sono le coordinate del mouse quando viene cliccato Sx
        int mx = e.getX();
        int my = e.getY();

        //l'intenzione è che quando si prema il bottone si passa dal men� principale
        if(Game.State == Game.STATE.GAMEOVER){

            if (mx >= replaybutton.getX() && mx <= (replaybutton.getWidth() + replaybutton.getX())) {
                if (my >= replaybutton.getY() && my <= (replaybutton.getY() +replaybutton.getHeight())) {
                    //Cambiamo lo stato

                    Game.State=Game.STATE.MENU; //torno allo State Menu
                }
            }

            //Esce dal gioco dopo esser stato nel menu di Game Over
            if (mx >= quitbuttontomenu.getX() && mx <= (quitbuttontomenu.getWidth() + quitbuttontomenu.getX())) {
                if (my >= quitbuttontomenu.getY() && my <= (quitbuttontomenu.getY() +quitbuttontomenu.getHeight())) {
                    //Usciamo dal gioco con il codice di errore
                    System.exit(1);
                }
            }


        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
