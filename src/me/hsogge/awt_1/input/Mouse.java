package me.hsogge.awt_1.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import me.hsogge.awt_1.world.World;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {
	
	private double x, y;
	private World world;
	
	public Mouse() {
		// TODO Auto-generated constructor stub
	}

	public void setWorld(World world) {
		this.world = world;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		String buttonString = "";
		int button = e.getButton();
		if (button == MouseEvent.BUTTON1) {
			buttonString = "mouse1";
		} else if (button == MouseEvent.BUTTON2) {
			buttonString = "mouse3";
		} else if (button == MouseEvent.BUTTON3) {
			buttonString = "mouse2";
		}
		world.getPlayer().onMousePress(buttonString);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		String buttonString = "";
		int button = e.getButton();
		if (button == MouseEvent.BUTTON1) {
			buttonString = "mouse1";
		} else if (button == MouseEvent.BUTTON2) {
			buttonString = "mouse3";
		} else if (button == MouseEvent.BUTTON3) {
			buttonString = "mouse2";
		}
		world.getPlayer().onMouseRelease(buttonString);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		world.getPlayer().onMouseScroll(e.getWheelRotation());
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
}
