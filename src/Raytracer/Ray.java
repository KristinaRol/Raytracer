package Raytracer;

public class Ray {

	public Vec3 origin, direction;
	
	public Ray(Vec3 origin, Vec3 direction) {
		this.origin = origin;
		this.direction = direction;
	}
	
	public Ray(Vec3 direction) {
		this(new Vec3(), direction);
	}
	
	public Vec3 at(double t) {
		return origin.add(direction.skalMul(t));
	}
}
