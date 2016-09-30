package nebulous.logic.objects;

import org.joml.Vector2f;
import org.joml.Vector3f;

import nebulous.graphics.Camera;
import nebulous.graphics.RenderEngine;
import nebulous.graphics.primatives.Model;

public class GameObject {
	
	private Model model;
	private Vector2f pos;
	private Vector3f scale;
	private float depth;
	private float rotaion;
	
	public GameObject(Model model, Vector2f pos){
		this.model = model;
		this.rotaion = 0.0f;
		this.scale = new Vector3f(1,1,1);
		this.pos = pos;
	}
	
	public void move(Vector2f direction, float ammount){
		setPosition(pos.add(new Vector2f(direction.x * ammount, direction.y * ammount)));
	}
	
	public void rotate(float degree){
		setRotaion(rotaion + degree);
	}
	
	public void scale(Vector3f scale){
		setScale(scale.mul(scale));
	}
	
	public void scale(float scale){
		setScale(getScale().add(new Vector3f(scale, scale, scale)));
	}
	
	public void setPosition(float x, float y){
		setPosition(new Vector2f(x, y));
	}

	public void render(){
		RenderEngine.render(this);
	}
	
	public Vector2f getPosition() {
		return pos;
	}

	public void setPosition(Vector2f pos) {
		this.pos = pos;
	}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale) {
		this.scale = scale;
	}

	public float getRotaion() {
		return rotaion;
	}

	public void setRotaion(float rotaion) {
		this.rotaion = rotaion;
	}

	public Model getModel() {
		return model;
	}

	public float getDepth() {
		return depth;
	}

	public void setDepth(float depth) {
		this.depth = depth;
	}

}
