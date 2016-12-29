package hsogge.awt_1.world.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import hsogge.awt_1.Main;
import hsogge.awt_1.util.Util;
import hsogge.awt_1.world.GameObject;
import hsogge.awt_1.world.World;
import hsogge.awt_1.world.tile.Interactable;

public class Mob extends Entity {
	private Rectangle duckHitbox;
	private Rectangle pushHitbox;
	private Rectangle interactBox;
	private boolean directionRight = true;
	protected BufferedImage[] textures;
	protected double defaultMaxVel;
	private int duckDifference;
	private int duckHeight;

	public Mob(BufferedImage[] textures, double x, double y, World world) {
		super(textures[0], x, y, 60, 120, world);
		this.textures = textures;
		duckDifference = (int) (height-height*0.75);
		duckHeight = height - duckDifference;
		duckHitbox = new Rectangle(this.x, this.y - duckDifference, width, duckDifference);
		pushHitbox = new Rectangle(this.x, this.y + height, width, duckDifference);
		interactBox = new Rectangle(this.x+width, this.y, width, height);
		defaultMaxVel = maxVel;
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
	private boolean mustPush = false;
	private boolean duck = false;
	private boolean ducking = false;

	public void duck() {
		duck = true;
		if (!ducking) {
			texture = textures[1];
			height = duckHeight;
			if (onGround) {
				realY += duckDifference;
				y += duckDifference;
			}
			ducking = true;
		}
	}
	
	protected void interact() {
		List<GameObject> wantsToInteract = new ArrayList<>();
		for (Interactable interactable : world.getInteractables()) {
			if (interactBox.intersects(interactable.getHitbox())) {
				wantsToInteract.add(interactable);
			}
		}
		if (wantsToInteract.size() > 0) {
			((Interactable) Util.closest(wantsToInteract, this)).changeState(this);
		}
	}

	protected void jump() {
		if (onGround) {
			velY = -23 * 32;
		}
	}
	
	protected void updateBoxes() {
		duckHitbox.setLocation(x, y - duckDifference);
		pushHitbox.setLocation(x, y + height);
		interactBox.setBounds(directionRight ? x+width : x-width, y, width, height);
	}

	public void tick() {

		updateBoxes();
		mustDuck = false;
		mustPush = false;
		int pushY = y;
		if (ducking) {
			for (Rectangle tileHitbox : world.getTileHitboxes()) {
				if (duckHitbox.intersects(tileHitbox)) {
					mustDuck = true;
					break;
				}
			}
		}

		if (ducking && !mustDuck) {
			for (Rectangle tileHitbox : world.getTileHitboxes()) {
				if (pushHitbox.intersects(tileHitbox)) {
					mustPush = true;
					pushY = (int) tileHitbox.getMinY();
					break;
				}
			}
		}

		if (!duck && !mustDuck && ducking) {
			texture = textures[0];
			height = duckHeight+duckDifference;
			ducking = false;
			if (mustPush) {
				realY = y = pushY - height;
			}
		}
		duck = false;
		if (ducking) {
			maxVel = defaultMaxVel / 3;
		} else {
			maxVel = defaultMaxVel;
		}

		super.tick();

		if (!directionRight) {
			texX = (int) (x + width);
			texWidth = -width;
		}
	}

	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		if (Main.getHUD().getDebugMode()) {
			Util.drawRectWithOffset(interactBox, g);
		}
		
		super.render(g);
	}

}
