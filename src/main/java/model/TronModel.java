package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TronModel {

	private final List<Player> players;

	private final KeyboardController keyboardController;
	private final PlayerMover playerMover;
	private final PlayerPathUpdater playerPathUpdater;

	public TronModel(int boardWidth, int boardHeight) {
		players = new ArrayList<>();
		keyboardController = new KeyboardController();
		playerMover = new PlayerMover(boardWidth, boardHeight);
		playerPathUpdater = new PlayerPathUpdater();
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

	public void tick() {
		playerMover.movePlayers(players);

		playerPathUpdater.updatePaths(players);
	}

}
