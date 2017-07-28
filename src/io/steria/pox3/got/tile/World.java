package io.steria.pox3.got.tile;

import java.util.Optional;

import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;
import io.steria.pox3.got.war.Direction;

public class World {

	Tile[][] tiles = new Tile[12][11]; // tableau de tableau : un tableau = 1
										// ligne
	boolean winter = false;
	int winterlattitude = 0;

	public World() {
		// generate();
	}

	public Domain getWinterfell7() {
		return (Domain) this.get(3, 3);
	}

	public Domain getEyrie1() {
		return (Domain) this.get(3, 4);
	}

	public Domain getThrone() {
		return (Domain) this.get(4, 7);
	}

	public Domain getMeereen() {
		return (Domain) this.get(6, 9);
	}

	public void generate() {
		assignFreeDomain(1, 0, 4, 2, "North");
		assignFreeDomain(8, 6, 2, 2, "Volantis");
		assignFreeDomain(8, 4, 2, 2, "Braavos");
		assignFreeDomain(8, 6, 2, 2, "Pentos");
		assignFreeDomain(10, 4, 1, 6, "RedWaste");

		House stark = new HouseFactory().getStark();
		this.assignDomainWithHouse(1, 2, 4, 2, "Winterfell", stark);

		House greyjoy = new HouseFactory().getGreyjoy();
		this.assignDomainWithHouse(1, 4, 2, 2, "Iron Islands", greyjoy);

		House arryn = new HouseFactory().getArryn();
		this.assignDomainWithHouse(3, 4, 2, 2, "The Eyrie", arryn);

		House lannister = new HouseFactory().getLannister();
		this.assignDomainWithHouse(1, 6, 2, 2, "Castarly Rock", lannister);

		House baratheon = new HouseFactory().getBaratheon();
		this.assignDomainWithHouse(3, 6, 2, 2, "Kings Landing", baratheon);

		House tyrell = new HouseFactory().getTyrell();
		this.assignDomainWithHouse(1, 8, 2, 2, "High Garden", tyrell);

		House martell = new HouseFactory().getMartell();
		this.assignDomainWithHouse(3, 8, 2, 2, "Dorne", martell);

		House targaryen = new HouseFactory().getTargaryen();
		this.assignDomainWithHouse(8, 8, 2, 2, "Meereen", targaryen);

		fillWater();
	}

	public void assignFreeDomain(int xOrigin, int yOrigin, int xSize, int ySize, String name) {

		assignDomainWithHouse(xOrigin, yOrigin, xSize, ySize, name, null);

	}

	public void assignDomainWithHouse(int xOrigin, int yOrigin, int xSize, int ySize, String name, House house) {

		int number = 1;

		for (int y = yOrigin; y < yOrigin + ySize; y++) {

			for (int x = xOrigin; x < xOrigin + xSize; x++) {

				Domain domain = new Domain(x, y, name + "-" + number);
				// domain.name = name+"-"+number;
				tiles[x][y] = domain;
				domain.house = house == null ? Optional.empty() : Optional.of(house);
				// si c'est nul ça met empty, sinon ça met la maison
				number++;
			}
		}
	}

	public void fillWater() {

		for (int y = 0; y <= 10; y++) {

			for (int x = 0; x <= 11; x++) {

				Tile tile = this.tiles[x][y];
				if (tile == null) {
					this.tiles[x][y] = new WaterTile(x, y);
				}

			}
		}

	}

	public boolean allowMove(Tile destination, boolean boat) {
		if (boat){
			return true;
		}
		if(destination instanceof WaterTile){
			return boat;
		}
		return true;
	}

	boolean isWinter() {
		return false;
	}

	void starWinter() {
		this.winter = true;
	}

	public void display() {
		for (int y = 0; y <= 10; y++) {

			for (int x = 0; x <= 11; x++) {
				Tile tile = this.tiles[x][y];
				if (tile.toString().length() > 10) {
					System.out.print(" " + tile.toString().substring(0, 10) + "\t"); // \t
																						// est
																						// une
																						// tabulation.
					// substring permet de couper le mot
				} else {
					System.out.print(" " + tile + "\t");
				}
			}

			System.out.println();
		}
	}

	public static void main(String[] args) {
		World map = new World();
		map.generate();
		map.display();
	}

	public Tile get(int x, int y) {
		return this.tiles[x][y];
	}

	public Optional<Tile> neighbour(Tile tile, Direction direction) {

		int x = tile.x;
		int y = tile.y;

		switch (direction) {
		case SOUTH:
			y++;
			break;

		case NORTH:
			y--;
			break;

		case EAT:
			x++;
			break;

		case WEST:
			x--;
			break;

		default:
			throw new IllegalArgumentException();
		}
		if (x < 0 || x >= this.tiles.length || y < 0 || y >= this.tiles[0].length) {
			return Optional.empty();
		}

		Tile neighbour = this.tiles[x][y];
		return Optional.of(neighbour);

	}
}
