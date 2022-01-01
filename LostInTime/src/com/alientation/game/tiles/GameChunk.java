package com.alientation.game.tiles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import com.alientation.game.Game;
import com.alientation.game.Handler;
import com.alientation.game.entity.Entity;
import com.alientation.game.graphics.SpriteSheet;

public class GameChunk {
	/*
	 * Location
	 */
	private int xPos, yPos;
	
	/*
	 * TODO: Change the way ground and object tiles are stored. Currently they are stored in an [x][y] pattern but should be in an [y][x] because of how matrices work
	 * 
	 */
	private GroundTile[][] groundTiles = new GroundTile[Game.CHUNK_SIZE][Game.CHUNK_SIZE];
	private ObjectTile[][] objectTiles = new ObjectTile[Game.CHUNK_SIZE][Game.CHUNK_SIZE];
	private ArrayList<Entity> entityList = new ArrayList<Entity>();
	
	/*
	 * Better rendering of GameChunk
	 */
	private GameChunkImage gci;
	private Handler handler;
	
	/*
	 * Init GameChunk
	 */
	public GameChunk(int xPos, int yPos, Handler handler) {
		this.xPos = xPos;
		this.handler = handler;
		this.yPos = yPos;
		gci = new GameChunkImage();
	}
	
	/*
	 * Ticks all Ground and Object Tiles
	 */
	public void tick() {
		int x,y;
		for (x = 0; x < 16; x++) {
			for (y = 0; y < 16; y++) {
				if (groundTiles[x][y] != null) {
					groundTiles[x][y].tick();
				}
				if (objectTiles[x][y] != null) {
					objectTiles[x][y].tick();
				}
			}
		}
	}
	
	/*
	 * Renders all Ground and Object Tiles
	 */
	public void render(Graphics g, float offsetX, float offsetY, SpriteSheet spriteSheet) {
		g.drawImage(gci.getBufferedImage(),(int) (xPos * Game.TILE_SIZE * Game.CHUNK_SIZE + offsetX), (int) (yPos * -1 * Game.TILE_SIZE * Game.CHUNK_SIZE + offsetY), null);
		int x,y;
		for (x = 0; x < Game.CHUNK_SIZE; x++) {
			for (y = 0; y < Game.CHUNK_SIZE; y++) {
				if (objectTiles[x][y] != null) {
					objectTiles[x][y].render(g, offsetX, offsetY, spriteSheet);
				}
			}
		}
		
		/*
		 * outlining chunk borders
		 */
		g.setColor(Color.white);
		g.drawRect((int) (xPos * Game.TILE_SIZE * Game.CHUNK_SIZE + offsetX), (int) (yPos * -1 * Game.TILE_SIZE * Game.CHUNK_SIZE + offsetY), Game.TILE_SIZE * Game.CHUNK_SIZE, Game.TILE_SIZE * Game.CHUNK_SIZE);
		
		g.setColor(Color.black);
		g.setFont(new Font("default", Font.BOLD, 16));
		g.drawString(xPos + "," + yPos, (int) (xPos * Game.TILE_SIZE * Game.CHUNK_SIZE + offsetX + (Game.TILE_SIZE/2) * Game.CHUNK_SIZE), (int) (yPos * -1 * Game.TILE_SIZE * Game.CHUNK_SIZE + offsetY + (Game.TILE_SIZE/2) * Game.CHUNK_SIZE));
	}
	
	/*
	 * Getters
	 */
	public int getXPos() { return xPos; }
	public int getYPos() { return yPos; }
	public GroundTile[][] getGroundTiles() { return groundTiles; }
	public ObjectTile[][] getObjectTiles() { return objectTiles; }
	public ArrayList<Entity> getEntities() { return entityList; }
	
	/*
	 * Setters
	 */
	public void addObjectTile(ObjectTile tile, int x, int y) { objectTiles[x][y] = tile; }
	public void removeObjectTile(int x, int y) { objectTiles[x][y] = null; }
	public void addGroundTile(GroundTile tile, int x, int y) { groundTiles[x][y] = tile; gci.updateBI((int)(tile.getX()), (int) (tile.getY()), Handler.spriteSheet.crop(tile.getTextureID()));}
	public void removeGroundTile(int x, int y) { groundTiles[x][y] = null; }
	public void removeEntity(Entity e) { entityList.remove(e); }
	public void addEntity(Entity e) { entityList.add(e); }
}