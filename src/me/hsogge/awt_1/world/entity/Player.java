package me.hsogge.awt_1.world.entity;

import java.awt.event.KeyEvent;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.input.Keyboard;
import me.hsogge.awt_1.world.World;

public class Player extends Mob {

	public Player(String name, World world) {
		super(name, Assets.ENTITY_PLAYER, 300, -128-400, world);
	}


	public void tick() {
		accelX = 0;

		if (Keyboard.isKeyDown(KeyEvent.VK_A)) {
			moveLeft();
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_D)) {
			moveRight();
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			jump();
		}
		if (Keyboard.isKeyPressed(KeyEvent.VK_E)) {
			interact();
		}
		

		if (Keyboard.isKeyDown(KeyEvent.VK_CONTROL)) {
			duck();
		}
		

		super.tick();
	}

}
