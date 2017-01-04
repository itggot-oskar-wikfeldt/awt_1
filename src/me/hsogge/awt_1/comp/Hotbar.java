package me.hsogge.awt_1.comp;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import me.hsogge.awt_1.Assets;
import me.hsogge.awt_1.Main;
import me.hsogge.awt_1.world.World;
import me.hsogge.awt_1.world.entity.Player;
import me.hsogge.awt_1.world.item.Item;

public class Hotbar {
	private BufferedImage texture;
	private BufferedImage textureSelected;
	private int x, y;
	Player player;
	World world;
	
	public Hotbar(World world) {
		player = world.getPlayer();
		texture = Assets.HOTBAR;
		textureSelected = Assets.SELECTED;
		this.world = world;
		x = Main.getCanvas().getWidth()/2 - texture.getWidth()/2;
		y = Main.getCanvas().getHeight()-16 - texture.getHeight();
	}
	
	public void updateLocation() {
		x = Main.getCanvas().getWidth()/2 - texture.getWidth()/2;
		y = Main.getCanvas().getHeight()-16 - texture.getHeight();
	}
	
	public void render(Graphics2D g) {
		g.drawImage(texture, x, y, null);
		g.drawImage(textureSelected, x + player.getSelectedItem()*80, y, null);
		for (int i = 0; i < player.getItems().length; i++) {
			if (player.getItems()[i] instanceof Item) {
				g.drawImage(player.getItems()[i].getTexture(), x + i*80+16, y + 16+16, 64, 64, null);
			}
		}
	}
}
