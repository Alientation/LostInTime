package com.alientation.game;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.HashMap;

import com.alientation.game.tiles.BlockID;
import com.alientation.game.tiles.GameChunk;
import com.alientation.game.tiles.GroundTile;
import com.alientation.game.tiles.ObjectTile;
import com.alientation.game.tiles.SpaceTile;
import com.alientation.game.tiles.Structure;
import com.alientation.game.tiles.StructureTile;

public class World {
	private float spawnX;
	private float spawnY;
	
	private HashMap<String,GameChunk> gameChunks = new HashMap<String, GameChunk>();
	private GameChunk[][] renderedChunk = new GameChunk[Game.renderDistance][Game.renderDistance];
	
	/*
	 * TODO: Create World Handler that stores every world in current game file, and handle world changes
	 * Initialize World
	 */
	public World(float spawnX, float spawnY) {
		/*
		 * TODO: create new world file
		 */
		this.spawnX = spawnX;
		this.spawnY = spawnY;
	}
	
	public World() {
		/*
		 * TODO: create new world file
		 */
		this.spawnX = 0;
		this.spawnY = 0;
	}
	
	public World(String fileName) {
		/*
		 * TODO: retrieve world file
		 */
	}
	
	/*
	 * Ticks every gamechunk and player
	 */
	public void tick() {
		for (int x = 0; x < renderedChunk.length; x++) {
			for (int y = 0; y < renderedChunk[x].length; y++) {
				if (renderedChunk[x][y] != null)
					renderedChunk[x][y].tick();
			}
		}
	}
	
	/*
	 * Renders the chunks then player
	 * TODO: make rendering have different layers
	 */
	public void render(Graphics g, float offsetX, float offsetY) {
		for (int x = 0; x < renderedChunk.length; x++) {
			for (int y = 0; y < renderedChunk[x].length; y++) {
				if (renderedChunk[x][y] != null) {
					renderedChunk[x][y].render(g, offsetX, offsetY, Handler.spriteSheet);
				}
			}
		}
		highlight(Game.handler.getMouseX(), Game.handler.getMouseY());
		Handler.p.render(g, offsetX, offsetY);
		
	}
	
	/*
	 * idk what this is tbh
	 */
	public void start(float posX, float posY) {
		Handler.p.setWorld(this);
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				renderedChunk[x][y] = gameChunks.get((x - 1 + (int)posX) + ", " + (y - 1 + (int)posY));
			}
		}
		Handler.p.setX(spawnX);
		Handler.p.setY(spawnY);
		
		
		World temp = new World();
		SpaceTile ot = new SpaceTile(448, 512, Game.TILE_SIZE, Game.TILE_SIZE, renderedChunk[1][1],  2, BlockID.Door, new Structure());
		for (int xPos = -1; xPos <= 1; xPos++) {
			for (int yPos = -1; yPos <= 1; yPos++) {
				GameChunk gameChunk = new GameChunk(xPos,yPos, Game.handler);
				for (int x = 0; x < 16; x++) {
					for (int y = 0; y < 16; y++) {
						gameChunk.addGroundTile(new GroundTile(x * Game.TILE_SIZE, y * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE,gameChunk, 3, BlockID.Sand), x,y);
					}
					if (xPos == 0 && yPos == 1) {
						ot = new SpaceTile(64, 64, 64, 64, gameChunk, 2, BlockID.Door, new Structure());
						gameChunk.addObjectTile(ot, (int)ot.getX()/Game.TILE_SIZE, (int) ot.getY()/Game.TILE_SIZE);
					}
				}
				temp.addGameChunk(gameChunk);
			}
		}
		
		addStructure(new Structure(new StructureTile(512, 512, 128,  128, 0, 0, 128, 128, renderedChunk[1][1], 2, BlockID.Structure), new SpaceTile(576, 576, 64, 64, renderedChunk[1][1],2, BlockID.Door, new Structure()), ot, -1, 256,256, 2, 2, this, temp));
	}
	
	/*
	 * Gets ObjectTile at location (x,y)
	 */
	public ObjectTile getObjectTileAt(float x, float y) {
		GameChunk gc = getGameChunkAt(x, y);
		int xx = 15 - (int)Math.floor(Math.abs(x%(Game.TILE_SIZE*16))/Game.TILE_SIZE);
		int yy = 15 - (int)Math.floor((Math.abs(y)%(Game.TILE_SIZE*16))/Game.TILE_SIZE);
		if (x >= 0) {
			xx = (int)Math.floor(Math.abs(x%(Game.TILE_SIZE*16))/Game.TILE_SIZE);
		}
		if (y <= 0) {
			yy =  (int)Math.floor((Math.abs(y)%(Game.TILE_SIZE*16))/Game.TILE_SIZE);
		}
		return gc.getObjectTiles()[xx][yy];
	}
	
	/*
	 * Gets GroundTile at location (x,y)
	 */
	public GroundTile getGroundTileAt(float x, float y) {
		GameChunk gc = getGameChunkAt(x, y);
		int xx = 15 - (int)Math.floor(Math.abs(x%(Game.TILE_SIZE*16))/Game.TILE_SIZE);
		int yy = 15 - (int)Math.floor((Math.abs(y)%(Game.TILE_SIZE*16))/Game.TILE_SIZE);
		if (x >= 0) {
			xx = (int)Math.floor(Math.abs(x%(Game.TILE_SIZE*16))/Game.TILE_SIZE);
		}
		if (y <= 0) {
			yy =  (int)Math.floor((Math.abs(y)%(Game.TILE_SIZE*16))/Game.TILE_SIZE);
		}
		return gc.getGroundTiles()[xx][yy];
	}
	
	/*
	 * Gets GameChunk at location (x,y)
	 */
	public GameChunk getGameChunkAt(float x, float y) {
		return gameChunks.getOrDefault(((int)Math.floor( x / (Game.TILE_SIZE * Game.CHUNK_SIZE))) +  ", " + (int)Math.ceil( y/ (Game.TILE_SIZE * Game.CHUNK_SIZE)) , new GameChunk((int)( x / (Game.TILE_SIZE * Game.CHUNK_SIZE)) ,(int)( (y + Game.TILE_SIZE * Game.CHUNK_SIZE) / (Game.TILE_SIZE * Game.CHUNK_SIZE)), Game.handler));
	}
	
	/*
	 * Highlights GroundTile at location (x,y)
	 */
	public void highlight(float x, float y) {
		GroundTile gt = getGroundTileAt(x - Handler.game.getCamera().getOffsetX(),-1 * (y - Handler.game.getCamera().getOffsetY()));
		if (gt != null)
			gt.highlight(Handler.game.getGraphics(), Handler.game.getCamera().getOffsetX(), Handler.game.getCamera().getOffsetY());
	}
	
	/*
	 * Checks if collision happened
	 * TODO: Remove this, this is useless
	 */
	public boolean isCollision(Rectangle r1, Rectangle r2) {
		int x1 = r1.x;
		int x2 = r1.x + r1.width;
		int y1 = r1.y - r1.height;
		int y2 = r1.y;
		int x3 = r2.x;
		int x4 = r2.x + r2.width;
		int y3 = r2.y - r2.height;
		int y4 = r2.y;
		
		if (x3 > x2 || y3 > y2 || x1 > x4 || y1 > y4) {
			return false;
		}
		return true;
	}
	
	/*
	 * Temporary method to add structure
	 * TODO: make this better
	 */
	public void addStructure(Structure structure) {
		for (int xx = (int) structure.getX() / 64; xx < structure.getX()/64 + structure.getWidth(); xx++) {
			for (int yy = structure.getY()/64; yy < structure.getY()/64 + structure.getHeight(); yy++) {
				if (xx == structure.getX()/64 && yy == structure.getY()/64)
					yy++;
				//Tile tile = getGroundTileAt(xx * tileSize - structure.getX() + (int) structure.getLeadTile().getX() + structure.getLeadTile().getChunk().getXPos() * 16 * tileSize, yy * tileSize - structure.getY() + (int) structure.getLeadTile().getY() + structure.getLeadTile().getChunk().getYPos() * 16 * tileSize);
				GameChunk gc = getGameChunkAt(xx * Game.TILE_SIZE, yy * Game.TILE_SIZE);
				gc.addObjectTile(new SpaceTile(xx * Game.TILE_SIZE, yy * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE, gc, -1, BlockID.Border, structure), xx, yy);
				
			}
		}
		structure.getEntranceTile().setStructure(structure);
		structure.getExitTile().setStructure(structure);
		structure.getLeadTile().getChunk().addObjectTile(structure.getLeadTile(), (int)structure.getLeadTile().getX() / Game.TILE_SIZE, (int)structure.getLeadTile().getY() / Game.TILE_SIZE);
		structure.getEntranceTile().getChunk().addObjectTile(structure.getEntranceTile(), (int)structure.getEntranceTile().getX()/Game.TILE_SIZE, (int)structure.getEntranceTile().getY()/Game.TILE_SIZE);
	
	}
	
	/*
	 * Setters
	 */
	public void setSpawnX(float spawnX) { this.spawnX = spawnX; }
	public void setSpawnY(float spawnY) { this.spawnY = spawnY; }
	public void addGameChunk(GameChunk gameChunk) { gameChunks.put(gameChunk.getXPos() + ", " + gameChunk.getYPos(), gameChunk); }
	
	/*
	 * Getters
	 */
	public GameChunk[][] getRenderedChunks(){ return renderedChunk; }
	public HashMap<String,GameChunk> getGameChunks() { return gameChunks; }
	public GameChunk getGameChunkAt() { return renderedChunk[1][1]; }
	public float getSpawnX() { return spawnX; }
	public float getSpawnY() { return spawnY; }
}