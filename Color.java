
public class Color{
	private double r,g,b,rgb;

	public Color(double r, double g, double b){
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public Color(Color c){
		this.r = c.getR();
		this.g = c.getG();
		this.b = c.getB();
	}

	public double getR(){
		return r;
	}

	public void setR(double r){
		this.r = r;
	}

	public void addR(double rad){
		r += rad;
	}

	public double getG(){
		return g;
	}

	public void setG(double g){
		this.g = g;
	}

	public void addG(double rad){
		g += rad;
	}

	public double getB(){
		return b;
	}

	public void setB(double b){
		this.b = b;
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
		//double rLD = (r * 0.27) / max.getR();
		double rLD = r / max.getR();
		if(rLD > 1.0){
			System.out.print("rLD: "+rLD);
			rLD = 1.0;
		}
		//double gLD = (g * 0.67) / max.getG();
		double gLD = g/ max.getG();
		if(gLD > 1.0){
			System.out.print("gLD: "+gLD);
			gLD = 1.0;
		}
		//double bLD = (b * 0.06) / max.getB();
		double bLD = b / max.getB();
		if(bLD > 1.0){
			System.out.print("bLD: "+bLD);
			bLD = 1.0;
		}
		int rgb = ((int)(rLD*255));
		rgb = (rgb << 8) + ((int)(gLD*255));
		rgb = (rgb << 8) + ((int)(bLD*255));
		return rgb;
	}

}