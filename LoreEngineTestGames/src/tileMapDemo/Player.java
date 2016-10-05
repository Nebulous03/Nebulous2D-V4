package tileMapDemo;

import org.joml.Vector2f;
import org.joml.Vector3f;

import nebulous.graphics.Camera;
import nebulous.graphics.RenderEngine;
import nebulous.graphics.primatives.Texture;
import nebulous.logic.Input;
import nebulous.logic.collisions.CollisionAABB;
import nebulous.logic.level.Level;
import nebulous.logic.objects.Entity;

public class Player extends Entity{
	
	private Camera camera;
	private float xVel, yVel;
	private Level level;

	public Player(Texture texture, Level level) {	//TODO: change this
		this.getModel().setTexture(texture);
		this.level = level;
	}

	@Override
	public void update() {
		
		if(Input.isKeyHeld(Input.KEY_A)) xVel = -0.02f; //move(new Vector2f(-1,0), 0.05f);
		if(Input.isKeyHeld(Input.KEY_D)) xVel = 0.02f; //move(new Vector2f(1,0), 0.05f);
		if(Input.isKeyHeld(Input.KEY_W)) yVel = 0.02f; //move(new Vector2f(0,1), 0.05f);
		if(Input.isKeyHeld(Input.KEY_S)) yVel = -0.02f; //move(new Vector2f(0,-1), 0.05f);
		if(Input.isKeyHeld(Input.KEY_SPACE)) yVel = +0.1f; //move(new Vector2f(0,-1), 0.05f);
		
		attemptMove();
		
		camera = RenderEngine.getCamera();
		camera.setPosition(new Vector3f(-this.getPosition().x * 2, -this.getPosition().y * 2, camera.getPosition().z)); //TODO: I don't like the x2
		updateCollision();
	}
	
	public void attemptMove(){
		Vector2f wPos = pos;
		
		CollisionAABB aabb = getAABB();
		aabb.setPosition(wPos.add(new Vector2f(xVel, yVel)));
		
		for(int i = 0; i < level.getEntitiesSize(); i++){
			CollisionAABB col = level.getEntity(level.getEtags().get(i)).getAABB();
			if(col != aabb){
				if(aabb.intersects(col)){
					wPos.add(new Vector2f(-xVel, -yVel));
					xVel = 0;
					yVel = 0;
				}
			}
		}
		
		if(wPos.add(new Vector2f(xVel, yVel)).x < 0){
			wPos = new Vector2f(0, wPos.y);
			xVel = 0f;
		}
		
		if(wPos.add(new Vector2f(xVel, yVel)).y < 0){
			wPos = new Vector2f(wPos.x, 0);
			yVel = 0f;
		}
		
		if(wPos.add(new Vector2f(xVel, yVel)).x > level.getMap(0).getWidth() - width){
			wPos = new Vector2f(level.getMap(0).getWidth() - width, wPos.y);
			xVel = 0f;
		}
		
		if(wPos.add(new Vector2f(xVel, yVel)).y > level.getMap(0).getHeight() - height){
			wPos = new Vector2f(wPos.x, level.getMap(0).getHeight() - height);
			yVel = 0f;
		}
		
		pos = wPos;
		
		xVel = 0;
		yVel = 0;
	}

}
