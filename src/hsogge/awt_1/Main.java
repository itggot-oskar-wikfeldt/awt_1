package hsogge.awt_1;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import hsogge.awt_1.comp.HUD;
import hsogge.awt_1.input.Keyboard;
import hsogge.awt_1.input.Mouse;
import hsogge.awt_1.world.World;

public class Main implements Runnable {

	final int WIDTH = 1280;
	final int HEIGHT = 720;

	JFrame frame;
	static Canvas canvas;
	BufferStrategy bufferStrategy;

	World world;
	static HUD hud;

	public Main() {
		frame = new JFrame("awt_1");

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		panel.setLayout(null);

		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);

		canvas.addMouseListener(new Mouse());
		canvas.addKeyListener(new Keyboard());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		canvas.requestFocus();

		world = new World(canvas);
		hud = new HUD(world);
	}
	
	static final int TICKRATE = 120;
	static double timePassed = 0;
	static int fps = 0;
	static int tps = 0;
	
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
	public static Canvas getCanvas() {
		return canvas;
	}
	public static HUD getHUD() {
		return hud;
	}
	
	
	public void run() {
		final double UPDATE_INTERVAL = 1000000000 / TICKRATE;

		long lastTime = System.nanoTime();
		long timer = System.nanoTime();
		double delta = 0;
		int ticks = 0;
		int frames = 0;

		while (true) {
			long now = System.nanoTime();
			timePassed += now/1000000000;
			delta += now - lastTime;
			lastTime = now;

			while (delta >= UPDATE_INTERVAL) {
				delta -= UPDATE_INTERVAL;
				update();
				/*
				if ((Keyboard.isKeyDown(KeyEvent.VK_ALT) && Keyboard.isKeyPressed(KeyEvent.VK_ENTER))
						|| Settings.fullscreenChangeQueued) {
					Settings.fullscreenChangeQueued = false;
					if (window.isFullscreen()) {
						createNewWindow(false);
					} else {
						createNewWindow(true);
					}
				}
				*/
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
		g.clearRect(0, 0, WIDTH, HEIGHT);
		render(g);
		g.dispose();
		bufferStrategy.show();
	}

	protected void update() {
		world.tick();
		Camera.tick(world.getPlayer());
		hud.tick();
	}

	protected void render(Graphics2D g) {
		world.render(g);
		hud.render(g);
	}

	public static void main(String[] args) {
		Main ex = new Main();
		new Thread(ex).start();
	}

}