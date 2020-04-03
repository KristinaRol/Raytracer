package Raytracer;

import java.util.ArrayList;

public class HittableList extends Hittable {

	ArrayList<Hittable> objects = new ArrayList<Hittable>();

	public void clear() {
		objects.clear();
	}

	public void add(Hittable h) {
		objects.add(h);
	}

	@Override
	public boolean hit(Ray r, double tMin, double tMax, HitRecord rec) {
		HitRecord tempRec = new HitRecord();
		boolean hitAnything = false;
		double closestSoFar = tMax;

		for (Hittable object : objects) {
			if (object.hit(r, tMin, tMax, tempRec)) {
				hitAnything = true;
				if (closestSoFar > tempRec.t) {
					closestSoFar = tempRec.t;
					rec.frontFace = tempRec.frontFace;
					rec.normal = tempRec.normal;
					rec.p = tempRec.p;
					rec.t = tempRec.t;
				}
			}
		}
		return hitAnything;
	}
	
	
}
