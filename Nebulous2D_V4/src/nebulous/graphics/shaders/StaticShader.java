package nebulous.graphics.shaders;

public class StaticShader extends Shader{
	
	public StaticShader() {
		super("/shaders/staticShader.vs", "/shaders/staticShader.fs");
	}

	@Override
	public void addUniforms() {
		
	}

	@Override
	public void updateUniforms() {
		
	}

}
