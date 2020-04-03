package Raytracer;

/**
 * p ist eintritspunkt vom strahl in hittable object, normal ist normalenvektor,
 * der senkrecht aus dem eintritspunkt zeigt, also normalenvektor der
 * tangetialebende an dem punkt
 */
public class HitRecord {

	public Vec3 p;
	public Vec3 normal;
	public double t;
	public boolean frontFace; //true falls in entgegengesetze richtung, false falls gleiche richtung

	public HitRecord() {
		//normal = new Vec3();
	}

	public void set_face_normal(Ray ray, Vec3 outwardNormal) {
		frontFace = ray.direction.dot(outwardNormal) < 0; 
		normal = frontFace ? outwardNormal : outwardNormal.negate();
	}
}
