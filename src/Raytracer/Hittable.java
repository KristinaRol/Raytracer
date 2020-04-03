package Raytracer;

public abstract class Hittable {

	/**
	 * 
	 * @param r strahl
	 * @param tMin betrachte nicht den ganzen strahl sondern einen ausschnitt, von tmin bis tmax
	 * @param tMax
	 * @param rec
	 * @return
	 */
	public abstract boolean hit(Ray r, double tMin, double tMax, HitRecord rec);

}
