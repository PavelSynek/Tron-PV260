package presentation;

import engine.Game;
import model.Direction;
import model.KeyboardControls;
import model.Player;
import model.TronModel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class TronGame extends Game {

	private TronModel tronModel;

	public static final int LINE_SIZE = 10;

	public void init() {
		tronModel = new TronModel(getEnvironment().getScreenWidth(), getEnvironment().getScreenHeight());

		Player player1 = new Player(new Point(40, 40), Direction.RIGHT, Color.green);
		Player player2 = new Player(new Point(600, 440), Direction.LEFT, Color.red);
		Player player3 = new Player(new Point(400, 200), Direction.LEFT, Color.yellow);

		tronModel.addPlayer(player1, new KeyboardControls(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT));
		tronModel.addPlayer(player2, new KeyboardControls(KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A));
		tronModel.addPlayer(player3, new KeyboardControls(KeyEvent.VK_U, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_H));

	}

	public void draw(Graphics2D graphics) {
		tronModel.movePlayers();

		if (isCollision(tronModel.getPlayers())) {
			getEnvironment().exit();
		}

		addPlayerPaths(tronModel.getPlayers());

		drawPlayers(graphics, tronModel.getPlayers());
	}

	private void addPlayerPaths(List<Player> players) {
		for (Player player : players) {
			player.getPath().add(player.getPosition());
		}
	}

	private void drawPlayers(Graphics2D graphics, List<Player> players) {
		for (Player player : players) {
			graphics.setColor(player.getColor());
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

	public void keyPressed(KeyEvent e) {
		tronModel.processEvent(e);
	}

	public void update(long timePassed) {

	}
}
