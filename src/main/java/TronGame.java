import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;
import java.util.List;

public class TronGame extends Core implements KeyListener, MouseListener, MouseMotionListener {
	private Player player1;
	private Player player2;

	public static final int MOVE_AMOUNT = 5;
	public static final int LINE_SIZE = 10;

	public void init() {
		super.init();

		Window w = sm.getFullScreenWindow();
		w.addKeyListener(this);
		w.addMouseListener(this);
		w.addMouseMotionListener(this);

		player1 = new Player(new Point(40, 40), Direction.RIGHT, Color.green);
		player2 = new Player(new Point(600, 440), Direction.LEFT, Color.red);
	}

	public static void main(String[] args) {
		new TronGame().run();
	}

	public void draw(Graphics2D graphics) {
		movePlayer(player1);
		movePlayer(player2);

		if (isCollision(Arrays.asList(player1, player2))) {
			System.exit(0);
		}

		player1.getPath().add(player1.getPosition());
		player2.getPath().add(player2.getPosition());

		drawBackground(graphics);

		drawPlayers(graphics, Arrays.asList(player1, player2));
	}

	public void drawBackground(Graphics2D graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, sm.getWidth(), sm.getHeight());
	}

	private void drawPlayers(Graphics2D graphics, List<Player> players) {
		for (Player player : players) {
			graphics.setColor(player.getColor());
			System.out.println(player.getPath());
			for (Point point : player.getPath()) {
				graphics.fillRect(point.x, point.y, LINE_SIZE, LINE_SIZE);
			}
		}
	}

	private boolean isCollision(List<Player> players) {
		for (Player player1 : players) {
			for (Player player2 : players) {
				for (Point point : player2.getPath()) {
					if (point.equals(player1.getPosition())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private void movePlayer(Player player) {
		Point position = new Point(player.getPosition());
		switch (player.getDirection()) {
			case UP:
				if (position.y > 0) {
					position.y -= MOVE_AMOUNT;
				} else {
					position.y = sm.getHeight();
				}
				break;
			case RIGHT:
				if (position.x < sm.getWidth()) {
					position.x += MOVE_AMOUNT;
				} else {
					position.x = 0;
				}
				break;
			case DOWN:
				if (position.y < sm.getHeight()) {
					position.y += MOVE_AMOUNT;
				} else {
					position.y = 0;
				}
				break;
			case LEFT:
				if (position.x > 0) {
					position.x -= MOVE_AMOUNT;
				} else {
					position.x = sm.getWidth();
				}
				break;
		}
		player.setPosition(position);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			if (player1.getDirection() != Direction.DOWN) {
				player1.setDirection(Direction.UP);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			if (player1.getDirection() != Direction.UP) {
				player1.setDirection(Direction.DOWN);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (player1.getDirection() != Direction.LEFT) {
				player1.setDirection(Direction.RIGHT);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (player1.getDirection() != Direction.RIGHT) {
				player1.setDirection(Direction.LEFT);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			if (player2.getDirection() != Direction.DOWN) {
				player2.setDirection(Direction.UP);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			if (player2.getDirection() != Direction.UP) {
				player2.setDirection(Direction.DOWN);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			if (player2.getDirection() != Direction.LEFT) {
				player2.setDirection(Direction.RIGHT);
			}
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			if (player2.getDirection() != Direction.RIGHT) {
				player2.setDirection(Direction.LEFT);
			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent arg0) {

	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {

	}

	public void mouseMoved(MouseEvent e) {

	}
}
