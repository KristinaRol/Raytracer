package Raytracer;

/**
 * Die Sphäre als mögliches Hittable Object.
 */
public class Sphere extends Hittable {

	Vec3 center;
	double radius;

	public Sphere(Vec3 center, double r) {
		this.center = center;
		this.radius = r;
	}

	@Override
	public boolean hit(Ray ray, double tMin, double tMax, HitRecord rec) {
		Vec3 oc = ray.origin.subtract(center);
		double a = ray.direction.lengthSquared();
		double halfb = oc.dot(ray.direction);
		double c = oc.lengthSquared() - radius * radius;
		double discriminant = halfb * halfb - a * c;

		if (discriminant > 0) {
			double root = Math.sqrt(discriminant);
			double temp = (-halfb - root) / a; //negative Wurzellösung, die Lösung mit dem kleineren t
			//schauen ob es in unserer Range liegt
			if (temp < tMax && temp > tMin) {
				//schreiben Informationen in unseren hitrecord rec
				rec.t = temp;
				rec.p = ray.at(rec.t);
				Vec3 outwardNormal = (rec.p.subtract(center)).skalMul(1/radius); //da Radius die Länge des Vektors hier ist
				rec.set_face_normal(ray, outwardNormal);
				return true;
			}
			temp = (-halfb + root) / a; //positive wurzellösung
			if (temp < tMax && temp > tMin) {
				rec.t = temp;
				rec.p = ray.at(rec.t);
				Vec3 outwardNormal = (rec.p.subtract(center)).skalMul(1/radius);
				rec.set_face_normal(ray, outwardNormal);
				return true;
			}
		}
		return false;
	}
}
