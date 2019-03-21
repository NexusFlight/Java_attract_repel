package proTemplate;


import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;

public class Template extends PApplet {
	ArrayList<Particle> p = new ArrayList<Particle>();
	ArrayList<Attractor> a = new ArrayList<Attractor>();
	ArrayList<Repellor> r = new ArrayList<Repellor>();
	public static void main(String[] args) {
		PApplet.main("proTemplate.Template");

	}

	public void settings() {
		size(1000, 900,P3D);
	}

	public void setup() {
		background(0);
		for (int i = 0; i < 10; i++) {
			p.add(new Particle(this,random(width),random(height),random(254),random(254),random(254),1000,random(50,100)));	
		}

		for (int i = 0; i < 5; i++) {
			a.add(new Attractor(random(width),random(height),this));	
		}

		for (int i = 0; i < 2; i++) {
			r.add(new Repellor(random(width),random(height),this));	
		}
	}

	public void draw() {

//		for(Attractor a : a) {
//			a.show();	
//		}
//		for (Repellor r : r) {
//			r.show();
//		}

		for (Particle p : p) {
			p.show();
			p.update();
			p.attract(p.getClosestAttractor(a));
			p.repel(p.getClosestRepellor(r));
		}



	}
}
