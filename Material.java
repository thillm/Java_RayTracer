public class Material{
	private Color ambient;
	private Color diffuse;
	private Color specular;
	private double alpha;

	public Material(Color ambient, Color diffuse, Color specular, double alpha){
		this.ambient = ambient;
		this.diffuse = diffuse;
		this.specular = specular;
		this.alpha = alpha;
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
}