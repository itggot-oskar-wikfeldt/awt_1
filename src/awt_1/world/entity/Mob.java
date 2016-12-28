package awt_1.world.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import awt_1.Assets;
import awt_1.world.World;

public class Mob extends Entity {
	private Rectangle duckHitbox;
	private boolean directionRight = true;

	public Mob(BufferedImage texture, double x, double y, int width, int height, World world) {
		super(texture, x, y, width, height, world);
		duckHitbox = new Rectangle((int) x - 32, (int) y, width, 32);
	}

	protected void moveLeft() {
		directionRight = false;
		if (onGround) {
			accelX = -acceleration;
		} else {
			accelX = -airAcceleration;
		}

	}

	protected void moveRight() {
		directionRight = true;
		if (onGround) {
			accelX = acceleration;
		} else {
			accelX = airAcceleration;
		}
	}

	private boolean mustDuck = false;
	private boolean duck = false;
	private boolean ducking = false;

	public void duck() {
		duck = true;
		if (!ducking) {
			texture = Assets.ENTITY_PLAYER_CROUCHING;
			height = 96;
			if (onGround) {
				realY += 32;
				y += 32;
			}
			ducking = true;
		}
	}

	protected void jump() {
		if (onGround) {
			velY = -23 * 32;
		}
	}

	public void tick(double delta) {

		duckHitbox.setLocation((int) x, (int) y - 32);
		mustDuck = false;
		if (ducking) {
			for (Rectangle tileHitbox : world.getTileHitboxes()) {
				if (duckHitbox.intersects(tileHitbox)) {
					mustDuck = true;
					break;
				}
			}
		}
		if (!duck && !mustDuck && ducking) {
			texture = Assets.ENTITY_PLAYER;
			height = 128;
			ducking = false;
			if (onGround) {
				System.out.println("hello");
				y -= 32;
				realY -= 32;
			}
		}
		duck = false;
		if (ducking) {
			maxVel = 2 * 64;
		} else {
			maxVel = 6 * 64;
		}

		super.tick(delta);

		if (!directionRight) {
			texX = (int) (x + width);
			texWidth = -width;
		}
	}

}
