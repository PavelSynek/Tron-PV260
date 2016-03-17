package model;

import java.awt.*;
import java.util.List;

public class CollisionDetector {

	public boolean isCollision(List<? extends PathCollidable> collidables) {
		for (PathCollidable collidable1 : collidables) {
			for (PathCollidable collidable2 : collidables) {
				for (Point point : collidable2.getPath()) {
					if (point.equals(collidable1.getPosition())) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
