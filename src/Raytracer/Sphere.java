package Raytracer;

public class Sphere extends Hittable {

	Vec3 center;
	double radius;

	public Sphere(Vec3 center, double r) {
		this.center = center;
		this.radius = r;
	}

	public boolean hit(Ray r, double tMin, double tMax, HitRecord rec) {
		Vec3 oc = r.origin.subtract(center);
		double a = r.direction.lengthSquared();
		double halfb = oc.dot(r.direction);
		double c = oc.lengthSquared() - radius * radius;
		double discriminant = halfb * halfb - a * c;

		if (discriminant > 0) {
			double root = Math.sqrt(discriminant);
			double temp = (-halfb - root) / a; //negative wurzellösung, die lösung mit dem kleineren t
			if (temp < tMax && temp > tMin) {
				rec.t = temp;
				rec.p = r.at(rec.t);
				Vec3 outwardNormal = (rec.p.subtract(center)).skalMul(1/radius);
				rec.set_face_normal(r, outwardNormal);
				return true;
			}
			temp = (-halfb + root) / a; //positive wurzellösung
			if (temp < tMax && temp > tMin) {
				rec.t = temp;
				rec.p = r.at(rec.t);
				Vec3 outwardNormal = (rec.p.subtract(center)).skalMul(1/radius);
				rec.set_face_normal(r, outwardNormal);
				return true;
			}
		}
		return false;
	}
}
