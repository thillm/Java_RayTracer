import org.apache.commons.math3.linear.*;

public class Triangle implements SceneObject{

	private Point v0;
	private Point v1;
	private Point v2;
	private Material material;
	private static double EPSILON = 0.000001;

	public Triangle(Point v0, Point v1, Point v2, Material m){
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		material = m;
	}

	public Material getMaterial(){
		return material;
	}

	public void transform(RealMatrix m){
		v0.transform(m);
		v1.transform(m);
		v2.transform(m);
	}

	public String toString(){
		return "V0:"+v0+" V1:"+v1+" V2:"+v2;
	}

	public IntersectData intersect(Ray r){
		IntersectData data = null;
		Point o = r.getOrigin();
		Vector d = r.getDirection();
		Vector e1 = v1.fromPoint(v0);
		//e1.normalize();
		Vector e2 = v2.fromPoint(v0);
		//e2.normalize();
		Vector bigT = o.fromPoint(v0);
		//bigT.normalize();
		Vector p = d.cross(e2);
		//p.normalize();
		Vector q = bigT.cross(e1);
		//q.normalize();

		double denom = p.dot(e1);
		if(Math.abs(denom) > EPSILON){
			double t = (q.dot(e2))/denom;
			double u = (p.dot(bigT))/denom;
			double v = (q.dot(d))/denom;
			if(t > 0 && u > 0 && v > 0 && (u+v) < 1){
				double x = o.getX() + d.getX()*t;
				double y = o.getY() + d.getY()*t;
				double z = o.getZ() + d.getZ()*t;
				Point intersectPoint = new Point(x,y,z);
				Vector norm = e1.cross(e2);
				norm.normalize();
				data = new IntersectData(intersectPoint,t,material,norm);
			}
		}
		return data;
	}

}