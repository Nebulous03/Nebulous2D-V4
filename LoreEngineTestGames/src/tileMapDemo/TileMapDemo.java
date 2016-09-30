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
import nebulous.logic.InputV2;
import nebulous.logic.objects.Entity;
import nebulous.main.Game;

public class TileMapDemo extends Game{
	
	public static final int width = 1366;
	public static final int height = 768;
	
	public Player player;
	public TileMap map;
	public TileMap map2;
	public Camera camera;
	
	public Shader inverted;

	public static void main(String[] args) {
		Game game = new TileMapDemo();
		game.window.setVideoMode(VideoMode.NORMAL, width, height);
		//game.window.setVideoMode(VideoMode.FULLSCREEN, 1920, 1080);
		game.window.setVSync(true);
		game.start();
	}

	@Override
	public void init() {
		
		inverted = new InvertedShader();
		
		Tile stone = new Tile(new Texture("/textures/mc/stone.png"));
		Tile grass = new Tile(new Texture("/textures/mc/grass_side.png"));
		Tile torch = new Tile(new Texture("/textures/mc/torch_on.png"));
		
		map = new TileMap(32, 16, 1, stone);
		map2 = new TileMap(32, 16, 1, MapType.COLLISION);
		
		map.setTile(grass, 0, 0);
		map.setTile(grass, 3, 3);
		map2.setTile(torch, 3, 4);
		
		for(int i = 0; i < map2.getWidth(); i++){
			map2.setTile(grass, i, 0);
		}
		
		camera = new Camera(new Vector3f(0,0,-10));
		RenderEngine.setCamera(camera);
		
		player = new Player(Texture.UNKNOWN2);
		player.setDepth(0.005f);
	}

	float cFov = 80;
	boolean invert = false;

	@Override
	public void update() {
//		if(InputV2.isKeyDown(InputV2.KEY_A)) camera.setPosition(camera.getPosition().add(new Vector3f(0.1f,0,0)));
//		if(InputV2.isKeyDown(InputV2.KEY_D)) camera.setPosition(camera.getPosition().add(new Vector3f(-0.1f,0,0)));
//		if(InputV2.isKeyDown(InputV2.KEY_W)) camera.setPosition(camera.getPosition().add(new Vector3f(0,-0.1f,0)));
//		if(InputV2.isKeyDown(InputV2.KEY_S)) camera.setPosition(camera.getPosition().add(new Vector3f(0,0.1f,0)));
		
		if(InputV2.isKeyHeld(InputV2.KEY_A)) player.move(new Vector2f(-1,0), 0.1f);
		if(InputV2.isKeyHeld(InputV2.KEY_D)) player.move(new Vector2f(1,0), 0.1f);
		if(InputV2.isKeyHeld(InputV2.KEY_W)) player.move(new Vector2f(0,1), 0.1f);
		if(InputV2.isKeyHeld(InputV2.KEY_S)) player.move(new Vector2f(0,-1), 0.1f);
		
		if(InputV2.isKeyHeld(InputV2.KEY_G)) camera.setPosition(camera.getPosition().add(new Vector3f(0,0,0.1f)));
		if(InputV2.isKeyHeld(InputV2.KEY_H)) camera.setPosition(camera.getPosition().add(new Vector3f(0,0,-0.1f)));
		if(InputV2.isKeyHeld(InputV2.KEY_E)) camera.setRotation(camera.getRotation().add(new Vector3f(0,0.1f,0)));
		if(InputV2.isKeyHeld(InputV2.KEY_R)) camera.setRotation(camera.getRotation().add(new Vector3f(0,-0.1f,0)));
		
		if(InputV2.isKeyHeld(InputV2.KEY_I)) cFov += 1;
		if(InputV2.isKeyHeld(InputV2.KEY_O)) cFov -= 1;
		
		if(InputV2.isKeyHeld(InputV2.KEY_PERIOD)){
			invert = !invert;
			if(invert){
				map.setCustomShader(inverted);
				map2.setCustomShader(inverted);
			} else {
				map.removeCusomShader();
				map2.removeCusomShader();
			}
		}
		
		if(invert)
			
		camera.setFov(cFov);
		
		if(InputV2.isKeyHeld(InputV2.KEY_ESCAPE)) stop();
		
		player.update(); //TODO: add to logic engine
	}

	@Override
	public void render() {
		player.render();
		map2.render();
		map.render();
	}

}
