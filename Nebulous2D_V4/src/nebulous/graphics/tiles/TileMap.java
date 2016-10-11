package nebulous.graphics.tiles;

import org.joml.Vector2f;

import nebulous.graphics.RenderEngine;
import nebulous.graphics.enums.MapType;
import nebulous.graphics.shaders.Shader;
import nebulous.logic.collisions.CollisionAABBV2;

public class TileMap {
	
	private MapType mapType;
	private int width, height;
	private float depth;
	private float tileSize;
	private Tile[] tiles;
	private CollisionAABBV2[] colliders;
	
	private Shader customShader = null;

	public TileMap(int xSize, int ySize, float tileSize, Tile defaultTile, MapType mapType) {
		this.mapType = mapType;
		this.width = xSize;
		this.height = ySize;
		this.depth = 0;
		this.tileSize = tileSize;
		this.tiles = new Tile[xSize * ySize];
		this.colliders = new CollisionAABBV2[xSize * ySize];
		populateTiles(defaultTile);
	}
	
	public TileMap(int xSize, int ySize, float tileSize, MapType mapType){
		this.mapType = mapType;
		this.width = xSize;
		this.height = ySize;
		this.depth = 0;
		this.tileSize = tileSize;
		this.tiles = new Tile[xSize * ySize];
		this.colliders = new CollisionAABBV2[xSize * ySize];
		populateTiles();
	}
	
	public TileMap(int xSize, int ySize, float tileSize, Tile defaultTile){
		this.mapType = MapType.NORMAL;
		this.width = xSize;
		this.height = ySize;
		this.depth = 0;
		this.tileSize = tileSize;
		this.tiles = new Tile[xSize * ySize];
		this.colliders = new CollisionAABBV2[xSize * ySize];
		populateTiles(defaultTile);
	}
	
	public TileMap(int xSize, int ySize, float tileSize){
		this.mapType = MapType.NORMAL;
		this.width = xSize;
		this.height = ySize;
		this.depth = 0;
		this.tileSize = tileSize;
		this.tiles = new Tile[xSize * ySize];
		this.colliders = new CollisionAABBV2[xSize * ySize];
		populateTiles();
	}
	
	public void populateTiles(){
		Tile blank = new Tile();
		for(int i = 0; i < tiles.length; i++)
			tiles[i] = blank;
		for(int i = 0; i < colliders.length; i++)
			colliders[i] = null;
	}
	
	public void populateTiles(Tile tile){
		for(int i = 0; i < tiles.length; i++)
			tiles[i] = tile;
	}
	
	public Tile getTile(int x, int y){
		return tiles[x + y * width];
	}
	
	public CollisionAABBV2 getTileAABB(int x, int y){
		return colliders[x + y * width];
	}
	
	public void setTile(Tile tile, int x, int y){
		tiles[x + y * width] = tile;
		if(mapType == MapType.COLLISION)
			System.out.println(x + ", " + y);
			colliders[x + y * width] = new CollisionAABBV2(new Vector2f(x, y), new Vector2f(1,1));
	}
	
	public void render(){
		RenderEngine.renderTileMap(this);
	}
	
	public void setCustomShader(Shader shader){
		this.customShader = shader;
	}
	
	public void removeCusomShader(){
		this.customShader = null;
	}
	
	public boolean hasCustomShader(){
		return (customShader != null);
	}
	
	public Shader getShader(){
		if(customShader != null)return customShader;
		else return RenderEngine.getDefaultShader();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Tile[] getTiles() {
		return tiles;
	}

	public float getTileSize() {
		return tileSize;
	}

	public Shader getCustomShader() {
		return customShader;
	}

	public void setTileSize(float tileSize) {
		this.tileSize = tileSize;
	}

	public MapType getMapType() {
		return mapType;
	}

	public float getDepth() {
		return depth;
	}

	public void setDepth(float depth) {
		this.depth = depth;
	}

}
