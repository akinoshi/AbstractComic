package ac.panels;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import processing.core.PApplet;

public class SecondDay extends Panel {

	public int res;
	public float step;
	public float[] ropeP, ropeV;
	public ArrayList<Cloud> clouds;
	
	Cloud c;
	Wave w;
	
	public SecondDay(PApplet p, Rectangle a) {
		super(p, a);
		// res = size.width;
		res = 30;
		step = size.width/res;
		ropeP = new float[res];
		ropeV = new float[res];
		
		for(int i = 0; i < res; i++) {
			ropeP[i] = 0;
			ropeV[i] = 0;
		}
		
		// ropeP[res/2] = 400;
		
		c = new Cloud();
		clouds = new ArrayList<Cloud>();
		for(int i = 0; i < 5; i++) {
			// clouds.add(new Cloud());
			clouds.add(new Cloud(clouds));
		}
		
		w = new Wave();
	}

	@Override
	public void display() {
		if(isInvert) {
			p.fill(0, 14, 33);
		} else {
			p.fill(255, 241, 222);
		}
		p.noStroke();
		p.rect(size.x, size.y, size.width, size.height);
		
		// c.display();
		for(int i = 0; i < clouds.size(); i++) {
			Cloud tempCloud = (Cloud) clouds.get(i);
			tempCloud.display();
		}
		
		p.noStroke();
		if(isInvert) {
			p.fill(136, 84, 60);
		} else {
			p.fill(119, 171, 195);
		}
		p.pushMatrix();
		p.translate(size.x, size.y+size.height/2);
		
		p.beginShape();
		p.vertex(0, ropeP[0]);
		for(int i = 1; i < res; i++) {
			p.vertex(step*i, ropeP[i]);
			// p.curveVertex(step*i, ropeP[i]);
		}
		p.vertex(size.width,ropeP[res-1]);
		p.vertex(size.width, size.height/2);
		p.vertex(0, size.height/2);
		p.endShape();
		p.popMatrix();
		
		w.display();
	}

	@Override
	public void update() {
		// c.update();
		
		ropeP[res-1] = ropeP[res-2];
		
		for(int i = 1; i < res-1; i++) {
			ropeV[i] += ((ropeP[i-1]-ropeP[i])) + (ropeP[i+1]-ropeP[i])/1.05;
			ropeV[i] -= ropeP[i]/100f;
			ropeV[i] /= 1.13f;
		}
		
		for(int i = 1; i < res-1; i++) {
			ropeP[i] += ropeV[i]/2;
		}
	}
	
	public void bang() {
		float temp = size.height/8;
		ropeV[0] -= temp;
		ropeV[1] -= temp;
		ropeV[2] -= temp;		
	}

	class Cloud {
		Rectangle bounds;
		Point2D pt1, pt2, pt3;
		float r1, r2, r3;
		
		Cloud() {
			pt1 = new Point2D.Float(p.random(size.x, size.x+size.width), p.random(size.y, size.y+size.height/2));
			pt2 = new Point2D.Float((float) pt1.getX()+p.random(size.width/12, size.width/10), (float) pt1.getY());
			pt3 = new Point2D.Float((float) pt2.getX()+p.random(size.width/12, size.width/10), (float) pt2.getY());
			r1 = p.random(size.width/10, size.width/6);
			r2 = p.random(size.width/10, size.width/6);
			r3 = p.random(size.width/10, size.width/6);
			bounds = new Rectangle();
			bounds.x = (int) (pt1.getX() - r1/2);
			bounds.width = (int) (pt3.getX() + r1/2) - bounds.x;
			bounds.y = (int) (pt1.getY() - Math.max(Math.max(r1/2, r2/2), r3/2));
			bounds.height = (int) Math.max(Math.max(r1/2, r2/2), r3/2);
		}
		
		Cloud(ArrayList<Cloud> cs) {
			boolean exit = false;
			while(!exit) {
				pt1 = new Point2D.Float(p.random(size.x, size.x+size.width), p.random(size.y, size.y+size.height/2));
				pt2 = new Point2D.Float((float) pt1.getX()+p.random(size.width/18, size.width/16), (float) pt1.getY());
				pt3 = new Point2D.Float((float) pt2.getX()+p.random(size.width/18, size.width/16), (float) pt2.getY());
				r1 = p.random(size.width/16, size.width/8);
				r2 = p.random(size.width/16, size.width/8);
				r3 = p.random(size.width/16, size.width/8);
				bounds = new Rectangle();
				bounds.x = (int) (pt1.getX() - r1/2);
				bounds.width = (int) (pt3.getX() + r3/2) - bounds.x;
				bounds.y = (int) (pt1.getY() - Math.max(Math.max(r1/2, r2/2), r3/2));
				bounds.height = (int) Math.max(Math.max(r1/2, r2/2), r3/2);
				
				int count = 0;
				if(cs.size() > 0) {
					for(int i = 0; i < cs.size(); i++) {
						Cloud tempCloud = (Cloud) cs.get(i);
						if(!bounds.intersects(tempCloud.bounds) && size.contains(bounds)) {
							count++;
						}
					}
					if(count == cs.size()) exit = true;
				} else {
					if(size.contains(bounds)) exit = true;
				}
				
			}
		}
		
		public void update() {
			/*
			pt1.setLocation(pt1.getX()+1, pt1.getY());
			pt2.setLocation(pt2.getX()+1, pt2.getY());
			pt3.setLocation(pt3.getX()+1, pt3.getY());
			*/
			bounds.x = (int) (pt1.getX() - r1/2);
			bounds.width = (int) (pt3.getX() + r3/2) - bounds.x;
			bounds.y = (int) (pt1.getY() - Math.max(Math.max(r1/2, r2/2), r3/2));
			bounds.height = (int) Math.max(Math.max(r1/2, r2/2), r3/2);
		}
		
		public void display() {
			// p.stroke(255, 0, 192);
			p.noStroke();
			if(isInvert) {
				p.fill(216, 182, 119);
			} else {
				p.fill(39, 73, 136);
			}
			// p.ellipse((float) pt1.getX(), (float) pt1.getY(), 5f, 5f);
			// p.ellipse((float) pt2.getX(), (float) pt2.getY(), 5f, 5f);
			// p.ellipse((float) pt3.getX(), (float) pt3.getY(), 5f, 5f);
			p.arc((float) pt1.getX(), (float) pt1.getY(), r1, r1, PI, TWO_PI);
			p.arc((float) pt2.getX(), (float) pt2.getY(), r2, r2, PI, TWO_PI);
			p.arc((float) pt3.getX(), (float) pt3.getY(), r3, r3, PI, TWO_PI);
			
			p.stroke(255, 0, 192);
			p.noFill();
			// p.rect(bounds.x, bounds.y, bounds.width, bounds.height);
		}
	}
	
	class Wave {
		float step;
		int number;
		
		Wave() {
			number = 5;
			step = size.width / number;
			// System.out.println(step);
		}
		
		public void update() {
			
		}
		
		public void display() {
			// p.stroke(64, 205, 189);
			// p.stroke(48, 135, 206);
			p.noFill();
			p.strokeWeight(5);
			for(int i = 0; i < number+1; i++) {
				if(i % 2 == 0) {
					if(isInvert) {
						p.stroke(191, 50, 66);
					} else {
						p.stroke(64, 205, 189);
					}
				} else {
					if(isInvert) {
						p.stroke(207, 120, 49);
					} else {
						p.stroke(48, 135, 206);
					}
				}
				if(i == 0) { 
					p.arc(size.x+step*i, size.y+5*size.height/8, step, step, 0, p.HALF_PI);
				} else if(i == number) {
					p.arc(size.x+step*i, size.y+5*size.height/8, step, step, p.HALF_PI, p.PI);
				} else {
					p.arc(size.x+step*i, size.y+5*size.height/8, step, step, 0, p.PI);
				}
				// p.point(step*i, 3*(size.y+size.height)/4);
			}
			
			for(int i = 0; i < number+1; i++) {
				if(i % 2 != 0) {
					if(isInvert) {
						p.stroke(191, 50, 66);
					} else {
						p.stroke(64, 205, 189);
					}
				} else {
					if(isInvert) {
						p.stroke(207, 120, 49);
					} else {
						p.stroke(48, 135, 206);
					}
				}
				if(i == 0) { 
					p.arc(size.x+step*i, size.y+8*size.height/10, step, step, 0, p.HALF_PI);
				} else if(i == number) {
					p.arc(size.x+step*i, size.y+8*size.height/10, step, step, p.HALF_PI, p.PI);
				} else {
					p.arc(size.x+step*i, size.y+8*size.height/10, step, step, 0, p.PI);
				}
				// p.point(step*i, 3*(size.y+size.height)/4);
			}
			p.strokeWeight(1);
		}
	}
}
