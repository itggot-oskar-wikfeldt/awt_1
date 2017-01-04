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
	protected int damage;
	private boolean hasHit = false;

	public Weapon(BufferedImage texture, double x, double y, Mob owner) {
		super(texture, x, y, owner);
		line = new Line2D.Double(x + width, y, x + width, y + height);
		// TODO Auto-generated constructor stub
	}

	public void tick() {
		attacking = using;
		if (!attacking)
			hasHit = false;

		if (attacking && !hasHit) {
			Mob mobToHurt = owner;
			boolean willHurt = false;
			for (Enemy enemy : owner.getWorld().getEnemies()) {
				if (line.intersects(enemy.getHitbox())) {
					willHurt = true;
					mobToHurt = enemy;
					hasHit = true;
				}

			}
			if (willHurt)
				mobToHurt.hurt(damage);
		}
		super.tick();
		line.setLine(x, y, x + weaponLength * Math.sin(angleRadians), y - weaponLength * Math.cos(angleRadians));
	}

	public void render(Graphics2D g) {
		super.render(g);
		int offsetX = (int) Camera.getOffsetX();
		int offsetY = (int) Camera.getOffsetY();
		if (Main.getHUD().getDebugMode())
			g.drawLine((int) line.x1 + offsetX, (int) line.y1 + offsetY, (int) line.x2 + offsetX,
					(int) line.y2 + offsetY);
	}

}
