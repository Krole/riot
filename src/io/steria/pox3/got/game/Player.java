package io.steria.pox3.got.game;

import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState;

import io.steria.pox3.got.objectives.ObjectiveCard;
import io.steria.pox3.got.story.House;

public class Player {

	String name;
	House house;
	ObjectiveCard card;
	PlayerState state = PlayerState.PLAYING; // 0: in play, 1: won, 2: lost, 3:
												// dead
	RoundState roundstate = RoundState.WAITING;

	// Bidirection arg
	Game game;
	Round currentRound;

	public int moves = 3;

	/*
	 * public Player(String name, House house) { //on a généré le constructeur
	 * donc le super apparait super(); this.name = name; this.house = house; }
	 */

	public Player(Game game, String name, House house) {// on a généré le
														// constructeur en
														// omettant le super
		this.game = game;
		this.name = name;
		this.house = house;
		// argh bidirectional
		this.house.setPlayer(this);
		// but this link never moves. Not so bad
	}
	

	public Game getGame() {
		return game;
	}

	boolean chooseName(String pName) {// p pour paramètre
		this.name = pName;
		return true;
	}

	boolean chooseHouse(House pHouse) {// choisi une maison de type maison
		this.house = pHouse;
		return true;
	}

	ObjectiveCard selectCard() {
		return null;
	}

	public void decreaseMoves() {
		if (this.roundstate == RoundState.WAITING) {
			throw new PlayerRoundEndedException();
		}
		this.moves--;
	}

}
