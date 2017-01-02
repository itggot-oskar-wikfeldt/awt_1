package me.hsogge.awt_1.world.entity;

import java.awt.event.KeyEvent;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.input.Keyboard;
import me.hsogge.awt_1.world.World;
import me.hsogge.awt_1.world.item.Item;
import me.hsogge.awt_1.world.item.Sword;

public class Player extends Mob {
	
	Sword sword; 

	public Player(String name, World world) {
		super(name, Assets.ENTITY_PLAYER, 300, -400, world);
		sword = new Sword(realX, realY, this);
		items.add(sword);
		
	}
	
	public void onMouseRelease(String button) {
			
	}
	
	public void onMousePress(String button) {
		if (button == "mouse2") {
			interactWithMouse();
		}
		if (button == "mouse1") {
			for (Item item : items) {
				item.use();
			}
		}
	}


	public void tick() {
		

		if (Keyboard.isKeyDown(KeyEvent.VK_A)) {
			moveLeft();
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_D)) {
			moveRight();
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_SPACE)) {
			jump();
		}
		
		if (Keyboard.isKeyDown(KeyEvent.VK_CONTROL)) {
			duck();
		}
	
		
		super.tick();
	}

}
