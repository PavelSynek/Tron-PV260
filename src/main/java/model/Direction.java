package model;

import java.awt.*;

public enum Direction {
	UP() {
		@Override
		Point calculateMovement(Point position, int speed, int boardWidth, int boardHeight) {
			Point point = new Point(position);
			if (point.y > 0) {
				point.y -= speed;
			} else {
				point.y = boardHeight;
			}
			return point;
		}
	},
	RIGHT() {
		@Override
		Point calculateMovement(Point position, int speed, int boardWidth, int boardHeight) {
			Point point = new Point(position);
			if (point.x < boardWidth) {
				point.x += speed;
			} else {
				point.x = 0;
			}
			return point;
		}
	}, DOWN() {
		@Override
		Point calculateMovement(Point position, int speed, int boardWidth, int boardHeight) {
			Point point = new Point(position);
			if (point.y < boardHeight) {
				point.y += speed;
			} else {
				point.y = 0;
			}
			return point;
		}
	}, LEFT() {
		@Override
		Point calculateMovement(Point position, int speed, int boardWidth, int boardHeight) {
			Point point = new Point(position);
			if (point.x > 0) {
				point.x -= speed;
			} else {
				point.x = boardWidth;
			}
			return point;
		}
	};

	abstract Point calculateMovement(Point position, int speed, int boardWidth, int boardHeight);

	public void moveIn(Player player, int speed, int boardWidth, int boardHeight) {
		Point position = calculateMovement(player.getPosition(), speed, boardWidth, boardHeight);
		player.setPosition(position);
		player.getPoints().add(position);
	}
}
