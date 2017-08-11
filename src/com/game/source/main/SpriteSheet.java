package com.game.source.main;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage image;
	
	public SpriteSheet(BufferedImage image) {
		this.image=image;
	}
	
	public BufferedImage grabImage(int width, int height) {
		
		BufferedImage img = image.getSubimage(0,  0, width, height);
		return img; 
		
	}
}
