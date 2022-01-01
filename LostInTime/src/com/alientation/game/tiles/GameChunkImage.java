package com.alientation.game.tiles;

import java.awt.image.BufferedImage;

import com.alientation.game.Game;

/*
 * This helps optimize chunk rendering by only rendering one large object instead of 256 small tiles
 */
public class GameChunkImage {
	private BufferedImage bi;
	
	public GameChunkImage() {
		bi = new BufferedImage(Game.CHUNK_SIZE * Game.TILE_SIZE,Game.CHUNK_SIZE * Game.TILE_SIZE,BufferedImage.TYPE_INT_ARGB);
	}
	
	public void updateBI(int x, int y, BufferedImage bi) {
		int xx,yy;
		for (yy = 0; yy < bi.getHeight(); yy++) {
			for (xx =0 ; xx < bi.getWidth(); xx++) {
				this.bi.setRGB(xx + x, yy + y, bi.getRGB(xx, yy));
			}
		}
	}
	
	public BufferedImage getBufferedImage() {
		return bi;
	}
}
