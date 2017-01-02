package me.hsogge.awt_1.world.item;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import me.hsogge.awt_1.Camera;
import me.hsogge.awt_1.Main;
import me.hsogge.awt_1.world.entity.Enemy;
import me.hsogge.awt_1.world.entity.Mob;

public class Weapon extends Item {

	private Line2D.Double line;
	private int weaponLength = 64;
	protected boolean attacking = false;
	
	public Weapon(BufferedImage texture, double x, double y, int width, int height, Mob owner) {
		super(texture, x, y, width, height, owner);
		line = new Line2D.Double(x+width, y, x+width, y+height);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		attacking = using;
		if (attacking) {
			Mob mobToKill = owner;
			boolean kill = false;
			for (Enemy enemy : owner.getWorld().getEnemies()) {
				if (line.intersects(enemy.getHitbox())) {
					kill = true;
					mobToKill = enemy;
				}

			}
			if (kill)
				mobToKill.kill();
		}
		super.tick();
		line.setLine(x, y, x+weaponLength*Math.sin(angleRadians), y-weaponLength*Math.cos(angleRadians));
	}
	
	public void render(Graphics2D g) {
		super.render(g);
		int offsetX = (int) Camera.getOffsetX();
		int offsetY = (int) Camera.getOffsetY();
		if (Main.getHUD().getDebugMode())
			g.drawLine((int) line.x1 + offsetX, (int) line.y1 + offsetY, (int) line.x2 + offsetX, (int) line.y2 + offsetY);
	}

}
