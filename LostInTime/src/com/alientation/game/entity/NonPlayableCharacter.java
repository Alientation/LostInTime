package com.alientation.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.alientation.game.Handler;

public class NonPlayableCharacter extends Entity{

	/*
	 * TODO: Make NPCs
	 */
	public NonPlayableCharacter(float x, float y, int height, int width, int xCollision, int yCollision, int widthCollision, int heightCollision, EntityID id, Handler handler) {
		super(x, y, height, width, xCollision, yCollision, widthCollision, heightCollision, id, handler);
	}

	@Override
	public void tick() {
		
		
	}

	@Override
	public void render(Graphics g, float offsetX, float offsetY) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int) y, width, height);
	}

	@Override
	public Rectangle getBounds(float offsetX, float offsetY) {
		return new Rectangle((int) (x + offsetX), (int) (y + offsetY), width, height);
	}

}