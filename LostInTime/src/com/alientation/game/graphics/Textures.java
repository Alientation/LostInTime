package com.alientation.game.graphics;

import java.awt.image.BufferedImage;

public class Textures {
	private BufferedImage[] bi = new BufferedImage[256];
	
	private String path;
	
	/*
	 * Init Textures
	 * Gets the path to the resource folder
	 */
	public Textures(String path) {
		this.path = path;
		loadTextures();
	}
	
	public void loadTextures() {
		
	}
	
	
}
