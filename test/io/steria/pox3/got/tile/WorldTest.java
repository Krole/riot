package io.steria.pox3.got.tile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;
import io.steria.pox3.got.war.Direction;

public class WorldTest {

	World world;

	@Before
	public void setUp() throws Exception {
		world = new World();
		world.generate();
	}

	/*@Test
	public void testMap() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testGenerate() {

		world.generate();
		assertTrue(world.tiles.length == 12);
		assertTrue(world.tiles[0].length == 11);
	}

	@Test
	public void assignFreeDomain() {
		world.assignFreeDomain(1, 0, 4, 2, "North");
		assertTrue(world.tiles[1][0] instanceof Domain);
		Domain north1 = (Domain) world.tiles[1][0];// on caste quand on a
													// instanceof
		assertTrue(north1.name.equals("North-1"));

		world.assignFreeDomain(8, 6, 2, 2, "Volantis");
		assertTrue(world.tiles[8][6] instanceof Domain);
		Domain volantis2 = (Domain) world.tiles[9][6];
		assertTrue(volantis2.name.equals("Volantis-2"));
	}

	@Test
	public void assignDomainWithHouse() {
		House stark = new HouseFactory().getStark();

		world.assignDomainWithHouse(1, 2, 4, 2, "Winterfell", stark);
		assertTrue(world.tiles[1][2] instanceof Domain);
		Domain winterfell1 = (Domain) world.tiles[2][3];
		assertTrue(winterfell1.house.equals(Optional.of(stark)));
		assertEquals("Winterfell-6", winterfell1.name);

		House martell = new HouseFactory().getMartell();
		world.assignDomainWithHouse(3, 8, 2, 2, "Dorne", martell);
		assertTrue(world.tiles[3][8] instanceof Domain);
		Domain dorne1 = (Domain) world.tiles[3][8];
		assertTrue(dorne1.house.equals(Optional.of(martell)));
		assertTrue(dorne1.name.equals("Dorne-1"));
	}

	
	@Test
	public void fillWater(){
		House stark = new HouseFactory().getStark();
		world.assignDomainWithHouse(1, 2, 4, 2, "Winterfell", stark);
		
		world.fillWater();
		
		Domain winterfell6 = (Domain) world.tiles[2][3];
		assertEquals("Winterfell-6", winterfell6.name);
		
		assertTrue(world.tiles[6][6] instanceof WaterTile);
		
		
	}
	@Test
	public void testBasicDirection(){
		
		Domain winterfell6  = (Domain) world.tiles[2][3];
		Tile tile = world.neighbour(winterfell6, Direction.EAT).get();
		assertTrue(tile.x == 3);
		assertTrue(tile.y == 3);
		
		Tile tile2 = world.neighbour(winterfell6, Direction.NORTH).get();
		assertTrue(tile2.x == 2);
		assertTrue(tile2.y == 2);
		
	}
		
		@Test
		public void testComplexDirection(){
		
		WaterTile corner  = (WaterTile) world.tiles[0][0];
		Optional<Tile> tile = world.neighbour(corner, Direction.WEST);
		assertFalse(tile.isPresent());
		
		tile = world.neighbour(corner, Direction.NORTH);
		assertFalse(tile.isPresent());
		
		tile = world.neighbour(corner, Direction.SOUTH);
		assertTrue(tile.isPresent());
		
	}
	
	@Test
	public void testAllowMove() {
		Tile corner  = world.tiles[0][0];
		Tile calais  = (Domain) world.tiles[1][0];
		Tile dunkerque  = (Domain) world.tiles[2][0];

		assertTrue(world.allowMove(corner, true));
		assertFalse(world.allowMove(corner, false));
		
		assertTrue(world.allowMove(dunkerque, true));
		assertTrue(world.allowMove(calais, false));

	}

	@Test
	public void testIsWinter() {
		assertFalse(world.winter); //assertTrue(map.winter == false
		//pour vérifier que winter est bien faux pour commencer
		world.starWinter();
		assertTrue(world.winter);
	}

}
