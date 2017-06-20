package io.steria.pox3.got.game;

import java.util.ArrayList;
import java.util.List;

import io.steria.pox3.got.objectives.ObjectiveCard;
import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;

/**
 * 
 * <strong>Starting</strong> class for launching game
 * 
 * @author AELION
 *
 */

public class Game {

	List<Player> players = new ArrayList<>(); // attribut en minuscuke, classe
												// en majuscule
	List<House> houses = new HouseFactory().getAllHouses();
	List<House> availableHouses = new HouseFactory().getAllHouses(); // ne passe
																		// pas
																		// par
																		// le
																		// constructeur

	List<ObjectiveCard> cards;
	List<Round> rounds = new ArrayList<>();

	public Game() {

	}

	public void init() {
		newRound();
	}

	/*
	 * void choosePlayerNumber(int number) {// camelcase this.players = new
	 * ArrayList<>();// possibilité de mettre 5 ou number for (int i = 0; i <
	 * number; i++) { this.players.add(new Player()); } }
	 */

	List<House> getAvailableHouses() {
		return availableHouses;
	}

	void savePlayer(Player player) {
		this.players.add(player);
		this.availableHouses.remove(player.house);
	}

	Round getCurrentRound() {
		return this.rounds.get(this.rounds.size() - 1);// récupérer le dernier
														// élément de la liste
	}

	void newRound() {
		this.rounds.add(new Round());
		// this.players.stream().forEach(p -> p.moves = 3);

		boolean firstNotDead = true;
		for (Player player : this.players) {
			if (player.roundstate != RoundState.DEAD) {
				player.moves = 3;
				if (firstNotDead) {
					player.roundstate = RoundState.PLAYING;

					firstNotDead = false;
				} else {
					player.roundstate = RoundState.WAITING;
				}
			} else {
				player.moves = 0;
			}

		}

		/*
		 * Round newRound = new Round(); this.rounds.add(newRound); version
		 * longue
		 */

	}

}
