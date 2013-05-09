import org.apache.commons.math3.linear.*;

public class TestScene{
	public static void main(String[] args){
		World w = new World();
		Sphere sp1 = new Sphere(new Point(-3,4,4),3.0,new Material(new Color(0.0,0,0),new Color(0.0,0,0),new Color(1.0,1.0,1.0),12,1.0,0.0,1.0));
		w.addObject(sp1);
		Sphere sp2 = new Sphere(new Point(-7,6,10),3.0,new Material(new Color(0,0,0.0),new Color(0,0,0.0),new Color(1.0,1.0,1.0),30,0.0,0.9,1.01));
		w.addObject(sp2);
		Sphere sp3 = new Sphere(new Point(-14,10,12),3.0,new Material(new Color(0,0,1.0),new Color(0,0,1.0),new Color(1.0,1.0,1.0),30,1.0,0.0,1.0));
		//w.addObject(sp3);
		Sphere sp4 = new Sphere(new Point(-11,4,5),3.0,new Material(new Color(1.0,0,0),new Color(1.0,0,0),new Color(1.0,1.0,1.0),12,1.0,0.0,1.0));
		//w.addObject(sp4);
		//Material checker1 = new Material(new Color(0.5,0.0,0.5),new Color(0.5,0.0,0.5),new Color(1.0,1.0,1.0),1.0,0.0,0.0,1.0);
		//Material checker2 = new Material(new Color(1.0,0.2,0),new Color(1.0,0.2,0),new Color(1.0,1.0,1.0),1.0,0.0,0.0,1.0);
		Material checker1 = new Material(new Color(1.0,0.0,0.0),new Color(1.0,0.0,0.0),new Color(1.0,1.0,1.0),1.0,0.0,0.0,1.0);
		Material checker2 = new Material(new Color(0.94,0.83,0.39),new Color(0.94,0.83,0.39),new Color(1.0,1.0,1.0),1.0,0.0,0.0,1.0);
		Triangle t1 = new Triangle(new Point(-20,0,-20),new Point(-20,0,20),new Point(20,0,-20),checker1,checker2);
		w.addObject(t1);
		Triangle t2 = new Triangle(new Point(20,0,20),new Point(20,0,-20),new Point(-20,0,20),checker1,checker2);
		w.addObject(t2);
		Light l1 = new Light(new Point(-10,20,30),new Color(1.0,1.0,1.0));
		w.addLight(l1);
		Light l2 = new Light(new Point(0,20,0),new Color(1.0,1.0,1.0));
		w.addLight(l2);
		Camera camera = new Camera(new Point(-7,7,30),new Point(-7,6,12), new Vector(0,1,0));
		RealMatrix view = camera.createViewMatrix();
		w.transformAll(view);
		Color[][] image = camera.render(w);
		double ldMax = 1.0;
		//ToneReproduction.reinhard(image,ldMax);
		ToneReproduction.ward(image,ldMax);
		ImageCreater.makeImage(image,ldMax,"TestScene.png");
	}
}