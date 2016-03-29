package engine;

import model.PointsCollidable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class Engine implements KeyListener, MouseListener, Environment {

	private static final DisplayMode modes[] =
			{
					new DisplayMode(1680, 1050, 32, 0),
					new DisplayMode(800, 600, 32, 0),
					new DisplayMode(800, 600, 24, 0),
					new DisplayMode(800, 600, 16, 0),
					new DisplayMode(640, 480, 32, 0),
					new DisplayMode(640, 480, 24, 0),
					new DisplayMode(640, 480, 16, 0),
			};

	private final Callbacks engineCallbacks;
	private ScreenManager sm;
	private boolean running;
	private long totalTime;

	public Engine(Callbacks engineCallbacks) {
		this.engineCallbacks = engineCallbacks;
	}

	public void init() {
		sm = new ScreenManager();
		DisplayMode dm = sm.findFirstCompatibleMode(modes);
		sm.setFullScreen(dm);
		Window window = sm.getFullScreenWindow();
		window.setFont(new Font("Arial", Font.PLAIN, 20));
		window.setBackground(Color.WHITE);
		window.setForeground(Color.RED);
		window.setCursor(window.getToolkit().createCustomCursor(
				new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), "null"));
		window.addKeyListener(this);
		window.addMouseListener(this);

		engineCallbacks.init();

		running = true;
	}

	public void run() {
		try {
			init();
			gameLoop();
		} finally {
			sm.restoreScreen();
		}
	}

	public void gameLoop() {
		totalTime = System.currentTimeMillis();

		while (running) {
			tick();

			draw();

			checkCollisions();

			try {
				Thread.sleep(20);
			} catch (Exception ex) {
			}
		}
	}

	private void tick() {
		long timePassed = System.currentTimeMillis() - totalTime;
		totalTime += timePassed;
		engineCallbacks.tick(timePassed);
	}

	private void draw() {
		Graphics2D graphics = sm.getGraphics();
		drawBackground(graphics);
		engineCallbacks.draw(graphics);
		graphics.dispose();
		sm.update();
	}

	private void checkCollisions() {
		List<? extends PointsCollidable> collidables = engineCallbacks.getCollidables();
		for (PointsCollidable collidable1 : collidables) {
			for (PointsCollidable collidable2 : collidables) {
				if (collidable1.collidesWith(collidable2)) {
					engineCallbacks.onCollision(collidable1, collidable2);
				}
			}
		}
	}

	private void drawBackground(Graphics2D graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, sm.getWidth(), sm.getHeight());
	}

	public int getScreenWidth() {
		return sm.getWidth();
	}

	public int getScreenHeight() {
		return sm.getHeight();
	}

	public void exit() {
		running = false;
		System.exit(0);
	}

	interface Callbacks {
		void init();

		void keyPressed(KeyEvent event);

		void mousePressed(MouseEvent event);

		void tick(long timePassed);

		void draw(Graphics2D graphics);

		void onCollision(PointsCollidable first, PointsCollidable second);

		List<? extends PointsCollidable> getCollidables();
	}

	public void keyTyped(KeyEvent event) {

	}

	public void keyPressed(KeyEvent event) {
		engineCallbacks.keyPressed(event);
	}

	public void keyReleased(KeyEvent event) {

	}

	public void mouseClicked(MouseEvent event) {

	}

	public void mousePressed(MouseEvent event) {

	}

	public void mouseReleased(MouseEvent event) {

	}

	public void mouseEntered(MouseEvent event) {

	}

	public void mouseExited(MouseEvent event) {

	}
}
