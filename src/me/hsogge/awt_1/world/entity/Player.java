package me.hsogge.awt_1.world.entity;

import java.awt.event.KeyEvent;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.input.Keyboard;
import me.hsogge.awt_1.world.World;
import me.hsogge.awt_1.world.item.Hammer;
import me.hsogge.awt_1.world.item.Sword;

public class Player extends Mob {

	public Player(World world) {
		super(Assets.ENTITY_PLAYER, 0, -128, world);
		items[0] = new Sword(this);
		items[1] = new Hammer(this);
		defaultMaxVel = 4*64;
		handTexture.setTexture(Assets.TEXTURE_PLAYERHAND);

	}

	public void onMouseRelease(String button) {

	}

	public void onMouseScroll(int scroll) {
		int it = scroll > 0 ? 1 : -1;
		for (int i = 0; i < Math.abs(scroll); i++) {
			selectedItemIndex += it;
			if (selectedItemIndex > 2)
				selectedItemIndex = 0;
			if (selectedItemIndex < 0)
				selectedItemIndex = 2;
		}
	}

	public void onMousePress(String button) {
		if (button == "mouse2") {
			interactWithMouse();
		}
		if (button == "mouse1") {
			useItem();
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
		if (Keyboard.isKeyDown(KeyEvent.VK_SHIFT)) {
			sprint();
		}

		// selecting items
		if (Keyboard.isKeyDown(KeyEvent.VK_1)) {
			selectedItemIndex = 0;
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_2)) {
			selectedItemIndex = 1;
		}
		if (Keyboard.isKeyDown(KeyEvent.VK_3)) {
			selectedItemIndex = 2;
		}

		super.tick();
	}

}
