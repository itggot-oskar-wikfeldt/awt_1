package me.hsogge.awt_1.world.item;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import me.hsogge.awt_1.Camera;
import me.hsogge.awt_1.world.GameObject;
import me.hsogge.awt_1.world.entity.Enemy;
import me.hsogge.awt_1.world.entity.Mob;

public class Item extends GameObject{
	
	Mob owner;
	private boolean attacking = false;
	private double angle = 45;
	

	public Item(BufferedImage texture, double x, double y, int width, int height, Mob owner) {
		super(texture, x, y, width, height, owner.getWorld());
		this.owner = owner;
		// TODO Auto-generated constructor stub
	}
	
	public void attack() {
		attacking = true;
	}
	
	public void tick() {
		if (owner.getDirectionRight()) {
			x = owner.getX() + owner.getHandRelX()-width/2;
			angle = 45;
		} else {
			x = owner.getX()+owner.getHandRelX() - width + width/2;
			angle = -45;
		}
		y = owner.getY()+owner.getHandRelY()-height;
		
		
		Mob mobToKill = owner;
		boolean kill = false;
		if (attacking) {
			for (Enemy enemy : owner.getWorld().getEnemies()) {
				if (getHitbox().intersects(enemy.getHitbox())) {
					kill = true;
					mobToKill = enemy;
				}
				
			}
		}
		
		if (kill) mobToKill.kill();
		
		
		
		attacking = false;
	
		
		super.tick();
		
	}
	
	public void render(Graphics2D g) {
		// The required drawing location
	
		int drawLocationX = texX + (int) Camera.getOffsetX();
		int drawLocationY = texY + (int) Camera.getOffsetY();

		// Rotation information

		double rotationRequired = Math.toRadians (angle);
		double locationX = texture.getWidth() / 2;
		double locationY = texture.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		g.drawImage(op.filter(texture, null), drawLocationX, drawLocationY, null);
		g.setColor(Color.MAGENTA);
		g.fillRect(drawLocationX, drawLocationY, 1, 1);
	}

}
