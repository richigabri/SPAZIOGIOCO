package com.game.source.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreManager {
    // elenco di punteggi, lo usiamo per salvare i punteggi migliori e confrontarli uno ad uno
    private ArrayList<Score> scores;

    // IMPORTANTE : questo è il file dove verranno salvati i punteggi più alti
    //usiamo un file di testo per maggiore semplicità nel visualizzare i risultati a occhio nudo
    private static final String HIGHSCORE_FILE = "scores.txt";

    //Inizializzo input e output per lavorare con il file dei punteggi
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public ScoreManager() {
        //inizializzo l'elenco di punteggi
        scores = new ArrayList<Score>();
    }
    
    public ArrayList<Score> getScores() {
    	//Getter MA dopo aver ordinato e caricato l'elenco di punteggi
    	loadScoreFile();
        sort();
        return scores;
    }
    
    private void sort() {
    	/* funzione per ordinare l'elenco di punteggi
    	 * Prima crea un comparator che ci servirà per la funzione sort all'interno di Collections
    	 */
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }
    
    //aggiungiamo un punteggio (i parametri da inserire sono passati da Game)
    public void addScore(String name, int score) {
        //carichiamo il file
    	loadScoreFile();
    	//creiamo il nuovo punteggio
        scores.add(new Score(name, score));
        //uploadiamo il file
        updateScoreFile();
    }
    
    @SuppressWarnings("unchecked")
	public void loadScoreFile() {
        //carica il punteggio sul file ()
    	try {
    		/*	Carico in input il file creato in precedenza
    		 * 	Riempio scores con i valori all'interno del file caricato
    		 * Controllo le eccezioni e gli errori (questi li ho copincollati dalla wiki)
    		 */
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("[Laad] FNF Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[Laad] IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("[Laad] CNF Error: " + e.getMessage());
        }
    	//per ultima cosa procedo a chiudere lo standard output
    	finally {
            try {
            	//chiudo l'output e segnalo gli eventuali errori
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Laad] IO Error: " + e.getMessage());
            }
        }
    }
    
    //Aggiorno il file precedente, ma invece che ricevere in input lo scrivo in output (per il resto è simile a loadScoreFile)
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("[Update] FNF Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("[Update] IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("[Update] Error: " + e.getMessage());
            }
        }
    }
    
    //Questo metodo restituisce i primi dieci Score di un elenco già ordinato
    public String getHighscoreString() {
        //inizializzo le variabili : il numero di max è il numero massimo di score che andremo a vedere ( primi max classificati)
    	String highscoreString = "";
        int max = 10;
        
        //creo una variabile arraylist e la riempio con gli score registrati
        ArrayList<Score> scores;
        scores = getScores();
        
        //controllo quali score visualizzare
        int i = 0;
        int x = scores.size();
        //se ci sono troppi score considero SOLO i primi 10 (o il valore in max)
        if (x > max) {
            x = max;
        }
        /*	per ognuno dei primi X score vado a scrivere il suo valore nella Stringa highscoreString con :
         * 	-POSIZIONE
         * -NOME
         * -PUNTEGGIO
         * 
         * dopodichè incremento I per passare all'elemento successivo
         */
        while (i < x) {
            highscoreString += (i + 1) + ".\t" + scores.get(i).getName() + "\t\t" + scores.get(i).getScore() + "\n";
            i++;
        }
        return highscoreString;
}
    
}