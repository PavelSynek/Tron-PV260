package presentation;

import engine.Game;
import model.Direction;
import model.KeySet;
import model.MouseSet;
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

		tronModel.addPlayer(player1, new KeySet(KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT));
		tronModel.addPlayer(player2, new KeySet(KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A));
		tronModel.addPlayer(player3, new MouseSet(MouseEvent.BUTTON1, MouseEvent.BUTTON3));
	}

	@Override
	public void tick(long timePassed) {
		tronModel.movePlayers();
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
	public void keyPressed(KeyEvent event) {
		tronModel.processEvent(event.getKeyCode());
	}

	@Override
	public void mousePressed(MouseEvent event) {
		tronModel.processEvent(event.getButton());
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
