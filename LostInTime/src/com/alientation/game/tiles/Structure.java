package com.alientation.game.tiles;

import com.alientation.game.World;

public class Structure {
	private int x; // top left position
	private int y;
	private int numTilesWidth;
	private int numTilesHeight;
	private int type;
	
	private StructureTile leadTile;
	private World oldWorld;
	private World newWorld;
	
	private SpaceTile entrance;
	private SpaceTile exit;
	
	public Structure() {
		
	}
	
	public Structure(StructureTile leadTile, SpaceTile entrance, SpaceTile exit, int type, int x, int y, int numTilesWidth, int numTilesHeight, World oldWorld, World newWorld) {
		this.leadTile = leadTile;
		this.entrance = entrance;
		this.exit = exit;
		this.type = type;
		this.x = x;
		this.y = y;
		this.numTilesWidth = numTilesWidth;
		this.numTilesHeight = numTilesHeight;
		this.oldWorld = oldWorld;
		this.newWorld = newWorld;
		leadTile.setStructure(this);
	}
	
	public StructureTile getLeadTile() { return leadTile; }
	public void setLeadTile(StructureTile leadTile) { this.leadTile = leadTile; }
	public int getX() { return x; }
	public void setX(int x) { this.x = x; }
	public int getY() { return y; }
	public void setY(int y) { this.y = y; }
	public int getWidth() { return numTilesWidth; }
	public void setWidth(int numTilesWidth) { this.numTilesWidth = numTilesWidth; }
	public int getHeight() { return numTilesHeight; }
	public void setHeight(int numTilesHeight) { this.numTilesHeight = numTilesHeight; }
	public void setCurrentWorld(World world) { newWorld = world; }
	public void setExitWorld(World world) { oldWorld = world; }
	public World getCurrentWorld() { return newWorld; }
	public World getExitWorld() { return oldWorld; }
	public SpaceTile getEntranceTile() { return entrance; }
	public SpaceTile getExitTile() { return exit; }
	public void setEntranceTile(SpaceTile entrance) { this.entrance = entrance; }
	public void setExitTile(SpaceTile exit) { this.exit = exit; }
}