package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player implements PointsCollidable {

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

	@Override
	public List<Point> getPoints() {
		return path;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public boolean collidesWith(PointsCollidable other) {
		if (this.equals(other)) {
			return collidesWithSelf();
		}
		return other.getPoints().contains(position);
	}

	private boolean collidesWithSelf() {
		List<Point> points = new ArrayList<>(path);
		points.remove(position);
		return points.contains(position);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Player player = (Player) o;

		if (!position.equals(player.position)) return false;
		if (direction != player.direction) return false;
		if (!path.equals(player.path)) return false;
		return color.equals(player.color);

	}

	@Override
	public int hashCode() {
		int result = position.hashCode();
		result = 31 * result + direction.hashCode();
		result = 31 * result + path.hashCode();
		result = 31 * result + color.hashCode();
		return result;
	}
}
