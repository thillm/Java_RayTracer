
public class Color{
	private double r,g,b,rgb;

	public Color(double r, double g, double b){
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public double getR(){
		return r;
	}

	public void addR(double rad){
		r += rad;
	}

	public double getG(){
		return g;
	}

	public void addG(double rad){
		g += rad;
	}

	public double getB(){
		return b;
	}

	public void addB(double rad){
		b += rad;
	}

	public void addColor(Color other){
		r += other.getR();
		g += other.getG();
		b += other.getB();
	}

	public void scaleBy(double value){
		r = r*value;
		g = g*value;
		b = b*value;
	}

	public int getRGB(Color max){
		int rgb = ((int)((r / max.getR())*255));
		rgb = (rgb << 8) + ((int)((g / max.getG())*255));
		rgb = (rgb << 8) + ((int)((b / max.getB())*255));
		return rgb;
	}

}