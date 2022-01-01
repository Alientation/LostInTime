package com.alientation.game.world;

import java.util.ArrayList;

import com.alientation.game.Game;
import com.alientation.game.entity.Entity;

public class TestGameChunk {
	/*
	 * World Coordinates
	 */
	private int chunkXPos;
	private int chunkYPos;
	
	/*
	 * Batch render groundLayer excluding animatedTiles
	 * groundLayer contains flat ground blocks with no height
	 * 
	 * groundLayer & topLayer (y,x)
	 */
	private TestBasicBlock[][] groundLayer = new TestBasicBlock[Game.CHUNK_SIZE][Game.CHUNK_SIZE];
	private TestBasicBlock[][] topLayer = new TestBasicBlock[Game.CHUNK_SIZE][Game.CHUNK_SIZE];
	private ArrayList<TestBasicAnimatedBlock> animatedTiles = new ArrayList<TestBasicAnimatedBlock>();
	
	/*
	 * keep this arraylist sorted from highest y to lowest y and lowest x to highest x
	 */
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	
	public TestGameChunk() {
		
	}
	
	
	
	public Entity getEntityNearestTo(int x, int y) {
		
		
		
		return null;
	}
	
	
	
	/*
	 * Returns a TestBasicBlock at a certain location specific to the TestGameChunk
	 * The x and y argument must be between 0 and 15
	 */
	
	public TestBasicBlock getBlockInGroundLayer(int x, int y) {
		return groundLayer[y][x];
	}
	
	public TestBasicBlock getBlockInTopLayer(int x, int y) {
		return topLayer[y][x];
	}
	
	public void setBlockToGroundLayer(int x, int y, TestBasicBlock block) {
		groundLayer[y][x] = block;
	}
	
	public void setBlockToTopLayer(int x, int y, TestBasicBlock block) {
		topLayer[y][x] = block;
	}
}
