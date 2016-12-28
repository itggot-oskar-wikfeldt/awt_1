package awt_1.world.tile;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import awt_1.world.GameObject;
import awt_1.world.World;

public class Tile extends GameObject {
	private Rectangle hitbox;
	public Tile(BufferedImage texture, int x, int y, World world) {
		super(texture, x, y, 32, 32, world);
		hitbox = new Rectangle(x, y, width, height);
		
	}
	public Rectangle getHitbox() {
		return hitbox;
	}

}
