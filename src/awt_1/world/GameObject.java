package awt_1.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import awt_1.Camera;

public class GameObject {

	protected BufferedImage texture;
	protected int x, y, texX, texY;
	protected double realX, realY;
	protected int width, height, texWidth, texHeight;
	protected World world;

	public GameObject(BufferedImage texture, double x, double y, int width, int height, World world) {
		this.texture = texture;
		realX = x;
		texX = this.x = (int) realX;
		realY = y;
		texY = this.y = (int) realY;
		
		this.width = texWidth = width;
		this.height = texHeight = height;
		this.world = world;

	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
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

	public void tick(Double delta) {
		texX = (int) x;
		texY = (int) y;
		texHeight = height;
		texWidth = width;

	}

	public void render(Graphics2D g) {
		g.drawImage(texture, (int) (texX + Camera.getOffsetX()), (int) (texY + Camera.getOffsetY()), texWidth, texHeight, null);
	}

}
