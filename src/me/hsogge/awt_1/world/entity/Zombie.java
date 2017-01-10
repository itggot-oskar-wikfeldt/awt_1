package me.hsogge.awt_1.world.entity;

import java.awt.Color;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.world.World;
import me.hsogge.awt_1.world.item.Sword;

public class Zombie extends Enemy {

	public Zombie(int x, int y, World world) {
		super(x, y, world);
		textures = Assets.ENTITY_ZOMBIE;
		health = maxHealth = 10;
		items[0] = new Sword(this);
		debugColor = Color.GREEN;
		// TODO Auto-generated constructor stub
	}

}
