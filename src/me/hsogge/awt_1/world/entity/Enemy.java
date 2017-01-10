package me.hsogge.awt_1.world.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.Camera;
import me.hsogge.awt_1.Main;
import me.hsogge.awt_1.world.World;

public class Enemy extends Mob {

	private Line2D.Double sightLine;
	protected Mob target;
	private Point enemyPoint;
	private Point targetPoint;
	private int targetCenterX;
	private int targetCenterY;
	List<Point> targetPoints = new ArrayList<>();
	protected int targetRange = 15 * 32;
	private double targetTime = -5;
	private Rectangle jumpBox1;
	private Rectangle jumpBox2;

	public Enemy(int x, int y, World world) {
		super(Assets.ENTITY_ZOMBIE, x, y, world);
		// TODO Auto-generated constructor stub
		defaultMaxVel = 1 * 64;
		target = world.getPlayer();
		targetCenterX = target.getCenterX();
		targetCenterY = target.getCenterY();

		for (int i = 0; i < Main.getTickrate() * 0.2; i++) {
			targetPoints.add(new Point(getCenterX(), getCenterY()));
		}

		jumpBox1 = new Rectangle(x + width, y + height - 3 * 32, width / 2, 3 * 32);
		jumpBox2 = new Rectangle(x + width, y, width / 2, height - 3 * 32);

		enemyPoint = new Point(getCenterX(), y);
		targetPoint = new Point(target.getCenterX(), target.getY());
		sightLine = new Line2D.Double(enemyPoint, targetPoint);
		world.getEnemies().add(this);
		handTexture.setTexture(Assets.TEXTURE_ZOMBIEHAND);
	}

	public void setTarget(Mob target) {
		this.target = target;
	}

	protected boolean movingRight = true;
	protected boolean targetReached = false;
	protected boolean canSeeTarget = false;

	public void tick() {
		if (getDirectionRight()) {
			jumpBox1.setBounds(x + width, y + height - 3 * 32, 8, 3 * 32);
			jumpBox2.setBounds(x + width, y, 8, height - 3 * 32);
		} else {
			jumpBox1.setBounds(x - 8, y + height - 3 * 32, 8, 3 * 32);
			jumpBox2.setBounds(x - 8, y, 8, height - 3 * 32);
		}

		enemyPoint.setLocation(getCenterX(), y + 26);
		targetPoint.setLocation(target.getCenterX(), target.getY() + 26);

		boolean inRange = (enemyPoint.distance(targetPoint) < targetRange);
		if (inRange) {
			sightLine.setLine(enemyPoint, targetPoint);
			canSeeTarget = true;
		}

		boolean jumpBox1Collides = false;
		boolean jumpBox2Collides = false;

		for (Rectangle tileHitbox : world.getTileHitboxes()) {
			if (inRange)
				if (sightLine.intersects(tileHitbox))
					canSeeTarget = false;
			if (jumpBox1.intersects(tileHitbox))
				jumpBox1Collides = true;
			if (jumpBox2.intersects(tileHitbox))
				jumpBox2Collides = true;
		}

		if ((velX != 0 && jumpBox1Collides && !jumpBox2Collides)
				|| (getCenterY() > targetCenterY && Math.abs(getCenterX() - targetCenterX) < width))
			jump();

		if (canSeeTarget) {
			targetTime = Main.getTimePassed();
			targetPoints.add(new Point(target.getCenterX(), target.getCenterY()));
			targetCenterX = targetPoints.get(0).x;
			targetCenterY = targetPoints.get(0).y;
			targetPoints.remove(0);

		}

		targetReached = false;

		if (Main.getTimePassed() - targetTime < 5) {
			sprint();
			if (getCenterX() < targetCenterX - width)
				moveRight();
			else if (getCenterX() > targetCenterX + width)
				moveLeft();
			else
				targetReached = true;

		} else {
			if ((velX == 0 && Math.floor(Math.random() * 400) == 0) && onGround)
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
			g.setColor(debugColor);
			g.drawLine((int) sightLine.getX1() + offsetX, (int) sightLine.getY1() + offsetY,
					(int) sightLine.getX2() + offsetX, (int) sightLine.getY2() + offsetY);
			g.drawRect(targetCenterX - 4 + (int) offsetX, targetCenterY - 4 + (int) offsetY, 8, 8);
		}

		if (health < maxHealth) {
			g.setColor(Color.BLACK);
			g.fillRect(x + width / 2 + offsetX - 24, y + height + 8 + offsetY, 48, 8);
			g.setColor(Color.RED);
			g.fillRect(x + width / 2 + offsetX - 24, y + height + 8 + offsetY, (int) (health * 48 / maxHealth), 8);
		}
	}

}
