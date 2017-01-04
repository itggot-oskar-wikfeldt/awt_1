package me.hsogge.awt_1.world.item;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.world.entity.Mob;

public class Sword extends Weapon {

	public Sword(Mob owner) {
		super(Assets.ITEM_SWORD, owner);

		usageSpeed = 4;
		damage = 3;
		// TODO Auto-generated constructor stub
	}

}
