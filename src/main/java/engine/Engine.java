package engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

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
		long totalTime = System.currentTimeMillis();

		while (running) {
			long timePassed = System.currentTimeMillis() - totalTime;
			totalTime += timePassed;
			engineCallbacks.update(timePassed);

			draw();

			try {
				Thread.sleep(20);
			} catch (Exception ex) {
			}
		}
	}

	private void draw() {
		Graphics2D graphics = sm.getGraphics();
		drawBackground(graphics);
		engineCallbacks.draw(graphics);
		graphics.dispose();
		sm.update();
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
		void keyPressed(KeyEvent e);

		void update(long timePassed);

		void draw(Graphics2D g);

		void init();
	}

	public void keyTyped(KeyEvent e) {
		// TODO: implement all listeners in callbacks
	}

	public void keyPressed(KeyEvent e) {
		engineCallbacks.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}
}