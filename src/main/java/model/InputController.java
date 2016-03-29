package model;

import java.util.ArrayList;
import java.util.List;

public class InputController {

	private final List<PlayerControls> playerControlsList;

	public InputController() {
		playerControlsList = new ArrayList<>();
	}

	public void addControls(PlayerControls playerControls) {
		playerControlsList.add(playerControls);
	}

	public void processEvent(int key) {
		for (PlayerControls playerControls : playerControlsList) {
			playerControls.processEvent(key);
		}
	}
}
