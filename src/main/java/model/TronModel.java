package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TronModel {

	private final List<Player> players;

	private final KeyboardController keyboardController;
	private final CollisionDetector collisionDetector;
	private final CollisionListener collisionListener;
	private final PlayerMover playerMover;
	private final PlayerPathUpdater playerPathUpdater;

	public interface CollisionListener {
		void onCollision();
	}

	public TronModel(int boardWidth, int boardHeight, CollisionListener listener) {
		collisionListener = listener;

		players = new ArrayList<>();
		keyboardController = new KeyboardController();
		collisionDetector = new CollisionDetector();
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

		checkCollisions();

		playerPathUpdater.updatePaths(players);
	}

	private void checkCollisions() {
		if (collisionDetector.isCollision(players)) {
			collisionListener.onCollision();
		}
	}

}
