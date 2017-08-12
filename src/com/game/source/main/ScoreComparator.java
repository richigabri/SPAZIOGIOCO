package com.game.source.main;

import java.util.Comparator;

//Come dice il nome, serve a confrontare due highscore e a passare il risultato allo ScoreManager che potrà modificare la tabella di conseguenza
public class ScoreComparator implements Comparator<Score> {
    //Funzione di confronto
	public int compare(Score score1, Score score2) {
    	//Salvo i valori del punteggio perchè mi interessa confrontare quelli
        int sc1 = score1.getScore();
        int sc2 = score2.getScore();
        /*	In base al confronto so che :
         *	
         *	-1 indica che il primo valore è superiore al secondo
         *	1 indica che il secondo valore è superiore al primo
         *	0 indica che sono uguali 
        */
        if (sc1 > sc2){
            return -1;
        }else if (sc1 < sc2){
            return +1;
        }else{
            return 0;
        }
    }
}
