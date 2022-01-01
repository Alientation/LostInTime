package com.alientation.game.graphics.GUI;

import java.awt.image.BufferedImage;

import com.alientation.game.graphics.ImageLoader;

public class DisplayCharacter {
	private CharacterID charID;
	private BufferedImage display;
	private String path;
	
	public DisplayCharacter(CharacterID charID, String path) {
		this.charID = charID;
		this.path = path;
		this.display = ImageLoader.loadImage(path + "\\" + charID);
	}
	
	public BufferedImage getBufferedImage() {
		return display;
	}
	
	public CharacterID getCharID() {
		return charID;
	}
}
