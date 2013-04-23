import org.apache.commons.math3.linear.*;
import java.util.*;

public class World{
	private List<SceneObject> objects = new LinkedList<SceneObject>();
	private List<Light> lights = new LinkedList<Light>();
	private Color background = new Color(0.492156, 0.48431372, 1.0);
		//private Color background = new Color(3, 3, 3.0);
	private static final double epsilon = 0.0001;
	private static final int MAX_DEPTH = 5;

	public void addObject(SceneObject obj){
		objects.add(obj);
	}

	public void addLight(Light light){
		lights.add(light);
	}

	public void setBackground(Color c){
		background = c;
	}

	public boolean transformObject(SceneObject obj, RealMatrix m){
		boolean found = false;
		for(SceneObject sObject : objects){
			if(obj == sObject){
				sObject.transform(m);
				found = true;
				break;
			}
		}
		return found;
	}

	public void transformAll(RealMatrix m){
		for(SceneObject sObject : objects ){
			sObject.transform(m);
		}
		for(Light light : lights){
			light.transform(m);
		}
	}


	public Color spawnRay(double x, double y, double z, double rx, double ry, double rz, int depth){
		return spawnRay(new Ray(x,y,z,rx,ry,rz),depth);
	}

	public IntersectData findClosestIntersection(Ray r){
		double closest = Double.MAX_VALUE;
		IntersectData closestIntersect = null;
		for(SceneObject sObject : objects){
			IntersectData iData = sObject.intersect(r);
			if(iData != null){
				double distance = iData.getDistance();
				if(distance > epsilon && distance < closest){
					closest = distance;
					closestIntersect = iData;
				}
			}
		}
		return closestIntersect;
	}

	public Color spawnRay(Ray r, int depth){
		
		Color rayColor = background;
		IntersectData closestIntersect = findClosestIntersection(r);
		if(closestIntersect != null){
			//Find view direction
			Vector rayDir = r.getDirection();
			Vector viewDirection = new Vector(-rayDir.getX(),-rayDir.getY(),-rayDir.getZ());
			viewDirection.normalize();
			closestIntersect.setViewDirection(viewDirection);
			//Find lights directly hitting the intersection location
			for(Light light : lights){
				boolean inLight = spawnShadowRay(light,closestIntersect.getIntersectPoint());
				if(inLight){
					closestIntersect.addLight(light);
				}
			}
			//Find Ray color
			rayColor = PhongIllumination.illuminate(closestIntersect);

			//Recursively spawn rays for reflection
			double reflectivity = closestIntersect.getMaterial().getReflective();
			if(reflectivity > 0 && depth < MAX_DEPTH){
				Vector norm = closestIntersect.getNormal();
				Vector reflectRayDir = rayDir.subtract(norm.scale(2*(norm.dot(rayDir))));
				reflectRayDir.normalize();
				Ray reflectRay = new Ray(closestIntersect.getIntersectPoint(),reflectRayDir);
				Color reflectColor = spawnRay(reflectRay,depth+1);
				reflectColor.scaleBy(reflectivity);
				rayColor.addColor(reflectColor);
			}
		}
		return rayColor;
	}


	/**
	*Returns true if the intersect point(ip) is under direct light of light l
	*/
	public Boolean spawnShadowRay(Light l,Point iP){
		boolean inLight = false;
		Point lP = l.getLocation();
		double distance = iP.distance(lP);
		Vector toLight = lP.fromPoint(iP);
		toLight.normalize();
		Ray shadowRay = new Ray(iP,toLight);
		IntersectData closest = findClosestIntersection(shadowRay);
		if(closest == null || closest.getDistance() > distance){
			inLight = true;
		}
		return inLight;
	}

}