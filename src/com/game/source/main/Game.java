package com.game.source.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JFrame;


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
	
	private Player p;
	
	private Controller c;
	private Textures tex;
	private Menu menu;
	private GameOver gameover;
	
	
	//elenco di tutti gli elementi buoni e cattivi 
	public LinkedList<EntityFriendly> ef;
	public LinkedList<EntityEnemy> ee;
	
	
	

	//Barra dei punti vita della nostra nave, da lavorarci per aggiungere le vite
	public static int health = 200; //punti vita del giocatore


	private static int score=0; //punteggio



	/* Creazione del men� : vari stati in cui mi trovo
	 * Nel nostro caso usiamo lo stato GAME quando sono in gioco e lo stato MENU quando sono nel men�, infine gameover quando ho perso
	 */
	public static enum STATE{
		MENU,
		GAME,
		GAMEOVER
		
	};
	//usiamo State per capire in quale situazione siamo, la inizializziamo al men� perch� il gioco all'avvio mostrer� il men�
	public static STATE State = STATE.MENU;
	
	public void init() {
		requestFocus();
		/*	PER CARICARE LO SFONDO
		*	BufferedImageLoader loader = new BufferedImageLoader();
		*/
		
		//Inizializzo la posizione
		this.addKeyListener(new KeyInput(this));
		
		//carichiamo le texture PRIMA della chiamata a player senn� non andrebbe
		tex = new Textures(this);
		c = new Controller(this, tex);
		p = new Player(300, 400, tex, this, c);
		menu = new Menu();
		gameover=new GameOver();

		//passo le dimensioni del men� a MouseListener
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
	
	//tutto ci� che viene aggiornato
	private void tick() {
		//eseguo le azioni SOLO SE sono nello stato Game
		if (State == STATE.GAME) {
			p.tick();
			c.tick();

			
			
		}
		//se finiscono i nemici passo alla seconda ondata
		if(enemy_killed >= enemy_count) {
			wave += 1;	//Aumento l'ondata per indicare che ho superato il livello
			enemy_count += 2;	//nel nostro gioco aggiungiamo 2 nemici ad ondata
			enemy_killed = 0;	//reseettiamo il numero di kill
			c.createEnemy(enemy_count);	//creiamo i nostri nuovi nemici
		}

		//quando la vita finisce creare il nemu nel quale si vede il punteggio finale e ritornare al menu principale o uscire dal gioco

		 if (health==0){
			 this.Reset();
			State=STATE.GAMEOVER;
		}
		
	
	}
	
	//tutto ci� che viene renderizzato
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
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this); //schermo nero di sfondo
		
		g.drawImage(background, 0, 0, null);
		
		
		//Come prima, renderizzo SOLO se sono nello stato GAME
		if(State == STATE.GAME) {
			p.render(g);
			c.render(g);
			
			//sfondo per far risaltare meglio i PV
			g.setColor(Color.gray);
			g.fillRect(5, 5, 200, 30);
			//disegno la nostra barra dei punti vita
			g.setColor(Color.green);
			g.fillRect(5, 5, health, 30);
			//bordino per distinguerla dallo sfondo
			g.setColor(Color.white);
			g.drawRect(5, 5, health, 30);
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
		if (State==STATE.GAMEOVER){
			//creo il menu game over **da rivedere**
			gameover.render(g);
		}
		
		
		//////////////////////////////////////////
		//TUTTO ci� tra i commenti verr� resettato una volta qui
		g.dispose();
		bs.show();
	}
	
	//comandi via tastiera
	public void keyPressed(KeyEvent e) {
		int key= e.getKeyCode();
		
		if (State == STATE.GAME) {
			if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
				p.setVelX(-5);
			}
			if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
				p.setVelX(5);
			}
			if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
				p.setVelY(-5);
			}
			if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
				p.setVelY(5);
			}
			if (key == KeyEvent.VK_SPACE && !is_shooting) {
				Sounds.playerShoot.play();
				is_shooting = true;
				c.addEntity(new Bullet(p.getX()+46, p.getY()-25, tex, this));//la navicella spara centrale
			} 
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key= e.getKeyCode();
		
		if (State == STATE.GAME) {
			if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
				p.setVelX(0);
			}
			if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
				p.setVelX(0);
			}
			if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
				p.setVelY(0);
			}
			if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
				p.setVelY(0);
			}
			if (key == KeyEvent.VK_SPACE) {
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

	public  void setScore(int score) {
		Game.score = score;
	}
	
	 public int getWave() {
		return wave;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}

	//Questo metodo mi riporta alle condizioni iniziali della mia prima ondata
	 public void Reset() {
		 //resetto vita e posizione
		 health = 200;
		 p.setVelX(0);
		 p.setVelY(0);
		 p.setX(300);
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