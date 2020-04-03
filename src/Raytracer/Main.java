package Raytracer;

import java.io.FileWriter;
import java.util.Random;

public class Main {

	public final static int WIDTH = 200;
	public final static int HEIGHT = 100;
	public final static int samples_per_pixel = 100;
	public final static int maxDepth = 50;

	public static void main(String[] args) {
		try {
			FileWriter writer = new FileWriter("picture3.ppm");
			writer.write("P3\n" + WIDTH + " " + HEIGHT + "\n255\n");
			
			HittableList world = new HittableList();
			world.add(new Sphere(new Vec3(0,0,-1), 0.5));
			world.add(new Sphere(new Vec3(0,-100.5,-1), 100));
			Camera camera = new Camera();

			for (int j = HEIGHT - 1; j >= 0; j--) {
				if (j % 10 == 0)
					System.out.println("\rScanlines remaining: " + j + " ");
				
				for (int i = 0; i < WIDTH; i++) {
					Vec3 color = new Vec3();
					for(int s = 0; s<samples_per_pixel; s++) {
						double u = (double) (i + randomDouble()) / WIDTH;
						double v = (double) (j + randomDouble()) / HEIGHT;
						Ray ray = camera.getRay(u, v);
						color = color.add(RayColor(ray, world, maxDepth));
					}
					color.writeColor(writer, samples_per_pixel);
				}
			}

			writer.close();
		} catch (Exception e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static double hitSphere(Vec3 center, double radius, Ray r) {
		Vec3 oc = r.origin.subtract(center);
		double a = r.direction.lengthSquared();
		double halfb = oc.dot(r.direction);
		double c = oc.lengthSquared() - radius * radius;
		double discriminant = halfb * halfb - a * c;
		
		if (discriminant < 0) {
			return -1;
		} else {
			return (-halfb - Math.sqrt(discriminant)) / a;
		}
	}

	public static Vec3 RayColor(Ray r, Hittable world, int depth) {
		HitRecord rec = new HitRecord();
		
		//if we,ve exceeded the ray bounce limit, no more light is gathered
		if(depth<=0) return new Vec3();
		
		if(world.hit(r, 0.001, Double.POSITIVE_INFINITY, rec)) {
			Vec3 target = rec.p.add(rec.normal.add(Vec3.randomInHemisphere(rec.normal)));
			return (RayColor(new Ray(rec.p, target.subtract(rec.p)), world, depth-1)).skalMul(0.5);
		}

		Vec3 unitDirection = r.direction.normalize(); // y wert in [-1,1]
		double t = 0.5 * (unitDirection.y + 1.0); // verschieben wert in [0,1]
		Vec3 v = (new Vec3(1, 1, 1)).skalMul(1 - t);
		Vec3 w = (new Vec3(0.5, 0.7, 1)).skalMul(t);
		return v.add(w);
	}
	
	public static double randomDouble(double min, double max) {
		Random r = new Random();
		return min + (max - min) * r.nextDouble();
	}
	
	public static double randomDouble() {
		return randomDouble(0,1);
	}

}
