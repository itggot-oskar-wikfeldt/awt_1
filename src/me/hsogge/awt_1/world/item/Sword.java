package me.hsogge.awt_1.world.item;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.world.entity.Mob;

public class Sword extends Weapon {
	
	
	
	public Sword(double x, double y, Mob owner) {
		super(Assets.ITEM_SWORD, x, y, 32, 64, owner);
		
		attackSpeed = 2;
		// TODO Auto-generated constructor stub
	}


}
