package org.object;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.graphics.Animation;
import org.graphics.Renderer;

/**
*@author Pujan
*
*Created on Nov 14, 2019
*/
public class Sprite {
	
	public float posX = 0;
	public float posY = 0;
	
	public Animation[] animations;
	public int currentAnimation = 0;
	
	public float width = 0;
	public float height = 0;
	
	public boolean isSolid = true;
	
	public Sprite(float posX, float posY) {
		this.posX = posX;
		this.posY = posY;
	}
	
	public void update(float deltaTime) {
		
	}
	
	public void render(Graphics g) {
		
		if(animations == null || currentAnimation >= animations.length) {
			return;
		}
		
		animations[currentAnimation].playAnimation();
		
		BufferedImage image = animations[currentAnimation].getImage();
		
		if(image == null) {
			return;
		}
		
		int realX = (int) posX - image.getWidth()/2;
		int realY = (int) posY - image.getHeight()/2;
		
		realX = realX - (int) Renderer.camX + Renderer.gameWidth / 2;
		realY = realY - (int) Renderer.camY + Renderer.gameHeight / 2;
		
		g.drawImage(image, realX, realY, image.getWidth(), image.getHeight(), null);
	}

}
