package me.hsogge.awt_1.world.entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.Camera;
import me.hsogge.awt_1.Main;
import me.hsogge.awt_1.world.World;

public class Enemy extends Mob {
	private Line2D.Double sightLine;
	private Mob target;
	private Point enemyPoint;
	private Point targetPoint;
	private int targetX;
	private int targetY;
	private int targetRange = 15*32;
	private double targetTime = 0;

	public Enemy(String name, int x, int y, World world) {
		super(name, Assets.ENTITY_ENEMY, x, y, world);
		// TODO Auto-generated constructor stub
		defaultMaxVel = 2 * 64;
		target = world.getPlayer();
		targetX = target.getCenterX();
		targetY = target.getCenterY();
		enemyPoint = new Point(getCenterX(), y);
		targetPoint = new Point(target.getCenterX(), target.getY());
		sightLine = new Line2D.Double(enemyPoint, targetPoint);
		world.getEnemies().add(this);
	}

	boolean movingRight = true;
	boolean targetReached = false;
	boolean canSeeTarget = false;

	public void tick() {
		enemyPoint.setLocation(getCenterX(), y+26);
		targetPoint.setLocation(target.getCenterX(), target.getY()+26);

		

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
			if (getCenterX() < targetX - 20)
				moveRight();
			else if (getCenterX() > targetX + 20)
				moveLeft();
			else
				targetReached = true;
	
			if (velX == 0 && !targetReached)
				jump();
		} else {
			if (velX == 0 && Math.floor(Math.random() * 400) == 0) movingRight = !movingRight;
			if (movingRight) moveRight();
			else moveLeft();
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
			g.drawLine((int) sightLine.getX1() + offsetX, (int) sightLine.getY1() + offsetY, (int) sightLine.getX2() + offsetX, (int) sightLine.getY2() + offsetY);
		}
	}

}
