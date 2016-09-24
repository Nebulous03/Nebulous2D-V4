package nebulous.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import nebulous.utils.BufferUtilities;

public class Mesh {
	
	public int vao; // Vertex Array Object
	public int vbo; // Vertex Buffer Object
	public int ibo; // Index Buffer Object
	public int cbo; // Color Buffer Object;
	public int vCount;
	
	private Shader customShader = null;

	public Mesh(float[] vertices, int[] indices, float[] colors) {
		FloatBuffer vertexBuffer = BufferUtilities.createFloatBuffer(vertices.length);
		vertexBuffer.put(vertices).flip();
		IntBuffer indexBuffer = BufferUtilities.createIntBuffer(indices.length);
		indexBuffer.put(indices).flip();
		FloatBuffer colorBuffer = BufferUtilities.createFloatBuffer(colors.length);
		colorBuffer.put(colors).flip();
		
		vCount = vertices.length / 2;
		
		vao = glGenVertexArrays();
		vbo = glGenBuffers();
		ibo = glGenBuffers();
		cbo = glGenBuffers();
		
		glBindVertexArray(vao);
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0); //Vertex

		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
		
		glBindBuffer(GL_ARRAY_BUFFER, cbo);
		glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
		glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0); //Color
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
