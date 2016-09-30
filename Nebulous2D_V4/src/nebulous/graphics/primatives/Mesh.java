package nebulous.graphics.primatives;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;

public class Mesh {
	
	public float[] vertices;
	public int[] indices;
	public float[] texCoords;
	public int vao = -2; // Vertex Array Object
	public int vbo = -2; // Vertex Buffer Object
	public int ibo = -2; // Index Buffer Object
	public int tbo = -2; // Texture Buffer Object;
	public float size;
	public int vCount;

	public Mesh(float[] vertices, int[] indices, float[] texCoords) {		//TODO: Simplify this
		this.vertices = vertices;
		this.indices = indices;
		this.texCoords = texCoords;
		this.size = 1;
		
		vCount = vertices.length / 2;
		vao = glGenVertexArrays();
		vbo = glGenBuffers();
		ibo = glGenBuffers();
		tbo = glGenBuffers();
		
		bindVAO(vao);
		
		addVertexAttribute(0, this.vbo, vertices, 3);
		addElementAttribute(ibo, indices);
		addVertexAttribute(1, this.tbo, texCoords, 2);
		
		unbindVAO();
		unbindArrayBuffer();
	}
	
	public static Mesh plane(){
		
		float[] vertices = new float[]{
				-1f,  1f, 0f,
			    -1f, -1f, 0f,
			     1f, -1f, 0f,
			     1f,  1f, 0f,
	        };
		 
		int[] indices = new int[]{
			        0, 1, 3, 3, 1, 2,
			};

		float[] textureCoords = new float[]{
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		return new Mesh(vertices, indices, textureCoords);
	}
	
	public void setSize(float size){
		this.size = size;
	}
	
	public void addVertexAttribute(int location, int bufferObjectIndex, float[] data, int size){
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data).flip();
		glBindBuffer(GL_ARRAY_BUFFER, bufferObjectIndex);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glVertexAttribPointer(location, size, GL_FLOAT, false, 0, 0);
	}
	
	public void addElementAttribute(int bufferObjectIndex, int[] data){
		IntBuffer indexBuffer = BufferUtils.createIntBuffer(data.length);
		indexBuffer.put(data).flip();
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, bufferObjectIndex);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, GL_STATIC_DRAW);
	}
	
	public void bindVAO(int vao){
		glBindVertexArray(vao);
	}
	
	public void unbindVAO(){
		glBindVertexArray(0);
	}
	
	public void unbindArrayBuffer(){
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	public void delete(){				//TODO: Add actual use
		glDeleteVertexArrays(vao);
		glDeleteBuffers(vbo);
	}

}
