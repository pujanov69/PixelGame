package org.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.game.Game;
import org.input.Input;
import org.world.World;

/**
*@author Pujan
*
*Created on Nov 13, 2019
*/
public class Renderer {

	private static Frame frame;
	private static Canvas canvas;
	private static int canvasWidth = 0;
	private static int canvasHeight = 0;
	
	private static final int GAME_WIDTH = 400;
	private static final int GAME_HEIGHT = 250;
	private static int gameWidth = 0;
	private static int gameHeight = 0;
	
	private static long lastFpsCheck = 0;
	private static int currentFPS = 0;
	private static int totalFrames = 0;
	
	private static void getBestSize() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		
		boolean done = false;
		
		while(!done) {
			canvasWidth += GAME_WIDTH;
			canvasHeight += GAME_HEIGHT;
		
			if (canvasWidth > screenSize.width || canvasHeight > screenSize.height) {
				canvasWidth -= GAME_WIDTH;
				canvasHeight -= GAME_HEIGHT;
				done = true;
			}
		}
		
		int xDiff = screenSize.width - canvasWidth ;
		int yDiff = screenSize.height - canvasHeight;
		int factor = canvasWidth/ GAME_WIDTH;
		
		gameWidth = canvasWidth / factor + xDiff / factor;
		gameHeight = canvasHeight /factor + yDiff/factor;
		
		System.out.println("gameSize: " + gameWidth + "x" + gameHeight);
		
		canvasWidth = gameWidth * factor;
		canvasHeight = gameHeight * factor;
	}
	
	private static void makeFullScreen() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = env.getDefaultScreenDevice();
		
		if(gd.isFullScreenSupported()) {
			frame.setUndecorated(true);
			gd.setFullScreenWindow(frame);
		}
	}
	
	public static void init() {
		getBestSize();
		frame = new Frame();
		canvas = new Canvas();
		
		canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));
		
		frame.add(canvas);
		makeFullScreen();
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		
		frame.addWindowListener(new WindowAdapter() {
			public void WindowClosing(WindowEvent e) {
				Game.quit();
			}
		});
		
		frame.setVisible(true);
		
		canvas.addKeyListener(new Input());
		canvas.requestFocus();
		
		startRendering();
		
		
	}
	
	private static void startRendering() {
		Thread thread = new Thread() {
			public void run() {
				GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
				VolatileImage vImage = gc.createCompatibleVolatileImage(gameWidth, gameHeight);
				
				while(true) {
					//FPS counter
					totalFrames ++;
					if(System.nanoTime() > lastFpsCheck + 1000000000) {
						lastFpsCheck = System.nanoTime();
						currentFPS = totalFrames;
						totalFrames= 0;
						System.out.println(currentFPS);
					}
					
					if(vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE) {
						vImage = gc.createCompatibleVolatileImage(gameWidth, gameHeight);
					}
					
					Graphics g = vImage.getGraphics();
					g.setColor(Color.black);
					g.fillRect(0, 0, gameWidth, gameHeight);
					//g.setColor(Color.red);
					//g.drawRect(10, 10, 100, 100);
					
					//RENDER Stuff
					
					World.update();
					World.render(g);
					
					//Draw FPS counter
					g.setColor(Color.LIGHT_GRAY);
					g.drawString(String.valueOf(currentFPS), 2, gameHeight - 4);
					
					
					g.dispose();
					g = canvas.getGraphics();
					g.drawImage(vImage, 0, 0, canvasWidth, canvasHeight, null);
					
					g.dispose();
				}
			}
		};
		thread.setName("Rendering Thread");
		thread.start();
	}
	
	public static BufferedImage loadImage(String path) throws IOException {
		BufferedImage rawImage = ImageIO.read(Renderer.class.getResource(path));
		BufferedImage finalImage = canvas.getGraphicsConfiguration()
				.createCompatibleImage(rawImage.getWidth(), rawImage.getHeight(), rawImage.getTransparency());
		
		finalImage.getGraphics().drawImage(rawImage, 0, 0, rawImage.getWidth(), rawImage.getHeight(), null);
		return finalImage;
	}
}
