package nebulous.logic.objects;

import org.joml.Vector2f;

import nebulous.graphics.primatives.Mesh;
import nebulous.graphics.primatives.Model;
import nebulous.graphics.primatives.Texture;
import nebulous.graphics.tiles.TileMap;
import nebulous.logic.collisions.CollisionAABBV2;
import nebulous.logic.collisions.CollisionEvent;
import nebulous.logic.level.Level;

public abstract class Entity extends GameObject{
	
	private CollisionAABBV2 aabb;
	private boolean collidable;
	
	// TODO: add support for rotated collision

	public Entity() {
		super(new Model(Mesh.plane(), null), new Vector2f(0,0));
		aabb = new CollisionAABBV2(new Vector2f(pos.x, pos.y), new Vector2f(1,1));
		collidable = true;
	}
	
	public Entity(Texture texture) {
		super(new Model(Mesh.plane(), texture), new Vector2f(0,0));
		aabb = new CollisionAABBV2(new Vector2f(pos.x, pos.y), new Vector2f(1,1));
		collidable = true;
	}

	public abstract void update();
	
	public void updateCollision(Level level){	// Create Collision rage variable
		if(collidable){
			
			aabb.getCenter().set(pos);
			
			TileMap map = level.getMapLayers().get(1);
			
			CollisionAABBV2[] boxes = new CollisionAABBV2[25];
			for(int i = 0; i < 5; i++){
				for(int j = 0; j < 5; j++){
					int xTile = (int)(((pos.x) + 0.5f) - (5)) + i;
					int yTile = (int)(((pos.y) + 0.5f) - (5)) + j;
					try{ boxes[i + j * 5] = map.getTileAABB(xTile, yTile);
					} catch (Exception e) {}
				}
			}
			
			for(int i = 0; i < 25; i++){
				
			}
			
			CollisionAABBV2 box = null;
			for(int i = 0; i < boxes.length; i++){
				if(boxes[i] != null){
					if(box == null) box = boxes[i];
					Vector2f length1 = box.getCenter().sub(pos, new Vector2f());
					Vector2f length2 = boxes[i].getCenter().sub(pos, new Vector2f());
					if(length1.lengthSquared() > length2.lengthSquared()){
						box = boxes[i];
					}
				}
			}
			
			if(box != null){
				CollisionEvent event = aabb.getCollision(box);
				if(event.intersecting){ 
					aabb.updatePosition(box, event);
					pos.set(aabb.getCenter());
				}
			}
			
 		}
	}
	
	public void setCollisionBox(CollisionAABBV2 aabb){
		this.aabb = aabb;
	}

	public boolean isCollidable() {
		return collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

}
