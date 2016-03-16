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

	public Direction checkDirection(KeyEvent event) {
		int keyCode = event.getKeyCode();
		if (keyCode == up) {
			return Direction.UP;
		} else if (keyCode == right) {
			return Direction.RIGHT;
		} else if (keyCode == down) {
			return Direction.DOWN;
		} else if (keyCode == left) {
			return Direction.LEFT;
		} else {
			return null;
		}
	}
}
