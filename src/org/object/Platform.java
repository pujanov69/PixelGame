package org.object;

import java.awt.Color;
import java.awt.Graphics;

import org.graphics.Renderer;

/**
*@author Pujan
*
*Created on Nov 15, 2019
*/
public class Platform extends Sprite{

	public Platform(float posX, float posY, float width, float height) {
		super(posX, posY);
		this.width = width;
		this.height = height;
	}

	public void render(Graphics g) {
		g.setColor(new Color(180,70,80));
		g.fillRect((int) (posX - width /2) - (int) Renderer.camX + Renderer.gameWidth /2,
				(int) (posY - height /2) - (int) Renderer.camY + Renderer.gameHeight /2, (int) width, (int) height);
	}
}
