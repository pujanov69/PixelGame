package org.test;

import java.awt.event.KeyEvent;
import java.io.IOException;

import org.graphics.Renderer;
import org.input.Input;
import org.object.Sprite;

/**
*@author Pujan
*
*Created on Nov 14, 2019
*/
public class TestSprite extends Sprite{

	public TestSprite(float posX, float posY) {
		super(posX, posY);
		
		try {
			image = Renderer.loadImage("/resources/image/test.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(float deltaTime) {
//		posX += 10*deltaTime;
		if(Input.getKey(KeyEvent.VK_W)) {
			posY -= 80 * deltaTime;
		}
		if(Input.getKey(KeyEvent.VK_S)) {
			posY += 80 * deltaTime;
		}
		if(Input.getKey(KeyEvent.VK_A)) {
			posX -= 80 * deltaTime;
		}
		if(Input.getKey(KeyEvent.VK_D)) {
			posX += 80 * deltaTime;
		}
	}

}
