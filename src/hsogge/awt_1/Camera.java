package hsogge.awt_1;

import hsogge.awt_1.world.GameObject;

public class Camera {

	private static double offsetX = 0;
	private static double offsetY = 0;
	private static int canvasWidth = Main.getCanvas().getWidth();
	private static int canvasHeight = Main.getCanvas().getHeight();
	
	public static void tick(GameObject focus) {
		
		double focusX = (int) -(focus.getX()-canvasWidth/2);
		double focusY = (int) -(focus.getY()-canvasHeight/2);
		
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
