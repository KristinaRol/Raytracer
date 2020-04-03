package Raytracer;

public class Camera {
	
	Vec3 lowLeftCorner = new Vec3(-2, -1, -1);
	Vec3 horizontal = new Vec3(4, 0, 0);
	Vec3 vertical = new Vec3(0, 2, 0);
	Vec3 origin = new Vec3(0, 0, 0);
	
	public Ray getRay(double u, double v) {
		Vec3 w = horizontal.skalMul(u).add(vertical.skalMul(v));
        return new Ray(origin, lowLeftCorner.add(w).subtract(origin));
    }

}
