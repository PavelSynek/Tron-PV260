package model;

import java.awt.*;
import java.util.List;

public interface PointsCollidable {

	List<Point> getPoints();

	boolean collidesWith(PointsCollidable other);
}
