package me.hsogge.awt_1.comp;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import me.hsogge.awt_1.input.Keyboard;
import me.hsogge.awt_1.world.World;

public class HUD {

	World world;
	Debug debugMenu;
	Hotbar hotbar;
	boolean debugMode = false;

	public HUD(World world) {
		debugMenu = new Debug(world);
		hotbar = new Hotbar(world);
	}

	public boolean getDebugMode() {
		return debugMode;
	}
	
	public void updateCanvasDimensions() {
		hotbar.updateLocation();
	}

	public void tick() {
		if (Keyboard.isKeyPressed(KeyEvent.VK_F3))
			debugMode = !debugMode;

		if (debugMode)
			debugMenu.tick();

	}

	public void render(Graphics2D g) {
		hotbar.render(g);
		
		if (debugMode)
			debugMenu.render(g);

	}

}
