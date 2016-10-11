package nebulous.logic.collisions;

import org.joml.Vector2f;

public class CollisionAABBV2 {
	
	private Vector2f center, halfExtent;
	
	public CollisionAABBV2(Vector2f center, Vector2f halfExtent) {
		this.center = center;
		this.halfExtent = halfExtent;
	}
	
	public CollisionEvent getCollision(CollisionAABBV2 aabb){
		Vector2f distance = aabb.center.sub(center, new Vector2f());
		distance.x = (float)Math.abs(distance.x);
		distance.y = (float)Math.abs(distance.y);
		distance.sub(halfExtent.add(aabb.halfExtent), new Vector2f());
		return new CollisionEvent(distance, distance.x < 0 && distance.y < 0);
	}
	
	public void updatePosition(CollisionAABBV2 aabb, CollisionEvent event){
		Vector2f correction = aabb.center.sub(center, new Vector2f());
		System.out.println("updatePosition in AABB");
		if(event.distance.x > event.distance.y){
			if(correction.x > 0){
				center.add(event.distance.x, 0);
			} else {
				center.add(-event.distance.x, 0);
			}
		} else {
			if(correction.y > 0){
				center.add(0, event.distance.y);
			} else {
				center.add(0, -event.distance.y);
			}
		}
	}

	public Vector2f getCenter() {
		return center;
	}

	public Vector2f getHalfExtent() {
		return halfExtent;
	}

}
