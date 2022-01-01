package com.alientation.game.graphics.GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.alientation.game.Game;
import com.alientation.game.Handler;
import com.alientation.game.graphics.GraphicsHandler;
import com.alientation.game.graphics.ImageLoader;

public class HUD {
	BufferedImage[] bi = new BufferedImage[5];
	
	BufferedImage healthBar;
	BufferedImage hungerBar;
	BufferedImage saturationBar;
	
	public HUD() {
		bi[0] = GraphicsHandler.scale(ImageLoader.loadImage(Game.getPath() + "\\res\\textures\\hotbar.png"),3);
		
		BufferedImage b = ImageLoader.loadImage(Game.getPath() + "\\res\\textures\\hungerhealthsaturation.png");
		//Full Heart
		bi[1] = GraphicsHandler.scale(b.getSubimage(0, 0, 16, 15), 3);
		//Half Heart
		bi[2] = GraphicsHandler.scale(b.getSubimage(17, 0, 14, 15),3);
		
		//Hunger
		bi[3] = GraphicsHandler.scale(b.getSubimage(0, 16, 17, 17),2);
		
		//Saturation
		bi[4] = GraphicsHandler.scale(b.getSubimage(0, 34, 10, 24),2);
		
		
		updateHealthBar();
		updateHungerBar();
		updateSaturationBar();
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {	
		g.drawImage(bi[0], Game.WIDTH/2 - bi[0].getWidth()/2, Game.HEIGHT - 64 - bi[0].getHeight(), null);
		
		/*
		g.drawImage(healthBar, Game.WIDTH/2 - 400, Game.HEIGHT - 92 - bi[0].getHeight() - healthBar.getHeight(), null);
		g.drawImage(hungerBar, Game.WIDTH/2 + 60, Game.HEIGHT - 92 - bi[0].getHeight() - healthBar.getHeight(), null);
		g.drawImage(saturationBar, Game.WIDTH/2 + 60, Game.HEIGHT - 92 - bi[0].getHeight() - healthBar.getHeight() - saturationBar.getHeight(), null);
		 */
	}
	
	public void updateHealthBar() {
		int numHalfHearts = (int) Handler.p.getHealth() / 5;
		healthBar = GraphicsHandler.duplicate(bi[1], numHalfHearts / 2, 4);
		if (numHalfHearts % 2 != 0) {
			healthBar = GraphicsHandler.combine(healthBar, bi[2], 4);
		}
		
	}
	
	public void updateHungerBar() {
		int numHunger = (int) Handler.p.getHunger() / 10;
		hungerBar = GraphicsHandler.duplicate(bi[3], numHunger, 3);
	}
	
	public void updateSaturationBar() {
		int numSaturation = (int) Handler.p.getSaturation() / 10;
		saturationBar = GraphicsHandler.duplicate(bi[4], numSaturation, 10);
	}
}
