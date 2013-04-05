import org.apache.commons.math3.linear.*;

public class Light{
	private Point location;
	private Color color;

	public Light(Point location,Color color){
		this.location = location;
		this.color = color;
	}

	public Color getColor(){
		return color;
	}

	public Point getLocation(){
		return location;
	}

	void transform(RealMatrix m){
		location.transform(m);
	}
}