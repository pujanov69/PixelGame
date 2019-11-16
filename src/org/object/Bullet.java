package org.object;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import org.graphics.Animation;
import org.graphics.Renderer;
import org.world.World;

/**
*@author Pujan
*
*Created on Nov 16, 2019
*/
public class Bullet extends Sprite{
	
	public int direction = 1; //-1 = left, 1= right
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
		
		Animation anim2 = new Animation();
		try {
			anim2.images.add(Renderer.loadImage("/resources/image/bullet_0_left.jpg"));
			anim2.images.add(Renderer.loadImage("/resources/image/bullet_1_left.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		animations = new Animation[] {
			anim, anim2	
		};
	}

	public void update(float deltaTime) {
		float moveX = 0;
		moveX += speed * direction;
		
		posX += moveX * deltaTime;
		
		if(direction > 0) {
			currentAnimation = 0;
		}
		if(direction < 0) {
			currentAnimation = 1;
		}
		
		Sprite[] colliders = getColliders(posX, posY);		
		
		if (colliders.length > 0) {
			for(Sprite sprite: colliders) {
				if (sprite instanceof BadGuy) {
					BadGuy badGuy = (BadGuy) sprite;
					badGuy.takeDamage(damage);
					World.currentWorld.removeSprite(this);
				}
			}
		}
	}
	
	
}
