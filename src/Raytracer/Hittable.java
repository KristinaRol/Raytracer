package Raytracer;

/**
 * Abstract class for anything a ray can hit.
 */
public abstract class Hittable {

	/**
	 * Wir schauen ob ray für t im bereich (tMin, tMax) irgendetwas trifft.
	 * @param tMin betrachte nicht den ganzen Strahl sondern einen Ausschnitt, von tmin bis tmax
	 * @param rec Der HitRecord indem die Infos gespeichert werden.
	 * @return True falls etwas getroffen wurde oder nicht.
	 */
	public abstract boolean hit(Ray r, double tMin, double tMax, HitRecord rec);

}
