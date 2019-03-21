package proTemplate;


import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class Particle{
	PApplet parent;
	PVector pos;
	PVector prevPos;
	PVector vel;
	PVector acc;
	float AG = 50;
	float RG = 10;
	float r;
	float g;
	float b;
	public Particle(PApplet parent,float x , float y,float r,float g,float b,float aG, float rG) {
		this.parent = parent;
		pos = new PVector(x,y);
		prevPos = pos.copy();
		vel = PVector.random2D();
		vel.limit(5);
		acc = new PVector();
		this.r = r;
		this.g = g;
		this.b = b;
		this.AG = aG;
		this.RG = rG;
	}
	
	public void update() {
		vel.add(acc);
		vel.limit(5);
		pos.add(vel);
		acc.mult(0);
		
		if(pos.x > parent.width) {
			pos.x = 0;
			prevPos.x = 0;
		}
		if(pos.x < 0) {
			pos.x = parent.width;
			prevPos.x = parent.width;
		}
		if(pos.y > parent.height) {
			pos.y = 0;
			prevPos.y = 0;
		}
		if(pos.y < 0) {
			pos.y = parent.height;
			prevPos.y = parent.height;
		}
	}
	
	public void show() {
		parent.stroke(r,g,b,100);
		parent.strokeWeight(2);
		parent.line(pos.x,pos.y,prevPos.x,prevPos.y);
		prevPos.x = pos.x;
		prevPos.y = pos.y;
	}
	
	public Attractor getClosestAttractor(ArrayList<Attractor> attractors) {
		Attractor output = null;
		float recordD = Float.MAX_VALUE;
		
		for (Attractor a:attractors) {
		
			PVector aPos = a.getPos();
			
			float d = pos.dist(aPos);
			if(d < recordD) {
				output = a;
				recordD = d;
			}
		}
		if(output == null) {
			output = attractors.get(0);
		}
		return output;
	}
	
	public Repellor getClosestRepellor(ArrayList<Repellor> repellors) {
			Repellor output = null;
			float recordD = Float.MAX_VALUE;
			
			for (Repellor a:repellors) {
			
				PVector aPos = a.getPos();
				
				float d = pos.dist(aPos);
				if(d < recordD) {
					output = a;
					recordD = d;
				}
			}
			if(output == null) {
				output = repellors.get(0);
			}
		return output;
	}
	
	public void attract(Attractor attractor) {
		PVector attractorPos = attractor.getPos();
		PVector force = PVector.sub(attractorPos, pos);
		float d = force.mag();
		float strength = AG / (d * d);
		if(d < RG) {
			strength *= -1;
		}
		force.setMag(strength);
		acc.add(force);
	}
	
	public void repel(Repellor repellor){
		PVector repellorPos = repellor.getPos();
		PVector force = PVector.sub(repellorPos, pos);
		float d = force.mag();
		float strength = RG / (d * d);
		force.setMag(-strength);
		acc.add(force);
	}
}
