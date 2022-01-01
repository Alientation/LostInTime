package com.alientation.game.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.alientation.game.Game;
import com.alientation.game.GameState;

public class Menu {
	/*
	 * TODO: Create menu handler that contains 'menu pages'. Buttons when clicked will either travel to a different 'menu page' or update some values.
	 */
	private Game game;
	
	/*
	 * Init Menu
	 */
	public Menu(Game game) {
		this.game = game;
	}
	
	/*
	 * Renders the menu
	 * TODO: Change this to match what was said above
	 */
	public void render(Graphics g) {
		
		switch(game.getGameState()) {
		case MainMenu:
			drawMainMenu(g);
			break;
		case Settings:
			drawSettings(g);
			break;
		case Help:
			drawHelp(g);
			break;
		case Pause:
			drawPause(g);
			break;
		case WorldList:
			drawWorldList(g);
			break;
		case CreateWorld:
			drawCreateWorld(g);
			break;
		default:
			
		}
	}
	
	/*
	 * Potential animation needs this
	 */
	public void tick() {
		
	}
	
	/*
	 * Handles click
	 * TODO: Change this to match what was said above
	 */
	public void handleClick(float mouseClickPosX, float mouseClickPosY) {
		
		switch(game.getGameState()) {
		case MainMenu:
			if (mouseClickPosX >= Game.WIDTH / 2 - 420 && mouseClickPosX <= Game.WIDTH / 2 - 270 && mouseClickPosY >= 350 && mouseClickPosY <= 400) {
				//pressed play button
				game.setGameState(GameState.WorldList);
				
			} else if (mouseClickPosX >= Game.WIDTH / 2 - 420 && mouseClickPosX <= Game.WIDTH / 2 - 270 && mouseClickPosY >= 450 && mouseClickPosY <= 500) {
				//pressed help button
				game.setGameState(GameState.Help);
				
			} else if (mouseClickPosX >= Game.WIDTH / 2 - 420 && mouseClickPosX <= Game.WIDTH / 2 + 130 && mouseClickPosY >= 550 && mouseClickPosY <= 600) {
				//pressed settings button
				game.setGameState(GameState.Settings);
				
			} else if (mouseClickPosX >= Game.WIDTH / 2 - 420 && mouseClickPosX <= Game.WIDTH / 2 - 270 && mouseClickPosY >= 650 && mouseClickPosY <= 700) {
				//pressed quit button
				game.stop();
			}
			break;
		case Settings:
			
			break;
		case Help:
			
			break;
		case Pause:
			
			break;
		case WorldList:
			if (mouseClickPosX >= Game.WIDTH / 2 - 420 && mouseClickPosX <= Game.WIDTH / 2 -70 && mouseClickPosY >= 350 && mouseClickPosY <= 400) {
				//play world
				game.setGameState(GameState.Game);
				game.play();
			}
			break;
		case CreateWorld:
			
			break;
		default:
			
		}
	}
	
	/*
	 * TODO: Change this to match what was said above
	 */
	private void drawMainMenu(Graphics g) {
		Rectangle playButton = new Rectangle(Game.WIDTH/2 - 420, 350, 150, 50);
		Rectangle helpButton = new Rectangle(Game.WIDTH/2 - 420, 450, 150, 50);
		Rectangle settingsButton = new Rectangle(Game.WIDTH/2 - 420, 550, 275, 50);
		Rectangle quitButton = new Rectangle(Game.WIDTH/2 - 420, 650, 150, 50);
		
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("D E S O L A T E    I S L A N D", Game.WIDTH/2 - 360, 200);
		
		g2d.draw(playButton);
		g.drawString("PLAY", playButton.x + 10, playButton.y + 43);
		g2d.draw(helpButton);
		g.drawString("HELP", helpButton.x + 10, helpButton.y + 43);
		g2d.draw(settingsButton);
		g.drawString("SETTINGS", settingsButton.x + 10, settingsButton.y + 43);
		g2d.draw(quitButton);
		g.drawString("QUIT", quitButton.x + 10, quitButton.y + 43);
	}
	
	private void drawSettings(Graphics g) {
		
	}
	
	private void drawHelp(Graphics g) {
		
	}


	private void drawPause(Graphics g) {
	
	}
	
	private void drawWorldList(Graphics g) {
		Rectangle playButton = new Rectangle(Game.WIDTH/2 - 420, 350, 350, 50);
		Graphics2D g2d = (Graphics2D) g;
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g2d.draw(playButton);
		g.drawString("PLAY WORLD", playButton.x + 10, playButton.y + 43);
		g.drawString("Worlds", Game.WIDTH/2 - 360, 200);
	}
	
	private void drawCreateWorld(Graphics g) {
		
	}
}