import org.apache.commons.math3.linear.*;

public interface SceneObject{
	
	IntersectData intersect(Ray r);
	void transform(RealMatrix m);
}