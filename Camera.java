import org.apache.commons.math3.linear.*;

public class Camera{

	private Point position;
	private Point lookAt;
	private Vector up;
	private static final double FILM_DISTANCE = 1.0;
	private static final double FILM_WIDTH_WORLD = 1.0;
	private static final double FILM_HEIGHT_WORLD = 1.0;
	private static final int FILM_WIDTH_PIXELS = 1000;
	private static final int FILM_HEIGHT_PIXELS = 1000;
	private static final double pixelHeigh = FILM_WIDTH_WORLD/FILM_WIDTH_PIXELS;
	private static final double pixelWidth = FILM_HEIGHT_WORLD/FILM_HEIGHT_PIXELS;
	private static final int SAMPLE_RATE = 3; 

	public Camera(){
		position = new Point(0,0,0);
		lookAt = new Point(0,0,-1);
		up = new Vector(0,1,0);
	} 

	public Camera(Point pos, Point lookAt, Vector up){
		position = pos;
		this.lookAt = lookAt;
		this.up = up;
		up.normalize();
	}

	public RealMatrix createViewMatrix(){
		RealMatrix view = null;
		//Find the basis of the new coordinate system
		Vector zAxis = position.fromPoint(lookAt);
		zAxis.normalize();
		Vector xAxis = up.cross(zAxis);
		xAxis.normalize();
		Vector yAxis = zAxis.cross(xAxis);
		yAxis.normalize();
		//Convert the camera pos to a Vector
		Vector eye = new Vector(position.getX(),position.getY(),position.getZ());
		//Construct the view matrix

		view = new Array2DRowRealMatrix(new double[][]{
										{xAxis.getX(),   xAxis.getY(),   xAxis.getZ(),   -eye.dot(xAxis)},
										{yAxis.getX(),   yAxis.getY(),   yAxis.getZ(),   -eye.dot(yAxis)},
										{zAxis.getX(),   zAxis.getY(),   zAxis.getZ(),   -eye.dot(zAxis)},
										{0,0,0,1}
										});

		return view;
	}

	public Color[][] render(World w){
		double startX = -FILM_WIDTH_WORLD/2 + pixelWidth/2;
		double startY = FILM_HEIGHT_WORLD/2 + -pixelHeigh/2;
		Color[][] image = new Color[FILM_HEIGHT_PIXELS][FILM_WIDTH_PIXELS];
		Point origin = new Point();
		for(int i=0; i<FILM_HEIGHT_PIXELS; i++){
			for(int j=0; j<FILM_WIDTH_PIXELS; j++){
				double x = startX + j * pixelWidth;
				double y = startY - i * pixelHeigh;
				/*
				Point p = new Point(x,y,-FILM_DISTANCE);
				Vector v = p.fromPoint(origin);
				v.normalize();
				//Ray r = new Ray(p,v);
				//Color c = w.spawnRay(r);
				*/
				Color c = renderPixel(w,x,y);
				image[i][j] = c;
			}
		}
		return image;
	}

	public Color renderPixel(World w, double x, double y){
		double incX = pixelWidth/SAMPLE_RATE;
		double incY = pixelHeigh/SAMPLE_RATE;
		double pixelStartX = x - pixelWidth/2;
		double pixelStartY = y + pixelHeigh/2;
		Point origin = new Point();
		Color colorSink = new Color(0.0,0.0,0.0);
		for(int i = 0; i < SAMPLE_RATE; i++ ){
			for(int j = 0; j < SAMPLE_RATE; j++){
				double sampleX = pixelStartX + incX * i;
				double sampleY = pixelStartY - incY * j;
				Point p = new Point(sampleX,sampleY,-FILM_DISTANCE);
				Vector v = p.fromPoint(origin);
				v.normalize();
				Ray r = new Ray(p,v);
				Color c = w.spawnRay(r,0);
				colorSink.addColor(c);
			}
		}
		colorSink.scaleBy((1.0/(SAMPLE_RATE*SAMPLE_RATE)));
		return colorSink;
	}

	public static void main(String[] args){
		System.out.println("pixelWidth: "+pixelWidth+" "+"pixelHeigh: "+pixelHeigh);
		Camera c = new Camera();
		c.render(new World());
	}
}