package awt_1;

import java.awt.Canvas;

import awt_1.world.GameObject;

public class Camera {

	private static double offsetX = 0;
	private static double offsetY = 0;

	public static void tick(GameObject focus, Canvas canvas, double delta) {
		double focusX = (int) -(focus.getX()-canvas.getWidth()/2);
		double focusY = (int) -(focus.getY()-canvas.getHeight()/2);
		
		double velX = (focusX - offsetX)/20;
		double velY = (focusY - offsetY)/20;
		offsetX += velX;
		offsetY += velY;
		
		
		/*
		offsetX = focusX;
		offsetY = focusY;
		*/
	}

	public static double getOffsetX() {
		return offsetX;
	}

	public static double getOffsetY() {
		return offsetY;
	}
}
