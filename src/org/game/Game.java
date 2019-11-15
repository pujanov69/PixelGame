package org.game;

import org.graphics.Renderer;
import org.object.Platform;
import org.object.Player;
import org.world.World;

/**
*@author Pujan
*
*Created on Nov 13, 2019
*/
public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Renderer.init();
		
		World.currentWorld = new World();
		World.currentWorld.addSprite(new Player(100, 100)); 
		World.currentWorld.addSprite(new Platform(200,200, 300, 20));
		//World.currentWorld.sprites.add(new TestSprite(500,300));
	}
	
	public static void quit() {
		System.exit(0);
	}

}
