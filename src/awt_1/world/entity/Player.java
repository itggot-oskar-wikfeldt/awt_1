package awt_1.world.entity;

import java.awt.event.KeyEvent;

import awt_1.Assets;
import awt_1.input.Keyboard;
import awt_1.world.World;

public class Player extends Mob {

	public Player(World world) {
		super(Assets.ENTITY_PLAYER, 320.5, -128-400, 64, 128, world);
	}


	public void tick(double delta) {
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
		

		if (Keyboard.isKeyDown(KeyEvent.VK_CONTROL)) {
			duck();
		}

		super.tick(delta);
		if (Keyboard.isKeyDown(KeyEvent.VK_F)) {
			y+=2;
		}
	}

}
