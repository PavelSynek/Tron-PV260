package model;

import java.awt.event.KeyEvent;

public class PlayerControls {

	private final KeyboardControls keyboardControls;
	private final Player player;

	public PlayerControls(Player player, KeyboardControls keyboardControls) {
		this.player = player;
		this.keyboardControls = keyboardControls;
	}

	public void processEvent(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (keyCode == keyboardControls.getUp() && player.getDirection() != Direction.DOWN) {
			player.setDirection(Direction.UP);
		} else if (keyCode == keyboardControls.getRight() && player.getDirection() != Direction.LEFT) {
			player.setDirection(Direction.RIGHT);
		} else if (keyCode == keyboardControls.getDown() && player.getDirection() != Direction.UP) {
			player.setDirection(Direction.DOWN);
		} else if (keyCode == keyboardControls.getLeft() && player.getDirection() != Direction.RIGHT) {
			player.setDirection(Direction.LEFT);
		}
	}
}
