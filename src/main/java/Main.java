import engine.Engine;
import presentation.TronGame;

public class Main {

	private Engine engine;
	private TronGame tronGame;

	public Main() {
		tronGame = new TronGame();
		engine = new Engine(tronGame);
		tronGame.setEnvironment(engine);
	}

	public static void main(String[] args) {
		new Main().run();
	}

	public void run() {
		engine.run();
	}
}
