package tileMapDemo;

import org.joml.Vector3f;

import nebulous.graphics.Camera;
import nebulous.graphics.RenderEngine;
import nebulous.graphics.primatives.Texture;
import nebulous.logic.objects.Entity;

public class Player extends Entity{
	
	private Camera camera;

	public Player(Texture texture) {	//TODO: change this
		this.getModel().setTexture(texture);
	}

	@Override
	public void update() {
		camera = RenderEngine.getCamera();
		camera.setPosition(new Vector3f(-this.getPosition().x, -this.getPosition().y, camera.getPosition().z));
	}

}
