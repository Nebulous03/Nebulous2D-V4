package nebulous.graphics;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import nebulous.utils.BufferUtilities;

public class Mesh {
	
	private int vao; // Vertex Array Object
	private int vbo; // Vertex Buffer Object
	private int ibo; // Index Buffer Object
	private int cbo; // Color Buffer Object;
	private int vCount;

	//TODO: Move all methods to renderer for efficiency
	
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
	    glBindVertexArray(vao);
	    glEnableVertexAttribArray(0);
	    glEnableVertexAttribArray(1);

	    glDrawElements(GL_TRIANGLES, vCount, GL_UNSIGNED_INT, 0);

	    glDisableVertexAttribArray(0);
	    glDisableVertexAttribArray(1);
	    glBindVertexArray(0);
	}
	
	//TODO: Add custom shader abilities

}