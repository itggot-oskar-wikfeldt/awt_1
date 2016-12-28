package awt_1.comp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import awt_1.world.World;

public class HUD {

	String stringCoordinates;
	World world;
	Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);

	public HUD(World world) {
		this.world = world;
		stringCoordinates = "x, y: " + (int) world.getPlayer().getX() + "; " + (int) (world.getPlayer().getY() + 128);
	}

	public void render(Graphics2D g) {
		stringCoordinates = "x, y: " + world.getPlayer().getRealX() + "; " + (world.getPlayer().getRealY() + 128);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(stringCoordinates, 0, font.getSize());
	}

}
