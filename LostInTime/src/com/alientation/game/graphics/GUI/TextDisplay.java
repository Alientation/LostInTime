package com.alientation.game.graphics.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import com.alientation.game.Game;

public class TextDisplay {
	private int x,y,width,height, margin;
	private int size;
	private String[] text;
	private String original;
	private int[] style;
	
	
	/*
	 * IN FUTURE, ADD SUPPORT TO INLINE COLORING
	 */
	private Color color;
	
	BufferedImage bi = null;
	
	public TextDisplay(String t, Color color, int x, int y, int width, int height, int margin, int size) {
		this.color = color;
		this.x = x;
		this.y = y;
		this.size = size;
		this.width = width;
		this.height = height;
		this.margin = margin;
		this.text = t.split(" ");
		this.original = t;
		processText();
		updateBI();
	}
	
	public void updateBI() {
		int character = 0;
		System.out.println(Arrays.toString(text));
		int xx = margin;
		int yy = margin;
		for (int t = 0; t < text.length; t++) {
			if (style[character] > 2) {
				int tt = style[character] % 10;
				xx += tt * (Game.pf.getBufferedImage(' ',0).getWidth() + size);
				int nn = style[character] / 10;
				yy += nn * (Game.pf.getBufferedImage(' ',style[character]).getHeight() * size + size);
				character++;
			}
			if (xx + getTextLength(text[t]) + Game.pf.getBufferedImage(' ',0).getWidth() * size >= width) {
				xx = margin;
				yy += Game.pf.getBufferedImage(text[t].charAt(0),style[character]).getHeight() * size + size;
			}
			if (xx != margin) {
				xx += Game.pf.getBufferedImage(' ',style[character]).getWidth() * size;
			}
			for (char c : text[t].toCharArray()) {
				for (int yyy = 0; yyy < Game.pf.getBufferedImage(c,style[character]).getHeight() * size; yyy++) {
					for (int xxx = 0; xxx < Game.pf.getBufferedImage(c,style[character]).getWidth() * size; xxx++) {
						if (xx + xxx < bi.getWidth() && yy + yyy < bi.getHeight() && Game.pf.getBufferedImage(c,style[character]).getRGB(xxx/size, yyy/size) != 0) {
							bi.setRGB(xx + xxx, yy + yyy, color.getRGB());
						}
					}
				}
				xx += Game.pf.getBufferedImage(c,style[character]).getWidth() * size + size;
				character++;
			}
		}
	}
	
	public void processText() {
		style = new int[original.length()];
		int character = 0;
		int prevStyle = 0;
		for (int s = 0; s < text.length; s++) {
			for (int c = 0; c < text[s].length(); c++) {
				boolean bool = true;
				if (text[s].charAt(c) == '&' && c != text[s].length()-1) {
					bool = false;
					switch(text[s].charAt(c+1)) {
					case '0':
						prevStyle = 0;
						break;
					case '1':
						prevStyle = 1;
						break;
						
					case '2':
						prevStyle = 2;
						break;
					case 'n':
						style[c] += 10;
						break;
					case 't':
						style[c] += 11;
					default:
						bool = true;
					}
				}
				if (bool == false) {
					if (c+2 < text[s].length())
						text[s] = text[s].substring(0, c) + text[s].substring(c+2);
					else
						text[s] = text[s].substring(0,c);
					c--;
				} else {
					style[character] = prevStyle;
					character++;
				}
			}
		}
		bi = new BufferedImage(2 * margin + width,2 * margin + height, BufferedImage.TYPE_INT_ARGB);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		processText();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		processText();
	}

	public int getMargin() {
		return margin;
	}

	public void setMargin(int margin) {
		this.margin = margin;
		processText();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
		processText();
	}

	public String getOriginal() {
		return original;
	}

	public void setOriginal(String original) {
		this.original = original;
		processText();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		processText();
	}

	public int getTextLength(String t) {
		int length = 0;
		for (char c : t.toCharArray()) {
			length += Game.pf.getBufferedImage(c,0).getWidth() * size + size;
		}
		return length;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(bi,x,y,width,height,null);
	}
}
