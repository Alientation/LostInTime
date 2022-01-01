package com.alientation.game.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.alientation.game.graphics.SpriteSheet;

public abstract class Tile {
	
	/*
	 * x and y in relation to gamechunk
	 */
	protected float x, y;
	protected int textureID, height, width;
	protected BlockID blockID;
	
	protected GameChunk gameChunk;
	
	/*
	 * Init Tile
	 */
	public Tile(float x, float y, int height, int width, GameChunk gameChunk, int textureID, BlockID blockID) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.gameChunk = gameChunk;
		this.textureID = textureID;
		this.blockID = blockID;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g, float offsetX, float offsetY, SpriteSheet spriteSheet);
	public abstract Rectangle getBounds(float offsetX, float offsetY);
	
	/*
	 * Setters
	 */
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public void addX(float x) { this.x += x; }
	public void addY(float y) { this.y += y; }
	public void setTextureID(int textureID) { this.textureID = textureID; }
	public void setBlockID(BlockID blockID) { this.blockID = blockID; }
	
	/*
	 * Getters
	 */
	public float getX() { return x; }
	public float getY() { return y; }
	public GameChunk getChunk() { return gameChunk; }
	public int getTextureID() { return textureID; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public BlockID getBlockID() { return blockID; }
}