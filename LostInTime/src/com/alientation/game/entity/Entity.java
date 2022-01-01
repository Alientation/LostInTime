package com.alientation.game.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.alientation.game.Handler;

public abstract class Entity {
	/*
	 * Location and Velocity
	 */
	protected float x, y, health, hunger, saturation;
	
	/*
	 * Collision data
	 */
	protected int height, width, xCollision, yCollision, widthCollision, heightCollision;
	
	protected Handler handler;
	protected EntityID id;
	
	/*
	 * Init Entity
	 */
	public Entity(float x, float y, int height, int width, int xCollision, int yCollision, int widthCollision, int heightCollision, EntityID id, Handler handler) {
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.xCollision = xCollision;
		this.yCollision = yCollision;
		this.widthCollision = widthCollision;
		this.heightCollision = heightCollision;
		this.id = id;
		this.handler = handler;
		this.health = 100;
		this.hunger = 100;
		this.saturation = 100;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g, float offsetX, float offsetY);
	public abstract Rectangle getBounds(float offsetX, float offsetY);
	
	/*
	 * Setters
	 */
	public void setX(float x) { this.x = x; }
	public void setY(float y) { this.y = y; }
	public void addX(float x) { this.x += x; }
	public void addY(float y) { this.y += y; }
	public void setId(EntityID id) { this.id = id; }
	public void setHeight(int height) { this.height = height; }
	public void setWidth(int width) { this.width = width; }
	
	
	/*
	 * Getters
	 */
	public float getX() { return x; }
	public float getY() { return y; }
	public EntityID getId() { return id; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public float getHealth() { return health; }
	public float getSaturation() { return saturation; }
	public float getHunger() { return hunger; }
}