package tileMapDemo;

import nebulous.graphics.primatives.Texture;
import nebulous.graphics.shaders.InvertedShader;
import nebulous.graphics.shaders.Shader;
import nebulous.graphics.tiles.Tile;
import nebulous.graphics.tiles.TileMap;
import nebulous.logic.level.Level;

public class LevelOne extends Level{
	
	public Shader inverted;

	public LevelOne() {
		
	}

	@Override
	public void init() {
		inverted = new InvertedShader();
		
		Tile stone = new Tile(new Texture("/textures/mc/stone.png"));
		Tile grass = new Tile(new Texture("/textures/mc/grass_side.png"));
		Tile torch = new Tile(new Texture("/textures/mc/torch_on.png"));
		
		TileMap map = new TileMap(32, 16, 1, stone);
		TileMap map2 = new TileMap(32, 16, 1);
		
		map2.setTile(grass, 0, 0);
		map2.setTile(grass, 3, 3);
		
		for(int i = 0; i < map2.getWidth(); i++){
			map2.setTile(grass, i, 0);
		}
		
		map2.setTile(torch, 3, 4);
		
		Player player = new Player(new Texture("/textures/mc/torch_on.png"));
		
		addMap(map);
		addMap(map2);
		add("player", player);
	}

	@Override
	public void update(double delta) {
		
	}

}
