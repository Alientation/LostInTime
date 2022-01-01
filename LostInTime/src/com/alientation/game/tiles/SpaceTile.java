package com.alientation.game.tiles;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.alientation.game.graphics.SpriteSheet;

public class SpaceTile extends ObjectTile{
	
	private Structure structure;
	
	public SpaceTile(float x, float y, int height, int width, GameChunk gameChunk, int textureID, BlockID blockID, Structure structure) {
		super(x, y, height, width, 0, 0, height, width, gameChunk, textureID, blockID);
		this.structure = structure;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g, float offsetX, float offsetY, SpriteSheet spriteSheet) {
		
	}

	@Override
	public Rectangle getBounds(float offsetX, float offsetY) { return structure.getLeadTile().getBounds(offsetX, offsetY); }
	
	public Structure getStructure() { return structure; }
	public void setStructure(Structure structure) { this.structure = structure; }
	
}