package me.hsogge.awt_1;

import java.awt.image.BufferedImage;

import me.hsogge.awt_1.util.Loader;

public class Assets {
	// Entities
	public static final BufferedImage[][] ENTITY_PLAYER = new BufferedImage[2][4];
	public static final BufferedImage[][] ENTITY_ENEMY = new BufferedImage[2][4];
	
	static {
		for (int i = 0; i < 4; i++) {
			ENTITY_PLAYER[0][i] = Loader.loadImage("/entities/player/standing/" + i + ".png");
			ENTITY_PLAYER[1][i] = Loader.loadImage("/entities/player/crouching/" + i + ".png");
			
			ENTITY_ENEMY[0][i] = Loader.loadImage("/entities/enemy/standing/" + i + ".png");
			ENTITY_ENEMY[1][i] = Loader.loadImage("/entities/enemy/crouching/" + i + ".png");
		}
	}

	// Tiles
	public static final BufferedImage TILE_GREEN = Loader.loadImage("/tiles/tile_green.png");
	public static final BufferedImage TILE_BLUE = Loader.loadImage("/tiles/tile_blue.png");
	public static final BufferedImage TILE_YELLOW = Loader.loadImage("/tiles/tile_yellow.png");
	
	public static final BufferedImage[] TILE_DOOR = {Loader.loadImage("/tiles/door_closed.png"), Loader.loadImage("/tiles/door_open.png"), Loader.loadImage("/tiles/door_open_right.png")};


	
}
