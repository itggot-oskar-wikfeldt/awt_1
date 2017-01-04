package me.hsogge.awt_1.world.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.Camera;
import me.hsogge.awt_1.Main;
import me.hsogge.awt_1.world.World;
import me.hsogge.awt_1.world.item.Sword;

public class Enemy extends Mob {
	private Line2D.Double sightLine;
	private Mob target;
	private Point enemyPoint;
	private Point targetPoint;
	private int targetX;
	private int targetY;
	private int targetRange = 15 * 32;
	private double targetTime = -5;
	

	public Enemy(int x, int y, World world) {
		super(Assets.ENTITY_ENEMY, x, y, world);
		// TODO Auto-generated constructor stub
		defaultMaxVel = 1 * 64;
		target = world.getPlayer();
		targetX = target.getCenterX();
		targetY = target.getCenterY();

		// poop code huehue
		targetX += targetY;
		targetX -= targetY;
		
		health = maxHealth = 10;
		items[0] = new Sword(this);
		enemyPoint = new Point(getCenterX(), y);
		targetPoint = new Point(target.getCenterX(), target.getY());
		sightLine = new Line2D.Double(enemyPoint, targetPoint);
		world.getEnemies().add(this);
		handTexture.setTexture(Assets.TEXTURE_ENEMYHAND);
	}

	boolean movingRight = true;
	boolean targetReached = false;
	boolean canSeeTarget = false;

	public void tick() {
		enemyPoint.setLocation(getCenterX(), y + 26);
		targetPoint.setLocation(target.getCenterX(), target.getY() + 26);

		if (enemyPoint.distance(targetPoint) < targetRange) {
			canSeeTarget = true;
			sightLine.setLine(enemyPoint, targetPoint);
			for (Rectangle tileHitbox : world.getTileHitboxes()) {
				if (sightLine.intersects(tileHitbox)) {
					canSeeTarget = false;
					break;
				}
			}
		}

		if (canSeeTarget) {
			targetTime = Main.getTimePassed();
			targetX = target.getCenterX();
			targetY = target.getCenterY();
		}

		targetReached = false;
		
		if (Main.getTimePassed() - targetTime < 5) {
			defaultMaxVel = 2*64;
			if (getCenterX() < targetX - width)
				moveRight();
			else if (getCenterX() > targetX + width)
				moveLeft();
			else
				targetReached = true;

			if (velX == 0 && !targetReached)
				jump();
		} else {
			defaultMaxVel = 1*64;
			if (velX == 0 && Math.floor(Math.random() * 400) == 0)
				movingRight = !movingRight;
			if (movingRight)
				moveRight();
			else
				moveLeft();
		}

		super.tick();
	}

	public void kill() {
		super.kill();
		world.getEnemies().remove(this);
	}

	public void render(Graphics2D g) {
		super.render(g);
		int offsetX = (int) Camera.getOffsetX();
		int offsetY = (int) Camera.getOffsetY();
		if (Main.getHUD().getDebugMode()) {
			g.drawLine((int) sightLine.getX1() + offsetX, (int) sightLine.getY1() + offsetY,
					(int) sightLine.getX2() + offsetX, (int) sightLine.getY2() + offsetY);
		}
		
		if (health < maxHealth) {
			g.setColor(Color.BLACK);
			g.fillRect(x + width/2 + offsetX - 24, y + height + 8 + offsetY, 48, 8);
			g.setColor(Color.RED);
			g.fillRect(x + width/2 + offsetX - 24, y + height + 8 + offsetY, (int) (health*4.8), 8);
		}
	}

}
