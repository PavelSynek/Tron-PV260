package model;

public class MouseControls implements PlayerControls {

	private final MouseSet mouseSet;
	private final Player player;

	public MouseControls(Player player, MouseSet mouseSet) {
		this.player = player;
		this.mouseSet = mouseSet;
	}

	@Override
	public void processEvent(int key) {
		if (key == mouseSet.getLeft()) {
			player.setDirection(player.getDirection().toLeft());
		} else if (key == mouseSet.getRight()) {
			player.setDirection(player.getDirection().toRight());
		}
	}
}
