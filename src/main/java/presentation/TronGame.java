package presentation;

import engine.Game;
import model.Direction;
import model.KeyboardControls;
import model.Player;
import model.PointsCollidable;
import model.TronModel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;

public class TronGame extends Game {

	private TronModel tronModel;

	public static final int LINE_SIZE = 10;

	public void init() {
		tronModel = new TronModel(getEnvironment().getScreenWidth(), getEnvironment().getScreenHeight());

		Player player1 = new Player(new Point(40, 40), Direction.RIGHT, Color.green);
		Player player2 = new Player(new Point(600, 440), Direction.LEFT, Color.red);
		Player player3 = new Player(new Point(400, 200), Direction.LEFT, Color.yellow);

		tronModel.addPlayer(player1, new KeyboardControls(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT));
		tronModel.addPlayer(player2, new KeyboardControls(KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A));
		tronModel.addPlayer(player3, new KeyboardControls(KeyEvent.VK_U, KeyEvent.VK_K, KeyEvent.VK_J, KeyEvent.VK_H));
	}

	@Override
	public void tick(long timePassed) {
		tronModel.tick();
	}

	@Override
	public void draw(Graphics2D graphics) {
		drawPlayers(graphics, tronModel.getPlayers());
	}

	@Override
	public void onCollision(PointsCollidable first, PointsCollidable second) {
		getEnvironment().exit();
	}

	@Override
	public List<? extends PointsCollidable> getCollidables() {
		return tronModel.getPlayers();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		tronModel.processEvent(e);
	}

	@Override
	public void mousePressed(MouseEvent event) {
		// TODO: handle mouse input
	}

	private void drawPlayers(Graphics2D graphics, List<Player> players) {
		for (Player player : players) {
			graphics.setColor(player.getColor());
			for (Point point : player.getPath()) {
				graphics.fillRect(point.x, point.y, LINE_SIZE, LINE_SIZE);
			}
		}
	}
}
