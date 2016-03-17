import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class KeyboardController {

	private final List<KeyboardControls> keyboards;

	public KeyboardController() {
		keyboards = new ArrayList<KeyboardControls>();
	}

	public void addControls(KeyboardControls controls) {
		keyboards.add(controls);
	}

	public void processEvent(KeyEvent keyEvent) {
		for (KeyboardControls keyboardControls : keyboards) {
			keyboardControls.processEvent(keyEvent);
		}
	}
}
