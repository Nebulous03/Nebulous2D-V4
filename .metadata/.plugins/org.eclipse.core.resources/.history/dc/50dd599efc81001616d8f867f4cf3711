package nebulous.graphics;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.glDrawArrays;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import java.nio.FloatBuffer;

import nebulous.utils.BufferUtilities;

public class Mesh {
	
	public int vao;
	public int vbo;
	public int ind;

	public Mesh(float[] vertices) {
		FloatBuffer vertexBuffer = BufferUtilities.createFloatBuffer(vertices.length);
		vertexBuffer.put(vertices).flip();
		
		vao = glGenVertexArrays();
		glBindVertexArray(vao);
		
		vbo = glGenBuffers();
		
		glBindBuffer(GL_ARRAY_BUFFER, vbo);
		glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
		
		glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
	}
	
	public void render(){
		// Bind to the VAO
	    glBindVertexArray(vao);
	    glEnableVertexAttribArray(0);

	    // Draw the vertices
	    glDrawArrays(GL_TRIANGLES, 0, 3);

	    // Restore state
	    glDisableVertexAttribArray(0);
	    glBindVertexArray(0);
	}
	
	public void useCustomShader(){
		
	}

}
