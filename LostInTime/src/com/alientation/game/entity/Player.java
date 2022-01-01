 package com.alientation.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.alientation.game.Game;
import com.alientation.game.Handler;
import com.alientation.game.World;
import com.alientation.game.graphics.GUI.HUD;
import com.alientation.game.tiles.BlockID;
import com.alientation.game.tiles.GroundTile;
import com.alientation.game.tiles.ObjectTile;
import com.alientation.game.tiles.SpaceTile;
import com.alientation.game.tiles.Structure;
public class Player extends Entity{
	
	public static int height = 64;
	public static int width = 32;
	public static int xCollision = 0;
	public static int yCollision = 32;
	public static int widthCollision = 32;
	public static int heightCollision = 32;
	
	
	private String username;
	private World world;
	private HUD hud;
	
	private double velocityHorizontalPositive = 0;
	private double velocityHorizontalNegative = 0;
	private double velocityVerticalPositive = 0;
	private double velocityVerticalNegative = 0;
	
	private boolean wKeyHeldDown = false;
	private boolean sKeyHeldDown = false;
	private boolean dKeyHeldDown = false;
	private boolean aKeyHeldDown = false;
	
	private int tickSinceWKeyPressed = 0;
	private int tickSinceSKeyPressed = 0;
	private int tickSinceDKeyPressed = 0;
	private int tickSinceAKeyPressed = 0;
	
	private double maxVelocity = 4;
	private double maxVelocityDiagonal = Math.sqrt(maxVelocity * maxVelocity / 2);
	private double acceleration = 0.5;
	
	
	
	
	
	
	
	private int tickSinceMoveStart = 0;
	private int tickSinceMoveEnd = 0;
	
	private int tickSinceHungerTaken = 0;
	private int tickSinceSaturationTaken = 0;
	
	
	/*
	 * Init Player
	 */
	public Player(float x, float y, String username, Handler handler) {
		super(x, y, height, width, xCollision, yCollision, widthCollision, heightCollision, EntityID.Player, handler);
		this.username = username;
	}
	
	public void init() {
		hud = new HUD();
	}
	
	
	public void move() {
		if (wKeyHeldDown) {
			tickSinceWKeyPressed++;
			if (velocityVerticalNegative != 0)
				velocityVerticalNegative = 0;
			else {
				velocityVerticalPositive = Math.min(maxVelocity, acceleration * tickSinceWKeyPressed);
				if ((dKeyHeldDown || aKeyHeldDown) && (dKeyHeldDown != aKeyHeldDown)) {
					velocityVerticalPositive = Math.min(maxVelocityDiagonal, acceleration * tickSinceWKeyPressed);
				}
			}
		} else {
			velocityVerticalPositive = 0;
			tickSinceWKeyPressed = 0;
		}
		if (dKeyHeldDown) {
			tickSinceDKeyPressed++;
			if (velocityHorizontalNegative != 0)
				velocityHorizontalNegative = 0;
			else {
				velocityHorizontalPositive = Math.min(maxVelocity, acceleration * tickSinceDKeyPressed);
				if ((wKeyHeldDown || sKeyHeldDown) && (wKeyHeldDown != sKeyHeldDown)) {
					velocityHorizontalPositive = Math.min(maxVelocityDiagonal, acceleration * tickSinceDKeyPressed);
				}
			}
		} else {
			velocityHorizontalPositive = 0;
			tickSinceDKeyPressed = 0;
		}
		if (sKeyHeldDown) {
			tickSinceSKeyPressed++;
			if (velocityVerticalPositive != 0)
				velocityVerticalPositive = 0;
			else {
				velocityVerticalNegative = Math.min(maxVelocity, acceleration * tickSinceSKeyPressed);
				if ((dKeyHeldDown || aKeyHeldDown) && (dKeyHeldDown != aKeyHeldDown)) {
					velocityVerticalNegative = Math.min(maxVelocityDiagonal, acceleration * tickSinceSKeyPressed);
				}
			}
		} else {
			tickSinceSKeyPressed = 0;
			velocityVerticalNegative = 0;
		}
		
		if (aKeyHeldDown) {
			tickSinceAKeyPressed++;
			if (velocityHorizontalPositive != 0)
				velocityHorizontalPositive = 0;
			else {
				velocityHorizontalNegative = Math.min(maxVelocity, acceleration * tickSinceAKeyPressed);
				if ((wKeyHeldDown || sKeyHeldDown) && (wKeyHeldDown != sKeyHeldDown)) {
					velocityHorizontalNegative = Math.min(maxVelocityDiagonal, acceleration * tickSinceAKeyPressed);
				}
			}
		} else {
			tickSinceAKeyPressed = 0;
			velocityHorizontalNegative = 0;
		}
	}
	
	
	/*
	 * Tick player velocity
	 */
	@Override
	public void tick() {
		move();
		tickSinceHungerTaken++;
		tickSinceSaturationTaken++;
		if (tickSinceHungerTaken > 600) {
			tickSinceHungerTaken = 0;
			removeHunger((int)(Math.random() * 5) + 1);
		}
		if (tickSinceSaturationTaken > 900) {
			tickSinceSaturationTaken = 0;
			removeSaturation((int)(Math.random() * 5) + 1);
		}
		
		float xx = x;
		float yy = y;
		if (velocityVerticalPositive - velocityVerticalNegative != 0 || velocityHorizontalPositive - velocityHorizontalNegative != 0) { 
			x += velocityHorizontalPositive - velocityHorizontalNegative;
			y += velocityVerticalPositive - velocityVerticalNegative;
			
			if (!checkbounds()) {
				x -= velocityHorizontalPositive - velocityHorizontalNegative;
				if (!checkbounds()) {
					y -= velocityVerticalPositive - velocityVerticalNegative;
					x += velocityHorizontalPositive - velocityHorizontalNegative;
					if (!checkbounds()) {
						x -= velocityHorizontalPositive - velocityHorizontalNegative;
					}
				}
			}
			
		}
		if (xx != x || yy != y) {
			tickSinceMoveEnd = 0;
			tickSinceMoveStart++;
			tickSinceHungerTaken++;
			tickSinceSaturationTaken++;
		} else {
			tickSinceMoveStart = 0;
			tickSinceMoveEnd++;
		}
		checkClick();
	}
	
	/*
	 * Render Player and display player position
	 * TODO: Add player texture and animation
	 * TODO: Create HUD and move appropriate displays to it
	 */
	@Override
	public void render(Graphics g, float offsetX, float offsetY) {
		g.setColor(Color.WHITE);
		g.fillRect((int) (x + offsetX), (int) (-1 * y + offsetY), width, height);
		
		if (world.getGroundTileAt(x, y) != null) {
			GroundTile groundTile = world.getGroundTileAt(x, y);
			g.drawString("FPS: " + Handler.game.getFPS() + " TPS: " + Handler.game.getFPS() + "       POS: " + (groundTile.getChunk().getXPos() * Game.CHUNK_SIZE * Game.TILE_SIZE + groundTile.getX()) + ", " + (groundTile.getChunk().getYPos() * Game.CHUNK_SIZE * Game.TILE_SIZE - world.getGroundTileAt(x, y).getY()), 40, 40);
			
		}
		hud.render(g);
	}
	
	/*
	 * Gets collision bounds
	 */
	@Override
	public Rectangle getBounds(float offsetX, float offsetY) { return new Rectangle((int) (x + offsetX) + xCollision, (int) (-1 * y + offsetY + yCollision), widthCollision, heightCollision); }
	
	/*
	 * Checks if the player collided with any surrounding ObjectTiles
	 */
	/*
	private boolean checkbounds() {
		ObjectTile[][] array = {
				{world.getObjectTileAt(x-Game.TILE_SIZE,y+Game.TILE_SIZE), world.getObjectTileAt(x,y+Game.TILE_SIZE), world.getObjectTileAt(x+Game.TILE_SIZE,y+Game.TILE_SIZE)},
				{world.getObjectTileAt(x-Game.TILE_SIZE,y), world.getObjectTileAt(x,y), world.getObjectTileAt(x+Game.TILE_SIZE,y)},
				{world.getObjectTileAt(x-Game.TILE_SIZE,y-Game.TILE_SIZE), world.getObjectTileAt(x,y-Game.TILE_SIZE), world.getObjectTileAt(x+Game.TILE_SIZE,y-Game.TILE_SIZE)}
		};
		
		for (int xx = 0; xx < array.length; xx++) {
			for (int yy = 0; yy < array[xx].length; yy++) {
				if (array[xx][yy] != null) {
					array[xx][yy].highlight(Handler.game.getGraphics(), Handler.game.getCamera().getOffsetX(), Handler.game.getCamera().getOffsetY());
					if (array[xx][yy].getBounds(Handler.game.getCamera().getOffsetX(), Handler.game.getCamera().getOffsetY()).intersects(getBounds(Handler.game.getCamera().getOffsetX(), Handler.game.getCamera().getOffsetY()))) {
						return false;
					}
				}
			}
		}
		return true;
	}
	*/
	public static int[][] direction = {
			{-1,1},{0,1},{1,1},{-1,0},{0,0},{1,0},{-1,-1},{0,-1},{1,-1}
	};
	private boolean checkbounds() {
		ObjectTile stuff;
		for (int i = 0; i < direction.length; i++) {
			stuff = world.getObjectTileAt(x + Game.TILE_SIZE * direction[i][0], y + Game.TILE_SIZE * direction[i][1]);
			if (stuff != null) {
				stuff.highlight(Handler.game.getGraphics(), Handler.game.getCamera().getOffsetX(), Handler.game.getCamera().getOffsetY());
				if (stuff.getBounds(Handler.game.getCamera().getOffsetX(), Handler.game.getCamera().getOffsetY()).intersects(getBounds(Handler.game.getCamera().getOffsetX(), Handler.game.getCamera().getOffsetY()))) {
					return false;
				}
			}
		}
		return true;
	}
	
	/*
	 * Check if the player has clicked on anything
	 */
	public void checkClick() {
		if (handler.getHasClicked()) {
			handler.setHasClicked(false);
			ObjectTile ot = world.getObjectTileAt(handler.getMouseClickX() - Handler.game.getCamera().getOffsetX(), -1 * handler.getMouseClickY() + Handler.game.getCamera().getOffsetY());
			if (ot != null) {
				if (ot.getBlockID().equals(BlockID.Door)) {
					System.out.println("Entering");
					Structure obj = ((SpaceTile) ot).getStructure();
					Handler.world = obj.getCurrentWorld();
					handler.start(Handler.game.getCamera());
				}
			}
		}
	}
	
	public void removeHealth(float num) {
		health -= num;
		hud.updateHealthBar();
	}
	
	public void removeHunger(float num) {
		hunger -= num;
		hud.updateHungerBar();
	}
	
	public void removeSaturation(float num) {
		saturation -= num;
		hud.updateSaturationBar();
	}
	
	/*
	 * Setters
	 */
	public void setWorld(World world) { this.world = world; }
	
	/*
	 * Getters
	 */
	public World getWorld() { return world; }
	public float getTickSinceMoveStart() { return tickSinceMoveStart; }
	public float getTickSinceMoveEnd() { return tickSinceMoveEnd; }


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		Player.height = height;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		Player.width = width;
	}


	public static int getxCollision() {
		return xCollision;
	}


	public static void setxCollision(int xCollision) {
		Player.xCollision = xCollision;
	}


	public static int getyCollision() {
		return yCollision;
	}


	public static void setyCollision(int yCollision) {
		Player.yCollision = yCollision;
	}


	public static int getWidthCollision() {
		return widthCollision;
	}


	public static void setWidthCollision(int widthCollision) {
		Player.widthCollision = widthCollision;
	}


	public static int getHeightCollision() {
		return heightCollision;
	}


	public static void setHeightCollision(int heightCollision) {
		Player.heightCollision = heightCollision;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public HUD getHud() {
		return hud;
	}


	public void setHud(HUD hud) {
		this.hud = hud;
	}


	public double getVelocityHorizontalPositive() {
		return velocityHorizontalPositive;
	}


	public void setVelocityHorizontalPositive(double velocityHorizontalPositive) {
		this.velocityHorizontalPositive = velocityHorizontalPositive;
	}


	public double getVelocityHorizontalNegative() {
		return velocityHorizontalNegative;
	}


	public void setVelocityHorizontalNegative(double velocityHorizontalNegative) {
		this.velocityHorizontalNegative = velocityHorizontalNegative;
	}


	public double getVelocityVerticalPositive() {
		return velocityVerticalPositive;
	}


	public void setVelocityVerticalPositive(double velocityVerticalPositive) {
		this.velocityVerticalPositive = velocityVerticalPositive;
	}


	public double getVelocityVerticalNegative() {
		return velocityVerticalNegative;
	}


	public void setVelocityVerticalNegative(double velocityVerticalNegative) {
		this.velocityVerticalNegative = velocityVerticalNegative;
	}


	public double getMaxVelocity() {
		return maxVelocity;
	}


	public void setMaxVelocity(double maxVelocity) {
		this.maxVelocity = maxVelocity;
	}


	public double getAcceleration() {
		return acceleration;
	}


	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}


	public int getTickSinceHungerTaken() {
		return tickSinceHungerTaken;
	}


	public void setTickSinceHungerTaken(int tickSinceHungerTaken) {
		this.tickSinceHungerTaken = tickSinceHungerTaken;
	}


	public int getTickSinceSaturationTaken() {
		return tickSinceSaturationTaken;
	}


	public void setTickSinceSaturationTaken(int tickSinceSaturationTaken) {
		this.tickSinceSaturationTaken = tickSinceSaturationTaken;
	}


	public void setTickSinceMoveStart(int tickSinceMoveStart) {
		this.tickSinceMoveStart = tickSinceMoveStart;
	}


	public void setTickSinceMoveEnd(int tickSinceMoveEnd) {
		this.tickSinceMoveEnd = tickSinceMoveEnd;
	}


	public boolean iswKeyHeldDown() {
		return wKeyHeldDown;
	}


	public void setwKeyHeldDown(boolean wKeyHeldDown) {
		this.wKeyHeldDown = wKeyHeldDown;
	}


	public boolean issKeyHeldDown() {
		return sKeyHeldDown;
	}


	public void setsKeyHeldDown(boolean sKeyHeldDown) {
		this.sKeyHeldDown = sKeyHeldDown;
	}


	public boolean isdKeyHeldDown() {
		return dKeyHeldDown;
	}


	public void setdKeyHeldDown(boolean dKeyHeldDown) {
		this.dKeyHeldDown = dKeyHeldDown;
	}


	public boolean isaKeyHeldDown() {
		return aKeyHeldDown;
	}


	public void setaKeyHeldDown(boolean aKeyHeldDown) {
		this.aKeyHeldDown = aKeyHeldDown;
	}
}