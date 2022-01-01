package com.alientation.game.world;

import java.awt.image.BufferedImage;

import com.alientation.game.graphics.ImageLoader;

public class TestBasicBlock {
	public static BufferedImage[] textures;
	public static int textureVariantCount;
	/*
	 * Block Coordinates
	 */
	protected int blockXPos;
	protected int blockYPos;
	protected int width;
	protected int height;
	
	public TestBasicBlock(int x, int y, int width, int height) {
		this.blockXPos = x;
		this.blockYPos = y;
		this.width = width;
		this.height = height;
	}
	
	public void tick() {
		
	}
	
	public void render() {
		
	}
	
	public static void register() {
		textures = new BufferedImage[textureVariantCount];
	}
	
	
	public BufferedImage[] getTextures() {
		return textures;
	}
	
	public BufferedImage getTexture(int variant) {
		return textures[variant];
	}
	
	public int getTextureVariantCount() {
		return textureVariantCount;
	}
}
