import java.util.*;

public class IntersectData{
	private Point intersection;
	private double distance;
	private Material material;
	private Vector normal;
	private Vector viewDirection;
	private List<Light> lights = null;

	public IntersectData(){
		intersection =null;
		distance = Double.MAX_VALUE;
		material = null;
		normal = null;
	}
	public IntersectData(Point p, double d, Material m, Vector n){
		intersection = p;
		distance = d;
		material = m;
		normal = n;
	}

	public List<Light> getLights(){
		return lights;
	}

	public void addLight(Light light){
		if(lights == null){
			lights = new LinkedList<Light>();
		}
		lights.add(light);
	}

	public void setViewDirection(Vector vd){
		viewDirection = vd;
	}

	public Vector getViewDirection(){
		return viewDirection;
	}

	public double getDistance(){
		return distance;
	}

	public void setDistance(double d){
		distance = d;
	}

	public Point getIntersectPoint(){
		return intersection;
	}

	public void setIntersectPoint(Point p){
		intersection = p;
	}

	public Material getMaterial(){
		return material;
	}

	public void setMaterial(Material m){
		material = m;
	}

	public Vector getNormal(){
		return normal;
	}

	public void setVector(Vector v){
		normal = v;
	}
}