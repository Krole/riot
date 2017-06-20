package io.steria.pox3.got.tile;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import io.steria.pox3.got.story.House;
import io.steria.pox3.got.story.HouseFactory;

public class WorldTest {

	World map;

	@Before
	public void setUp() throws Exception {
		map = new World();
	}

	/*@Test
	public void testMap() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testGenerate() {

		map.generate();
		assertTrue(map.tiles.length == 12);
		assertTrue(map.tiles[0].length == 11);
	}

	@Test
	public void assignFreeDomain() {
		map.assignFreeDomain(1, 0, 4, 2, "North");
		assertTrue(map.tiles[1][0] instanceof Domain);
		Domain north1 = (Domain) map.tiles[1][0];// on caste quand on a
													// instanceof
		assertTrue(north1.name.equals("North-1"));

		map.assignFreeDomain(8, 6, 2, 2, "Volantis");
		assertTrue(map.tiles[8][6] instanceof Domain);
		Domain volantis2 = (Domain) map.tiles[9][6];
		assertTrue(volantis2.name.equals("Volantis-2"));
	}

	@Test
	public void assignDomainWithHouse() {
		House stark = new HouseFactory().getStark();

		map.assignDomainWithHouse(1, 2, 4, 2, "Winterfell", stark);
		assertTrue(map.tiles[1][2] instanceof Domain);
		Domain winterfell1 = (Domain) map.tiles[2][3];
		assertTrue(winterfell1.house.equals(Optional.of(stark)));
		assertEquals("Winterfell-6", winterfell1.name);

		House martell = new HouseFactory().getMartell();
		map.assignDomainWithHouse(3, 8, 2, 2, "Dorne", martell);
		assertTrue(map.tiles[3][8] instanceof Domain);
		Domain dorne1 = (Domain) map.tiles[3][8];
		assertTrue(dorne1.house.equals(Optional.of(martell)));
		assertTrue(dorne1.name.equals("Dorne-1"));
	}

	
	@Test
	public void fillWater(){
		House stark = new HouseFactory().getStark();
		map.assignDomainWithHouse(1, 2, 4, 2, "Winterfell", stark);
		
		map.fillWater();
		
		Domain winterfell6 = (Domain) map.tiles[2][3];
		assertEquals("Winterfell-6", winterfell6.name);
		
		assertTrue(map.tiles[6][6] instanceof WaterTile);
		
		
	}
	
	
	@Test
	public void testAllowMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsWinter() {
		assertFalse(map.winter); //assertTrue(map.winter == false
		//pour vérifier que winter est bien faux pour commencer
		map.starWinter();
		assertTrue(map.winter);
	}

}
