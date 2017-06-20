package io.steria.pox3.got.war;

import io.steria.pox3.got.game.Player;
import io.steria.pox3.got.story.House;
import io.steria.pox3.got.tile.Tile;

public interface IArmy {

	int getTotalTroops(); /*c'est une interface, donc ne prend pas d'espace mémoire. dans une interface il n'y a que des fonctions!!*/
	int getMovedTroops();
	int getReadyTroops();
	
	
	
	void move(int troops, Direction direction); //soit il bouge tout, soit une partie
	
	/**
	 * 
	 * Move all the army
	 * @param domain
	 */
	public void move(Direction direction);
	
	boolean attack (IArmy ennemy);/*pour l'instant renvoie oui ou non*/
	
	Tile getPosition();
	House getHouse();
	
	ArmyState getState();
	
	Player getPlayer();
	
	
}
