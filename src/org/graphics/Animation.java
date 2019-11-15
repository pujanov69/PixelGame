package org.graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
*@author Pujan
*
*Created on Nov 16, 2019
*/
public class Animation {

	public ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	public int currentImage = 0;
	
	public int fps = 12;
	private long lastTime = 0;
	
	public void playAnimation() {
		if(System.nanoTime() > lastTime + (1000000000/fps)) {
			currentImage ++;
			if (currentImage >= images.size()) {
				currentImage = 0;
			}
		}
	}
	
	public BufferedImage getImage() {
		if(images.size() > currentImage) {
			return images.get(currentImage);	
		}
		
		return null;
		
	}
}
