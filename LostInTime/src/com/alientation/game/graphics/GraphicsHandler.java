package com.alientation.game.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.alientation.game.Game;

public class GraphicsHandler {
	
	
	public static BufferedImage scale(BufferedImage old, int factor) {
		if (factor < 1) {
			Game.logger.addLog("Invalid Factor", "Factor in scale(BufferedImage old, int factor) has to be >= 1, factor given is " + factor);
			return old;
		}
		BufferedImage bi = new BufferedImage(old.getWidth() * factor, old.getHeight() * factor, BufferedImage.TYPE_INT_ARGB);
		int y;
		int x;
		int yy;
		int xx;
		for (y = 0; y < old.getHeight(); y++) {
			for (x = 0; x < old.getWidth(); x++) {
				for (yy = y*factor; yy < y *factor + factor; yy++) {
					for (xx = x*factor; xx < x * factor + factor; xx++) {
						bi.setRGB(xx, yy, old.getRGB(x, y));
					}
				}
			}
		}
		return bi;
	}
	
	
	public static BufferedImage duplicate(BufferedImage old, int numDuplicates, int pixelsInBetween) {
		if (numDuplicates <= 0) {
			Game.logger.addLog("Invalid numDuplicates", "numDuplicates in duplicate(BufferedImage old, int numDuplicates, int pixelsInBetween) has to be >= 1, numDuplicates given is " + numDuplicates);
			return old;
		} else if (pixelsInBetween < 0) {
			Game.logger.addLog("Invalid pixelsInBetween", "pixelsInBetween in duplicate(BufferedImage old, int numDuplicates, int pixelsInBetween) has to be >= 0, pixelsInBetween given is " + pixelsInBetween);
			return old;
		}
		BufferedImage bi = new BufferedImage(old.getWidth() * numDuplicates + pixelsInBetween * (numDuplicates-1), old.getHeight(), BufferedImage.TYPE_INT_ARGB);
		int y,x,xx;
		for (y = 0; y < old.getHeight(); y++) {
			for (x = 0; x < old.getWidth(); x++) {
				for (xx = x; xx < bi.getWidth(); xx+=old.getWidth() + pixelsInBetween) {
					bi.setRGB(xx, y, old.getRGB(x, y));
				}
				
				
				/*for (int yy = y; yy < bi.getHeight(); yy++) {
					for (int xx = x; xx < bi.getWidth(); xx+=old.getWidth() + pixelsInBetween) {
						bi.setRGB(xx, yy, bi.getRGB(x, y));
					}
				}
				*/
			}
		}
		return bi;
	}
	
	
	public static BufferedImage combine(BufferedImage first, BufferedImage second, int pixelsInBetween) {
		if (pixelsInBetween < 0) {
			Game.logger.addLog("Invalid pixelsInBetween", "pixelsInBetween in combine(BufferedImage first, BufferedImage second, int pixelsInBetween) has to be >= 0, pixelsInBetween given is " + pixelsInBetween);
			return first;
		}
		BufferedImage bi = new BufferedImage(first.getWidth() + second.getWidth() + pixelsInBetween, Math.max(first.getHeight(), second.getHeight()),BufferedImage.TYPE_INT_ARGB);
		int y,x;
		for (y = 0; y < first.getHeight(); y++) {
			for (x = 0; x < first.getWidth(); x++) {
				bi.setRGB(x, y, first.getRGB(x, y));
			}
		}
		for (y = 0; y < second.getHeight(); y++) {
			for (x = 0; x < second.getWidth(); x++) {
				bi.setRGB(x + first.getWidth() + pixelsInBetween, y, second.getRGB(x, y));
			}
		}
		return bi;
	}
	
	
	public static void savePNG(BufferedImage bi, String filepath) {
		try {
		    File outputfile = new File(filepath);
		    ImageIO.write(bi, "png", outputfile);
		} catch (IOException e) {
		    // handle exception
			Game.logger.addLog("Error when saving image", "Filepath: " + filepath + "   BufferedImage Memory Address" + bi);
		}
	}
	
}
