package me.hsogge.awt_1.world.item;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import me.hsogge.awt_1.Camera;
import me.hsogge.awt_1.world.GameObject;
import me.hsogge.awt_1.world.entity.Mob;

public class Item extends GameObject {

	Mob owner;
	protected boolean using = false;
	protected double angle = 45;
	protected double angleRadians = Math.toRadians(angle);
	protected double usageSpeed = 2;

	public Item(BufferedImage texture, double x, double y, Mob owner) {
		super(texture, x, y, 128, 128, owner.getWorld());
		this.owner = owner;
		// TODO Auto-generated constructor stub
	}

	public void use() {
		using = true;
	}

	public void tick() {

		angle = Math.abs(angle);
		x = owner.getX() + owner.getHandRelX();
		y = owner.getY() + owner.getHandRelY();

		
		if (using) {
			
			angle += usageSpeed;

			if (Math.abs(angle) > 90)
				using = false;
		} else
			angle = 45;
		
		angle *= owner.getDirectionRight() ? 1 : -1;
		angleRadians = Math.toRadians(angle);

	

		super.tick();

	}

	public void render(Graphics2D g) {
		// The required drawing location

		int drawLocationX = texX + (int) Camera.getOffsetX();
		int drawLocationY = texY + (int) Camera.getOffsetY();

		// Rotation information

		double rotationRequired = Math.toRadians(angle);
		double locationX = texture.getWidth() / 2;
		double locationY = texture.getHeight() / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations

		g.drawImage(op.filter(texture, null), drawLocationX - texture.getWidth() / 2,
				drawLocationY - texture.getHeight() / 2,
				/* owner.getDirectionRight() ? 64 : -64, 64, */ null);

	}

}
