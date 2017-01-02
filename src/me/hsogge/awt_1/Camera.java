package me.hsogge.awt_1;

import me.hsogge.awt_1.world.GameObject;

public class Camera {

	private static double offsetX = 0;
	private static double offsetY = 0;
	
	
	public static double getOffsetX() {
		return offsetX;
	}

	public static double getOffsetY() {
		return offsetY;
	}
	
	private static int canvasWidth = Main.getCanvas().getWidth();
	private static int canvasHeight = Main.getCanvas().getHeight();
	
	public static void updateCanvasDimensions() {
		int oldCanvasWidth = canvasWidth;
		int oldCanvasHeight = canvasHeight;
		canvasWidth = Main.getCanvas().getWidth();
		canvasHeight = Main.getCanvas().getHeight();
		offsetX += -oldCanvasWidth/2 + canvasWidth/2;
		offsetY += -oldCanvasHeight/2 + canvasHeight/2;
	}
	
	public static void tick(GameObject focus) {
		
		double focusX = -(focus.getRealCenterX()-canvasWidth/2);
		double focusY = -(focus.getRealCenterY()-canvasHeight/2);
		
		double velX = (focusX - offsetX)/20;
		double velY = (focusY - offsetY)/20;
		
		offsetX += velX;
		offsetY += velY;
		
		
		
		/*
		offsetX = focusX;
		offsetY = focusY;
		*/
	}


}
