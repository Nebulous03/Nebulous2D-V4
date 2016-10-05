package nebulous.logic.collisions;

import org.joml.Vector2f;

public class CollisionAABB {
	
	private float width, height;
	private float halfWidth, halfHeight;
	private float left, top, right, bottom;
	private Vector2f pos;
	private float sensitivity = 0.001f;
	
	public CollisionAABB(Vector2f pos, float width, float height) {
		this.width = width;
		this.height = height;
		this.halfWidth = width / 2f;
		this.halfHeight = height / 2f;
//		this.center = new Vector2f(pos.x + halfWidth, pos.y + halfHeight);
		this.pos = pos;
		this.left = pos.x;
		this.top = pos.y + height;
		this.right = pos.x + width;
		this.bottom = pos.y;		
	}
	
	public boolean intersects(CollisionAABB aabb){
		if(right - sensitivity < aabb.left) return false;	//TODO: Alter sensitivity maybe
		if(left + sensitivity > aabb.right) return false;
		if(top - sensitivity < aabb.bottom) return false;
		if(bottom + sensitivity > aabb.top) return false;
		return true;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getHalfWidth() {
		return halfWidth;
	}

	public float getHalfHeight() {
		return halfHeight;
	}

	public Vector2f getPosition() {
		return pos;
	}

	public void setPosition(Vector2f pos) {
		this.pos = pos; //new Vector2f(pos.x + halfWidth, pos.y + halfHeight);
		this.left = pos.x;
		this.top = pos.y + height;
		this.right = pos.x + width;
		this.bottom = pos.y;
	}

	public float getY1() {
		return top;
	}

	public float getX1() {
		return left;
	}

	public float getY2() {
		return bottom;
	}

	public float getX2() {
		return right;
	}

}
