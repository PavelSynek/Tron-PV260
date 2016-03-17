package model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyboardController {

	private final List<PlayerControls> playerControlsList;

	public KeyboardController() {
		playerControlsList = new ArrayList<>();
	}

	public void addControls(PlayerControls playerControls) {
		playerControlsList.add(playerControls);
	}

	public void processEvent(KeyEvent keyEvent) {
		for (PlayerControls playerControls : playerControlsList) {
			playerControls.processEvent(keyEvent);
		}
	}
}
