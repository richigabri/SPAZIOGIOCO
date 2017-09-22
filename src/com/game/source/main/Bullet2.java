package com.game.source.main;

import com.game.source.main.classes.EntityFriendly;

import java.awt.*;

/**
 * Created by Utente on 22/09/2017.
 */
public class Bullet2 extends GameObject implements EntityFriendly {
    //creiamo e inizializziamo le variabili
    private Textures tex;
    @SuppressWarnings("unused")
    private Game game;


    public Bullet2(double x, double y, Textures tex, Game game) {
        super(x,y);	//USO I dati del costruttore in GameObject
        this.tex = tex;
        this.game = game;
    }

    //movimento proiettile
    public void tick() {
        y -= 10;

    }

    public Rectangle getBounds() {
        //DA CONTROLLARE PER LE DIMENSIONI NON FISSATE DEGLI OGGETTI
        return new Rectangle((int)x, (int)y, getWidth(), getHeight());
    }

    //disegno proiettile
    public void render(Graphics g) {
        g.drawImage(tex.bullet2,  (int)x , (int)y, null);
    }


    //getter & setter
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    //getter delle dimensioni
    public int getWidth() {
        return tex.bullet.getWidth();
    }

    public int getHeight() {
        return tex.bullet.getHeight();
    }
}
