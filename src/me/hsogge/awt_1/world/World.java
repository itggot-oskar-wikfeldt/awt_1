package me.hsogge.awt_1.world;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.Camera;
import me.hsogge.awt_1.Main;
import me.hsogge.awt_1.world.entity.Enemy;
import me.hsogge.awt_1.world.entity.Mob;
import me.hsogge.awt_1.world.entity.Player;
import me.hsogge.awt_1.world.tile.Door;
import me.hsogge.awt_1.world.tile.Interactable;
import me.hsogge.awt_1.world.tile.Tile;

public class World {

	Player player;
	Enemy enemy;
	Enemy enemy2;
	List<GameObject> tiles = new ArrayList<>();
	List<Mob> mobs = new ArrayList<>();
	List<Enemy> enemies = new ArrayList<>();
	List<Interactable> interactables = new ArrayList<>();
	List<Rectangle> tileHitboxes = new ArrayList<>();
	List<GameObject> lateRender = new ArrayList<>();
	Tile tile1;
	Tile tile2;
	Door door;
	Color backgroundColor;

	public final int GRAVITY = 70 * 64;

	public World() {
		player = new Player(this);
		enemy = new Enemy(13 * 32, -128, this);
		enemy2 = new Enemy(14*32, -128, this);

		// ground and walls
		for (int i = 0; i < 200; i++)
			tiles.add(new Tile("transparent", i-100, 0, this));

		for (int i = 0; i < 10; i++) {
			tiles.add(new Tile("transparent", -100, -(i+1), this));
			tiles.add(new Tile("transparent", 100, -(i+1), this));
		}

		for (int i = 0; i < 13; i++) {
			tiles.add(new Tile("green", 10 + i, -5, this));
		}

		for (int i = 0; i < 4; i++) {
			tiles.add(new Tile("yellow", 19, -i - 1, this));
		}

		tiles.add(new Tile("yellow", 5, -1, this));
		tiles.add(new Tile("yellow", 6, -1, this));

		tiles.add(new Tile("blue", 22, -1, this));
		tiles.add(new Tile("blue", 23, -1, this));
		tiles.add(new Tile("blue", 24, -1, this));

		door = new Door(10, -4, this);
		tiles.add(door);

		backgroundColor = new Color(18, 232, 184);
	}

	public List<GameObject> getTiles() {
		return tiles;
	}

	public List<GameObject> getLateRender() {
		return lateRender;
	}

	public List<Interactable> getInteractables() {
		return interactables;
	}

	public List<Mob> getMobs() {
		return mobs;
	}

	public List<Enemy> getEnemies() {
		return enemies;
	}

	public Player getPlayer() {
		return player;
	}

	public List<Rectangle> getTileHitboxes() {
		return tileHitboxes;
	}

	public void tick() {
		player.tick();
		for (Enemy enemy : enemies)
			enemy.tick();

	}

	public void render(Graphics2D g) {
		g.setColor(backgroundColor);
		g.fillRect(0, 0, Main.getCanvas().getWidth(), Main.getCanvas().getHeight());
		g.drawImage(Assets.GRADIENT, 0, (int) Camera.getOffsetY(), null);

		for (GameObject tile : tiles)
			if (tile.getRenderOrder() == 1)
				tile.render(g);

		for (Enemy enemy : enemies)
			enemy.render(g);

		player.render(g);
		for (GameObject tile : tiles)
			if (tile.getRenderOrder() == 0)
				tile.render(g);

	}
}
