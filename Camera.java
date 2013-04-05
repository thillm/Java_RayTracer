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
/*	
		RealMatrix m2 = new Array2DRowRealMatrix(new double[][]{
										{xAxis.getX(),   yAxis.getX(),   zAxis.getX(),   0},
										{xAxis.getY(),   yAxis.getY(),   zAxis.getY(),   0},
										{xAxis.getZ(),   yAxis.getZ(),   zAxis.getZ(),   0},
										{0,				 0,				 0,				 1}
										});

		RealMatrix m1 = new Array2DRowRealMatrix(new double[][]{
										{1,   0,   0,   -position.getX()},
										{0,   1,   0,   -position.getY()},
										{0,   0,   1,   -position.getZ()},
										{0,   0,   0,   1				}
										});

		view = m2.multiply(m1);
*/		
/*		
		view = new Array2DRowRealMatrix(new double[][]{
										{xAxis.getX(),   yAxis.getX(),   zAxis.getX(),   0},
										{xAxis.getY(),   yAxis.getY(),   zAxis.getY(),   0},
										{xAxis.getZ(),   yAxis.getZ(),   zAxis.getZ(),   0},
										{-eye.dot(xAxis),-eye.dot(yAxis),-eye.dot(zAxis),1}
										});			
*/
		view = new Array2DRowRealMatrix(new double[][]{
										{xAxis.getX(),   yAxis.getX(),   zAxis.getX(),   -eye.dot(xAxis)},
										{xAxis.getY(),   yAxis.getY(),   zAxis.getY(),   -eye.dot(yAxis)},
										{xAxis.getZ(),   yAxis.getZ(),   zAxis.getZ(),   -eye.dot(zAxis)},
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
				Point p = new Point(x,y,-FILM_DISTANCE);
				Vector v = p.fromPoint(origin);
				v.normalize();
				Ray r = new Ray(p,v);
				Color c = w.spawnRay(r);
				image[i][j] = c;
			}
		}
		return image;
	}

	public static void main(String[] args){
		System.out.println("pixelWidth: "+pixelWidth+" "+"pixelHeigh: "+pixelHeigh);
		Camera c = new Camera();
		c.render(new World());
	}
}