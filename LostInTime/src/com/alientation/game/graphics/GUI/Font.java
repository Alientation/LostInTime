package com.alientation.game.graphics.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;

import com.alientation.game.Game;
import com.alientation.game.graphics.ImageLoader;
import com.alientation.game.util.JSONLoader;

public class Font {
	
	public BufferedImage[][] bis;
	public BufferedImage[] characters;
	public BufferedImage[] bi = new BufferedImage[3];
	public Font(String path1, String path2, String path3, int numFonts) {
		try {
			bi[0] = ImageIO.read(new File(path1));
			bi[1] = ImageIO.read(new File(path2));
			//bi[2] = ImageIO.read(new File(path3));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bis = new BufferedImage[3][numFonts];
	}
	
	@SuppressWarnings("unchecked")
	public Font(String JSONPath) {
		JSONObject jo = JSONLoader.loadJson(JSONPath);
		characters = new BufferedImage[(int) jo.get("numFonts")];
		HashMap<CharacterID,String> hm = (HashMap<CharacterID, String>) jo.get("characterTextureMap");
		int pos = 0;
		for (CharacterID charID : CharacterID.values()) {
			if (hm.get(charID) != null) {
				characters[pos] = ImageLoader.loadImage(hm.get(charID));
			} else {
				characters[pos] = null;   //make a standard image that signifies a uninitialized texture
			}
			pos++;
			if (pos == characters.length) {
				Game.logger.addLog("Error: incorrect number of fonts", "help send help sos");
				break;
			}
		}
	}
	
	
	public void loadImages(BufferedImage bi) {
		
	}
	
	public BufferedImage[][] getBufferedImages() { return bis; }
	public void drawFont(int ID, int style, int x, int y, Color color, int size, Graphics g) { 
		g.drawImage(bis[style][ID], x, y, size * (bis[style][ID].getWidth()), size * (bis[style][ID].getHeight()),null);
	}
}
