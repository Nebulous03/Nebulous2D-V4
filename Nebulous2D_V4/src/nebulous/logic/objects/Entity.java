package nebulous.logic.objects;

import org.joml.Vector2f;

import nebulous.graphics.primatives.Mesh;
import nebulous.graphics.primatives.Model;
import nebulous.graphics.primatives.Texture;
import nebulous.logic.collisions.CollisionAABB;

public abstract class Entity extends GameObject{
	
	private CollisionAABB aabb;
	private boolean collidable;
	
	// TODO: add support for rotated collision

	public Entity() {
		super(new Model(Mesh.plane(), null), new Vector2f(0,0));
		aabb = new CollisionAABB(pos, width, height);
	}
	
	public Entity(Texture texture) {
		super(new Model(Mesh.plane(), texture), new Vector2f(0,0));
		aabb = new CollisionAABB(pos, width, height);
	}

	public abstract void update();
	
	public void updateCollision(){	// SET IF NOT ALREADY
		aabb.setPosition(pos);
		if(collidable){
			// Do stuff
		}
	}
	
	public boolean intersects(Entity entity){
		return aabb.intersects(entity.getAABB());
	}
	
	public void setCollisionBox(CollisionAABB aabb){
		this.aabb = aabb;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	public CollisionAABB getAABB() {
		return aabb;
	}

}
