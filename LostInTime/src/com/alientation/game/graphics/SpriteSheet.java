package com.alientation.game.graphics;

import java.awt.image.BufferedImage;

import com.alientation.game.Game;

public class SpriteSheet {
	private BufferedImage[] sheet;
	private int[][] textures = {
			{0,0,64,64},    //0 Grass
			{64,0,64,64},   //1 Water
			{128,0,64,64},	//2 Dirt
			{192,0,64,64}	//3 Sand
	};
	
	/*
	 * Init SpriteSheet
	 * Loads textures into BufferedImages
	 * TODO: Combine SpriteSheet and Textures into one class
	 */
	public SpriteSheet() {
		
		/*
		 * 0 - 127: Tile Textures
		 * 128 - 159: GUI Textures
		 * 160 - 256: Entity Textures
		 */
		
		/*
		 * Tile Textures
		 */
		BufferedImage sheet = ImageLoader.loadImage(Game.path + "\\res\\textures\\sheet.png");
		this.sheet = new BufferedImage[Game.NUM_TEXTURES];
		for (int x = 0; x < textures.length; x++) {
			this.sheet[x] = sheet.getSubimage(textures[x][0], textures[x][1], textures[x][2], textures[x][3]);
		}
		
		/*
		 * GUI Textures
		 */
		/*
		 * Hotbar
		 */
		this.sheet[128] = ImageLoader.loadImage(Game.path + "\\res\\textures\\hotbar.png");
		
		/*
		 * Full Heart
		 */
		BufferedImage bi = ImageLoader.loadImage(Game.path + "\\res\\textures\\hungerhealthsaturation.png");
		this.sheet[129] = bi.getSubimage(0, 0, 16, 15);
		
		/*
		 * Half Heart
		 */
		this.sheet[130] = bi.getSubimage(17, 0, 14, 15);
		
		/*
		 * Hunger
		 */
		this.sheet[131] = bi.getSubimage(0, 23, 17, 17);
		
		/*
		 * Saturation
		 */
		this.sheet[132] = bi.getSubimage(19, 16, 10, 24);
	}
	
	/*
	 * Gets a specific texture based on a textureID
	 */
	public BufferedImage crop(int textureID) { return sheet[textureID]; }
}