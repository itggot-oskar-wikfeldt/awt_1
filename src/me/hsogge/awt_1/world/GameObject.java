package me.hsogge.awt_1.world;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.hsogge.awt_1.Camera;

public class GameObject {

	protected BufferedImage texture;
	protected int x, y, texX, texY;
	protected double realX, realY;
	protected int width, height, texWidth, texHeight;
	protected World world;
	private Rectangle hitbox;
	protected int renderOrder = 0;

	public GameObject(BufferedImage texture, double x, double y, int width, int height, World world) {
		this.texture = texture;
		realX = x;
		texX = this.x = (int) realX;
		realY = y;
		texY = this.y = (int) realY;
		this.width = texWidth = width;
		this.height = texHeight = height;
		this.world = world;
		hitbox = new Rectangle(this.x, this.y, width, height);

	}

	public Rectangle getHitbox() {
		hitbox.setBounds(x, y, width, height);
		return hitbox;
	}
	
	public World getWorld() {
		return world;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setTexX(int texX) {
		this.texX = texX;
	}
	
	public void setTexY(int texY) {
		this.texY = texY;
	}
	
	public void setTexWidth(int texWidth) {
		this.texWidth = texWidth;
	}
	
	public void setTexHeight(int texHeight) {
		this.texHeight = texHeight;
	}
	
	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}
	
	public int getRenderOrder() {
		return renderOrder;
	}

	public int getCenterX() {
		return x + width / 2;
	}

	public int getCenterY() {
		return y + height / 2;
	}

	public double getRealCenterX() {
		return realX + width / 2;
	}

	public double getRealCenterY() {
		return realY + height / 2;
	}

	public double getRealX() {
		return realX;
	}

	public double getRealY() {
		return realY;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
	
	public BufferedImage getTexture() {
		return texture;
	}


	public void tick() {
		texX = x;
		texY = y;
		texHeight = height;
		texWidth = width;

	}


	public void render(Graphics2D g) {
		
		g.drawImage(texture, (int) (texX + Camera.getOffsetX()), (int) (texY + Camera.getOffsetY()), texWidth, texHeight, null);
	}

}
