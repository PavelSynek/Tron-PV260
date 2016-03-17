package model;

import java.awt.event.KeyEvent;

public class KeyboardControls {

	private final Player player;
	private final int up;
	private final int right;
	private final int down;
	private final int left;

	public KeyboardControls(Player player, int up, int right, int down, int left) {
		this.player = player;
		this.up = up;
		this.right = right;
		this.down = down;
		this.left = left;
	}

	public void processEvent(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (keyCode == up && player.getDirection() != Direction.DOWN) {
			player.setDirection(Direction.UP);
		} else if (keyCode == right && player.getDirection() != Direction.LEFT) {
			player.setDirection(Direction.RIGHT);
		} else if (keyCode == down && player.getDirection() != Direction.UP) {
			player.setDirection(Direction.DOWN);
		} else if (keyCode == left && player.getDirection() != Direction.RIGHT) {
			player.setDirection(Direction.LEFT);
		}
	}
}
