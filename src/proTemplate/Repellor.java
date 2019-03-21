package proTemplate;

import processing.core.PApplet;
import processing.core.PVector;

public class Repellor {

	PApplet parent;
	private float x;
	private float y;
	
	public Repellor(float x,float y,PApplet parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}
	
	
	public PVector getPos() {
		return new PVector(x,y);
	}
	
	public void show() {
		parent.stroke(255,0,0);
		parent.strokeWeight(2);
		parent.point(x, y);
	}
	
}
