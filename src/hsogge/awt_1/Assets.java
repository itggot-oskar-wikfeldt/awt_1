package hsogge.awt_1;

import java.awt.image.BufferedImage;

import hsogge.awt_1.util.Loader;

public class Assets {

	// Tiles
	public static final BufferedImage TILE_GREEN = Loader.loadImage("/tiles/tile_green.png");
	public static final BufferedImage TILE_BLUE = Loader.loadImage("/tiles/tile_blue.png");
	public static final BufferedImage TILE_YELLOW = Loader.loadImage("/tiles/tile_yellow.png");
	
	public static final BufferedImage[] TILE_DOOR = {Loader.loadImage("/tiles/door_closed.png"), Loader.loadImage("/tiles/door_open.png"), Loader.loadImage("/tiles/door_open_right.png")};

	// Entities
	public static final BufferedImage[] ENTITY_PLAYER = {Loader.loadImage("/entities/player.png"), Loader.loadImage("/entities/player_crouching.png")};
	public static final BufferedImage[] ENTITY_ENEMY = {Loader.loadImage("/entities/enemy.png"), Loader.loadImage("/entities/enemy_crouching.png")};
	//public static final BufferedImage ENTITY_PLAYER_CROUCHING = Loader.loadImage("/entities/player_crouching.png");
}
