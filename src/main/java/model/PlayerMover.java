package model;

import java.awt.*;
import java.util.List;

public class PlayerMover {

	private static final int MOVE_AMOUNT = 5;

	private final int boardWidth;
	private final int boardHeight;

	public PlayerMover(int boardWidth, int boardHeight) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
	}

	public void movePlayers(List<Player> players) {
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
}
