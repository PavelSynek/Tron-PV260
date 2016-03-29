package model;

import java.util.ArrayList;
import java.util.List;

public class TronModel {

	private static final int MOVE_AMOUNT = 5;

	private final List<Player> players;

	private final InputController inputController;
	private final int boardWidth;
	private final int boardHeight;

	public TronModel(int boardWidth, int boardHeight) {
		players = new ArrayList<>();
		inputController = new InputController();
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
	}

	public void addPlayer(Player player, KeySet keySet) {
		players.add(player);
		inputController.addControls(new KeyboardControls(player, keySet));
	}

	public void addPlayer(Player player, MouseSet mouseSet) {
		players.add(player);
		inputController.addControls(new MouseControls(player, mouseSet));
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void processEvent(int key) {
		inputController.processEvent(key);
	}

	public void movePlayers() {
		for (Player player : players) {
			player.getDirection().moveIn(player, MOVE_AMOUNT, boardWidth, boardHeight);
		}
	}

}
