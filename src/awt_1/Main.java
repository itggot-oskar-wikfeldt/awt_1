package awt_1;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

import awt_1.comp.HUD;
import awt_1.input.Keyboard;
import awt_1.input.Mouse;
import awt_1.world.World;

public class Main implements Runnable {

	final int WIDTH = 1280;
	final int HEIGHT = 720;

	JFrame frame;
	Canvas canvas;
	BufferStrategy bufferStrategy;
	
	World world;
	HUD hud;

	public Main() {
		frame = new JFrame("awt_1");

		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(new Dimension(WIDTH-10, HEIGHT-10));
		panel.setLayout(null);

		canvas = new Canvas();
		canvas.setBounds(0, 0, WIDTH, HEIGHT);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);

		canvas.addMouseListener(new Mouse());
		canvas.addKeyListener(new Keyboard());

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		canvas.requestFocus();

		world = new World(canvas);
		hud = new HUD(world);
	}

	long desiredFPS = 120;
	long desiredDeltaLoop = (1000 * 1000 * 1000) / desiredFPS;

	boolean running = true;

	public void run() {

		long beginLoopTime;
		long endLoopTime;
		long currentUpdateTime = System.nanoTime();
		long lastUpdateTime;
		long deltaLoop;

		while (running) {
			beginLoopTime = System.nanoTime();

			render();

			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
			update(((double) (currentUpdateTime - lastUpdateTime)) / (1000 * 1000 * 1000));

			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;

			if (deltaLoop > desiredDeltaLoop) {
				// Do nothing. We are already late.
			} else {
				try {
					Thread.sleep((long) ((desiredDeltaLoop - deltaLoop) / (1000 * 1000)));
				} catch (InterruptedException e) {
					// Do nothing
				}
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

	protected void update(double delta) {
		world.tick(delta);
		Camera.tick(world.getPlayer(), canvas, delta);
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