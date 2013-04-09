import org.apache.commons.math3.linear.*;

public class Triangle implements SceneObject{

	private Point v0;
	private Point v1;
	private Point v2;
	private Material material1;
	private Material material2;
	private static double EPSILON = 0.000001;
	private static double SQUARE_SIZE = 0.1;
	private static double MAND_SIZE = 0.2;
	private static double MAND_WIDTH = 1.0/MAND_SIZE;

	public Triangle(Point v0, Point v1, Point v2, Material m1, Material m2){
		this.v0 = v0;
		this.v1 = v1;
		this.v2 = v2;
		material1 = m1;
		material2 = m2;
	}

	public Material checkerShader(double u, double v){
		Material mat = null;
		int row = (int)(u/SQUARE_SIZE);
		int col = (int)(v/SQUARE_SIZE);
		//System.out.println("u:"+u+" v:"+v+" row:"+row+" col:"+col);
		if((row + col) % 2 == 0){
			mat = material1;
		}else{
			mat = material2;
		}
		return mat;
	}


	public Material mandlebrot(double u, double v){
		Material mat;
		double minRe = -2.0;
		double maxRe = 1.0;
		double minIm = -1.2;
		double maxIm = minIm+(maxRe-minRe);
		double re_factor = (maxRe-minRe)/(MAND_WIDTH-1);
		double im_factor = (maxIm-minIm)/(MAND_WIDTH-1);
		int maxIter = 20;

		double c_im = maxIm - (v * MAND_WIDTH) *im_factor;
		double c_re = minRe + (u * MAND_WIDTH) * re_factor;
		double z_re = c_re;
		double z_im = c_im;	
		boolean isInside = true;
		for(int n=0; n<maxIter; ++n)
        {
            double z_re2 = z_re*z_re;
            double z_im2 = z_im*z_im;
            if((z_re2 + z_im2) > 4)
            {
                isInside = false;
                break;
            }
            z_im = (2*z_re*z_im) + c_im;
            z_re = z_re2 - z_im2 + c_re;
        }

        if(isInside){
        	mat = material1;
		}else{
			mat = material2;
		}

		return mat;
	}

	public void transform(RealMatrix m){
		v0.transform(m);
		v1.transform(m);
		v2.transform(m);
	}

	public String toString(){
		return "V0:"+v0+" V1:"+v1+" V2:"+v2;
	}

	public IntersectData intersect(Ray r){
		IntersectData data = null;
		Point o = r.getOrigin();
		Vector d = r.getDirection();
		Vector e1 = v1.fromPoint(v0);
		//e1.normalize();
		Vector e2 = v2.fromPoint(v0);
		//e2.normalize();
		Vector bigT = o.fromPoint(v0);
		//bigT.normalize();
		Vector p = d.cross(e2);
		//p.normalize();
		Vector q = bigT.cross(e1);
		//q.normalize();

		double denom = p.dot(e1);
		if(Math.abs(denom) > EPSILON){
			double t = (q.dot(e2))/denom;
			double u = (p.dot(bigT))/denom;
			double v = (q.dot(d))/denom;
			if(t > 0 && u > 0 && v > 0 && (u+v) < 1){
				double x = o.getX() + d.getX()*t;
				double y = o.getY() + d.getY()*t;
				double z = o.getZ() + d.getZ()*t;
				Point intersectPoint = new Point(x,y,z);
				Vector norm = e1.cross(e2);
				norm.normalize();
				Material mat = mandlebrot(u,v);
				data = new IntersectData(intersectPoint,t,mat,norm);
			}
		}
		return data;
	}

}