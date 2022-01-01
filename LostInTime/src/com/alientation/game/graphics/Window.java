package com.alientation.game.graphics;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import com.alientation.game.Game;

public class Window extends Canvas{
	private static final long serialVersionUID = -8882282105973546239L;
	
	/*
	 * Init Window
	 * TODO: Allow resizable windows
	 */
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
}