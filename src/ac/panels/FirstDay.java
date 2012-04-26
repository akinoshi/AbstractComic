package ac.panels;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D.Float;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;

public class FirstDay extends Panel {

	public static final float REFRACTION_INDEX = 1.52f;
	
	public Point p1, p2, p3;
	public Line2D.Float l1, l2, l3, w1, w2, w3, w4;
	float centerX, centerY;
	float prismRadius, prismHeight;
	public ArrayList<Point2D> points;
	public Line2D.Float[] planes;
	public Line2D.Float[] walls;
	float theta1, theta2, angle;
	int avoidPlane;
	float index;
	LightHead lighthead;
	private Key[] keys;
	public ArrayList<LightHead> lightheads;
	Stars stars;
	Point rp1, rp2;
	Point[] rps;
	int[] rainbowColors = {p.color(255, 0, 0),
						   p.color(255, 127, 0),
						   p.color(255, 255, 0),
						   p.color(0, 255, 0),
						   p.color(0, 0, 255),
						   // p.color(75, 0, 130),
						   p.color(127, 0, 255)};
	int[] invertedRainbowColors = {p.color(0, 255, 255),
								   p.color(0, 128, 255),
								   p.color(0, 0, 255),
								   p.color(255, 0, 255),
								   p.color(255, 255, 0),
								   p.color(128, 255, 0)};
	
	float M = 0.8f;		// Mass
	float K = 0.1f;   	// Spring constant
	float D = 0.2f;  	// Damping
	float R = 0.0f;    	// Rest position
	float ps = 0.0f; 	// Position
	float vs = 0.0f;  	// Velocity
	float as = 0f;    	// Acceleration
	float f = 0f;     	// Force
	
	public FirstDay(PApplet p, Rectangle a) {
		super(p, a);
		centerX = a.x + a.width/2;
		centerY = a.y + a.height/2;
		prismRadius = (float) (0.3 * a.width * Math.sqrt(3) / 3);
		prismHeight = (float) (0.3 * a.width * Math.sqrt(3) / 2);
		this.p1 = new Point((int) centerX, (int) (centerY-prismRadius));
		this.p2 = new Point((int) (centerX+Math.cos(Math.PI/6)*prismRadius),
							(int) (centerY+Math.sin(Math.PI/6)*prismRadius));
		this.p3 = new Point((int) (centerX-Math.cos(Math.PI/6)*prismRadius),
							(int) (centerY+Math.sin(Math.PI/6)*prismRadius));
		
		// Set up triangle lines
		l1 = new Line2D.Float(p1.x, p1.y, p2.x, p2.y);
		l2 = new Line2D.Float(p2.x, p2.y, p3.x, p3.y);
		l3 = new Line2D.Float(p3.x, p3.y, p1.x, p1.y);
		planes = new Line2D.Float[3];
		planes[0] = l1;
		planes[1] = l2;
		planes[2] = l3;
		
		// Set up wall lines
		w1 = new Line2D.Float(a.x, a.y, a.x+a.width, a.y);
		w2 = new Line2D.Float(a.x+a.width, a.y, a.x+a.width, a.y+a.height);
		w3 = new Line2D.Float(a.x+a.width, a.y+a.height, a.x, a.y+a.height);
		w4 = new Line2D.Float(a.x, a.y+a.height, a.x, a.y);
		walls = new Line2D.Float[4];
		walls[0] = w1;
		walls[1] = w2;
		walls[2] = w3;
		walls[3] = w4;
		
		rp1 = new Point();
		rp2 = new Point();
		rps = new Point[7];
		for(int i = 0; i < rps.length; i++) {
			rps[i] = new Point();
		}
		
		points = new ArrayList<Point2D>();
		// setPoints();
		// lighthead = new LightHead(points.get(0));
		lighthead = new LightHead();
		keys = new Key[4];
		// setKeys();
		
		stars = new Stars(new Point2D.Double(centerX, centerY));
	}
	
	public void update() {
		
		if(!lighthead.isDead) {
			Point2D temp = new Point2D.Float((float) lighthead.getPosition().getX(), (float) lighthead.getPosition().getY());
			float tempX = (float) (temp.getX() + ( points.get(lighthead.index).getX() - lighthead.getPosition().getX() ) * 2 / 12);
			float tempY = (float) (temp.getY() + ( points.get(lighthead.index).getY() - lighthead.getPosition().getY() ) * 2 / 12);
			lighthead.addPosition(new Point2D.Float(tempX, tempY));
			
			if(lighthead.getDistanceTo(points.get(lighthead.index)) < 1) {
				
				if(lighthead.index < 3) {
					Key tempKey = keys[lighthead.index];
					tempKey.emit(points.get((lighthead.index+1)%points.size()));
				}
				
				lighthead.index++;
				if(lighthead.index > 3) {
					lighthead.die();
				}
			}
		}
		
		// Update particles
		if(!lighthead.isDead) {
			for(int j = 0; j < keys.length; j++) {
				Key tempKey = keys[j];
				if(tempKey.particles.size() > 0) {
					for(int i = 0; i < tempKey.particles.size(); i++) {
						if(Math.random() > 0.4) {
							Particle particle = (Particle) tempKey.particles.get(i);
					
							particle.x += particle.vx;
							particle.y += particle.vy;
					
							particle.vx *= 0.97;
							particle.vy *= 0.97;
					
							particle.rotation += particle.vr;
						}
				
						if(Math.random() > 0.95) {
							tempKey.particles.remove(0);
						}
				
						while(tempKey.particles.size() > 35) {
							tempKey.particles.remove(0);
						}
					}
				}
			}
		}
		
		if(lighthead.index == 3) R = theta2;
		updateSpring();
	}
	
	public void display() {
		if(isInvert) {
			p.fill(255);
		} else {
			p.fill(0);
		}
		p.noStroke();
		p.rect(size.x, size.y, size.width, size.height);

		stars.display();
		
		// Draw ring
		if(isInvert) {
			p.stroke(0, 0, 0, 128);
		} else {
			p.stroke(255, 255, 255, 128);
		}
		p.noFill();
		p.ellipse(centerX, centerY, prismHeight*2, prismHeight*2);
		
		/*
		// Draw points
		p.stroke(255, 0, 192, 128);
	    p.fill(255, 0, 192);
	    for(int i = 0; i < points.size(); i++) {
			Point temp = (Point) points.get(i);
			p.ellipse(temp.x, temp.y, 5, 5);
		}
		*/
		
		/*
	    // Draw lines
		p.stroke(255, 0, 192, 128);
	    p.noFill();
	    p.beginShape();
		for(int i = 0; i < points.size(); i++) {
			Point temp = (Point) points.get(i);
			p.vertex(temp.x, temp.y);
		}
		p.endShape();
		*/
	    
		// Draw light
		if(isInvert) {
			p.stroke(0, 7, 97);
		} else {
			p.stroke(255, 248, 158);
		}
		p.strokeWeight(2);
		p.beginShape();
		if(!lighthead.isDead) {
			for(int i = 0; i < lighthead.positions.size(); i++) {
				Point2D temp = (Point2D) lighthead.positions.get(i);
				// if(lighthead.index != 3) p.vertex((float) temp.getX(), (float) temp.getY());
				p.vertex((float) temp.getX(), (float) temp.getY());
			}
		}
		p.endShape();
		p.strokeWeight(1);
		
		// Draw prism shadow
		p.noStroke();
		p.beginShape(p.TRIANGLES);
		if(isInvert) {
			p.fill(0, 0, 0, 48);
		} else {
			p.fill(255, 255, 255, 48);
		}
		p.vertex(p1.x, p1.y);
		if(isInvert) {
			p.fill(0, 0, 0, 24);
		} else {
			p.fill(255, 255, 255, 24);
		}
		p.vertex(p2.x-prismHeight*0.2f, p2.y);
		if(isInvert) {
			p.fill(0, 0, 0, 12);
		} else {
			p.fill(255, 255, 255, 12);
		}
		p.vertex(p3.x, p3.y);
		p.endShape();
		
		// Draw prism
		if(isInvert) {
			p.stroke(0, 0, 0, 128);
		} else {
			p.stroke(255, 255, 255, 128);
		}
		p.beginShape(p.TRIANGLES);
		if(isInvert) {
			p.fill(0, 0, 0, 48);
		} else {
			p.fill(255, 255, 255, 48);
		}
		p.vertex(p1.x, p1.y);
		if(isInvert) {
			p.fill(0, 0, 0, 24);
		} else {
			p.fill(255, 255, 255, 24);
		}
		p.vertex(p2.x, p2.y);
		if(isInvert) {
			p.fill(0, 0, 0, 12);
		} else {
			p.fill(255, 255, 255, 12);
		}
		p.vertex(p3.x, p3.y);
		p.endShape();
		
		// Draw particles
		if(!lighthead.isDead) {
			for(int j = 0; j < keys.length; j++) {
				Key tempKey = keys[j];
				if(tempKey.particles.size() > 0) {
					for(int i = 0; i < tempKey.particles.size(); i++) {
						if(Math.random() > 0.4) {
							Particle particle = (Particle) tempKey.particles.get(i);
							/*
							particle.x += particle.vx;
							particle.y += particle.vy;
					
							particle.vx *= 0.97;
							particle.vy *= 0.97;
					
							particle.rotation += particle.vr;
							*/
							double x = particle.x + Math.cos(particle.rotation) * particle.rotationRadius;
							double y = particle.y + Math.sin(particle.rotation) * particle.rotationRadius;
					
							p.strokeWeight(2);
							if(isInvert) {
								p.stroke(0, 7, 97, p.random(0.3f*255, 255));
							} else {
								p.stroke(255, 248, 158, p.random(0.3f*255, 255));
							}
							p.point((float) x, (float) y);
							p.strokeWeight(1);
						}
						/*
						if(Math.random() > 0.95) {
							tempKey.particles.remove(0);
						}
				
						while(tempKey.particles.size() > 35) {
							tempKey.particles.remove(0);
						}
						*/
					}
				}
			}
		}
		
		/*
		p.noStroke();
		p.fill(255, 0, 192);
		p.ellipse(rp1.x, rp1.y, 10, 10);
		p.ellipse(rp2.x, rp2.y, 10, 10);
		*/
		
		// Draw rainbow
		if(lighthead.index == 3) {
			p.noStroke();
			Point tempPoint = (Point) points.get(2);
			for(int i = 0; i < rps.length-1; i++) {
				p.beginShape();
				if(isInvert) {
					p.fill(invertedRainbowColors[i]);
				} else {
					p.fill(rainbowColors[i]);
				}
				p.vertex(tempPoint.x, tempPoint.y);
				p.vertex(rps[i].x, rps[i].y);
				p.vertex(rps[i+1].x, rps[i+1].y);
				p.endShape(PConstants.CLOSE);
			}
		}
		
	}
	
	public void bang() {
		angle = (float) (Math.random()*2*Math.PI);
		setPoints();
		lighthead = new LightHead(points.get(0));
		lighthead.create();
		setKeys();
	}
	
	public void bang(int midi) {
		if(lighthead.isDead) {
			angle = (float) p.map(midi, 21, 108, 0, p.TWO_PI);
			setPoints();
			lighthead = new LightHead(points.get(0));
			lighthead.create();
			setKeys();
		}
	}
	
	public void setPanel(Rectangle a) {
		super.setPanel(a);
		centerX = a.x + a.width/2;
		centerY = a.y + a.height/2;
		prismRadius = (float) (0.3 * a.width * Math.sqrt(3) / 3);
		this.p1 = new Point((int) centerX, (int) (centerY-prismRadius));
		this.p2 = new Point((int) (centerX+Math.cos(Math.PI/6)*prismRadius),
							(int) (centerY+Math.sin(Math.PI/6)*prismRadius));
		this.p3 = new Point((int) (centerX-Math.cos(Math.PI/6)*prismRadius),
							(int) (centerY+Math.sin(Math.PI/6)*prismRadius));
	}
	
	public void setPoints() {
		points.clear();
		// Add 1st point
		// angle = (float) (Math.random()*2*Math.PI);
		// angle = p.map(p.mouseX, 0, size.width, 0, p.TWO_PI);
		// angle = p.radians(180);
		points.add(new Point((int) (centerX+prismHeight*Math.cos(angle)),
							 (int) (centerY+prismHeight*Math.sin(angle))));
		
		// Add 2nd point
		Point2D.Float cp = new Point2D.Float((float) points.get(0).getX(), (float) points.get(0).getY());
		Point2D.Float np = new Point2D.Float(centerX, centerY);
		Line2D.Float beam = new Line2D.Float(cp, np);
		avoidPlane = -1;
		Line2D.Float temp = getNextLine(beam, planes, (float) 1.0, (float) REFRACTION_INDEX, 0);
		Point point2 = new Point((int) temp.getX1(), (int) temp.getY1());
		points.add(point2);
		
		// Add 3rd point
		cp.setLocation(point2.x, point2.y);
		np.setLocation((float) temp.getX2(), (float) temp.getY2());
		beam.setLine(cp, np);		
		
		if(angle >= p.PI/6 && angle < 5*p.PI/6) {
			avoidPlane = 1;
		} else if (angle >= 5*p.PI/6 && angle < 3*p.HALF_PI) {
			avoidPlane = 2;
		} else {
			avoidPlane = 0;
		}
		temp = getNextLine(beam, planes, (float) REFRACTION_INDEX, (float) 1.0, 1);
		Point point3 = new Point((int) temp.getX1(), (int) temp.getY1());
		points.add(point3);
		
		// Add 5th point
		cp.setLocation((float) temp.getX1(), (float) temp.getY1());
		np.setLocation((float) temp.getX2(), (float) temp.getY2());
		beam.setLine(cp, np);
				
		avoidPlane = -1;
		temp = getNextLine(beam, walls, (float) 1.0, (float) 1.0, 0);
		Point point4 = new Point((int) temp.getX1(), (int) temp.getY1());
		points.add(point4);
		
		// p.println(point4.toString());
		// Prepare rainbow variables
		double step = rp1.distance(new Point2D.Float((float) rp2.x, (float) rp2.y))/6;
		if(point4.x == size.x || point4.x == size.x+size.width) {
			rp1.setLocation(point4.x, point4.y-size.height/18);
			rp2.setLocation(point4.x, point4.y+size.height/18);
			
			rps[0] = rp1;
			for(int i = 0; i < rps.length-1; i++) {
				rps[i+1].x = rps[0].x;
				if(rp1.y < rp2.y) {
					rps[i+1].y = (int) (rps[i].y + step);
				} else if(rp1.y > rp2.y) {
					rps[i+1].y = (int) (rps[i].y - step);
				}
			}
			
		} else if(point4.y == size.y || point4.y == size.y+size.height) {
			rp1.setLocation(point4.x-size.height/18, point4.y);
			rp2.setLocation(point4.x+size.height/18, point4.y);
			
			rps[0] = rp1;
			for(int i = 0; i < rps.length-1; i++) {
				rps[i+1].y = rps[0].y;
				if(rp1.x < rp2.x) {
					rps[i+1].x = (int) (rps[i].x + step);
				} else if(rp1.x > rp2.x) {
					rps[i+1].x = (int) (rps[i].x - step);
				}
			}
			
		}
	}
	
	public void setKeys() {		
		for(int i = 0; i < points.size(); i++) {
			keys[i] = new Key(points.get(i));
		}
	}
	
	public void resetKeys() {
		for(int i = 0; i < points.size(); i++) {
			Key tempKey = keys[i];
			tempKey.resetPosition(points.get(i));
		}
	}
	
	public Line2D.Float getNextLine(Line2D.Float beam, Line2D.Float[] planes, float n1, float n2, int mode) {
		int planeNum = -1;
		
		Point2D.Float cp = new Point2D.Float();
		for(int i = 0; i < planes.length; i++) {
			if(beam.intersectsLine(planes[i]) && i != avoidPlane) {
				// if(planes.length > 3) p.println(i);
				planeNum = i;
				cp = getIntersect(beam.getP1(), beam.getP2(), planes[i].getP1(), planes[i].getP2());
				theta1 = getAngle(beam.getP1(), beam.getP2(), planes[i].getP1(), planes[i].getP2());
			}
		}
		theta1 -= PConstants.HALF_PI;
		theta2 = (float) (Math.asin(Math.sin(theta1)*n1/n2));
		// Take care for NaN result
		if(theta2 != theta2) theta2 = 0;
		
		Point2D.Float np;
		float dx, dy, ndx, ndy;
		if(planeNum == 2) {
			if(mode == 0) {
				dx = (float) (size.width*Math.tan(theta2));
				dy = (float) -size.width;
				ndx =  dx * cos(p.radians(-120)) + dy * sin(p.radians(-120));
				ndy = -dx * sin(p.radians(-120)) + dy * cos(p.radians(-120));
				ndx += cp.getX();
				ndy += cp.getY();
				np = new Point2D.Float(ndx, ndy);
			} else {
				dx = (float) (size.width*Math.tan(-theta2));
				dy = (float) -size.width;
				ndx =  dx * cos(p.radians(60)) + dy * sin(p.radians(60));
				ndy = -dx * sin(p.radians(60)) + dy * cos(p.radians(60));
				ndx += cp.getX();
				ndy += cp.getY();
				np = new Point2D.Float(ndx, ndy);
			}
		} else if(planeNum == 0) {
			if(mode == 0) {
				dx = (float) (size.width*Math.tan(theta2));
				dy = (float) -size.width;
				ndx =  dx * cos(p.radians(120)) + dy * sin(p.radians(120));
				ndy = -dx * sin(p.radians(120)) + dy * cos(p.radians(120));
				ndx += cp.getX();
				ndy += cp.getY();
				np = new Point2D.Float(ndx, ndy);
			} else {
				dx = (float) (size.width*Math.tan(-theta2));
				dy = (float) -size.width;
				ndx =   dx * cos(p.radians(-60)) + dy * sin(p.radians(-60));
				ndy =  -dx * sin(p.radians(-60)) + dy * cos(p.radians(-60));
				ndx += cp.getX();
				ndy += cp.getY();
				np = new Point2D.Float(ndx, ndy);
			}
		} else {
			if(mode == 0) {
				np = new Point2D.Float((float) (centerX+size.height/2*Math.tan(theta2)), (float) 0);
			} else {
				np = new Point2D.Float((float) (centerX+size.height/2*Math.tan(theta2)), size.y+size.height);
			}
		}
		return new Line2D.Float(cp, np);
	}
	
	/*
	public Line2D.Float getNextLine(Line2D.Float beam, Line2D.Float[] planes, float n1, float n2) {
		Point2D.Float cp = new Point2D.Float();
		for(int i = 0; i < planes.length; i++) {
			if(beam.intersectsLine(planes[i]) && i != avoidPlane) {
				cp = getIntersect(beam.getP1(), beam.getP2(), planes[i].getP1(), planes[i].getP2());
				theta1 = getAngle(beam.getP1(), beam.getP2(), planes[i].getP1(), planes[i].getP2());
			}
		}
		theta1 -= PConstants.HALF_PI;
		theta2 = (float) (Math.asin(Math.sin(theta1)*n1/n2));
		Point2D.Float np = new Point2D.Float((float) (centerX+centerY*Math.tan(theta2)), (float) 0);
		return new Line2D.Float(cp, np);
	}
	*/
	
	// http://www.ahristov.com/tutorial/geometry-games/intersection-lines.html
	public Point2D.Float getIntersect(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
	    float d = (float) ((p1.getX()-p2.getX())*(p3.getY()-p4.getY()) - (p1.getY()-p2.getY())*(p3.getX()-p4.getX()));
	    if (d == 0) return null;
	    float x = (float) (((p3.getX()-p4.getX())*(p1.getX()*p2.getY()-p1.getY()*p2.getX())-(p1.getX()-p2.getX())*(p3.getX()*p4.getY()-p3.getY()*p4.getX()))/d);
	    float y = (float) (((p3.getY()-p4.getY())*(p1.getX()*p2.getY()-p1.getY()*p2.getX())-(p1.getY()-p2.getY())*(p3.getX()*p4.getY()-p3.getY()*p4.getX()))/d);
	    return new Point2D.Float(x, y);
	}
	
	// http://www.ahristov.com/tutorial/geometry-games/angle-between-lines.html
	public float getAngle(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
		float phi = (float) Math.acos( ( (p2.getX()-p1.getX())*(p4.getX()-p3.getX()) +
								 		 (p2.getY()-p1.getY())*(p4.getY()-p3.getY()) ) / (
								 		 Math.sqrt((p2.getX()-p1.getX())*(p2.getX()-p1.getX())+(p2.getY()-p1.getY())*(p2.getY()-p1.getY())) *
								 		 Math.sqrt((p4.getX()-p3.getX())*(p4.getX()-p3.getX())+(p4.getY()-p3.getY())*(p4.getY()-p3.getY()))
								     ));
		return phi;
	}
	
	class Key {
		
		Point2D position;
		ArrayList<Particle> particles;
		
		Key(Point2D pt) {
			this.position = pt;
			particles = new ArrayList<Particle>();
		}
		
		void resetPosition(Point2D pt) {
			position = pt;
		}
		
		void emit(Point2D direction) {
			int n = (int) (20 + Math.round(Math.random()*20));
			for(int i = 0; i < n; i++) {
				Particle a = new Particle();
				
				a.x = position.getX();
				a.y = position.getY();
				
				double dx = direction.getX() - a.x;
				double dy = direction.getY() - a.y;
				
				a.x += dx * 0.7 * (double) i / (double) n;
				a.y += dy * 0.7 * (double) i / (double) n;
				
				double rr = ((dx+dy)/500) * (double) i / (double) n;
				
				a.x += -rr + Math.random() * (rr+rr);
				a.y += -rr + Math.random() * (rr+rr);
				
				a.vx = dx/(100+(Math.random()*500));
				a.vy = dy/(100+(Math.random()*500));
				a.vr = -0.1 + Math.random() * 0.2;
				
				a.rotationRadius = Math.random() * 12;
				
				this.particles.add(a);
			}
		}
	}
	
	class Particle {
		double x, y, vx, vy, vr;
		double rotation, rotationRadius;
	}
	
	class LightHead {
		
		public ArrayList<Point2D> positions;
		public int index = 0;
		public int length = 10;
		public boolean isDead = true;
		
		LightHead() {
			positions = new ArrayList<Point2D>();
		}
		
		LightHead(Point2D pt) {
			positions = new ArrayList<Point2D>();
			positions.add(pt);
		}
		
		float getDistanceTo(Point2D pt) {
			Point2D temp = getPosition();
			float dx = (float) (pt.getX() - temp.getX());
			float dy = (float) (pt.getY() - temp.getY());
			return (float) Math.sqrt(dx*dx + dy*dy);
		}
		
		void addPosition(Point2D pt) {
			while(positions.size() > length) {
				positions.remove(0);
			}
			positions.add(pt);
		}
		
		void create() {
			isDead = false;
		}
		
		void die() {
			isDead = true;
		}
		
		Point2D getPosition() {
			return (Point2D) positions.get(positions.size()-1);
		}
	}
	
	class Stars {
		
		public int count = 50;
		public Point2D center;
		public Point2D[] positions;
		public double[] scales;
		public float noiseX, noiseY;
		
		Stars(Point2D pt) {
			center = pt;
			positions = new Point2D[count];
			scales = new double[count];
			for(int i = 0; i < count; i++) {
				positions[i] = new Point2D.Double(p.random(-size.width/2, size.width/2),
						  p.random(-size.height/2, size.height/2));
				/*
				positions[i] = new Point2D.Double(size.getX()+Math.random()*size.width,
												  size.getY()+Math.random()*size.height);
				*/
				scales[i] = ((double) i / (double) count + 0.01);
			}
			noiseX = p.random(size.width);
			noiseY = p.random(size.height);
		}
		
		void resetPositions() {
			p.pushMatrix();
			for(int i = 0; i < count; i++) {
				positions[i] = new Point2D.Double(p.random(-size.width/2, size.width/2),
												  p.random(-size.height/2, size.height/2));
				scales[i] = ((double) i / (double) count + 0.01);
			}
			p.popMatrix();
			
			/*
			for(int i = 0; i < count; i++) {
				positions[i] = new Point2D.Double(size.getX()+Math.random()*size.width,
												  size.getY()+Math.random()*size.height);
				scales[i] = ((double) i / (double) count + 0.01);
			}
			*/
		}
		
		void update() {
			for(int i = 0; i < count; i++) {
				float dx = 5 * (p.noise(noiseX / 10f + (float) positions[i].getX() / 100f) - 0.5f);
				float dy = 5 * (p.noise(noiseY / 10f + (float) positions[i].getY() / 100f) - 0.5f);
				positions[i].setLocation(positions[i].getX()+dx, positions[i].getY()+dy);
			}
		}
		
		void update(Point2D pt) {
			for(int i = 0; i < count; i++) {
				positions[i].setLocation(positions[i].getX()+pt.getX(), positions[i].getY()+pt.getY());
			}
		}
		
		void display() {
			p.noStroke();
			if(isInvert) {
				p.fill(0);
			} else {
				p.fill(255);
			}
			p.pushMatrix();
			p.translate(centerX, centerY);
			p.rotate(ps);
			// p.println(size.toString());
			for(int i = 0; i < count; i++) {
				p.pushMatrix();
				p.scale((float) scales[i]);
				if(size.contains(positions[i].getX()+centerX, positions[i].getY()+centerY)) {
					// p.ellipse((float) positions[i].getX(), (float) positions[i].getY(), 10f, 10f);
					p.ellipse((float) positions[i].getX(), (float) positions[i].getY(), size.width*(10f/1024f), size.width*(10f/1024f));
				}
				p.popMatrix();
			}
			p.popMatrix();
		}
	}
	
	void updateSpring() {
	    f = -K * (ps - R);    // f=-ky
	    as = f / M;           // Set the acceleration, f=ma == a=f/m
	    vs = D * (vs + as);   // Set the velocity
	    ps = ps + vs;         // Updated position
	    
	    if(Math.abs(vs) < 0.1) {
	    	vs = 0.0f;
		}
	}
}