package awt_1;

import java.awt.image.BufferedImage;

import awt_1.util.Loader;

public class Assets {

	// Tiles
	public static final BufferedImage TILE_GREEN = Loader.loadImage("/tiles/tile_green.png");
	public static final BufferedImage TILE_BLUE = Loader.loadImage("/tiles/tile_blue.png");

	// Entities
	public static final BufferedImage ENTITY_PLAYER = Loader.loadImage("/entities/player.png");
	public static final BufferedImage ENTITY_PLAYER_CROUCHING = Loader.loadImage("/entities/player_crouching.png");
}
