package model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TronModel {

	public static final int MOVE_AMOUNT = 5;

	private final List<Player> players;
	private final KeyboardController keyboardController;
	private final CollisionListener collisionListener;
	private final int boardWidth;
	private final int boardHeight;

	public interface CollisionListener {
		void onCollision();
	}

	public TronModel(int boardWidth, int boardHeight, CollisionListener listener) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		collisionListener = listener;

		players = new ArrayList<>();
		keyboardController = new KeyboardController();
	}

	public void addPlayer(Player player, KeyboardControls keyboardControls) {
		players.add(player);
		keyboardController.addControls(new PlayerControls(player, keyboardControls));
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void processEvent(KeyEvent event) {
		keyboardController.processEvent(event);
	}

	public void movePlayers() {
		for (Player player : players) {
			movePlayer(player);
		}

		checkCollisions();

		addPlayerPaths(players);
	}

	private void addPlayerPaths(List<Player> players) {
		for (Player player : players) {
			player.getPath().add(player.getPosition());
		}
	}

	private void movePlayer(Player player) {
		Point position = new Point(player.getPosition());
		switch (player.getDirection()) {
			case UP:
				if (position.y > 0) {
					position.y -= MOVE_AMOUNT;
				} else {
					position.y = boardHeight;
				}
				break;
			case RIGHT:
				if (position.x < boardWidth) {
					position.x += MOVE_AMOUNT;
				} else {
					position.x = 0;
				}
				break;
			case DOWN:
				if (position.y < boardHeight) {
					position.y += MOVE_AMOUNT;
				} else {
					position.y = 0;
				}
				break;
			case LEFT:
				if (position.x > 0) {
					position.x -= MOVE_AMOUNT;
				} else {
					position.x = boardWidth;
				}
				break;
		}
		player.setPosition(position);
	}

	private void checkCollisions() {
		for (Player player1 : players) {
			for (Player player2 : players) {
				for (Point point : player2.getPath()) {
					if (point.equals(player1.getPosition())) {
						collisionListener.onCollision();
					}
				}
			}
		}
	}
}
