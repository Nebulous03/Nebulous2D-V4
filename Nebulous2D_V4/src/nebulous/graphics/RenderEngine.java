package nebulous.graphics;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

import nebulous.main.Game;
import nebulous.utils.math.Matrix4f;

public class RenderEngine {
	
	private static final float DEFAULT_FOV = 80.0f;
	private static final float DEFAULT_Z_NEAR = 0.001f;
	private static final float DEFAULT_Z_FAR = 1000f;
	private static Shader DEFAULT_SHADER;
	
	private static float FOV;
	private static float Z_NEAR;
	private static float Z_FAR;
	private static float ORTHO_LEFT;
	private static float ORTHO_RIGHT;
	private static float ORTHO_TOP;
	private static float ORTHO_BOTTOM;
	private static float aspectRatio;
	private static Matrix4f projectionMatrix;
	private static Projection projectionType;
	
	private static Window window;
	private static Game game;
	
	public RenderEngine(Game game){
		RenderEngine.game = game;
		RenderEngine.window = game.getWindow(); // TODO: Move Window in here
		RenderEngine.projectionType = Projection.PERSPECTIVE;
		RenderEngine.FOV = (float) Math.toRadians(DEFAULT_FOV);
		RenderEngine.Z_NEAR = DEFAULT_Z_NEAR;
		RenderEngine.Z_FAR = DEFAULT_Z_FAR;
		RenderEngine.ORTHO_LEFT = 0;
		RenderEngine.ORTHO_RIGHT = window.getWidth();
		RenderEngine.ORTHO_TOP = 0;
		RenderEngine.ORTHO_BOTTOM = window.getHeight();
	}
	
	public static void init(){
		DEFAULT_SHADER = new Shader("/shaders/defaultShader.vs", "/shaders/defaultShader.fs");
		aspectRatio = (float) window.getWidth() / window.getHeight();
		if(projectionType == Projection.PERSPECTIVE)projectionMatrix = 
				new Matrix4f().initPerspective(FOV, aspectRatio, Z_NEAR, Z_FAR);
		if(projectionType == Projection.ORTHOGRAPHIC)projectionMatrix = 
				new Matrix4f().initOrthographic(ORTHO_LEFT, ORTHO_RIGHT, ORTHO_BOTTOM, ORTHO_TOP, Z_NEAR, Z_FAR);
	}
	
	public static void update(){
		window.update();
	}
	
	public static void render(){
		game.render();
		window.render();
	}
	
	public static void renderMesh(Mesh mesh){
		if(mesh.hasCustomShader()) mesh.getShader().bind();
		else DEFAULT_SHADER.bind();
		
		glBindVertexArray(mesh.vao);
	    glEnableVertexAttribArray(0);
	    glEnableVertexAttribArray(1);

	    glDrawElements(GL_TRIANGLES, mesh.vCount, GL_UNSIGNED_INT, 0);

	    glDisableVertexAttribArray(0);
	    glDisableVertexAttribArray(1);
	    glBindVertexArray(0);
		
		if(mesh.hasCustomShader()) mesh.getShader().unbind();
		else DEFAULT_SHADER.unbind();
	}
	
	public static Shader getDefaultShader(){
		return DEFAULT_SHADER;
	}

	public static float getFOV() {
		return FOV;
	}

	public static void setFOV(float fOV) {
		FOV = (float)Math.toRadians(fOV);
	}

	public static float getZ_NEAR() {
		return Z_NEAR;
	}

	public static void setZ_NEAR(float z_NEAR) {
		Z_NEAR = z_NEAR;
	}

	public static float getZ_FAR() {
		return Z_FAR;
	}

	public static void setZ_FAR(float z_FAR) {
		Z_FAR = z_FAR;
	}

	public static float getDefaultFov() {
		return DEFAULT_FOV;
	}

	public static float getDefaultZNear() {
		return DEFAULT_Z_NEAR;
	}

	public static float getDefaultZFar() {
		return DEFAULT_Z_FAR;
	}

	public static float getAspectRatio() {
		return aspectRatio;
	}

	public static Matrix4f getProjection() {
		return projectionMatrix;
	}

	public static Window getWindow() {
		return window;
	}

	public static Game getGame() {
		return game;
	}

	public static Projection getProjectionType() {
		return projectionType;
	}

	public static void setProjectionType(Projection projectionType) {
		RenderEngine.projectionType = projectionType;
	}

}
