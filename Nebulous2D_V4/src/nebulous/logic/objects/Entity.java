package nebulous.logic.objects;

import org.joml.Vector2f;

import nebulous.graphics.primatives.Mesh;
import nebulous.graphics.primatives.Model;

public abstract class Entity extends GameObject{

	public Entity() {
		super(new Model(Mesh.plane(), null), new Vector2f(0,0));
	}

	public abstract void update();

}
