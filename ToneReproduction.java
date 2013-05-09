public class ToneReproduction{
	private static final double sigma = 1.001;

	public static void reinhard(Color[][] colors,double ldMax){
		double[][] lum = illuminance(colors);
		double lal = logAvgLum(lum);
		double scale = 0.18 / lal;
		//Scaled luminence
		for(int i=0; i < colors.length; i++){
			for(int j=0; j < colors[0].length; j++){
				colors[i][j].scaleBy(scale);
			}
		}
		//Relectance
		double r,g,b;
		for(int i=0; i < colors.length; i++){
			for(int j=0; j < colors[0].length; j++){
				r = colors[i][j].getR();
				g = colors[i][j].getG();
				b = colors[i][j].getB();
				colors[i][j].setR(r/(r+1));
				colors[i][j].setG(g/(g+1));
				colors[i][j].setB(b/(b+1));
			}
		}
		//target lum
		for(int i=0; i < colors.length; i++){
			for(int j=0; j < colors[0].length; j++){
				colors[i][j].scaleBy(ldMax);
			}
		}
	}
	
	public static void ward(Color[][] colors,double ldMax){
		double[][] lum = illuminance(colors);
		double lal = logAvgLum(lum);
		double sf = (1.219 +Math.pow((ldMax/2),0.4));
		sf = sf / (1.219 + Math.pow(lal,0.4));
		sf = Math.pow(sf,2.5);

		for(int i=0; i < colors.length; i++){
			for(int j=0; j < colors[0].length; j++){
				colors[i][j].scaleBy(sf);
			}
		}
	}

	public static double logAvgLum(double[][] lum){
		double sum = 0.0;
		for(int i=0; i < lum.length; i++){
			for(int j=0; j < lum[0].length; j++){
				sum += Math.log10(sigma + lum[i][j]);
			}
		}
		sum = (sum / (lum.length * lum[0].length));
		return (Math.pow(10,sum));
	}

	public static double[][] illuminance(Color[][] colors){
		double[][] lum = new double [colors.length][colors[0].length];
		Color c;
		for(int i=0; i < colors.length; i++){
			for(int j=0; j < colors[0].length; j++){
				c = colors[i][j];
				lum[i][j] = c.getR() * 0.27 + c.getG() * 0.67 + c.getB() * 0.06;
			}	
		}
		return lum;
	}


}