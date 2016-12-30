package me.hsogge.awt_1.comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

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
	String stringCoordinates = "x, y: ";
	String stringTPS = "tps: ";
	String stringFPS = "fps: ";
	
	public void tick() {
		
		if (Keyboard.isKeyPressed(KeyEvent.VK_F3)) {
			debugMode = !debugMode;
		}
		if (debugMode) {
			stringCoordinates = "x, y: " + world.getPlayer().getRealX() + "; " + (world.getPlayer().getRealY() + 128);
			stringTPS = "tps: " + Main.getTPS();
			stringFPS = "fps: " + Main.getFPS();
		}
		
	}
	
	public boolean getDebugMode() {
		return debugMode;
	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setFont(font);
		if (debugMode) {
			g.drawString(stringCoordinates, 0, font.getSize());
			g.drawString(stringTPS, 0, font.getSize()*2);
			g.drawString(stringFPS, 0, font.getSize()*3);
		}
	}

}
