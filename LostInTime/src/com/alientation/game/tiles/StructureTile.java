package com.alientation.game.tiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.alientation.game.graphics.SpriteSheet;

public class StructureTile extends ObjectTile{
	private Structure structure;
	public StructureTile(float x, float y, int height, int width, int xCollision, int yCollision, int widthCollision, int heightCollision, GameChunk gameChunk, int textureID, BlockID blockID) {
		super(x, y, height, width, xCollision, yCollision, widthCollision, heightCollision, gameChunk, textureID, blockID);
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g, float offsetX, float offsetY, SpriteSheet spriteSheet) {
		g.drawImage(spriteSheet.crop(textureID), (int) (gameChunk.getXPos() * width * 16 + x + offsetX), (int) (gameChunk.getYPos() * -1 * height * 16 + y + offsetY), height, width, null);
	}

	@Override
	public Rectangle getBounds(float offsetX, float offsetY) {
		return new Rectangle((int) (gameChunk.getXPos() * width * 16 + x + offsetX) + xCollision, (int) (-1 * gameChunk.getYPos() * height * 16 + y + offsetY + yCollision), widthCollision, heightCollision);
	}
	
	public void highlight(Graphics g, float offsetX, float offsetY) {
		g.setColor(Color.orange);
		g.drawRect((int) (gameChunk.getXPos() * width * 16 + x + offsetX), (int) (gameChunk.getYPos() * -1 * height * 16 + y + offsetY), height, width);
	}
	
	public Structure getStructure() {
		return structure;
	}
	public void setStructure(Structure structure) {
		this.structure = structure;
	}

}