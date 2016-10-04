package tileMapDemo;

import org.joml.Vector2f;
import org.joml.Vector3f;

import nebulous.graphics.Camera;
import nebulous.graphics.RenderEngine;
import nebulous.graphics.enums.MapType;
import nebulous.graphics.enums.VideoMode;
import nebulous.graphics.primatives.Texture;
import nebulous.graphics.shaders.InvertedShader;
import nebulous.graphics.shaders.Shader;
import nebulous.graphics.tiles.Tile;
import nebulous.graphics.tiles.TileMap;
import nebulous.logic.Input;
import nebulous.main.Game;

public class TileMapDemo extends Game{
	
	public static final int width = 1366;
	public static final int height = 768;
	
	public Camera camera;
	public LevelOne level;
	
	public static void main(String[] args) {
		Game game = new TileMapDemo();
		game.window.setVideoMode(VideoMode.NORMAL, width, height);
		//game.window.setVideoMode(VideoMode.FULLSCREEN, 1920, 1080);
		game.window.setVSync(true);
		game.start();
	}

	@Override
	public void init() {
		level = new LevelOne();
		camera = new Camera(new Vector3f(0,0,-10));
		RenderEngine.setCamera(camera);
		addLevel("levelOne", level);
		setActiveLevel(level);
	}

	float cFov = -80.0f;
	boolean invert = false;

	@Override
	public void update() {
//		if(InputV2.isKeyDown(InputV2.KEY_A)) camera.setPosition(camera.getPosition().add(new Vector3f(0.1f,0,0)));
//		if(InputV2.isKeyDown(InputV2.KEY_D)) camera.setPosition(camera.getPosition().add(new Vector3f(-0.1f,0,0)));
//		if(InputV2.isKeyDown(InputV2.KEY_W)) camera.setPosition(camera.getPosition().add(new Vector3f(0,-0.1f,0)));
//		if(InputV2.isKeyDown(InputV2.KEY_S)) camera.setPosition(camera.getPosition().add(new Vector3f(0,0.1f,0)));
		
		if(Input.isKeyHeld(Input.KEY_A)) level.getObject("player").move(new Vector2f(-1,0), 0.1f);
		if(Input.isKeyHeld(Input.KEY_D)) level.getObject("player").move(new Vector2f(1,0), 0.1f);
		if(Input.isKeyHeld(Input.KEY_W)) level.getObject("player").move(new Vector2f(0,1), 0.1f);
		if(Input.isKeyHeld(Input.KEY_S)) level.getObject("player").move(new Vector2f(0,-1), 0.1f);
		
		if(Input.isKeyHeld(Input.KEY_G)) camera.setPosition(camera.getPosition().add(new Vector3f(0,0,0.1f)));
		if(Input.isKeyHeld(Input.KEY_H)) camera.setPosition(camera.getPosition().add(new Vector3f(0,0,-0.1f)));
		if(Input.isKeyHeld(Input.KEY_E)) camera.setRotation(camera.getRotation().add(new Vector3f(0,0.1f,0)));
		if(Input.isKeyHeld(Input.KEY_R)) camera.setRotation(camera.getRotation().add(new Vector3f(0,-0.1f,0)));
		
		if(Input.isKeyHeld(Input.KEY_I)) cFov += 0.05f;
		if(Input.isKeyHeld(Input.KEY_O)) cFov -= 0.05f;
		
//		if(Input.isKeyHeld(Input.KEY_PERIOD)){
//			invert = !invert;
//			if(invert){
//				map.setCustomShader(inverted);
//				map2.setCustomShader(inverted);
//			} else {
//				map.removeCusomShader();
//				map2.removeCusomShader();
//			}
//		}
			
//		camera.setFovInDegrees(cFov);
		
		if(Input.isKeyHeld(Input.KEY_ESCAPE)) stop();
	}
}
