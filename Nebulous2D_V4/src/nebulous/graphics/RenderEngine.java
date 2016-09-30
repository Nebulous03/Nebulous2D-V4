package nebulous.graphics;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import nebulous.graphics.enums.Projection;
import nebulous.graphics.primatives.Mesh;
import nebulous.graphics.primatives.Model;
import nebulous.graphics.primatives.Texture;
import nebulous.graphics.shaders.DefaultShader;
import nebulous.graphics.shaders.Shader;
import nebulous.graphics.tiles.TileMap;
import nebulous.logic.objects.GameObject;
import nebulous.main.Game;

public class RenderEngine {
	
	private static Shader DEFAULT_SHADER;
	private static Camera DEFAULT_CAMERA;
	
	public static Window window;
	public static Game game;
	public static Camera camera;
	
	public RenderEngine(Game game){
		RenderEngine.game = game;
		RenderEngine.window = game.getWindow(); // TODO: Move Window in here
	}
	
	public static void init(){
		DEFAULT_SHADER = new DefaultShader();
		DEFAULT_CAMERA = new Camera(new Vector3f(0,0,0));
	}
	
	public static void update(){
		window.update();
	}
	
	public static void render(){
		game.render();
		window.render();
	}
	
	public static void render(GameObject object){
		Model model = object.getModel();
		Mesh mesh = object.getModel().getMesh();
		Shader shader = model.getShader();
		if(model.hasCustomShader()) shader.bind();
		else DEFAULT_SHADER.bind();
		
		model.getShader().updateUniforms();
		glBindVertexArray(mesh.vao);
	    glEnableVertexAttribArray(0);
	    glEnableVertexAttribArray(1);

	    /* Transform Matrix */
	    Matrix4f transform = Transform.getTransformationMatrix(
	    		new Vector3f(object.getPosition().x, object.getPosition().y, object.getDepth()), object.getRotaion(), object.getScale());
	    shader.setUniformMat4f(shader.getUniform("transform"), transform);
	    
	    /* Perspective Matrix */
	    Matrix4f perspective = Transform.getPerspectiveMatrix(camera.getFov(), camera.getAspectRatio(), camera.getZNear(), camera.getZFar());
	    shader.setUniformMat4f(shader.getUniform("perspective"), perspective);
	    
	    /* View Matrix */
	    Matrix4f view = Transform.getViewMatrix(camera);
	    shader.setUniformMat4f(shader.getUniform("view"), view);
	    
	    GL13.glActiveTexture(GL13.GL_TEXTURE0);
	    GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().textureID);
	    glDrawElements(GL_TRIANGLES, mesh.vCount, GL_UNSIGNED_INT, 0);

	    glDisableVertexAttribArray(0);
	    glDisableVertexAttribArray(1);
	    glBindVertexArray(0);
		
		if(model.hasCustomShader()) shader.unbind();
		else DEFAULT_SHADER.unbind();
	}
	
	public static void renderTileMap(TileMap map){
		Shader shader = map.getShader();
		if(map.hasCustomShader()) shader.bind();
		else DEFAULT_SHADER.bind();
		
		for(int y = 0; y < map.getHeight(); y++){
			for(int x = 0; x < map.getWidth(); x++){
				if(!map.getTile(x, y).isBlank()){
					
					Mesh mesh = map.getTile(x, y).getModel().getMesh();
					Texture texture = map.getTile(x, y).getModel().getTexture();
					
					map.getShader().updateUniforms();
					glBindVertexArray(mesh.vao);
				    glEnableVertexAttribArray(0);
				    glEnableVertexAttribArray(1);
	
				    /* Transform Matrix*/
				    Matrix4f transform = Transform.getTransformationMatrix(new Vector3f(x * map.getTileSize() * 2, y * map.getTileSize() * 2, map.getDepth()), 0, new Vector3f(map.getTileSize()));
				    shader.setUniformMat4f(shader.getUniform("transform"), transform);
				    
				    /* Perspective Matrix*/
				    Matrix4f perspective = Transform.getPerspectiveMatrix(camera.getFov(), camera.getAspectRatio(), camera.getZNear(), camera.getZFar());
				    shader.setUniformMat4f(shader.getUniform("perspective"), perspective);
				    
				    /* View Matrix*/
				    Matrix4f view = Transform.getViewMatrix(camera);
				    shader.setUniformMat4f(shader.getUniform("view"), view);
				    
				    GL13.glActiveTexture(GL13.GL_TEXTURE0);
				    GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.textureID);
				    glDrawElements(GL_TRIANGLES, mesh.vCount, GL_UNSIGNED_INT, 0);
	
				    glDisableVertexAttribArray(0);
				    glDisableVertexAttribArray(1);
				    glBindVertexArray(0);
				}
			}
		}
		
		if(map.hasCustomShader()) shader.unbind();
		else DEFAULT_SHADER.unbind();
	}
	
	public static Shader getDefaultShader(){
		return DEFAULT_SHADER;
	}

	public static Window getWindow() {
		return window;
	}

	public static Game getGame() {
		return game;
	}

	public static Camera getCamera() {
		return camera;
	}

	public static void setCamera(Camera camera) {
		RenderEngine.camera = camera;
	}

	public static Shader getDEFAULT_SHADER() {
		return DEFAULT_SHADER;
	}

	public static Camera getDEFAULT_CAMERA() {
		return DEFAULT_CAMERA;
	}

}
