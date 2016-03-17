package model;

import java.util.List;

public class PlayerPathUpdater {

	public void updatePaths(List<Player> players) {
		for (Player player : players) {
			player.getPath().add(player.getPosition());
		}
	}
}
