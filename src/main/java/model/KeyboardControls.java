package model;

public class KeyboardControls implements PlayerControls {

	private final KeySet keySet;
	private final Player player;

	public KeyboardControls(Player player, KeySet keySet) {
		this.player = player;
		this.keySet = keySet;
	}

	@Override
	public void processEvent(int key) {
		if (key == keySet.getUp() && player.getDirection() != Direction.DOWN) {
			player.setDirection(Direction.UP);
		} else if (key == keySet.getRight() && player.getDirection() != Direction.LEFT) {
			player.setDirection(Direction.RIGHT);
		} else if (key == keySet.getDown() && player.getDirection() != Direction.UP) {
			player.setDirection(Direction.DOWN);
		} else if (key == keySet.getLeft() && player.getDirection() != Direction.RIGHT) {
			player.setDirection(Direction.LEFT);
		}
	}
}
