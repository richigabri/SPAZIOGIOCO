package com.game.source.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HighScore implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int score;
	private String name;
	
	//Costruttore
	public HighScore(int score, String name)
	{
		setScore(score);
		setName(name);
	}
	
	//Getters e Setters
	public void setScore(int score)
	{
		this.score=score;
	}
	
	public int getScore()
	{
		return score;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}
	//Metodi di management per l'highscore
	//Confronta this.score con l'argomento e ritorna chi è il più grande
	public int compareTo(HighScore h)
	{
		return new Integer(this.score).compareTo(h.score);
	}
	
	//Se non esiste il file ne creo uno vuoto con valori standard per evitare errori
	private static void initializeFile()
	{
		//inizializzo l'array dei punteggi
		HighScore[] h={new HighScore(0," "),new HighScore(0," "),new HighScore(0," "),
				new HighScore(0," "),new HighScore(0," "),new HighScore(0," "),
				new HighScore(0," "),new HighScore(0," "),new HighScore(0," "),
				new HighScore(0," ")};
		try 
		{
			System.out.println("File Creato");
			ObjectOutputStream o=new ObjectOutputStream(new FileOutputStream("HighScores.dat"));
			o.writeObject(h);
			o.close();
		} catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
	
	//Leggo il file con i punteggi e ritorno i valori
	public static HighScore[] getHighScores()
	{
		//se non esiste lo creo
		if (!new File("HighScores.dat").exists())
			initializeFile();
		try 
		{
			ObjectInputStream o=new ObjectInputStream(new FileInputStream("HighScores.dat"));
			HighScore[] h=(HighScore[]) o.readObject();
			return h;
			//ho salvato i valori nel mio array
		} catch (IOException e) {e.printStackTrace();} 
		catch (ClassNotFoundException e) {e.printStackTrace();}
		return null;
	}
	
	//Aggiunge un nuovo highscore al file e ne mantiene l'ordine
	public static void addHighScore(HighScore h)
	{
		HighScore[] highScores=getHighScores();
		highScores[highScores.length-1]=h;
		for (int i=highScores.length-2; i>=0; i--)
		{
			//li confronto per sapere in che ordine metterli con un file temporaneo
			if (highScores[i+1].compareTo(highScores[i])>0)
			{
				HighScore temp=highScores[i];
				highScores[i]=highScores[i+1];
				highScores[i+1]=temp;
			}
		}
		try 
		{
			//salvo il nuovo elenco sul file
			ObjectOutputStream o=new ObjectOutputStream(new FileOutputStream("HighScores.dat"));
			o.writeObject(highScores);
			o.close();
		} catch (FileNotFoundException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}
	}
	
	
}