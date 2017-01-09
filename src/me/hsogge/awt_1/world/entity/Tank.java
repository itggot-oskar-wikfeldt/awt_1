package me.hsogge.awt_1.world.entity;

import java.awt.Color;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.world.World;
import me.hsogge.awt_1.world.item.Hammer;

public class Tank extends Enemy {

	public Tank(int x, int y, World world) {
		super(x, y, world);
		textures = Assets.ENTITY_PLAYER;
		health = maxHealth = 50;
		defaultMaxVel = 0.5*64;
		items[0] = new Hammer(this);
		debugColor = new Color(255, 0, 0);
		// TODO Auto-generated constructor stub
	}

}
