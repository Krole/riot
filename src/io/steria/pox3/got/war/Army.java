package io.steria.pox3.got.war;

import io.steria.pox3.got.game.Player;
import io.steria.pox3.got.story.House;
import io.steria.pox3.got.tile.Domain;
import io.steria.pox3.got.tile.Tile;
import io.steria.pox3.got.tile.World;

public class Army implements IArmy {

	int readyTroops;
	int movedTroops;
	House house;
	Tile position; // ces 3 lignes sont des attributs que l'on retrouve dans
						// notre diagramme UML

	public Army(int troops, House house, Domain position) {
		this.readyTroops = 0;
		this.house = house;
		this.position = position;

	}

	@Override
	public Player getPlayer() {
		return this.house.getPlayer(); // house connait son player
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
		if (troops > this.readyTroops){
			throw new IllegalArgumentException();
		}
		
		World world = this.getPlayer().getGame().getWorld();	
		if (troops == this.readyTroops){
			
			Tile destination = world.neighbour(this.getPosition(), direction)
					.orElseThrow(()->new IllegalArgumentException());
			
			if (world.allowMove(destination,this.getHouse().hasBoat())){
				this.position = destination;
				this.getPlayer().decreaseMoves();
				this.readyTroops = 0;
			}else{
				throw new IllegalStateException("You don't have boat");
			}
		}
		//TODO nicolas GOT-2342: case where we split army
	}

	@Override
	public void move(Direction direction) {
		this.move(this.readyTroops, direction);		
	}

}
