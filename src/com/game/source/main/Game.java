package com.game.source.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.game.source.main.classes.EntityEnemy;
import com.game.source.main.classes.EntityFriendly;

public class Game extends Canvas implements Runnable {
	
	//dimensioni della finestra e titolo
	public static final long serialVersionUID = 1L;
	public static final int WIDTH = 320;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 2;
	public final String TITLE = "SPACE SHOOTER GABRI-RICHI";



	private boolean running = false;
	private Thread thread;
	
	//buffer immagini e sfondo
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
	/* METODO INIZIALE CON SPRITESHEET tengo per sciurezza
	 * private BufferedImage spriteSheet = null;
	 */
	private BufferedImage background = null;
	
	//flag per glitch comandi
	private boolean is_shooting = false;
	
	//numero nemici ondata e morti, le inizializziamo alla prima ondata
	private int enemy_count = 0;
	private int enemy_killed = 0;
	private int wave = 0;
	
	private Player p;//primo giocatore
	private Player2 p2; //secondo giocatore


	private Controller c;
	private Textures tex;
	private Menu menu;
	private GameOver gameover;
	private ScoreMenu punteggi;


	
	
	//elenco di tutti gli elementi buoni e cattivi 
	public LinkedList<EntityFriendly> ef;
	public LinkedList<EntityEnemy> ee;
	
	
	

	//Barra dei punti vita della nostra nave, da lavorarci per aggiungere le vite
	public static int health =200; //punti vita del  primo giocatore
	public static  int health2 = 200; // punti vita del secondo giocatore


	private static int score=0; //punteggio
	private static int score2=0;

	public static int flagplayer=1;//flag per il numero di giocare (di default c'Ã¨ solo un giocatore) 1=un giocare 0=2giocatori

	/* Creazione del menï¿½ : vari stati in cui mi trovo
	 * Nel nostro caso usiamo lo stato GAME quando sono in gioco e lo stato MENU quando sono nel menï¿½, infine gameover quando ho perso
	 */
	public static enum STATE{
		MENU,
		GAME,
		GAMEOVER,
		SCORE,
		
	}
	//usiamo State per capire in quale situazione siamo, la inizializziamo al menï¿½ perchï¿½ il gioco all'avvio mostrerï¿½ il menï¿½
	public static STATE State = STATE.MENU;

	public void init() {
		requestFocus();
		/*	PER CARICARE LO SFONDO
		*	BufferedImageLoader loader = new BufferedImageLoader();
		*/

		//Inizializzo la posizione
		this.addKeyListener(new KeyInput(this));
		
		//carichiamo le texture PRIMA della chiamata a player sennï¿½ non andrebbe
		tex = new Textures(this);
		c = new Controller(this, tex);
		p = new Player(150, 400, tex, this, c);
		p2=new Player2(450, 400, tex, this, c);
		menu = new Menu();
		gameover=new GameOver();
		punteggi=new ScoreMenu();

		//passo le dimensioni del menï¿½ a MouseListener
		this.addMouseListener(new MouseInput(menu));
		this.addMouseListener(new MouseInput2(gameover));
		
		//Inizializzo le liste di amici e nemici
		ef = c.getEntityFriendly();
		ee = c.getEntityEnemy();
		
		//creo N nemici per ondata
		c.createEnemy(enemy_count);

	}
	
	//avvio gioco
	private synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	//per fermare il gioco
	private synchronized void stop() {
		if(!running)
			return;
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	//ciclo di gioco che si ripete
	public void run() {
		init();
		long lasttime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = (1000000000)/amountOfTicks;	//per equilibrare il tempo che passa
		double delta = 0;	//per il tempo che passa
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		//misuro anche FPS e tick
		while(running) {
			//tempo attuale
			long now = System.nanoTime();
			delta += (now - lasttime)/ ns;
			lasttime = now;
			if (delta >= 1) {
				tick();
				updates ++;
				delta --;
			}
			render();
			frames++;
			
			if ((System.currentTimeMillis() - timer) > 1000) {
				timer += 1000;
				System.out.println(updates + "Ticks, Fps" + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	//tutto ciï¿½ che viene aggiornato
	private void tick() {
		//eseguo le azioni SOLO SE sono nello stato Game
		if (State == STATE.GAME) {

			p.tick();
			if (flagplayer != 1) {
				p2.tick();
			}
			c.tick();


		}
		//se finiscono i nemici passo alla seconda ondata
		if (enemy_killed >= enemy_count) {
			wave += 1;    //Aumento l'ondata per indicare che ho superato il livello
			enemy_count += 2;    //nel nostro gioco aggiungiamo 2 nemici ad ondata
			enemy_killed = 0;    //reseettiamo il numero di kill
			c.createEnemy(enemy_count);    //creiamo i nostri nuovi nemici
		}

		//quando la vita finisce creare il nemu nel quale si vede il punteggio finale e ritornare al menu principale o uscire dal gioco
		if (flagplayer == 1)
			if (health <= 0) {
				score2 = score;
				State = STATE.GAMEOVER;
				//Se ho ottenuto un punteggio decente lo salvo
				if (score>=HighScore.getHighScores()[9].getScore())
				{
					String name=JOptionPane.showInputDialog(null, "Complimenti, hai realizzato un record!\nInserisci il tuo nome.\n(Nota: verranno salvati solo 10 caratteri).",
							"Tetris", JOptionPane.INFORMATION_MESSAGE);
					//se il nome è valido inserisco il punteggio nel file
					if (name!=null)
						HighScore.addHighScore(new HighScore(score,(name.length()>10)?name.substring(0, 10):name));
				}
				this.Reset();
			}
			if(flagplayer==0) {
				if(health<=0){
					//rimuovere la navicella 1
				}
				if(health2<=0){
					//rimuovere la navicella 2
				}
				if (health <= 0 && health2 <= 0) {
					score2 = score;
					//Se ho ottenuto un punteggio decente lo salvo
					if (score>=HighScore.getHighScores()[9].getScore())
					{
						String name=JOptionPane.showInputDialog(null, "Complimenti, hai realizzato un record!\nInserisci il tuo nome.\n(Nota: verranno salvati solo 10 caratteri).",
								"Tetris", JOptionPane.INFORMATION_MESSAGE);
						//se il nome è valido inserisco il punteggio nel file
						if (name!=null)
							HighScore.addHighScore(new HighScore(score,(name.length()>10)?name.substring(0, 10):name));
					}
					State = STATE.GAMEOVER;
					this.Reset();
				}
			}
	}



	//tutto ciò che viene renderizzato
	private void render() {
		//gestisce il buffering
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3); //3 buffer che caricano immagini a catena
			return;
		}
		
		//imposta il buffer per disegnare
		Graphics g = bs.getDrawGraphics();
		
		//////////////////////////////////////////

		g.drawImage(tex.background, 0, 0, getWidth(), getHeight(), this); //schermo nero di sfondo

        g.drawImage(background, 0, 0, null);
		
		
		//Come prima, renderizzo SOLO se sono nello stato GAME
		if(State == STATE.GAME) {
			
			p.render(g);
			if(flagplayer!=1){
			p2.render(g);}
			c.render(g);

			//sfondo per far risaltare meglio i PV
			g.setColor(Color.GRAY);
			if(flagplayer!=1){
			g.fillRect(5, 5, 200, 35);}
			g.fillRect(5,5,200,15);
			//disegno la nostra barra dei punti vita primo giocatore
			g.setColor(Color.CYAN);
			g.fillRect(5, 5, health, 15);
			//disegno la barra dei punti vita per il secondo giocatore
			if(flagplayer!=1){
			g.setColor(Color.red);
			g.fillRect(5, 25, health2, 15);}
			//bordino per distinguerla dallo sfondo
			g.setColor(Color.white);
			g.drawRect(5, 5, health, 15);
			if(flagplayer!=1){
			g.drawRect(5,25,health2,15);}
			//visualizzo lo score
			g.drawString("SCORE: "+score ,270, 30);
			//Visualizzo ondate e nemici rimasti
			g.drawString("ONDATA: "+wave ,400, 30);
			g.drawString("NEMICI RIMASTI: "+(enemy_count - enemy_killed) ,500, 30);
			
			
			
		} 
		else if (State == STATE.MENU) {
			//qui disegno e creo il mio menu

			menu.render(g);

		}
		else if (State == STATE.SCORE) {
			
			punteggi.render(g);
		}
		
		
		if (State==STATE.GAMEOVER){
			//creo il menu game over **da rivedere**
			gameover.render(g);
		}




		
		
		//////////////////////////////////////////
		//TUTTO ciï¿½ tra i commenti verrï¿½ resettato una volta qui
		g.dispose();
		bs.show();
	}
	
	//comandi via tastiera
	public void keyPressed(KeyEvent e) {
		int key= e.getKeyCode();		
		// con le frecce muovo il player1 con le lettere (ASDW) muovo il player2
		if (State == STATE.GAME) {
			if (key == KeyEvent.VK_LEFT) {
				p.setVelX(-5);
			}
			if ( key == KeyEvent.VK_A && flagplayer!=1) {
				p2.setVelX(-5);
			}
			if (key == KeyEvent.VK_RIGHT ) {
				p.setVelX(5);
			}
			if (key == KeyEvent.VK_D && flagplayer!=1) {
				p2.setVelX(5);
			}
			if (key == KeyEvent.VK_UP ) {
				p.setVelY(-5);
			}
			if ( key == KeyEvent.VK_W && flagplayer!=1) {
				p2.setVelY(-5);
			}
			if (key == KeyEvent.VK_DOWN ) {
				p.setVelY(5);
			}
			if ( key == KeyEvent.VK_S && flagplayer!=1) {
				p2.setVelY(5);
			}
			if (key == KeyEvent.VK_SPACE && !is_shooting) {
				Sounds.playerShoot.play();
				is_shooting = true;
				c.addEntity(new Bullet(p.getX()+46, p.getY()-25, tex, this));//la navicella spara centrale
			}
			if (key == KeyEvent.VK_R && !is_shooting && flagplayer!=1) {
				Sounds.playerShoot.play();
				is_shooting = true;
				c.addEntity(new Bullet2(p2.getX()+46, p2.getY()-25, tex, this));//la navicella spara centrale
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key= e.getKeyCode();
		
		if (State == STATE.GAME) {
			if (key == KeyEvent.VK_LEFT ) {
				p.setVelX(0);
			}
			if ( key == KeyEvent.VK_A && flagplayer!=1) {
				p2.setVelX(0);
			}
			if (key == KeyEvent.VK_RIGHT ) {
				p.setVelX(0);
			}
			if ( key == KeyEvent.VK_D && flagplayer!=1) {
				p2.setVelX(0);
			}
			if (key == KeyEvent.VK_UP ) {
				p.setVelY(0);
			}
			if ( key == KeyEvent.VK_W && flagplayer!=1) {
				p2.setVelY(0);
			}
			if (key == KeyEvent.VK_DOWN ) {
				p.setVelY(0);
			}
			if ( key == KeyEvent.VK_S && flagplayer!=1) {
				p2.setVelY(0);
			}
			if (key == KeyEvent.VK_SPACE) {
				is_shooting = false;
			}
			if (key == KeyEvent.VK_R && flagplayer!=1) {
				is_shooting = false;
			}
		}
	}
	
	public static void main(String args[]) {
		Game game = new Game();
		
		//definisco le dimensioni massime della mia finestra
		game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
		Sounds.gameMusic.play(); //colonna sonora
	}
	
	//GETTERS & SETTERS
	
	public int getEnemy_count() {
		return enemy_count;
	}

	public void setEnemy_count(int enemy_count) {
		this.enemy_count = enemy_count;
	}

	public int getEnemy_killed() {
		return enemy_killed;
	}

	public void setEnemy_killed(int enemy_killed) {
		this.enemy_killed = enemy_killed;
	}
	
	public static int getScore() {
		return score;
	}
	public static int getScore2() {
		return score2;
	}


	public static void setScore(int score) {
		Game.score = score;
	}
	
	 public int getWave() {
		return wave;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}

	//Questo metodo mi riporta alle condizioni iniziali della mia prima ondata
	 public  void Reset() {
		 //resetto vita e posizione

		 if(flagplayer==0){
			 health = 200;
			 health2 = 200;
		 }
		 else
			 health = 200;

		 p.setVelX(0);
		 p.setVelY(0);
		 p.setX(150);//300
		 p.setY(400);
		 //resetto highscore e ondate
		 this.setScore(0);
		 this.setEnemy_count(0);
		 this.setEnemy_killed(0);
		 this.setWave(0);
		 //pulisco il campo di battaglia dai nemici
		 ee.clear();
	 }
	 
	/*	PROVO A CANCELLARE PER AVERE L'IMMAGINE DELL'OGGETTO DENTRO OGNI CLASSE
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(BufferedImage spriteSheet) {
		this.spriteSheet = spriteSheet;
	} 
	*/
	
}