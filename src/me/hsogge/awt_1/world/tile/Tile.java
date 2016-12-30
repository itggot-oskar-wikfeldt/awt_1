package me.hsogge.awt_1.world.tile;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.world.GameObject;
import me.hsogge.awt_1.world.World;

public class Tile extends GameObject {

	public Tile(String texture, int x, int y, World world) {
		super(Assets.TILE_BLUE, x*32, y*32, 32, 32, world);
		world.getTileHitboxes().add(getHitbox());
		if (texture == "blue")
			this.texture = Assets.TILE_BLUE;
		else if (texture == "green")
			this.texture = Assets.TILE_GREEN;
		else if (texture == "yellow")
			this.texture = Assets.TILE_YELLOW;
		else
			System.out.println("invalid texture");
	}

}
