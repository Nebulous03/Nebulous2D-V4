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

	public Mesh getMesh() {
		return mesh;
	}

	public Texture getTexture() {
		return texture;
	}
	
	public void render(){
		RenderEngine.renderMesh(this);
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

}
