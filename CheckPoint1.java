import org.apache.commons.math3.linear.*;

public class CheckPoint1{
	public static void main(String[] args){
		World w = new World();
		Sphere sp1 = new Sphere(new Point(-3,4,5),3.0,new Material(new Color(1.0,0,0),new Color(1.0,0,0),new Color(1.0,1.0,1.0),12));
		w.addObject(sp1);
		Sphere sp2 = new Sphere(new Point(-7,6,12),3.0,new Material(new Color(0,0,1.0),new Color(0,0,1.0),new Color(1.0,1.0,1.0),30));
		w.addObject(sp2);

		Material checker1 = new Material(new Color(0.5,0.0,0.5),new Color(0.5,0.0,0.5),new Color(1.0,1.0,1.0),1.0);
		Material checker2 = new Material(new Color(1.0,0.2,0),new Color(1.0,0.2,0),new Color(1.0,1.0,1.0),1.0);
		//Triangle t1 = new Triangle(new Point(-20,0,-20),new Point(20,0,-20),new Point(-20,0,20),checker1,checker2);
		Triangle t1 = new Triangle(new Point(-20,0,-20),new Point(-20,0,20),new Point(20,0,-20),checker1,checker2);
		w.addObject(t1);
		Triangle t2 = new Triangle(new Point(20,0,20),new Point(20,0,-20),new Point(-20,0,20),checker1,checker2);
		w.addObject(t2);
		Light l1 = new Light(new Point(-10,20,30),new Color(1.0,1.0,1.0));
		w.addLight(l1);
		Light l2 = new Light(new Point(0,20,0),new Color(1.0,1.0,1.0));
		w.addLight(l2);
		System.out.println("Triangle 1:"+t1);
		System.out.println("Before Transform:"+sp2);
		Camera camera = new Camera(new Point(0,50,0.01),new Point(0,7,0), new Vector(0,1,0));
		RealMatrix view = camera.createViewMatrix();
		System.out.println("Matrix :");
		System.out.println(view);
		w.transformAll(view);
		System.out.println("Triangle 1:"+t1);
		System.out.println("After Transform:"+sp2);
		Color[][] image = camera.render(w);
		ImageCreater.makeImage(image,"TestScene.png");
	}
}