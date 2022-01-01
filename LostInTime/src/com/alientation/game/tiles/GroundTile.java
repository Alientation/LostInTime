package com.alientation.game.tiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.alientation.game.graphics.SpriteSheet;

public class GroundTile extends Tile{
	
	/*
	 * Init GroundTile
	 */
	public GroundTile(float x, float y, int height, int width, GameChunk gameChunk, int textureID, BlockID blockID) {
		super(x, y, height, width, gameChunk, textureID, blockID);
	}
	
	/*
	 * Ticks Tile
	 */
	@Override
	public void tick() {
		
		
	}
	
	/*
	 * Render Tile
	 */
	@Override
	public void render(Graphics g, float offsetX, float offsetY, SpriteSheet spriteSheet) {
		
		g.drawImage(spriteSheet.crop(textureID), (int) (gameChunk.getXPos() * width * 16 + x + offsetX), (int) (gameChunk.getYPos() * -1 * height * 16 + y + offsetY), null);
		g.setColor(Color.DARK_GRAY);
		g.drawRect((int) (gameChunk.getXPos() * width * 16 + x + offsetX),(int) (gameChunk.getYPos() * -1 * height * 16 + y + offsetY), width, height);
		g.drawString((gameChunk.getXPos() * 16 + (int)Math.ceil(x/64)) + "," + (gameChunk.getYPos() * 16 - (int)Math.ceil(y/64)), (int) (gameChunk.getXPos() * width * 16 + x + offsetX + 10),(int) (gameChunk.getYPos() * -1 * height * 16 + y + offsetY + 30));
	}
	
	/*
	 * Gets bounds
	 */
	@Override
	public Rectangle getBounds(float offsetX, float offsetY) {
		return new Rectangle((int)(gameChunk.getXPos() * width * 16 + x + offsetX),(int) (gameChunk.getYPos() * height * -16 + y + offsetY),width, height);
	}
	
	/*
	 * Highlights the tile
	 */
	public void highlight(Graphics g, float offsetX, float offsetY) {
		g.setColor(Color.blue);
		g.drawRect((int) (gameChunk.getXPos() * width * 16 + x + offsetX),(int) (gameChunk.getYPos() * -1 * height * 16 + y + offsetY), width, height);
	}

}