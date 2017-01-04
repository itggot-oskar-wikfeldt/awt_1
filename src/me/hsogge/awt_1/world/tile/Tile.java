package me.hsogge.awt_1.world.tile;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.world.GameObject;
import me.hsogge.awt_1.world.World;

public class Tile extends GameObject {
	
	private final static int SIZE = 32;
	
	public Tile(String texture, int xPos, int yPos, World world) {
		super(Assets.TILE_BLUE, xPos*SIZE, yPos*SIZE, SIZE, SIZE, world);
		world.getTileHitboxes().add(getHitbox());
		if (texture == "blue")
			this.texture = Assets.TILE_BLUE;
		else if (texture == "green")
			this.texture = Assets.TILE_GREEN;
		else if (texture == "yellow")
			this.texture = Assets.TILE_YELLOW;
		else if (texture == "transparent")
			this.texture = Assets.TEXTURE_TRANSPARENT;
		else
			System.out.println("invalid texture");
	}

}
