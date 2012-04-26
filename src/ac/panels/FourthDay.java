package ac.panels;

import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D.Float;
import java.util.ArrayList;

import ac.panels.SecondDay.Cloud;

import processing.core.PApplet;

public class FourthDay extends Panel {

	float radius;
	float sunAngle, moonAngle;
	float dx, dy;
	float start, stop;
	float tempX, tempY;
	// ArrayList<Point2D> points;
	Point2D[] points;
	int pointNum;
	Line2D l1, l2;
	Star s;
	Polygon night;
	ArrayList<Star> stars;
	int cnt;
	
	public FourthDay(PApplet p, Rectangle a) {
		super(p, a);
		radius = Math.min(size.width, size.height);
		radius *= 0.8f;
		sunAngle = p.atan((float) size.width / (float) size.height) + p.HALF_PI;
		start = p.degrees(sunAngle);
		
		dx = (size.width - radius)/2;
		float tempAngle = p.HALF_PI - p.atan((float) size.width / (float) size.height);
		dy = dx * p.tan(tempAngle);
		// points = new ArrayList<Point2D>();
		
		l1 = new Line2D.Float(size.x, size.y, size.x, size.y+size.height);
		l2 = new Line2D.Float(size.x, size.y, size.x+size.width, size.y);
		
		s = new Star();
		
		night = new Polygon();
		night.addPoint(size.x, size.y+size.height);
		night.addPoint(size.x+size.width, size.y+size.height);
		night.addPoint(size.x+size.width, size.y);
		for(float i = sunAngle-p.PI; i < sunAngle; i+=p.PI/12) {
			night.addPoint((int) (size.x+size.width/2+radius/2*p.cos(i)),
					       (int) (size.y+size.height/2+radius/2*p.sin(i)));
		}
		
		stars = new ArrayList<Star>();
		for(int i = 0; i < 5; i++) {
			stars.add(new Star(stars));
		}
		
		for(float i = start+6f; i <= start+180f; i+=8f) {
			pointNum++;
		}
		// System.out.println(pointNum);
		points = new Point2D[pointNum];
		bang();
	}

	@Override
	public void display() {
		// Draw background for the sun
		// p.stroke(255, 74, 96);
		p.noStroke();
		if(isInvert) {
			p.fill(0, 181, 159);
		} else {
			p.fill(255, 74, 96);
		}
		p.triangle(size.x, size.y, size.x+size.width, size.y, size.x, size.y+size.height);		
		
		// Draw background for the moon
		// p.stroke(71, 72, 74);
		p.noStroke();
		if(isInvert) {
			p.fill(184, 183, 181);
		} else {
			p.fill(71, 72, 74);
		}
		p.triangle(size.x, size.y+size.height, size.x+size.width, size.y+size.height, size.x+size.width, size.y);
		
		// Draw half sun
		// p.stroke(255, 253, 54);
		p.noStroke();
		if(isInvert) {
			p.fill(0, 2, 201);
		} else {
			p.fill(255, 253, 54);
		}
		// p.arc(size.x+size.width/2, size.y+size.height/2, radius, radius, sunAngle, sunAngle + p.PI);
		/*
		float tempX = dx*p.cos(sunAngle)*p.sin(0.005f*p.millis());
		float tempY = dx*p.sin(sunAngle)*p.sin(0.005f*p.millis());
		*/
		p.arc(size.x+size.width/2 + tempX,
			  size.y+size.height/2 + tempY,
			  radius, radius, sunAngle, sunAngle + p.PI);
		
		// Draw radial rays
		// points.clear();
		// p.stroke(255);
		//============================================================
		/*
		int cnt = 0;
		int num = 0;
		for(float i = start+6f; i <= start+180f; i+=8f) {
			
			Line2D tempLine = new Line2D.Float(size.x+size.width/2+tempX,
					   size.y+size.height/2+tempY,
					   size.x+size.width/2+tempX+size.width*2*p.cos(p.radians(i)),
					   size.y+size.height/2+tempY+size.width*2*p.sin(p.radians(i)));
			
			if(l1.intersectsLine(tempLine)) {
				points[num] = getIntersect(tempLine.getP1(), tempLine.getP2(), l1.getP1(), l1.getP2());
				cnt++;
			} else if(l2.intersectsLine(tempLine)) {
				points[num] = getIntersect(tempLine.getP1(), tempLine.getP2(), l2.getP1(), l2.getP2());
			}
			
			num++;
		}
		*/
		//============================================================
		// System.out.println(points.size());
		
		// p.stroke(255, 253, 54);
		// p.noStroke();
		// p.fill(255, 253, 54);
		if(pointNum % 2 == 0) {
			for(int i = 0; i < pointNum; i+=2) {
				p.beginShape();
				// p.vertex(size.x+size.width/2, size.y+size.height/2);
				p.vertex(size.x+size.width/2+tempX, size.y+size.height/2+tempY);
				p.vertex((float) points[i].getX(), (float) points[i].getY());
				if(i == cnt || i == cnt-1) p.vertex(size.x, size.y);
				p.vertex((float) points[i+1].getX(), (float) points[i+1].getY());
				p.endShape();
			}
		} else {
			for(int i = 0; i < pointNum-1; i+=2) {
				p.beginShape();
				// p.vertex(size.x+size.width/2, size.y+size.height/2);
				p.vertex(size.x+size.width/2+tempX, size.y+size.height/2+tempY);
				p.vertex((float) points[i].getX(), (float) points[i].getY());
				if(i == cnt || i == cnt-1) p.vertex(size.x, size.y);
				p.vertex((float) points[i+1].getX(), (float) points[i+1].getY());
				p.endShape();
			}
		}
		
		/*
		if(points.size() % 2 == 0) {
			for(int i = 0; i < points.size(); i+=2) {
				p.beginShape();
				// p.vertex(size.x+size.width/2, size.y+size.height/2);
				p.vertex(size.x+size.width/2+tempX, size.y+size.height/2+tempY);
				p.vertex((float) points.get(i).getX(), (float) points.get(i).getY());
				if(i == cnt || i == cnt-1) p.vertex(size.x, size.y);
				p.vertex((float) points.get(i+1).getX(), (float) points.get(i+1).getY());
				p.endShape();
			}
		} else {
			for(int i = 0; i < points.size()-1; i+=2) {
				p.beginShape();
				// p.vertex(size.x+size.width/2, size.y+size.height/2);
				p.vertex(size.x+size.width/2+tempX, size.y+size.height/2+tempY);
				p.vertex((float) points.get(i).getX(), (float) points.get(i).getY());
				if(i == cnt || i == cnt-1) p.vertex(size.x, size.y);
				p.vertex((float) points.get(i+1).getX(), (float) points.get(i+1).getY());
				p.endShape();
			}
		}
		*/
		
		p.stroke(255, 0, 192);
		p.fill(255, 0, 192);
		for(int i = 0; i < pointNum; i++) {
			// p.ellipse((float) points.get(i).getX(), (float) points.get(i).getY(), 5, 5);
		}
		
		for(int i = 0; i < stars.size(); i++) {
			Star tempStar = (Star) stars.get(i);
			tempStar.display();
		}
		
		// Draw half moon
		// p.pushMatrix();
		// p.translate(size.x+size.width/2, size.y+size.height/2);
		// p.translate((size.x+size.width/2)+dx, (size.y+size.height/2)-dy);
		/*
		p.translate((size.x+size.width/2)+dx*p.abs(p.sin(0.005f*p.millis())),
				    (size.y+size.height/2)-dy*p.abs(p.sin(0.005f*p.millis())));
		*/
		
		// p.stroke(255);
		p.noStroke();
		if(isInvert) {
			p.fill(0);
		} else {
			p.fill(255);
		}
		p.arc(size.x+size.width/2 - tempX,
			  size.y+size.height/2 - tempY,
			  radius, radius, sunAngle - p.PI, sunAngle);
		// p.popMatrix();
		
		// s.display();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void bang() {
		tempX = dx*p.cos(sunAngle)*p.abs( p.sin(0.005f*p.millis()) );
		tempY = dx*p.sin(sunAngle)*p.abs( p.sin(0.005f*p.millis()) );
		// int cnt = 0;
		cnt = 0;
		int num = 0;
		for(float i = start+6f; i <= start+180f; i+=8f) {
			Line2D tempLine = new Line2D.Float(size.x+size.width/2+tempX,
					   size.y+size.height/2+tempY,
					   size.x+size.width/2+tempX+size.width*2*p.cos(p.radians(i)),
					   size.y+size.height/2+tempY+size.width*2*p.sin(p.radians(i)));
			
			if(l1.intersectsLine(tempLine)) {
				points[num] = getIntersect(tempLine.getP1(), tempLine.getP2(), l1.getP1(), l1.getP2());
				cnt++;
			} else if(l2.intersectsLine(tempLine)) {
				points[num] = getIntersect(tempLine.getP1(), tempLine.getP2(), l2.getP1(), l2.getP2());
			}
			
			num++;
		}
	}
	
	// http://www.ahristov.com/tutorial/geometry-games/intersection-lines.html
	public Point2D.Float getIntersect(Point2D p1, Point2D p2, Point2D p3, Point2D p4) {
	    float d = (float) ((p1.getX()-p2.getX())*(p3.getY()-p4.getY()) - (p1.getY()-p2.getY())*(p3.getX()-p4.getX()));
	    if (d == 0) return null;
	    float x = (float) (((p3.getX()-p4.getX())*(p1.getX()*p2.getY()-p1.getY()*p2.getX())-(p1.getX()-p2.getX())*(p3.getX()*p4.getY()-p3.getY()*p4.getX()))/d);
	    float y = (float) (((p3.getY()-p4.getY())*(p1.getX()*p2.getY()-p1.getY()*p2.getX())-(p1.getY()-p2.getY())*(p3.getX()*p4.getY()-p3.getY()*p4.getX()))/d);
	    return new Point2D.Float(x, y);
	}

	class Star {
		Rectangle bounds;
		int color;
		int invertedColor;
		float scale;
		
		Star() {
			bounds = new Rectangle();
			bounds.x = (int) p.random(size.x, size.x+size.width);
			bounds.y = (int) p.random(size.y, size.y+size.height);
			bounds.width = size.width / 20;
			bounds.height = bounds.width;
			if(Math.random() > 0.5) {
				color = p.color(255);
				invertedColor = p.color(0);
			} else {
				color = p.color(71, 203, 192);
				invertedColor = p.color(184, 52, 63);
			}
			scale = p.random(0.4f, 1.0f);
		}
		
		Star(ArrayList<Star> ss) {
			boolean exit = false;
			while(!exit) {
				bounds = new Rectangle();
				bounds.x = (int) p.random(size.x, size.x+size.width);
				bounds.y = (int) p.random(size.y, size.y+size.height);
				bounds.width = size.width / 20;
				bounds.height = bounds.width;
				if(Math.random() > 0.5) {
					color = p.color(255);
					invertedColor = p.color(0);
				} else {
					color = p.color(71, 203, 192);
					invertedColor = p.color(184, 52, 63);
				}
				scale = p.random(0.4f, 1.0f);
				
				int count = 0;
				if(ss.size() > 0) {
					for(int i = 0; i < ss.size(); i++) {
						Star tempStar = (Star) ss.get(i);
						if(!bounds.intersects(tempStar.bounds) && night.contains(bounds)) {
							count++;
						}
					}
					if(count == ss.size()) exit = true;
				} else {
					if(night.contains(bounds)) exit = true;
				}
			}
		}
		
		public void display() {
			p.pushMatrix();
			// p.scale(scale);
			p.noStroke();
			if(isInvert) {
				p.fill(invertedColor);
			} else {
				p.fill(color);
			}
			p.rect(bounds.x, bounds.y, bounds.width, bounds.height);
			if(isInvert) {
				p.fill(184, 183, 181);
			} else {
				p.fill(71, 72, 74);
			}
			p.arc(bounds.x, bounds.y, bounds.width, bounds.height, 0, p.HALF_PI);
			p.arc(bounds.x+bounds.width, bounds.y, bounds.width, bounds.height, p.HALF_PI, p.PI);
			p.arc(bounds.x+bounds.width, bounds.y+bounds.height, bounds.width, bounds.height, p.PI, 3*p.HALF_PI);
			p.arc(bounds.x, bounds.y+bounds.height, bounds.width, bounds.height, 3*p.HALF_PI, p.TWO_PI);
			p.popMatrix();
		}
	}
} 