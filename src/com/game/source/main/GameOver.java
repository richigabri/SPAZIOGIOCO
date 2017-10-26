package com.game.source.main;

import java.awt.*;

import static com.game.source.main.Game.getScore;
import static com.game.source.main.Game.getScore2;

public class GameOver extends Menu {


    public Rectangle replaybutton = new Rectangle(Game.WIDTH/2-120 , 150, 100, 50);

    public Rectangle quitbuttontomenu = new Rectangle(Game.WIDTH/2 + 320, 150, 100, 50);


    public void render(Graphics g) {

        //Cast a Graphics2D per disegnare il rettangolo
        Graphics2D g2d = (Graphics2D) g;

        //creo il font a mio piacimento 
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        
        //lo imposto come standard e imposto anche il colore
        g.setFont(fnt0);
        g.setColor(Color.white);

        //scrivo ciò che ci sara nel mio menu gameover
            g.drawString("GAME OVER", Game.WIDTH / 2, 100);

        //Aggiungo le scritte e disegno le voci del mio menu
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);

        // riempio le voci del mio menù modificando le x e le y perchï¿½ andrebbe a scrivere nell'angolo in alto a destra
        g.drawString("Menù", replaybutton.x + 11, replaybutton.y +35);
        g.drawString("Esci", quitbuttontomenu.x + 19, quitbuttontomenu.y + 35);
        g.drawString("IL TUO PUNTEGGIO E' : "+  getScore2(),140,300);
        
        
        
        //disegno i bordi delle voci del menï¿½
        g2d.draw(replaybutton);
        g2d.draw(quitbuttontomenu);

    }

    public Rectangle getReplaybutton() {
        return replaybutton;
    }

    public void setReplaybutton(Rectangle replaybutton) {
        this.replaybutton = replaybutton;
    }

    public Rectangle getQuitbuttontomenu() {
        return quitbuttontomenu;
    }

    public void setQuitbuttontomenu(Rectangle quitbuttontomenu) {
        this.quitbuttontomenu = quitbuttontomenu;
    }


}
