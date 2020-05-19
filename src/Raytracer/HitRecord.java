package Raytracer;

/**
 * In einer Instanz dieser Klasse sind Infos darüber gespeichert wo und wie ein
 * Strahl irgendein hattable object getroffen hat.
 */
public class HitRecord {

	/**
	 * p ist der Eintritspunkt vom Strahl in das hittable object.
	 */
	public Vec3 p;
	/**
	 * Normalenvektor der senkrecht aus dem Eintritspunkt zeigt, also Normalenvektor
	 * der Tangetialebende an dem Punkt, Orientierung ist entgegengesetzt des
	 * Strahls.
	 */
	public Vec3 normal;
	/**
	 * Parameter t der zum Eintrittspunkt führt, p=ray(t).
	 */
	public double t;
	/**
	 * True falls Vectoren in entgegengesetze Richtung (ray kommt von außen, normal
	 * zeigt nach außen), false falls gleiche Richtung (ray kommt von innen, normal
	 * zeigt nach außen).
	 */
	public boolean frontFace;

	/**
	 * Setzt den normalenvektor so, dass er entgegengesetzt des rays zeigt.
	 */
	public void set_face_normal(Ray ray, Vec3 outwardNormal) {
		frontFace = ray.direction.dot(outwardNormal) < 0;
		normal = frontFace ? outwardNormal : outwardNormal.negate();
	}
}
