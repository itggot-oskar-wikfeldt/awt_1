package me.hsogge.awt_1.util;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.List;

import me.hsogge.awt_1.Camera;
import me.hsogge.awt_1.world.GameObject;

public class Util {
	public static double decrement(double value1, double value2) {
		if (value1 > 0) {
			value1 -= value2;
			if (value1 < 0) {
				return 0;
			} else {
				return value1;
			}
		} else {
			value1 += value2;
			if (value1 > 0) {
				return 0;
			} else {
				return value1;
			}
		}
	}
	
	public static void drawRectWithOffset(Rectangle r, Graphics2D g) {
		g.drawRect(r.x + (int) Camera.getOffsetX(), r.y + (int) Camera.getOffsetY(), r.width, r.height);
	}
	
	public static GameObject closest(List<GameObject> objects, GameObject targetObject) {
		GameObject closest = objects.get(0);
		int targetCenter = targetObject.getCenterX();
		int smallestDelta = (int) Math.abs(closest.getCenterX()-targetCenter);
		for (GameObject object : objects) {
			if (object == objects.get(0)) {
				continue;
			}
			int delta = (int) Math.abs(object.getCenterX()-targetCenter);
			if (delta < smallestDelta) {
				smallestDelta = delta;
				closest = object;
			}
		}
		return closest;
	}
	
	public static int distance(GameObject obj1, GameObject obj2) {
		return (int) Math.sqrt(Math.pow(obj1.getCenterX()-obj2.getCenterX(), 2) + Math.pow(obj1.getCenterY()-obj2.getCenterY(), 2));
	}
	
	public static BufferedImage rotateImage(BufferedImage src, double degrees) {
	    double radians = Math.toRadians(degrees);

	    int srcWidth = src.getWidth();
	    int srcHeight = src.getHeight();

	    double sin = Math.abs(Math.sin(radians));
	    double cos = Math.abs(Math.cos(radians));
	    int newWidth = (int) Math.floor(srcWidth * cos + srcHeight * sin);
	    int newHeight = (int) Math.floor(srcHeight * cos + srcWidth * sin);

	    BufferedImage result = new BufferedImage(newWidth, newHeight,
	        src.getType());
	    Graphics2D g = result.createGraphics();
	    g.translate((newWidth - srcWidth) / 2, (newHeight - srcHeight) / 2);
	    g.rotate(radians, srcWidth / 2, srcHeight / 2);
	    g.drawRenderedImage(src, null);

	    return result;
	}
}
