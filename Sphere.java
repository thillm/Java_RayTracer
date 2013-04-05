import org.apache.commons.math3.linear.*;

public class Sphere implements SceneObject{
	private Point center;
	private double radius;
	private Material material;

	public Sphere(Point center, double radius, Material m){
		this.center = center;
		this.radius = radius;
		material = m;
	}

	public Material getMaterial(){
		return material;
	}

	public void transform(RealMatrix m){
		center.transform(m);
	}

	public String toString(){
		return "C:"+center+" R:"+radius;
	}

	public IntersectData intersect(Ray r){
		IntersectData data = null;
		Point o = r.getOrigin();
		Vector d = r.getDirection();
		double xDiff = o.getX() - center.getX();
		double yDiff = o.getY() - center.getY();
		double zDiff = o.getZ() - center.getZ();
		double b = 2 * ((d.getX() * xDiff) + (d.getY() * yDiff) + (d.getZ() * zDiff));
		double c = (xDiff * xDiff) + (yDiff * yDiff) + (zDiff * zDiff) - (radius * radius);

		double discriminant = (b * b) - (4 * c);
		if(discriminant > 0){
			double w1 = (-b + Math.sqrt(discriminant))/2.0;
			double w2 = (-b - Math.sqrt(discriminant))/2.0;
			if(w1 > 0 && (w1 < w2 || w2 < 0) ){
				double x = o.getX() + d.getX()*w1;
				double y = o.getY() + d.getY()*w1;
				double z = o.getZ() + d.getZ()*w1;
				Point intersectPoint = new Point(x,y,z);
				Vector norm = intersectPoint.fromPoint(center);
				norm.normalize();
				data = new IntersectData(intersectPoint,w1,material,norm);
			} else if(w2 > 0){
				double x = o.getX() + d.getX()*w2;
				double y = o.getY() + d.getY()*w2;
				double z = o.getZ() + d.getZ()*w2;
				Point intersectPoint = new Point(x,y,z);
				Vector norm = intersectPoint.fromPoint(center);
				norm.normalize();
				data = new IntersectData(intersectPoint,w2,material,norm);
			}
		}
		return data;
	}


}