import org.apache.commons.math3.linear.*;

public class Vector{
	private RealVector direction;

	public Vector(){
		direction = new ArrayRealVector(new double[]{0,0,0,0});
	}

	public Vector(double x, double y, double z){
		direction = new ArrayRealVector(new double[]{x,y,z,0});
	}

	private Vector(RealVector dir){
		direction = dir;
	}

	public double getX(){
		return direction.getEntry(0);
	}

	public double getY(){
		return direction.getEntry(1);
	}

	public double getZ(){
		return direction.getEntry(2);
	}

	public Vector add(Vector other){
		return new Vector(direction.add(other.direction));
	}

	public Vector subtract(Vector other){
		return new Vector(direction.subtract(other.direction));
	}
	public Vector scale(double scaleAmmount){
		return new Vector(direction.copy().mapMultiplyToSelf(scaleAmmount));
	}
	public double dot(Vector other){
		return direction.dotProduct(other.direction);
	}

	public Vector cross(Vector other){
		double x = (getY()*other.getZ())-(getZ()*other.getY());
		double y = (getZ()*other.getX())-(getX()*other.getZ());
		double z = (getX()*other.getY())-(getY()*other.getX());
		return new Vector(x,y,z);
	}

	public double length(){
		return direction.getNorm();
	}

	public void normalize(){
		direction.unitize();
	}

	public void transform(RealMatrix m){
		direction = m.operate(direction);
	}

	public String toString(){
		return direction.toString();
	}

	public static void main(String[] args){
		Vector v1 = new Vector(2,4,5);
		System.out.println("V1 before normalize: "+v1);
		v1.normalize();
		System.out.println("V1 after normalize: "+v1);
	}
}