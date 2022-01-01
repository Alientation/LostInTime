package com.alientation.game.tiles;

import java.util.HashMap;

import com.alientation.game.Game;
import com.alientation.game.Handler;
import com.alientation.game.graphics.Camera;

public class ChunkLoader implements Runnable{
	
	private boolean running;
	private Handler handler;
	private Thread thread;
	
	/*
	 * Init ChunkLoader
	 */
	public ChunkLoader() {
		this.handler = Game.handler;
	}
	
	/*
	 * Separate loop to load rendered chunks
	 */
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		long now;
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				delta--;
			}
			
			if (System.currentTimeMillis() - timer > 2000) {
				timer += 2000;
				checkRenderedChunks(Handler.game.getCamera());
			}
		}
		stop();
	}
	
	/*
	 * Starts new thread for chunk loading
	 */
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	/*
	 * Stops chunkloader thread
	 */
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Ticks
	 */
	public void tick() {
		checkRenderedChunks(Handler.game.getCamera());
	}
	
	/*
	 * Checks if the renderedChunks should be changed
	 */
	public void checkRenderedChunks(Camera c) {
		GameChunk[][] renderedChunk = Handler.world.getRenderedChunks();
		
		HashMap<String,GameChunk> gameChunks = Handler.world.getGameChunks();
		if (renderedChunk[1][1] != null) {
			if (renderedChunk[1][1].getXPos() * Game.TILE_SIZE * Game.CHUNK_SIZE > Handler.p.getX() + Handler.p.getWidth()) {
				//shift right
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (renderedChunk[x][y] != null)
							renderedChunk[x][y] = gameChunks.getOrDefault((renderedChunk[x][y].getXPos() - 1) +  ", " + renderedChunk[x][y].getYPos(), new GameChunk(renderedChunk[x][y].getXPos() - 1,renderedChunk[x][y].getYPos(), handler));
					}
				}
			} else if (renderedChunk[1][1].getXPos() * Game.TILE_SIZE * Game.CHUNK_SIZE + Game.TILE_SIZE * Game.CHUNK_SIZE < Handler.p.getX() + Handler.p.getWidth()) {
				//shift left
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						if (renderedChunk[x][y] != null)
							renderedChunk[x][y] = gameChunks.getOrDefault((renderedChunk[x][y].getXPos() + 1) +  ", " + renderedChunk[x][y].getYPos(), new GameChunk(renderedChunk[x][y].getXPos() + 1,renderedChunk[x][y].getYPos(), handler));
					}
				}
			}
			if (renderedChunk[1][1] != null) {
				if (renderedChunk[1][1].getYPos() * Game.TILE_SIZE * Game.CHUNK_SIZE - Game.TILE_SIZE * Game.CHUNK_SIZE > Handler.p.getY() - Handler.p.getHeight()) {
					//shift down
					for (int x = 0; x < 3; x++) {
						for (int y = 0; y < 3; y++) {
							if (renderedChunk[x][y] != null)
								renderedChunk[x][y] = gameChunks.getOrDefault((renderedChunk[x][y].getXPos()) +  ", " + (renderedChunk[x][y].getYPos() - 1), new GameChunk(renderedChunk[x][y].getXPos(),renderedChunk[x][y].getYPos() - 1, handler));
						}
					}
				} else if (renderedChunk[1][1].getYPos() * Game.TILE_SIZE * Game.CHUNK_SIZE < Handler.p.getY() - Handler.p.getHeight()) {
					//shift up
					for (int x = 0; x < 3; x++) {
						for (int y = 0; y < 3; y++) {
							if (renderedChunk[x][y] != null)
								renderedChunk[x][y] = gameChunks.getOrDefault((renderedChunk[x][y].getXPos()) +  ", " + (renderedChunk[x][y].getYPos() + 1), new GameChunk(renderedChunk[x][y].getXPos(),renderedChunk[x][y].getYPos() + 1, handler));
						}
					}
				}
			}
		} else {
			//error
		}
	}
}