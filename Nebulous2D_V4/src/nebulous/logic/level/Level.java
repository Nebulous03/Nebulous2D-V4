package nebulous.logic.level;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import nebulous.graphics.RenderEngine;
import nebulous.graphics.tiles.TileMap;
import nebulous.logic.objects.Entity;
import nebulous.logic.objects.GameObject;

public abstract class Level {
	
	private Map<Integer, TileMap> mapLayers;
	private Map<String, GameObject> objects;
	private ArrayList<String> tags;
	private int mapsSize = 0;
	private int objectsSize = 0;

	public Level() {
		mapLayers = new HashMap<Integer, TileMap>();
		objects = new HashMap<String, GameObject>();
		tags = new ArrayList<String>();
	}
	
	public void add(String tag, GameObject object){
		objects.put(tag, object);
		tags.add(tag);
		object.setDepth(mapsSize * 0.01f);
		objectsSize++;
	}
	
	public void addMap(TileMap map){
		mapLayers.put(mapsSize, map);
		map.setDepth(mapsSize * 0.01f);
		mapsSize++;
	}
	
	public TileMap getMap(int id){
		return mapLayers.get(id);
	}
	
	public GameObject getObject(String tag){
		return objects.get(tag);
	}
	
	public abstract void init();
	public abstract void update(double delta);
	
	public void updateObjects(){
		for(int i = 0; i < objectsSize; i++)
			if(objects.get(tags.get(i)) instanceof Entity){
				Entity e = (Entity)objects.get(tags.get(i));
				e.update();
			}
	}
	
	public void render(){
		for(int i = 0; i < mapsSize; i++){
			RenderEngine.renderTileMap(mapLayers.get(i));
		}
		for(int i = 0; i < objectsSize; i++){
			objects.get(tags.get(i)).render();
		}
	}

}
