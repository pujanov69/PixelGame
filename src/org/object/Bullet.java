package org.object;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import org.graphics.Animation;
import org.graphics.Renderer;

/**
*@author Pujan
*
*Created on Nov 16, 2019
*/
public class Bullet extends Sprite{
	
	public int direction = 0; //0 = left, 1= right
	public float speed = 400.0f;
	public float damage = 10.0f;

	public Bullet(float posX, float posY, int direction) {
		super(posX, posY);
		this.direction = direction;
		width = 10;
		height = 10;
		isSolid = false;
		
		Animation anim = new Animation();
		try {
			anim.images.add(Renderer.loadImage("/resources/image/bullet_0.jpg"));
			anim.images.add(Renderer.loadImage("/resources/image/bullet_1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		animations = new Animation[] {
			anim	
		};
	}

	public void update(float deltaTime) {
		float moveX = 0;
		if(direction == 0) {
			moveX -= speed;
		}else {
			moveX += speed;
		}
		
		posX += moveX * deltaTime;
	}
	
	
}
