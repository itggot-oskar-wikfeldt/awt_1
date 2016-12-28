package awt_1.world;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import awt_1.Assets;
import awt_1.world.entity.Player;
import awt_1.world.tile.Tile;

public class World {
	
	Player player;
	List<Tile> tiles = new ArrayList<>();
	List<Rectangle> tileHitboxes = new ArrayList<>();
	Tile tile1;
	Tile tile2;
	Canvas canvas;
	
	public final int GRAVITY = 70 * 64;

	public World(Canvas canvas) {
		this.canvas = canvas;
		player = new Player(this);
		for (int i = 0; i < 40	; i++) {
			tiles.add(new Tile(Assets.TILE_BLUE, i * 32, 0, this));
		}
		for (int i = 0; i < 3	; i++) {
			tiles.add(new Tile(Assets.TILE_GREEN, 416, -i*32-32, this));
			tiles.add(new Tile(Assets.TILE_GREEN, 416+32, -i*32-32, this));
			tiles.add(new Tile(Assets.TILE_GREEN, i*32+416-32, -4*32, this));
		}
		
		for (int i = 0; i < 4	; i++) {
			tiles.add(new Tile(Assets.TILE_GREEN, i*32+416-32, -4*32, this));
		}
		

		for (Tile tile : tiles) {
			tileHitboxes.add(tile.getHitbox());
		}
	}

	public List<Tile> getTiles() {
		return tiles;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public List<Rectangle> getTileHitboxes() {
		return tileHitboxes;
	}

	public void tick(double delta) {
		player.tick(delta);
		for (Tile tile : tiles) {
			tile.tick(delta);
		}
	}

	public void render(Graphics2D g) {
		g.setColor(new Color(18, 232, 184));
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		player.render(g);
		for (Tile tile : tiles) {
			tile.render(g);
		}
	}
}
