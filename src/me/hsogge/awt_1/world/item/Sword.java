package me.hsogge.awt_1.world.item;

import java.awt.geom.Line2D;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.world.entity.Mob;

public class Sword extends Item {
	
	private Line2D.Double line;
	
	public Sword(double x, double y, Mob owner) {
		super(Assets.ITEM_SWORD, x, y, 32, 64, owner);
		line = new Line2D.Double(x+width, y, x+width, y+height);
		// TODO Auto-generated constructor stub
	}

}
