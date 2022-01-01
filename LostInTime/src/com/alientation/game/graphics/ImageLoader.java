package com.alientation.game.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	/*
	 * Helper class to load an image
	 * TODO: Combine all helper classes and methods into one class called Utilities
	 */
	public static BufferedImage loadImage(String path) {
		try {
			System.out.println(path);
			return ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
