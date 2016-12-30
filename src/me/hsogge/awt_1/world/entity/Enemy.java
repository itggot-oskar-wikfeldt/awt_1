package me.hsogge.awt_1.world.entity;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.world.World;

public class Enemy extends Mob{
	

	public Enemy(String name, int x, int y, World world) {
		super(name, Assets.ENTITY_ENEMY, x, y, world);
		// TODO Auto-generated constructor stub
		defaultMaxVel = 2 * 64;
	}
	
	boolean movingRight = true;
	
	public void tick() {
		
		if (velX == 0) {
			movingRight = !movingRight;
		}
		
		if (movingRight) {
			moveRight();
		} else {
			moveLeft();
		}
		
		//duck();
		//jump();
		super.tick();
	}

}
