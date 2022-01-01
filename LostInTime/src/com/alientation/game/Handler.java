package com.alientation.game;

import com.alientation.game.entity.*;
import com.alientation.game.graphics.Camera;
import com.alientation.game.graphics.SpriteSheet;
import com.alientation.game.tiles.GameChunk;
import java.awt.Color;
import java.awt.Graphics;

public class Handler {
	/*
	 * Current Game, Player, and World
	 */
	public static Game game;
	public static Player p;
	public static World world;
	
	/*
	 * Mouse positions
	 */
	private float mouseX, mouseY;
	private boolean hasClicked = false;
	private float mouseClickPosX = 0, mouseClickPosY = 0;
	
	public static SpriteSheet spriteSheet;
	
	/*
	 * Initializes Handler
	 */
	public Handler(Game game) {
		Handler.game = game;
		importSpriteSheet();
		p = new Player(0, 0, "John Doe", this);
		p.init();
		world = new World(0, 0);
	}
	
	/*
	 * Ticks every object in game
	 */
	public void tick() {
		world.tick();
	}
	
	/*
	 * Renders World, highlights mouse position, and displays the location of mouse
	 */
	public void render(Graphics g, float offsetX, float offsetY) {
		world.render(g, offsetX, offsetY);
		p.tick();
		g.setColor(Color.white);
		g.drawString((int)(mouseX - offsetX) + ", " + (int)(-1 * (mouseY - offsetY)), (int)mouseX, (int)mouseY);
	}
	
	/*
	 * Camera init
	 */
	public void start(Camera c) {
		p.setWorld(world);
		float playerX = p.getX();
		float playerY = p.getY();
		int posX = (int) (playerX / (Game.TILE_SIZE * Game.CHUNK_SIZE));
		int posY = (int) (playerY / (-1 * Game.TILE_SIZE * Game.CHUNK_SIZE));
		world.start(posX, posY);
	}
	
	/*
	 * Handler clicks depending on GameState
	 */
	public void handleClick(float x, float y) {
		if (game.getGameState().equals(GameState.Game)) {
			setClickPos(x,y);
		} else {
			game.getMenu().handleClick(x, y);
		}
	}
	
	public void importSpriteSheet() {
		spriteSheet = new SpriteSheet();
		
	
	}
	
	
	/*
	 * Setters
	 */
	public void setMousePos(float x, float y) { mouseX = x; mouseY = y; }
	public void setClickPos(float x, float y) { mouseClickPosX = x; mouseClickPosY = y; hasClicked = true; }
	public void setHasClicked(boolean bool) { hasClicked = bool; }
	public void addGameChunk(GameChunk gameChunk) { world.addGameChunk(gameChunk); }
	
	/*
	 * Getters
	 */
	public float getMouseClickX() { return mouseClickPosX; }
	public float getMouseClickY() { return mouseClickPosY; }
	public float getMouseX() { return mouseX; }
	public float getMouseY() { return mouseY; }
	public boolean getHasClicked() { return hasClicked; }
	public GameChunk getGameChunkAt() { return world.getGameChunkAt(); }
}