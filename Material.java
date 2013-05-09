public class Material{
	private Color ambient;
	private Color diffuse;
	private Color specular;
	private double alpha;
	private double reflective;
	private double transmissive;
	private double indexOfRefraction;

	public Material(Color ambient, Color diffuse, Color specular, double alpha, double reflective, double transmissive, double index){
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.alpha = alpha;
		this.reflective = reflective;
		this.transmissive = transmissive;
		this.indexOfRefraction = index;
	}

	public Material(){
		this.ambient = new Color(0.0,1.0,0.0);
		this.diffuse = new Color(0.0,1.0,0.0);
		this.specular = new Color(1.0,1.0,1.0);
		this.alpha = 2.0;
	}
	public Color getAmbient(){
		return ambient;
	}

	public Color getDiffuse(){
		return diffuse;
	}

	public Color getSpecular(){
		return specular;
	}

	public double getAlpha(){
		return alpha;
	}

	public double getReflective(){
		return reflective;
	}

	public double getTransmissive(){
		return transmissive;
	}

	public double getIndex(){
		return indexOfRefraction;
	}
}