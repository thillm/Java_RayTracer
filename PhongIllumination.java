import java.util.*;

public class PhongIllumination{
	private static Color ambientLight = new Color(0.1,0.1,0.1);

	public static Color illuminate(IntersectData data){
		Color finalColor = new Color(0.0,0.0,0.0);
		//Caculate Specular and Diffuse Lighting
		List<Light> lights = data.getLights();
		Point iP = data.getIntersectPoint();
		Vector norm = data.getNormal();
		Vector viewDir = data.getViewDirection();
		Material mat = data.getMaterial();

		if(lights != null){
			for(Light light : lights){
				Point lP = light.getLocation();
				Vector toLight = lP.fromPoint(iP);
				Vector reflectionDirection = norm.scale((toLight.dot(norm) * 2)).subtract(toLight);
				toLight.normalize();
				reflectionDirection.normalize();
				//Find radiosity towards viewDirection from this light
				Color diffuseColor = diffuse(toLight,norm,mat,light);
				Color specularColor = specular(reflectionDirection,viewDir,mat,light);
				finalColor.addColor(diffuseColor);
				finalColor.addColor(specularColor);
			}
		}
		//Add Ambient Lighting
		Color ambientColor = ambient(mat);
		finalColor.addColor(ambientColor);
		return finalColor;
	}

	public static Color diffuse(Vector lightDirection, Vector norm, Material mat, Light light){
		Color matColor = mat.getDiffuse();
		Color lightColor = light.getColor();
		double intensity = lightDirection.dot(norm);
		if(intensity < 0){
			intensity = 0;
		}
		double diffuseR = lightColor.getR() * matColor.getR() * intensity;
		double diffuseG = lightColor.getG() * matColor.getG() * intensity;
		double diffuseB = lightColor.getB() * matColor.getB() * intensity;
		Color diffuseColor = new Color(diffuseR,diffuseG,diffuseB);

		return diffuseColor;
	}

	public static Color specular(Vector reflectionDirection, Vector viewDirection, Material mat, Light light){
		Color matColor = mat.getSpecular();
		Color lightColor = light.getColor();
		double result = reflectionDirection.dot(viewDirection);
		if(result < 0){
			result = 0.0;
		}
		double intensity = Math.pow(result,mat.getAlpha());
		if(intensity < 0){
			intensity = 0;
		}
		double specularR = lightColor.getR() * matColor.getR() * intensity;
		double specularG = lightColor.getG() * matColor.getG() * intensity;
		double specularB = lightColor.getB() * matColor.getB() * intensity;
		Color specularColor = new Color(specularR,specularG,specularB);
		return specularColor;
	}

	public static Color ambient(Material mat){
		Color matColor = mat.getAmbient();
		double ambientLightR = ambientLight.getR() * matColor.getR();
		double ambientLightG = ambientLight.getG() * matColor.getG();
		double ambientLightB = ambientLight.getB() * matColor.getB();
		Color ambientColor = new Color(ambientLightR,ambientLightG,ambientLightB);
		return ambientColor;
	}
}