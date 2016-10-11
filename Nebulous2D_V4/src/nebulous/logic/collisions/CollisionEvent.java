package nebulous.logic.collisions;

import org.joml.Vector2f;

public class CollisionEvent {
	
	public Vector2f distance;
	public boolean intersecting;

	public CollisionEvent(Vector2f distance, boolean intersecting){
		this.distance = distance;
		this.intersecting = intersecting;
	}

	public Vector2f getDistance() {
		return distance;
	}

	public boolean isIntersecting() {
		return intersecting;
	}

}
