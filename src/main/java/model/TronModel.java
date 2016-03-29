package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TronModel {

	private static final int MOVE_AMOUNT = 5;

	private final List<Player> players;

	private final KeyboardController keyboardController;
	private final int boardWidth;
	private final int boardHeight;

	public TronModel(int boardWidth, int boardHeight) {
		players = new ArrayList<>();
		keyboardController = new KeyboardController();
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
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
		movePlayers();
	}

	private void movePlayers() {
		for (Player player : players) {
			player.getDirection().moveIn(player, MOVE_AMOUNT, boardWidth, boardHeight);
		}
	}

}
