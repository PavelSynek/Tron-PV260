package model;

public class KeyboardControls {

	private final int up;
	private final int right;
	private final int down;
	private final int left;

	public KeyboardControls(int up, int right, int down, int left) {
		this.up = up;
		this.right = right;
		this.down = down;
		this.left = left;
	}

	public int getUp() {
		return up;
	}

	public int getRight() {
		return right;
	}

	public int getDown() {
		return down;
	}

	public int getLeft() {
		return left;
	}
}
