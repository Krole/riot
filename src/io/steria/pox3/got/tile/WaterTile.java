package io.steria.pox3.got.tile;

public class WaterTile extends Tile{
	
	public Object water;//a enlever
	
	public WaterTile(int x, int y) {
		super (x, y);		
	}

	@Override
	public String toString() {
		return "~water~";
	}
	
}
