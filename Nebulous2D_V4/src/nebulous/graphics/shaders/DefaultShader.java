package nebulous.graphics.shaders;

import nebulous.graphics.RenderEngine;

public class DefaultShader extends Shader{

	private int uniform_transform;
	
	public DefaultShader() {
		super("/shaders/defaultShader.vs", "/shaders/defaultShader.fs");
	}

	@Override
	public void addUniforms() {
		uniform_transform = getUniform("transform");
		setUniformMat4f(uniform_transform, RenderEngine.getProjectionMatrix());
	}

	@Override
	public void updateUniforms() {
		
	}

}
