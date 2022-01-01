package com.alientation.game.graphics.GUI;

import java.awt.Color;
import java.awt.Graphics;

public class TextBox {
	private TextDisplay td;
	private int x, y, width, height;
	private Color backgroundColor;
	
	public TextBox(String t, Color color, int x, int y, int width, int height, int size, int margin, Color backgroundColor) {
		this.td = new TextDisplay(t, color, x, y , width, height, margin, size);
		this.x = x - margin;
		this.y = y - margin;
		this.width = width + margin * 2;
		this.height = height + margin*2;
		this.backgroundColor = backgroundColor;
	}

	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(x, y, width, height);
		td.render(g);
	}
	
	
	public Color getTextColor() {
		return td.getColor();
	}
	
	
	public void setTextColor(Color color) {
		td.setColor(color);
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
		td.setX(x);
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
		td.setY(y);
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
		td.setWidth(width);
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
		td.setHeight(height);
	}


	public Color getBackgroundColor() {
		return backgroundColor;
	}


	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
}
