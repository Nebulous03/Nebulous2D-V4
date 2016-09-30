package nebulous.graphics.shaders;

public class DefaultShader extends Shader{
	
	public DefaultShader() {
		super("/shaders/defaultShader.vs", "/shaders/defaultShader.fs");
	}

	@Override
	public void addUniforms() {
		
	}

	@Override
	public void updateUniforms() {
		
	}

}
