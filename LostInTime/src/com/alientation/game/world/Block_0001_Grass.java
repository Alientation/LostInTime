package com.alientation.game.world;

import com.alientation.game.Game;

public class Block_0001_Grass extends TestBasicBlock{
	private int variant;
	
	public Block_0001_Grass(int x, int y, int width, int height, int variant) {
		super(x, y, width, height);
		this.variant = variant;
	}
	
	public void render() {
		Game.g.drawImage(textures[variant],(int) (blockXPos * Game.TILE_SIZE + Game.camera.getOffsetX()),(int) (-1 * blockYPos * Game.TILE_SIZE + Game.camera.getOffsetY()), width, height, null);
	}
	
}
