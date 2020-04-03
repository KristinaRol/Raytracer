package Raytracer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Vec3 {

	public double x, y, z;

	public Vec3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3() {
		this(0, 0, 0);
	}

	public Vec3 add(Vec3 v) {
		return new Vec3(v.x + this.x, v.y + this.y, v.z + this.z);
	}

	public Vec3 subtract(Vec3 v) {
		return new Vec3(this.x - v.x, this.y - v.y, this.z - v.z);
	}

	public Vec3 multpl(Vec3 v) {
		return new Vec3(this.x * v.x, this.y * v.y, this.z * v.z);
	}

	public Vec3 negate() {
		return new Vec3(-this.x, -this.y, -this.z);
	}

	public Vec3 skalMul(double d) {
		return new Vec3(d * this.x, d * this.y, d * this.z);
	}

	public double dot(Vec3 v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}

	public Vec3 cross(Vec3 v) {
		return new Vec3(this.y * v.z - this.z * v.y, this.z * v.x - this.x * v.z, this.x * v.y - this.y * v.x);
	}

	public double length() {
		return Math.sqrt(this.lengthSquared());
	}

	public double lengthSquared() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	public Vec3 normalize() {
		double l = this.length();
		return new Vec3(this.x / l, this.y / l, this.z / l);
	}
	
	public static Vec3 random() {
		Random r = new Random();
		return new Vec3(r.nextDouble(), r.nextDouble(), r.nextDouble());
	}
	
	public static Vec3 random(double min, double max) {
		return new Vec3(randomDouble(min, max),randomDouble(min, max),randomDouble(min, max));
	}
	
	//diffuse method 3 (rec.normal übergeben)
	public static Vec3 randomInHemisphere(Vec3 normal) {
		Vec3 inUnitSphere = randomUnitVector();
		if(inUnitSphere.dot(normal)>0) { //in the same hemisphere as the normal
			return inUnitSphere;
		}
		return inUnitSphere.negate();
	}
	
	//diffuse method 2
	public static Vec3 randomUnitVector() {
		double a = randomDouble(0, 2*Math.PI);
		double z = randomDouble(-1, 1);
		double r = Math.sqrt(1 - z*z);
		return new Vec3(r*Math.cos(a), r*Math.sin(a), z);
	}
	
	//diffuse method 1
	public static Vec3 randomInUnitSphere() {
		while(true) {
			Vec3 p = Vec3.random(-1, 1);
			if(p.lengthSquared() >= 1) continue;
			return p;
		}
	}
	
	public static double randomDouble(double min, double max) {
		Random r = new Random();
		return min + (max - min) * r.nextDouble();
	}

	public double clamp(double x, double min, double max) {
		if (x < min)
			return min;
		if (x > max)
			return max;
		return x;
	}

	public void writeColor(FileWriter writer, int samples_per_pixel) throws IOException {
		// divide color total by number of samples and gamma-correct for gamma value of 2.0
		double scale = 1.0 / samples_per_pixel;
		double r = Math.sqrt(scale * this.x);
		double g = Math.sqrt(scale * this.y);
		double b = Math.sqrt(scale * this.z);

		// write translated [0,255] value in color component
		writer.write((int) (256 * clamp(r, 0, 0.9999)) + " " + (int) (256 * clamp(g, 0, 0.9999)) + " "
				+ (int) (256 * clamp(b, 0, 0.9999)) + "\n");
	}

}
