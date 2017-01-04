package me.hsogge.awt_1.world.tile;

import java.awt.Rectangle;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.world.World;
import me.hsogge.awt_1.world.entity.Mob;

public class Door extends Interactable {
	private boolean open = true;
	private int originalX;
	private Rectangle closedHitbox = new Rectangle(0,0,0,0);
	
	public Door(int x, int y, World world) {
		super(Assets.TILE_DOOR[0], x*32+8, y*32, 16, 128, world);
		numOfStates = 2;
		originalX = this.x;
		closedHitbox.setBounds(this.x, this.y, width, height);
		close();

		
		
		// TODO Auto-generated constructor stub
	}
	
	public void open(Mob mob) {
		if (!open) {
			open = true;
			renderOrder = 1;
			world.getTileHitboxes().remove(getHitbox());
			if (mob.getCenterX() < getCenterX()) {
				texWidth = width = 64;
				texture = Assets.TILE_DOOR[2];
				texX = x;
			} else {
				texWidth = width = 64;
				texture = Assets.TILE_DOOR[1];
				texX = x = originalX - (64-16);
			}
		}
		
	}
	
	public void close() {
		if (open) {
			for (Mob mob : world.getMobs()) {
				if (closedHitbox.intersects(mob.getHitbox())) {
					return;			
				}
			}
			world.getTileHitboxes().remove(getHitbox());
			open = false;
			texture = Assets.TILE_DOOR[0];
			texWidth = 24;
			width = 16;
			x = originalX;
			texX = x - 4;	
			world.getTileHitboxes().add(getHitbox());
			renderOrder = 0;
		}
	}
	
	public void changeState(Mob mob) {
		super.changeState(mob);
		if (currentState == 0) {
			close();
		} else if (currentState == 1) {
			open(mob);
		}
	}
	
	public void changeState(Mob mob, String state) {
		if (state == "open") {
			open(mob);
		} else {
			close();
		}
	}
	
	public boolean getOpen() {
		return open;
	}
	


}
