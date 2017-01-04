package me.hsogge.awt_1;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import me.hsogge.awt_1.comp.HUD;
import me.hsogge.awt_1.input.Keyboard;
import me.hsogge.awt_1.input.Mouse;
import me.hsogge.awt_1.world.World;

public class Main implements Runnable {
	
	final int WIDTH = 1280;
	final int HEIGHT = 720;
	int width = WIDTH;
	int height = HEIGHT;

	JFrame frame;
	static Canvas canvas;
	BufferStrategy bufferStrategy;
	Cursor cursor;
	Mouse mouse;

	World world;
	static HUD hud;

	public Main() {
		initMain();
		resetGame();
	}
	
	private boolean fullscreen = !true;
	
	private void initMain() {
		frame = new JFrame("awt_1");
		
		if (fullscreen) {
			frame.setUndecorated(true);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			width = Toolkit.getDefaultToolkit().getScreenSize().width;
			height = Toolkit.getDefaultToolkit().getScreenSize().height;
		} else {
			width = WIDTH;
			height = HEIGHT;
		}
		
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(width, height));
		panel.setLayout(null);
		

		canvas = new Canvas();
		canvas.setBounds(0, 0, width, height);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);
		
		mouse = new Mouse();

		canvas.addMouseListener(mouse);
		canvas.addMouseMotionListener(mouse);
		canvas.addMouseWheelListener(mouse);
		canvas.addKeyListener(new Keyboard());
		
		cursor = Toolkit.getDefaultToolkit().createCustomCursor(Assets.TEXTURE_TRANSPARENT, new Point(0, 0), "cursor");
		canvas.setCursor(cursor);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, height+30);
		frame.setResizable(false);
		frame.setLocationByPlatform(!fullscreen);
		if (fullscreen) frame.setLocation(0, 0);
		frame.setVisible(true);

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		canvas.requestFocus();
	}
	
	static final int TICKRATE = 120;
	static double timePassed = 0;
	static int fps = 0;
	static int tps = 0;
	
	public void toggleFullscreen() {
		fullscreen = !fullscreen;
		frame.dispose();
		initMain();
		
		mouse.setWorld(world);
		Camera.updateCanvasDimensions();
		hud.updateCanvasDimensions();
	}
	
	public static int getTickrate() {
		return TICKRATE;
	}
	
	public static int getFPS() {
		return fps;
	}
	public static int getTPS() {
		return tps;
	}
	public static double getTimePassed() {
		return timePassed;
	}
	public static HUD getHUD() {
		return hud;
	}
	public static Canvas getCanvas() {
		return canvas;
	}
	private void resetGame() {
		world = new World();
		mouse.setWorld(world);
		hud = new HUD(world);
	}
	
	
	public void run() {
		final double UPDATE_INTERVAL = 1000000000 / TICKRATE;

		long lastTime = System.nanoTime();
		long startTime = System.nanoTime();
		long timer = System.nanoTime();
		double delta = 0;
		int ticks = 0;
		int frames = 0;

		while (true) {
			long now = System.nanoTime();
			delta += now - lastTime;
			timePassed = (double) (System.nanoTime()-startTime)/1000000000;
			lastTime = now;

			while (delta >= UPDATE_INTERVAL) {
				delta -= UPDATE_INTERVAL;
				update();
				
				if (Keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
					System.exit(0);			
				}
				
				if (Keyboard.isKeyPressed(KeyEvent.VK_DELETE)) {
					resetGame();
				}
				
				if (Keyboard.isKeyPressed(KeyEvent.VK_ENTER) && Keyboard.isKeyDown(KeyEvent.VK_ALT)) {
					toggleFullscreen();
				}
				
				ticks++;
			}

			render();
			frames++;

			if (timer + 1000000000 <= System.nanoTime()) {
				timer += 1000000000;
				tps = ticks;
				fps = frames;
				ticks = 0;
				frames = 0;
			}
		}
	}

	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, width, height);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}
	
	private static double mouseX = 0;
	private static double mouseY = 0;

	protected void update() {
		world.tick();
		Camera.tick(world.getPlayer());
		hud.tick();
		mouseX = mouse.getX();
		mouseY = mouse.getY();
	}
	
	public static double getMouseX() {
		return mouseX;
	}
	
	public static double getMouseY() {
		return mouseY;
	}

	protected void render(Graphics2D g) {
		world.render(g);
		hud.render(g);
		g.drawImage(Assets.CURSOR, (int) mouseX, (int) mouseY, 32, 32, null);
	}
	
	private static Thread thread;

	public static void main(String[] args) {
		Main ex = new Main();
		thread = new Thread(ex);
		thread.start();
	}

}