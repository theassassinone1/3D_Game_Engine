package com.base.engin;

public class Transform {
	
	//variables
	private static float zNear;
	private static float zFar;
	private static float width;
	private static float height;
	private static float fov;

	private static Camera camera;
	
	private Vector3f translation;
	private Vector3f rotation;
	private Vector3f scale;
	
	//constructor
	public Transform(){
		
		translation = new Vector3f(0,0,0);
		rotation = new Vector3f (0,0,0);
		scale = new Vector3f(1,1,1);
	}
	
	//converts Vector3f translation, rotation and scale into matrices 
	public Matrix4f getTransformation(){
		
		//creates translation, rotation and scale matrices
		Matrix4f translationMatrix = new Matrix4f().initTranslation(translation.getX(), translation.getY(), translation.getZ());
		Matrix4f rotationMatrix = new Matrix4f().initRotation(rotation.getX(), rotation.getY(), rotation.getZ());
		Matrix4f sacleMatrix = new Matrix4f().initScale(scale.getX(), scale.getY(), scale.getZ());
		
		//returns the scale then rotation then translation in that order because of the order of multiplication 
		return translationMatrix.multi(rotationMatrix.multi(sacleMatrix));
	}
	
	//perspective Transformation (transformation + perspective)
	public Matrix4f getProjectedTransformation(){
		
		//creates new transformation matrix equals to get transformation
		Matrix4f transformationMatrix = getTransformation();
		//creates projection matrix and inits projection 
		Matrix4f projectionMatrix = new Matrix4f().initProjection(fov, width, height, zNear, zFar);
		//creates camera rotation matrix and inits camera 
		Matrix4f cameraRotationMatrix = new Matrix4f().initCamera(camera.getForward(), camera.getUp());
		//creates camera translation matrix and inits camera 
		Matrix4f cameraTranslationMatrix = new Matrix4f().initTranslation(-camera.getPos().getX(),-camera.getPos().getY(),-camera.getPos().getZ());
		
		//returns the projection multiplied by the camera rotation multiplied by the camera translation multiplied by transformation 
		return projectionMatrix.multi(cameraRotationMatrix.multi(cameraTranslationMatrix.multi(transformationMatrix)));
	}
	
	//gets translation 
	public Vector3f getTranslation() {
		//returns translation
		return translation;
	}
	
	//sets projection
	public static void setProjection(float fov,float width, float height, float zNear, float zFar){
		
		//sets variables
			//fov equals fov 
			Transform.fov = fov;
			//width equals width 
			Transform.width = width;
			//height equals height 
			Transform.height = height;
			//zNear equals zNear 
			Transform.zNear = zNear;
			//zFar equals zFar 
			Transform.zFar = zFar;
	}
	
	//set translation (Vector3f)
	public void setTranslation(Vector3f translation) {
		//translation equals translation 
		this.translation = translation;
	}
	
	//set translation 
	public void setTranslation(float x , float y, float z) {
		//translation equals a new vector3f 
		this.translation = new Vector3f(x, y, z);;
	}
	
	//gets rotation
	public Vector3f getRotation() {
		//returns rotation
		return rotation;
	}

	//sets rotation (Vector3f)
	public void setRotation(Vector3f rotation) {
		//rotation equals rotation 
		this.rotation = rotation;
	}
	
	//sets rotation (floats x,y,z)
	public void setRotation(float x, float y, float z) {
		//rotation equals a new vector3f  
		this.rotation = new Vector3f(x,y,z);
	}
	
	//gets scale
	public Vector3f getScale() {
		//returns scale
		return scale;
	}
	
	//sets scale (Vector3f)
	public void setScale(Vector3f scale) {
		//scale equals scale
		this.scale = scale;
	}
	
	//sets scale (floats x,y,z)
	public void setScale(float x, float y, float z) {
		//scale equals a new vector3f  
		this.scale = new Vector3f(x,y,z);	
	}
	
	//gets camera
	public static Camera getCamera() {
		//returns camera
		return camera;
	}
	
	//sets camera
	public static void setCamera(Camera camera) {
		//camera equals camera
		Transform.camera = camera;
	}
}
