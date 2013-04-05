import org.apache.commons.math3.linear.*;

public class Ray{
	private Point origin;
	private Vector direction;

	public Ray(){
		origin = new Point();
		direction = new Vector();
	}

	public Ray(double x, double y, double z, double dx, double dy, double dz){
		origin = new Point(x,y,z);
		direction = new Vector(dx,dy,dz);
		direction.normalize();
	}

	public Ray(Point p, Vector v){
		origin = p;
		direction = v;
		direction.normalize();
	}

	public Point getOrigin(){
		return origin;
	}

	public Vector getDirection(){
		return direction;
	}
}