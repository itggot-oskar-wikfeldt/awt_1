package hsogge.awt_1.world.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import hsogge.awt_1.Main;
import hsogge.awt_1.util.Util;
import hsogge.awt_1.world.GameObject;
import hsogge.awt_1.world.World;

public class Entity extends GameObject {

	public Entity(BufferedImage texture, double x, double y, int width, int height, World world) {
		super(texture, x, y, width, height, world);

		hitboxX = new Rectangle(this.x, this.y, width, height);
		hitboxY = new Rectangle(this.x, this.y, width, height);
	}

	protected double velX = 0;
	protected double velY = 0;
	protected double maxVel = 6 * 64;
	protected int accelX = 0;
	protected int accelY = world.GRAVITY;
	protected int friction = 30 * 64;
	protected int airResistance = 5 * 64;
	protected int acceleration = friction + 20 * 64;
	protected int airAcceleration = airResistance + 5 * 64;

	protected void move() {
		if (Math.abs(velX) > maxVel) {
			accelX = 0;
		}
		velX += accelX * delta / 2;
		if (onGround) {
			velX = (float) Util.decrement(velX, friction * delta / 2);
		} else {
			velX = (float) Util.decrement(velX, airResistance * delta / 2);
		}

		velY += accelY * delta / 2;

		realX += velX * delta;
		realY += velY * delta;
	}

	protected boolean onGround = false;
	private Rectangle hitboxX;
	private Rectangle hitboxY;
	
	protected double delta = 1.0/Main.getTickrate();

	public void tick() {

		int oldX = x;
		int oldY = y;

		move();

		x = velX > 0 ? (int) Math.ceil(realX) : (int) Math.floor(realX);
		y = velY > 0 ? (int) Math.ceil(realY) : (int) Math.floor(realY);

		hitboxX.setBounds(x, oldY, width, height);
		hitboxY.setBounds(oldX, y, width, height);

		onGround = false;

		boolean yScanComplete = false;
		boolean xScanComplete = false;

		for (Rectangle tileHitbox : world.getTileHitboxes()) {

			if (hitboxX.intersects(tileHitbox) && !xScanComplete) {
				if (velX > 0) {
					realX = x = (int) (tileHitbox.getMinX() - width);
				} else {
					realX = x = (int) tileHitbox.getMaxX();
				}
				velX = 0;
				xScanComplete = true;
			}

			if (hitboxY.intersects(tileHitbox) && !yScanComplete) {
				if (velY > 0) {
					realY = y = (int) (tileHitbox.getMinY() - height);
					onGround = true;
				} else {
					realY = y = (int) tileHitbox.getMaxY();
				}
				velY = 0;
				yScanComplete = true;
			}

			if (yScanComplete && xScanComplete) {
				break;
			}

		}
		
		

		super.tick();
	}
	
	public void render(Graphics2D g) {
		super.render(g);
		g.setColor(Color.BLACK);
		if (Main.getHUD().getDebugMode()) {
			Util.drawRectWithOffset(hitboxX, g);
			Util.drawRectWithOffset(hitboxY, g);
		}
	}

}
