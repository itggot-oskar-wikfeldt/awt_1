package me.hsogge.awt_1.comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import me.hsogge.awt_1.Camera;
import me.hsogge.awt_1.Main;
import me.hsogge.awt_1.world.World;

public class Debug {
	World world;
	Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
	String stringCoordinatesX = "x: ";
	String stringCoordinatesY = "y: ";
	String stringTPS = "tps: ";
	String stringFPS = "fps: ";
	String stringMouseX = "mouse x: ";
	String stringMouseY = "mouse y: ";
	int padding = 20;

	public Debug(World world) {
		this.world = world;

	}

	public void tick() {

		stringCoordinatesX = String.format("x: %1$.2f", world.getPlayer().getRealX());
		stringCoordinatesY = String.format("y: %1$.2f", world.getPlayer().getRealY());
		stringTPS = "tps: " + Main.getTPS();
		stringFPS = "fps: " + Main.getFPS();
		stringMouseX = String.format("mouse x: %1$.1f", (Main.getMouseX() - Camera.getOffsetX()));
		stringMouseY = String.format("mouse y: %1$.1f", (Main.getMouseY() - Camera.getOffsetY()));

	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.setFont(font);

		g.drawString(stringCoordinatesX, 0 + padding, font.getSize());
		g.drawString(stringCoordinatesY, 0 + padding, font.getSize() * 2);
		g.drawString(stringTPS, 0 + padding, font.getSize() * 3);
		g.drawString(stringFPS, 0 + padding, font.getSize() * 4);
		g.drawString(stringMouseX, 0 + padding, font.getSize() * 5);
		g.drawString(stringMouseY, 0 + padding, font.getSize() * 6);

	}

}
