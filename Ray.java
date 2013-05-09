import org.apache.commons.math3.linear.*;

public class Ray{
	private Point origin;
	private Vector direction;
	private double indexOfRefraction;

	public Ray(){
		origin = new Point();
		direction = new Vector();
		indexOfRefraction = 0.0;
	}

	public Ray(double x, double y, double z, double dx, double dy, double dz, double index){
		origin = new Point(x,y,z);
		direction = new Vector(dx,dy,dz);
		direction.normalize();
		indexOfRefraction = index;
	}

	public Ray(Point p, Vector v, double index){
		origin = p;
		direction = v;
		direction.normalize();
		indexOfRefraction = index;
	}

	public Point getOrigin(){
		return origin;
	}

	public Vector getDirection(){
		return direction;
	}

	public double getIndex(){
		return indexOfRefraction;
	}
}