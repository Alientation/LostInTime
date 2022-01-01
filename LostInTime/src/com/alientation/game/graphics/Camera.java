package com.alientation.game.graphics;

import java.awt.Graphics;
import java.util.Stack;

import com.alientation.game.Game;
import com.alientation.game.Handler;

public class Camera {
	/*
	 * TODO: Make smoother Camera movement
	 */
	private Handler handler;
	private float x, y, offsetX, offsetY;
	private Stack<float[]> stack = new Stack<float[]>();
	
	/*
	 * Init Camera
	 */
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
		this.handler = Game.handler;
		offsetX = x;
		offsetY = y;
		float[][] floats = new float[1][2];
		for (int xx = 0; xx < floats.length; xx++) {
			floats[xx][0] = x;
			floats[xx][1] = y;
			stack.push(floats[xx]);
		}
	}
	
	/*
	 * Calculates how far the camera must move and pushes it onto a stack to create a delayed Camera movement
	 */
	public void tick() {
		offsetX = stack.firstElement()[0];
		offsetY = stack.firstElement()[1];
		stack.firstElement()[0] = x - Handler.p.getX();
		stack.firstElement()[1] = y - Handler.p.getY() * -1;
		stack.push(stack.pop());
	}
	
	/*
	 * IDK why this is needed here but whatever
	 */
	public void render(Graphics g) {
		
	}
	
	/*
	 * Getters
	 */
	public float getOffsetX() { return offsetX; }
	public float getOffsetY() { return offsetY; }
	public float getX() { return x; }
	public float getY() { return y; }
	
	/*
	 * Setters
	 */
	public void setOffsetX(float offsetX) { this.offsetX = offsetX; }
	public void setOffsetY(float offsetY) { this.offsetY = offsetY; }
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
}
