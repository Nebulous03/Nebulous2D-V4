package nebulous.graphics;

import org.joml.Vector3f;

import nebulous.logic.components.ILogicComponent;

public class Camera implements ILogicComponent{
	
	private Vector3f pos;
	private Vector3f rotation;
	
	public static final float DEFAULT_FOV = 80.0f;
	public static final float DEFAULT_Z_NEAR = 0.01f;
	public static final float DEFAULT_Z_FAR = 1000f;
	
	private float fov;
	private float aspectRatio;
	private float zNear;
	private float zFar;
	
	public Camera(Vector3f pos){
		Window window = RenderEngine.getWindow();
		this.pos = pos;
		this.rotation = new Vector3f(0,0,0);
		this.fov = (float)Math.toRadians(DEFAULT_FOV);
		this.aspectRatio = (float) window.getWidth() / window.getHeight();
		this.zNear = DEFAULT_Z_NEAR;
		this.zFar = DEFAULT_Z_FAR;
	}
	
	public void adjust(float fov, float aspectRatio, float zNear, float zFar){
		this.fov = (float)Math.toRadians(fov);
		this.aspectRatio = aspectRatio;
		this.zNear = zNear;
		this.zFar = zFar;
	}
	
	@Override
	public void init() {}

	@Override
	public void update(double delta) {
		
	}

	@Override
	public void render() {}

	public Vector3f getPosition() {
		return pos;
	}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation) {
		this.rotation = rotation;
	}

	public void setPosition(Vector3f pos) {
		this.pos = pos;
	}

	public float getFovInDegrees() {
		return (float)Math.toDegrees(fov);
	}
	
	public float getFov() {
		return fov;
	}

	public void setFovInDegrees(float fov) {
		this.fov = fov;
	}

	public Vector3f getPos() {
		return pos;
	}
	
	public float getAspectRatio() {
		return aspectRatio;
	}

	public float getZNear() {
		return zNear;
	}

	public float getZFar() {
		return zFar;
	}
	
}
