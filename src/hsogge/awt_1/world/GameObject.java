package hsogge.awt_1.world;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import hsogge.awt_1.Camera;

public class GameObject {

	protected BufferedImage texture;
	protected int x, y, texX, texY;
	protected double realX, realY;
	protected int width, height, texWidth, texHeight;
	protected World world;
	private Rectangle hitbox;

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

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public int getCenterX() {
		return x+width/2;
	}
	
	public int getCenterY() {
		return y+height/2;
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

	public void tick() {
		texX = (int) x;
		texY = (int) y;
		texHeight = height;
		texWidth = width;

	}

	public void render(Graphics2D g) {
		g.drawImage(texture, (int) (texX + Camera.getOffsetX()), (int) (texY + Camera.getOffsetY()), texWidth, texHeight, null);
	}

}
