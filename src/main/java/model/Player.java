package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player implements PathCollidable {

	private Point position;
	private Direction direction;
	private final List<Point> path;
	private final Color color;

	public Player(Point position, Direction direction, Color color) {
		this.position = position;
		this.direction = direction;
		this.color = color;
		this.path = new ArrayList<>();
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public List<Point> getPath() {
		return path;
	}

	public Color getColor() {
		return color;
	}
}
