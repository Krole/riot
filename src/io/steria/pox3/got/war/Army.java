package io.steria.pox3.got.war;

import io.steria.pox3.got.game.Player;
import io.steria.pox3.got.story.House;
import io.steria.pox3.got.tile.Domain;
import io.steria.pox3.got.tile.Tile;

public class Army implements IArmy {
	
	int readyTroops;
	int movedTroops;
	House house;
	Domain position; //ces 3 lignes sont des attributs que l'on retrouve dans notre diagramme UML

	
	public Army(int troops, House house, Domain position) {
		this.readyTroops = 0;
		this.house = house;
		this.position = position;
		
	}

	@Override
	public Player getPlayer() {
		return this.house.getPlayer(); //house connait son player
	}
	
	@Override
	public boolean attack(IArmy ennemy) {		
		return false;
	}

	@Override
	public Tile getPosition() {
		return this.position;
	}

	@Override
	public House getHouse() {
		return this.house;
	}

	@Override
	public ArmyState getState() {
		return ArmyState.IDLE;
	}

	@Override
	public int getTotalTroops() {		
		return 0;
	}

	@Override
	public int getMovedTroops() {
		return 0;
	}

	@Override
	public int getReadyTroops() {
		return 0;
	}

	@Override
	public void move(int troops, Direction direction) {
	}

	@Override
	public void move(Direction direction) {			
			this.getPlayer().decreaseMoves();
		}
		
	}
	
	

