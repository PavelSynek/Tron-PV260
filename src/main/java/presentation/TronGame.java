package presentation;

import engine.Game;
import model.Direction;
import model.KeyboardController;
import model.KeyboardControls;
import model.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TronGame extends Game {

	private List<Player> players;
	private KeyboardController keyboardController;

	public static final int MOVE_AMOUNT = 5;
	public static final int LINE_SIZE = 10;

	public TronGame() {

		Player player1 = new Player(new Point(40, 40), Direction.RIGHT, Color.green);
		Player player2 = new Player(new Point(600, 440), Direction.LEFT, Color.red);
		Player player3 = new Player(new Point(400, 200), Direction.LEFT, Color.yellow);

		players = new ArrayList<Player>();
		players.add(player1);
		players.add(player2);
		players.add(player3);

		keyboardController = new KeyboardController();
		keyboardController.addControls(
				new KeyboardControls(player1, KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT));
		keyboardController.addControls(
				new KeyboardControls(player2, KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A));
		keyboardController.addControls(
				new KeyboardControls(player3, KeyEvent.VK_U, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_H));
	}

	public void draw(Graphics2D graphics) {
		movePlayers(players);

		if (isCollision(players)) {
			getEnvironment().exit();
		}

		addPlayerPaths(players);

		drawPlayers(graphics, players);
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

	private void movePlayers(List<Player> players) {
		for (Player player : players) {
			movePlayer(player);
		}
	}

	private void movePlayer(Player player) {
		Point position = new Point(player.getPosition());
		switch (player.getDirection()) {
			case UP:
				if (position.y > 0) {
					position.y -= MOVE_AMOUNT;
				} else {
					position.y = getEnvironment().getScreenHeight();
				}
				break;
			case RIGHT:
				if (position.x < getEnvironment().getScreenWidth()) {
					position.x += MOVE_AMOUNT;
				} else {
					position.x = 0;
				}
				break;
			case DOWN:
				if (position.y < getEnvironment().getScreenHeight()) {
					position.y += MOVE_AMOUNT;
				} else {
					position.y = 0;
				}
				break;
			case LEFT:
				if (position.x > 0) {
					position.x -= MOVE_AMOUNT;
				} else {
					position.x = getEnvironment().getScreenWidth();
				}
				break;
		}
		player.setPosition(position);
	}

	public void keyPressed(KeyEvent e) {
		keyboardController.processEvent(e);
	}

	public void update(long timePassed) {

	}
}
