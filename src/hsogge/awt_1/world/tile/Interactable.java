package hsogge.awt_1.world.tile;

import java.awt.image.BufferedImage;

import hsogge.awt_1.world.GameObject;
import hsogge.awt_1.world.World;
import hsogge.awt_1.world.entity.Mob;

public class Interactable extends GameObject{
	protected int numOfStates;
	protected int currentState = 0;
	public Interactable(BufferedImage texture, double x, double y, int width, int height, World world) {
		super(texture, x, y, width, height, world);
		world.getInteractables().add(this);
		world.getTileHitboxes().add(getHitbox());
	}
	
	public void changeState(Mob mob) {
		currentState += 1;
		if (currentState == numOfStates) {
			currentState = 0;
		}
	}
	
}
