package Raytracer;

/**
 * Rays implementiert als Geraden mit Stütz- und Richtungsvektor.
 */
public class Ray {

	public Vec3 origin, direction;
	
	/**
	 * Gerade durch origin in Richtung direction.
	 */
	public Ray(Vec3 origin, Vec3 direction) {
		this.origin = origin;
		this.direction = direction;
	}
	
	/**
	 * Gerade durch Urpsrung in Richtung direction.
	 */
	public Ray(Vec3 direction) {
		this(new Vec3(), direction);
	}
	
	/**
	 * Berechnet Punkt auf Geraden p=ray(t).
	 */
	public Vec3 at(double t) {
		return origin.add(direction.skalMul(t));
	}
}
