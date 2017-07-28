package io.steria.pox3.got.game;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import io.steria.pox3.got.game.Game;
import io.steria.pox3.got.game.Player;
import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;
import io.steria.pox3.got.tile.World;

public class GameTest {
	World world;
	Game game;
	Player nicolas;
	Player anne;
	House stark, lannister;
	
	@Before //pour lancer la fonction avant chaque test
	public void setUp(){
		world = new World();
		world.generate();
		System.out.println("reinitialize new game");
		this.game = new Game(world);
		
		HouseFactory factory = new HouseFactory();
		this.stark = factory.getStark();
		this.lannister = factory.getLannister(); //game.houses.get(1)
		
		this.anne = new Player(game, "Anne", lannister);
		this.nicolas = new Player(game, "Nicolas", stark);
		
//this pas obligé en soi mais permet de savoir qu'il s'agit d'un attribut, bleu =attribut
	}

	/*@Test
	public void testChoosePlayerNumber() {
		game.choosePlayerNumber(5);
		assertTrue(game.players.size() == 5);

	}fonction a disparu*/

	@Test
	public void testGetAvailableHouses() {
		assertEquals(8,game.getAvailableHouses().size());
		//game.choosePlayerNumber(3);
		assertTrue (game.getAvailableHouses().size() == 8);
		//assertTrue (game.players.size() == 3); //pour savoir si j'ai bien 3 joueurs
	}

	@Test
	public void testSavePlayer() {
		assertTrue (game.getAvailableHouses().size() == 8);
		//game.choosePlayerNumber(2);
		anne.house = lannister;
		game.savePlayer(anne);
		System.out.println(anne.house);
		assertTrue (game.getAvailableHouses().size() == 7);
	
		
	}

}
