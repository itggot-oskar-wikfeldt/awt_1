package me.hsogge.awt_1.world.item;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.world.entity.Mob;

public class Hammer extends Weapon {

	public Hammer(Mob owner) {
		super(Assets.ITEM_HAMMER, owner.getRealX(), owner.getRealY(), owner);
		
		usageSpeed = 1;
		damage = 6;
		// TODO Auto-generated constructor stub
	}

}
