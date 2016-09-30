package transformDemo;

import org.joml.Vector2f;
import org.joml.Vector3f;

import nebulous.graphics.Camera;
import nebulous.graphics.primatives.Mesh;
import nebulous.graphics.primatives.Model;
import nebulous.graphics.primatives.Texture;
import nebulous.graphics.shaders.WhiteShader;
import nebulous.logic.Input;
import nebulous.logic.objects.GameObject;
import nebulous.main.Game;
import nebulous.utils.Console;

public class TransformDemo extends Game{

	private static final int width = 1366;
	private static final int height = 768;
	private static final String title = "Nebulous2D Game Engine";

	public static TransformDemo game = new TransformDemo();
	
	public Model model;
	public Model model2;
	public GameObject plane;
	public GameObject plane2;
	public Camera camera;
	
	
	public static void main(String[] args) {
		game.getWindow().vSync(true);
		//game.getWindow().setVideoMode(VideoMode.FULLSCREEN, 1920, 1080);
		game.start();
	}
	
	public TransformDemo() {
		super(width, height, title); // TODO: Fix this
	}
	@Override
	public void init() {
		
		camera = new Camera(new Vector3f(0,0,-10));
		
		//Object 1
		model = new Model(Mesh.plane(), new Texture("/textures/unknown.png"));
		plane = new GameObject(model, new Vector2f(0,0));
		plane.scale(-0.5f);
		
		// Object 2
		model2 = new Model(Mesh.plane(), new Texture("/textures/unknown.png"));
		model2.setCustomShader(new WhiteShader());
		plane2 = new GameObject(model2, new Vector2f(0,0));
		plane2.scale(-0.5f);
		
	}
	
	@Override
	public void update() {
		if(Input.isKeyHeld(Input.KEY_ESCAPE)) stop();
		if(Input.isKeyHeld(Input.KEY_SPACE))Console.println("SPACE!");
//		if(InputV2.isKeyDown(InputV2.KEY_W)) plane.move(new Vector2f(0,1), 0.02f);
//		if(InputV2.isKeyDown(InputV2.KEY_S)) plane.move(new Vector2f(0,-1), 0.02f);
//		if(InputV2.isKeyDown(InputV2.KEY_D)) plane.move(new Vector2f(1,0), 0.02f);
//		if(InputV2.isKeyDown(InputV2.KEY_A)) plane.move(new Vector2f(-1,0), 0.02f);
		if(Input.isKeyHeld(Input.KEY_E)) plane.rotate(-0.05f);
		if(Input.isKeyHeld(Input.KEY_R)) plane.rotate(0.05f);
		if(Input.isKeyHeld(Input.KEY_G)) plane.scale(-0.01f);
		if(Input.isKeyHeld(Input.KEY_H)) plane.scale(0.01f);
		//Console.println(plane.getScale());
		if(Input.isKeyHeld(Input.KEY_A)) camera.setRotation(camera.getRotation().add(new Vector3f(-0.5f,0,0)));
		if(Input.isKeyHeld(Input.KEY_D)) camera.setRotation(camera.getRotation().add(new Vector3f(0.5f,0,0)));
		if(Input.isKeyHeld(Input.KEY_W)) camera.setRotation(camera.getRotation().add(new Vector3f(0,-0.5f,0)));
		if(Input.isKeyHeld(Input.KEY_S)) camera.setRotation(camera.getRotation().add(new Vector3f(0,0.5f,0)));
		
		Console.println(camera.getRotation().toString());
	}

	@Override
	public void render() {
	    plane.render();
	    //plane2.render();
	}

}
