package Raytracer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Implemantation von Vektoren im R^3.
 */
public class Vec3 {

	public double x, y, z;

	/**
	 * Constructor for a vector with given components.
	 */
	public Vec3(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Constructor for 0 vector.
	 */
	public Vec3() {
		this(0, 0, 0);
	}

	public Vec3 add(Vec3 v) {
		return new Vec3(v.x + this.x, v.y + this.y, v.z + this.z);
	}

	public Vec3 subtract(Vec3 v) {
		return new Vec3(this.x - v.x, this.y - v.y, this.z - v.z);
	}

	/**
	 * Multiplies vector components with each other (not mathematical but useful).
	 */
	public Vec3 multpl(Vec3 v) {
		return new Vec3(this.x * v.x, this.y * v.y, this.z * v.z);
	}

	public Vec3 negate() {
		return new Vec3(-this.x, -this.y, -this.z);
	}

	public Vec3 skalMul(double d) {
		return new Vec3(d * this.x, d * this.y, d * this.z);
	}

	/**
	 * Standart-Skalarprodukt.
	 */
	public double dot(Vec3 v) {
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}

	/**
	 * Kreuzprodukt.
	 */
	public Vec3 cross(Vec3 v) {
		return new Vec3(this.y * v.z - this.z * v.y, this.z * v.x - this.x * v.z, this.x * v.y - this.y * v.x);
	}

	/**
	 * Euklidische Norm des Vektors.
	 */
	public double length() {
		return Math.sqrt(this.lengthSquared());
	}

	/**
	 * Euklidische Norm des Vektors zm Quadrat.
	 */
	public double lengthSquared() {
		return this.x * this.x + this.y * this.y + this.z * this.z;
	}

	/**
	 * Gibt den Vektor in normiert zurück.
	 */
	public Vec3 normalize() {
		double l = this.length();
		return new Vec3(this.x / l, this.y / l, this.z / l);
	}
	
	/**
	 * Gibt vector mit random Einträgen zwischen 0 und 1 zurück.
	 */
	public static Vec3 random() {
		Random r = new Random();
		return new Vec3(r.nextDouble(), r.nextDouble(), r.nextDouble());
	}
	
	/**
	 * Gibt vector mit random Einträgen zwischen min und max zurück.
	 */
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
	
	/**
	 * Writes rgb color value into the file, wird auf der farbe als vektor aufgerufen.
	 * @param samples_per_pixel
	 * @throws IOException
	 */
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
