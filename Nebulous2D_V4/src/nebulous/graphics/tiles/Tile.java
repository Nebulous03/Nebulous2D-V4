package nebulous.graphics.tiles;

import nebulous.graphics.primatives.Mesh;
import nebulous.graphics.primatives.Model;
import nebulous.graphics.primatives.Texture;

public class Tile {
	
	private Model model;
	private boolean blank;
	
	public Tile(Texture texture){
		this.model = new Model(Mesh.plane(), texture);
		this.blank = false;
	}
	
	public Tile(){
		this.model = new Model(Mesh.plane(), null);
		this.blank = true;
	}
	
	public void setBlank(boolean blank){
		this.blank = blank;
	}
	
	public boolean isBlank(){
		return blank;
	}

	public Model getModel() {
		return model;
	}
	
	public void setTexture(Texture texture){
		this.getModel().setTexture(texture);
		this.blank = false;
	}

	public Texture getTexture() {
		return this.getModel().getTexture();
	}

}
