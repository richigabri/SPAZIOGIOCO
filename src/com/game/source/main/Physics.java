package com.game.source.main;

import com.game.source.main.classes.EntityEnemy;
import com.game.source.main.classes.EntityFriendly;

public class Physics {
	//Classe che controlla le collisioni tra le varie entità
	
	//CONTROLLO SE IL MIO ELEMENTO AMICO SELEZIONATO COLLIDE CON UNO QUALSIASI TRA TUTTI GLI ELEMENTI NEMICI, e ritorno vero o falso
	public static boolean Collision(EntityFriendly ef, EntityEnemy ente){
		
		//COSA CI VA IN GETBOUNDS? LE MISURE DI ef E ente
		if( ef.getBounds().intersects(ente.getBounds()) ) {
			return true;
		}
		//se non collidono ovviamente ritorna false
		return false;
	}	

	//CONTROLLO SE IL MIO ELEMENTO NEMICO SELEZIONATO COLLIDE CON UNO QUALSIASI TRA TUTTI GLI ELEMENTI AMICI, e ritorno vero o falso
	public static boolean Collision(EntityEnemy ee, EntityFriendly entf){
		
		//COSA CI VA IN GETBOUNDS? LE MISURE DI ee E entf
		if( ee.getBounds().intersects(entf.getBounds()) ) {
			return true;
		}
		//se non collidono ovviamente ritorna false
		return false;
	}
}
