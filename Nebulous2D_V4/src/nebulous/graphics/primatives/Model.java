package nebulous.graphics.primatives;

import nebulous.graphics.RenderEngine;
import nebulous.graphics.shaders.Shader;

public class Model {
	
	private Mesh mesh;
	private Texture texture;
	private Shader customShader = null;
	
	public Model(Mesh mesh, Texture texture){
		this.mesh = mesh;
		this.texture = texture;
	}
	
	public void setSize(float size){
		mesh.setSize(size);
	}

	public Mesh getMesh() {
		return mesh;
	}

	public Texture getTexture() {
		if(texture == null) return Texture.UNKNOWN;
		else return texture;
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

	public Shader getCustomShader() {
		return customShader;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

}
