import java.io.*;
import java.awt.image.*;
import javax.imageio.*;

public class ImageCreater{
	private static final String FILE_LOCATION = "./pictures/";

	public static void makeImage(Color[][] colors,String fileName){
		int width = colors[0].length;
		int height = colors.length;
		double maxR = 0;
		double maxG = 0;
		double maxB = 0;
		double r;
		double g;
		double b;
		for(int i=0; i < height; i++){
			for(int j=0; j < width; j++){
				r = colors[i][j].getR();
				g = colors[i][j].getG();
				b = colors[i][j].getB();
				if(r > maxR){
					maxR = r;
				}
				if(g > maxG){
					maxG = g;
				}
				if(b > maxB){
					maxB = b;
				}
			}
		}		

		Color max = new Color(maxR,maxG,maxB);

		int[] pixels = new int[width * height];
		int count = 0;
		for(int i=0; i < height; i++){
			for(int j=0; j < width; j++){
				pixels[count] = colors[i][j].getRGB(max);
				count++;
			}
		}

		try{
			BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			img.getRaster().setDataElements(0, 0, width, height, pixels);
			File outputFile = new File(FILE_LOCATION+fileName);
			ImageIO.write(img,"png",outputFile);
		} catch (IOException e) {
			System.err.print(e);
		}
	}

	public static void main(String[] args){
		Color red = new Color(255,0,0);
		Color blue = new Color(0,0,255);
		Color[][] colors = new Color[50][50];
		for(int i=0; i < 50; i++){
			for(int j=0; j < 50; j++){
				if(i < 25){
					colors[i][j] = red;
				}else{
					colors[i][j] = blue;
				}
			}
		}
		makeImage(colors,"TestImage.png");
	}
}