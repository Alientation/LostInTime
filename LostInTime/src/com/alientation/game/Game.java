package com.alientation.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.alientation.game.graphics.Camera;
import com.alientation.game.graphics.Menu;
import com.alientation.game.graphics.Window;
import com.alientation.game.graphics.GUI.PixelFont;
import com.alientation.game.input.KeyInput;
import com.alientation.game.input.MouseInput;
import com.alientation.game.tiles.BlockID;
import com.alientation.game.tiles.ChunkLoader;
import com.alientation.game.tiles.GameChunk;
import com.alientation.game.tiles.GroundTile;
import com.alientation.game.tiles.ObjectTile;
import com.alientation.game.util.Logger;


public class Game extends Canvas implements Runnable{
	/*
	 * Static Variables for Game Window
	 * TODO: Make 
	 */
	private static final long serialVersionUID = 9004752513695225084L;
	public static Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
	
	public static final int WIDTH = 1280;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SIZE = 64;
	public static final int NUM_TEXTURES = 256;
	public static final int TILE_SIZE = 64;
	public static final int CHUNK_SIZE = 16;
	public static final int numOfTicks = 60;
	public static GameState gameState = GameState.MainMenu;
	
	public static int renderDistance = 3;
	
	//GameThread
	private Thread thread1;
	private boolean running = false;
	
	//Chunk Loading Thread
	private ChunkLoader chunkLoader;
	
	//Graphics
	public static Graphics g;
	private BufferStrategy bs;
	public static PixelFont pf;
	public static int fps;
	public static int tps;
	
	//Game Logic
	public static Handler handler;
	public static Menu menu;
	public static Camera camera;
	
	//Debug Tools
	public static Logger logger;
	
	public Game() {
		//GSON test
		//Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(Component.class, new ComponentDeserializer()).registerTypeAdapter(GameObject.class, new GameObjectDeserializer()).create();
		logger = new Logger();
		pf = new PixelFont();
		handler = new Handler(this);
		chunkLoader = new ChunkLoader();
		
		/*
		 * Load textures from file path
		 * Maybe combine SpriteSheet and Textures class into one class
		 */
		
		/*
		 * Player set-up
		 */
		this.addKeyListener(new KeyInput(3.5f));
		MouseInput mi = new MouseInput(handler);
		this.addMouseListener(mi);
		this.addMouseMotionListener(mi);
		
		/*
		 * Finalizing
		 */
		menu = new Menu(this);
		camera = new Camera(WIDTH/2, HEIGHT/2);
		
		/*
		 * (Temporary) Building World
		 */
		
		for (int xPos = -5; xPos <= 5; xPos++) {
			for (int yPos = -5; yPos <= 5; yPos++) {
				GameChunk gameChunk = new GameChunk(xPos,yPos, handler);
				for (int x = 0; x < 16; x++) {
					for (int y = 0; y < 16; y++) {
						gameChunk.addGroundTile(new GroundTile(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE,gameChunk, 0, BlockID.Grass), x,y);
					}
				}
				gameChunk.addObjectTile(new ObjectTile((float)(5 * TILE_SIZE),  (float)(5 * TILE_SIZE), TILE_SIZE, TILE_SIZE, 0, 0, TILE_SIZE,TILE_SIZE, gameChunk, 1, BlockID.Water), 5,5);
				handler.addGameChunk(gameChunk);
			}
		}
		
		new Window(WIDTH, HEIGHT, "Desolate Island - DEV MODE - v0.0.3", this);
		//this.start();
	}
	
	/*
	 * Game Loop
	 */
	
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = numOfTicks;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int numTicks = 0;
		long now;
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				numTicks++;
				delta--;
				render();
				frames++;
			}
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				
				fps = frames;
				tps = numTicks;
				frames = 0;
				numTicks = 0;
			}
		}
		stop();
	}
	
	/*
	 * Starts the Game Thread and Chunk Loader thread
	 */
	
	public synchronized void start() {
		thread1 = new Thread(this);
		thread1.start();
		chunkLoader.start();
		running = true;
	}
	
	/*
	 * Stops all threads
	 */
	public synchronized void stop() {
		try {
			thread1.join();
			chunkLoader.stop();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * GameTick
	 */
	private void tick() {
		switch(gameState) {
		case MainMenu:
			menu.tick();
			break;
		case Settings:
			break;
		case Pause:
			break;
		case WorldList:
			break;
		case CreateWorld:
			break;
		case Game:
			handler.tick();
			camera.tick();
		default:
			break;
		}
	}
	/*
	 * Sets the camera at the right location
	 * TODO: remove this method and find some other place to put it
	 */
	public void play() {
		handler.start(camera);
	}
	
	/*
	 * Renders using a bufferstrategy
	 */
	private void render() {
		bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		switch(gameState) {
		case MainMenu:
			g.setColor(Color.black);
			g.fillRect(0,0,WIDTH,HEIGHT);
			menu.render(g);
			break;
		case Settings:
			break;
		case Pause:
			break;
		case WorldList:
			g.setColor(Color.black);
			g.fillRect(0,0,WIDTH,HEIGHT);
			menu.render(g);
			break;
		case CreateWorld:
			break;
		case Game:
			//render game objects
			g.setColor(Color.black);
			g.fillRect(0,0,WIDTH,HEIGHT);
			handler.render(g, camera.getOffsetX(), camera.getOffsetY());
			break;
		default:
			break;
		}
		g.dispose();
		bs.show();
	}
	
	/*
	 * Initializer
	 */
	public static void main(String args[]) { new Game(); }
	
	/*
	 * Setters
	 */
	public void setGameState(GameState gs) { gameState = gs; }
	
	/*
	 * Getters
	 */
	public int getFPS() { return fps; }
	public int getTPS() { return tps; }
	public GameState getGameState() { return gameState; }
	public Camera getCamera() { return camera; }
	public Menu getMenu() { return menu; }
	public PixelFont getPixelFont() { return pf; }
	public Graphics getGraphics() { return g; }
	public static Path getPath() { return path; }
}