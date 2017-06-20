package io.steria.pox3.got.story;

import java.util.ArrayList;
import java.util.List;

public class HouseFactory {
	private static List<House> houses = new ArrayList<>();

	public List<House> getAllHouses() {
		if (houses.size() == 8) {
			return houses;
		}
		House stark = new House("Stark", "Winter is coming");
		House lannister = new House("Lannister", "Hear me Roar!");
		House baratheon = new House("Baratheon", "Ours is the fury");
		House targaryen = new House("Targaryen", "Fire and Blood");
		House tyrell = new House("Martell", "Growing Strong");
		House martell = new House("Martell", "Unbowed, Unbent ,Unbroken");
		House greyjoy = new House("Greyjoy", "We do not Sow");
		House arryn = new House("Arryn", "As high as honor");

		houses.add(stark);
		houses.add(lannister);
		houses.add(baratheon);
		houses.add(targaryen);
		houses.add(tyrell);
		houses.add(martell);
		houses.add(greyjoy);
		houses.add(arryn);
		return houses;

	}

	public House getStark() {
		return getAllHouses().get(0);
	}
	
	public House getLannister() {
		return getAllHouses().get(1);
	}
	
	public House getBaratheon() {
		return getAllHouses().get(2);
	}

	public House getTargaryen() {
		return getAllHouses().get(3);
	}
	
	public House getTyrell() {
		return getAllHouses().get(4);
	}
	
	public House getMartell() {
		return getAllHouses().get(5);
	}
	
	public House getGreyjoy() {
		return getAllHouses().get(6);
	}

	public House getArryn() {
		return getAllHouses().get(7);
	}
}
