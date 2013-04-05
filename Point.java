import org.apache.commons.math3.linear.*;

public class Point {
	private RealVector location;

	public Point(){
		location = new ArrayRealVector(new double[]{0,0,0,1});
	}

	public Point(double x, double y, double z){
		location = new ArrayRealVector(new double[]{x,y,z,1});
	}

	public double getX(){
		return location.getEntry(0);
	}

	public double getY(){
		return location.getEntry(1);
	}

	public double getZ(){
		return location.getEntry(2);
	}

	public double distance(Point other){
		return location.getDistance(other.location);
	}

	public Vector fromPoint(Point other){
		double x = getX() - other.getX();
		double y = getY() - other.getY();
		double z = getZ() - other.getZ();
		return new Vector(x,y,z);
	}

	public Point addVector(Vector other){
		double x = getX() + other.getX();
		double y = getY() + other.getY();
		double z = getZ() + other.getZ();
		return new Point(x,y,z);
	}

	public String toString(){
		return location.toString();
	}


	public void transform(RealMatrix m){
		location = m.operate(location);
	}

	public static void main(String[] args){
		Point p1 = new Point(1,0,3);
		Point p2 = new Point(-1,2,0);
		double dist = p1.distance(p2);
		System.out.println("Testing distance"+p1+" and"+p2+": "+dist);

		RealMatrix m = new Array2DRowRealMatrix(new double[][]{{1,0,0,2},{0,1,0,3},{0,0,1,4},{0,0,0,1}});
		System.out.println("Transformation matrix: "+m+"\n Points Before: "+p1+" "+p2);
		p2.transform(m);
		p1.transform(m);
		System.out.println("Points After: "+p1+" "+p2);
	}
}