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
	private Map<String, Entity> entities;
	private ArrayList<String> otags;
	private ArrayList<String> etags;
	private int mapsSize = 0;
	private int objectsSize = 0;
	private int entitiesSize = 0;

	public Level() {
		mapLayers = new HashMap<Integer, TileMap>();
		objects = new HashMap<String, GameObject>();
		entities = new HashMap<String, Entity>();
		otags = new ArrayList<String>();
		etags = new ArrayList<String>();
	}
	
	public void add(String tag, GameObject object){
		objects.put(tag, object);
		otags.add(tag);
		object.setDepth(mapsSize * 0.01f);
		objectsSize++;
	}
	
	public void add(String tag, Entity entity){
		entities.put(tag, entity);
		etags.add(tag);
		entity.setDepth(mapsSize * 0.01f);
		entitiesSize++;
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
	
	public Entity getEntity(String tag){
		return entities.get(tag);
	}
	
	public abstract void init();
	public abstract void update(double delta);
	
	public void updateObjects(){
		for(int i = 0; i < entitiesSize; i++){
			entities.get(etags.get(i)).update();
		}
	}
	
	public void render(){
		for(int i = 0; i < mapsSize; i++){
			RenderEngine.renderTileMap(mapLayers.get(i));
		}
		for(int i = 0; i < objectsSize; i++){
			objects.get(otags.get(i)).render();
		}
		for(int i = 0; i < entitiesSize; i++){
			entities.get(etags.get(i)).render();
		}
	}

	public Map<Integer, TileMap> getMapLayers() {
		return mapLayers;
	}

	public Map<String, GameObject> getObjects() {
		return objects;
	}

	public Map<String, Entity> getEntities() {
		return entities;
	}

	public ArrayList<String> getOtags() {
		return otags;
	}

	public ArrayList<String> getEtags() {
		return etags;
	}

	public int getMapsSize() {
		return mapsSize;
	}

	public int getObjectsSize() {
		return objectsSize;
	}

	public int getEntitiesSize() {
		return entitiesSize;
	}

}
