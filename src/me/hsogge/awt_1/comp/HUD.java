package me.hsogge.awt_1.comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import me.hsogge.awt_1.Camera;
import me.hsogge.awt_1.Main;
import me.hsogge.awt_1.input.Keyboard;
import me.hsogge.awt_1.world.World;

public class HUD {

	World world;
	
	public HUD(World world) {
		this.world = world;
	}
	
	boolean debugMode = false;
	Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
	String stringCoordinatesX = "x: ";
	String stringCoordinatesY = "y: ";
	String stringTPS = "tps: ";
	String stringFPS = "fps: ";
	String stringMouseX = "mouse x: ";
	String stringMouseY = "mouse y: ";
	int padding = 20;
	
	public void tick() {
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_F3)) {
			debugMode = !debugMode;
		}
		if (debugMode) {
			stringCoordinatesX = String.format("x: %1$.2f", world.getPlayer().getRealX());
			stringCoordinatesY = String.format("y: %1$.2f", world.getPlayer().getRealY());
			stringTPS = "tps: " + Main.getTPS();
			stringFPS = "fps: " + Main.getFPS();
			stringMouseX = String.format("mouse x: %1$.1f", (Main.getMouseX() - Camera.getOffsetX()));
			stringMouseY = String.format("mouse y: %1$.1f", (Main.getMouseY() - Camera.getOffsetY()));
		}
		
	}
	
	public boolean getDebugMode() {
		return debugMode;
	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setFont(font);
		if (debugMode) {
			g.drawString(stringCoordinatesX, 0 + padding, font.getSize());
			g.drawString(stringCoordinatesY, 0 + padding, font.getSize()*2);
			g.drawString(stringTPS, 0 + padding, font.getSize()*3);
			g.drawString(stringFPS, 0 + padding, font.getSize()*4);
			g.drawString(stringMouseX, 0 + padding, font.getSize()*5);
			g.drawString(stringMouseY, 0 + padding, font.getSize()*6);
		}
	}

}
