package com.game.source.main;

import java.io.Serializable;

//Classe che gestisce il punteggio del giocatore, Serializable serve per conservare i dati associati al punteggio tra le varie run
//Ci permette di creare un oggetto di tipo Score (a noi servirà un array di score per la tabella)
@SuppressWarnings("serial")
public class Score  implements Serializable {
	//Le mie variabili saranno il punteggio E il nome del giocatore
	private int score;
    private String name;
    
    //Getter dei miei valori per il confronto
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
    
    //Costruttore
    public Score(String name, int score) {
        this.score = score;
        this.name = name;
    }
}