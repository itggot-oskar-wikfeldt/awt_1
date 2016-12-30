package me.hsogge.awt_1.util;

import java.awt.Graphics2D;
import java.awt.Rectangle;
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
}
