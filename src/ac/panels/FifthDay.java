package ac.panels;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;

public class FifthDay extends Panel {
	Wave wave;
	Mountain mountain;
	Flock flock;
	Fish fishA, fishB, fishC;
	
	public FifthDay(PApplet p, Rectangle a) {
		super(p, a);
		wave = new Wave();
		mountain = new Mountain();
		flock = new Flock();
		for (int i = 0; i < 15; i++) {
		    flock.addBoid(new Boid(new PVector(size.x+size.width/2, size.y+size.height), 3.0f, 0.05f));
		}
		fishA = new Fish(0.6f);
		fishB = new Fish(0.5f);
		fishC = new Fish(0.4f);
	}

	@Override
	public void display() {
		p.noStroke();
		if(isInvert) {
			p.fill(253, 219, 217);
		} else {
			p.fill(2, 36, 38);
		}
		p.rect(size.x, size.y, size.width, size.height);
		
		mountain.display();
		// flock.run();
		flock.display();
		// wave.display();
		wave.drawBackWave();
		fishA.display();
		fishB.display();
		fishC.display();
		wave.drawFrontWave();
	}

	@Override
	public void update() {
		flock.run();
	}
	
	public void bang() {
		int seed = (int) p.random(100);
		if(seed <= 33) {
			fishA.tick();
		} else if(seed > 33 && seed <= 66) {
			fishB.tick();
		} else if(seed > 66) {
			fishC.tick();
		}
	}

	class Wave {
		int pointNum;
		float step, offset, noise;
		Point2D[] w1Points, w2Points, w3Points;
		
		Wave() {
			pointNum = 22;
			w1Points = new Point2D[pointNum];
			w2Points = new Point2D[pointNum];
			w3Points = new Point2D[pointNum];
			step = size.width / (pointNum-1);
			noise = size.width / 180f;
			
			// Prepare 1st wave
			for(int i = 1; i < w1Points.length; i++) {
				// float x = size.x + i * step + p.random(-noise, noise);
				float x = size.x + i * step;
				if(i % 3 == 0) {
					// float y = size.y + size.height - size.height * (50f/768f) + p.random(-noise, noise);
					float y = size.y + size.height - size.width * (50f/1024f);
					w1Points[i] = new Point2D.Float(x, y);
				} else {
					// float y = size.y + size.height - size.height * (100f/768f) + p.random(-noise, noise);
					float y = size.y + size.height - size.width * (100f/1024f);
					w1Points[i] = new Point2D.Float(x, y);
				}
			}
			w1Points[0] = new Point2D.Float(size.x, size.y+size.height);
			w1Points[w1Points.length-1] = new Point2D.Float(size.x+size.width, size.y+size.height);
			
			// Prepare 2nd wave
			for(int i = 1; i < w2Points.length; i++) {
				// float x = size.x + i * step + p.random(-noise, noise);
				float x = size.x + i * step;
				if(i % 3 == 0) {
					// float y = size.y + size.height - size.height * (100f/768f) + p.random(-noise, noise);
					float y = size.y + size.height - size.width * (100f/1024f);
					w2Points[i] = new Point2D.Float(x, y);
				} else {
					// float y = size.y + size.height - size.height * (150f/768f) + p.random(-noise, noise);
					float y = size.y + size.height - size.width * (150f/1024f);
					w2Points[i] = new Point2D.Float(x, y);
				}
			}
			// w2Points[0] = new Point2D.Float(size.x, size.y+size.height);
			// w2Points[w2Points.length-1] = new Point2D.Float(size.x+size.width, size.y+size.height);
			w2Points[0] = new Point2D.Float(size.x, size.y+size.height-size.height*(50f/768f));
			w2Points[w2Points.length-1] = new Point2D.Float(size.x+size.width, size.y+size.height-size.height*(50f/768f));
		}
		
		public void display() {
			// Draw 2nd wave
			p.stroke(52, 99, 105);
			p.fill(52, 99, 105);
			p.beginShape();
			// p.curveVertex(size.x, size.y+size.height);
			p.curveVertex((float) w2Points[0].getX(), (float) w2Points[0].getY());
			for(int i = 0; i < w2Points.length; i++) {
				p.curveVertex((float) w2Points[i].getX(), (float) w2Points[i].getY());
			}
			// p.curveVertex(size.x+size.width, size.y+size.height);
			p.curveVertex((float) w2Points[w2Points.length-1].getX(), (float) w2Points[w2Points.length-1].getY());
			p.vertex(size.x+size.width, size.y+size.height);
			p.vertex(size.x, size.y+size.height);
			// p.vertex(size.x+size.width/2, size.y+size.height);
			// p.vertex(size.x, size.y+size.height);
			p.endShape();
			
			// Draw 1st wave
			p.stroke(91, 159, 168);
			p.fill(91, 159, 168);
			p.beginShape();
			p.curveVertex(size.x, size.y+size.height);
			for(int i = 0; i < w1Points.length; i++) {
				p.curveVertex((float) w1Points[i].getX(), (float) w1Points[i].getY());
			}
			p.curveVertex(size.x+size.width, size.y+size.height);
			// p.vertex(size.x+size.width/2, size.y+size.height);
			// p.vertex(size.x, size.y+size.height);
			p.endShape();
			
			/*
			// Draw points
			p.noStroke();
			p.fill(255, 0, 0);
			for(int i = 0; i < w2Points.length; i++) {
				p.ellipse((float) w2Points[i].getX(), (float) w2Points[i].getY(), 5, 5);
			}
			*/
		}
		
		public void drawFrontWave() {
			if(isInvert) {
				p.stroke(164, 96, 87);
				p.fill(164, 96, 87);
			} else {
				p.stroke(91, 159, 168);
				p.fill(91, 159, 168);				
			}
			p.beginShape();
			p.curveVertex(size.x, size.y+size.height);
			for(int i = 0; i < w1Points.length; i++) {
				p.curveVertex((float) w1Points[i].getX(), (float) w1Points[i].getY());
			}
			p.curveVertex(size.x+size.width, size.y+size.height);
			p.endShape();
		}
		
		public void drawBackWave() {
			if(isInvert) {
				p.stroke(203, 156, 150);
				p.fill(203, 156, 150);
			} else {
				p.stroke(52, 99, 105);
				p.fill(52, 99, 105);
			}
			p.beginShape();
			p.curveVertex((float) w2Points[0].getX(), (float) w2Points[0].getY());
			for(int i = 0; i < w2Points.length; i++) {
				p.curveVertex((float) w2Points[i].getX(), (float) w2Points[i].getY());
			}
			p.curveVertex((float) w2Points[w2Points.length-1].getX(), (float) w2Points[w2Points.length-1].getY());
			p.vertex(size.x+size.width, size.y+size.height);
			p.vertex(size.x, size.y+size.height);
			p.endShape();
		}
	}
	
	class Mountain {
		PVector[] m1, m2, m3;
		
		Mountain() {
			// Prepare 1st mountain
			m1 = new PVector[4];
			m2 = new PVector[4];
			m3 = new PVector[4];
			
			for(int i = 0; i < m1.length; i++) {
				m1[i] = new PVector();
				m2[i] = new PVector();
				m3[i] = new PVector();
			}
			
			m1[0].x = size.x;
			m1[0].y = size.y + size.height;
			m1[1].x = size.x + size.width/3;
			// m1[1].y = size.y + 2*size.height/3;
			m1[1].y = size.y + size.height - size.width * (250f/1024f);
			m1[2].x = size.x + size.width;
			m1[2].y = size.y + size.height;
			m1[3].x = size.x + 2*size.width/3;
			m1[3].y = size.y + size.height;
			
			m2[0].x = size.x + size.width/4;
			m2[0].y = size.y + size.height;
			m2[1].x = size.x + size.width/2;
			// m2[1].y = size.y + size.height/2;
			m2[1].y = size.y + size.height - size.width * (400f/1024f);
			m2[2].x = size.x + 8*size.width/10;
			m2[2].y = size.y + size.height;
			m2[3].x = size.x + 7*size.width/10;
			m2[3].y = size.y + size.height;
			
			m3[0].x = size.x + size.width/3;
			m3[0].y = size.y + size.height;
			m3[1].x = size.x + 2*size.width/3;
			// m3[1].y = size.y + 2*size.height/3;
			m3[1].y = size.y + size.height - size.width * (300f/1024f);
			m3[2].x = size.x + size.width;
			m3[2].y = size.y + size.height;
			m3[3].x = size.x + 9*size.width/10;
			m3[3].y = size.y + size.height;
		}
		
		void display() {
			// Draw 1st mountain
			if(isInvert) {
				p.stroke(0);
				p.fill(0);
			} else {
				p.stroke(255);
				p.fill(255);
			}
			p.triangle(m1[0].x, m1[0].y, m1[1].x, m1[1].y, m1[2].x, m1[2].y);
			if(isInvert) {
				p.stroke(164, 96, 87);
				p.fill(164, 96, 87);
			} else {
				p.stroke(91, 159, 168);
				p.fill(91, 159, 168);
			}
			p.triangle(m1[1].x, m1[1].y, m1[2].x, m1[2].y, m1[3].x, m1[3].y);
			
			
			// Draw 2nd mountain
			if(isInvert) {
				p.stroke(0);
				p.fill(0);
			} else {
				p.stroke(255);
				p.fill(255);
			}
			p.triangle(m2[0].x, m2[0].y, m2[1].x, m2[1].y, m2[2].x, m2[2].y);
			if(isInvert) {
				p.stroke(164, 96, 87);
				p.fill(164, 96, 87);
			} else {
				p.stroke(91, 159, 168);
				p.fill(91, 159, 168);
			}
			p.triangle(m2[1].x, m2[1].y, m2[2].x, m2[2].y, m2[3].x, m2[3].y);
			
			// Draw 3rd mountain
			if(isInvert) {
				p.stroke(0);
				p.fill(0);
			} else {
				p.stroke(255);
				p.fill(255);
			}
			p.triangle(m3[0].x, m3[0].y, m3[1].x, m3[1].y, m3[2].x, m3[2].y);
			if(isInvert) {
				p.stroke(164, 96, 87);
				p.fill(164, 96, 87);
			} else {
				p.stroke(91, 159, 168);
				p.fill(91, 159, 168);
			}
			p.triangle(m3[1].x, m3[1].y, m3[2].x, m3[2].y, m3[3].x, m3[3].y);
		}
	}
	
	class Boid {
		
		  PVector loc;
		  PVector vel;
		  PVector acc;
		  float r;
		  float maxforce;    // Maximum steering force
		  float maxspeed;    // Maximum speed

		  Boid(PVector l, float ms, float mf) {
		    acc = new PVector(0,0);
		    vel = new PVector(p.random(-1,1),p.random(-1,1));
		    loc = l.get();
		    r = size.width * (10f/1024f);
		    maxspeed = ms;
		    maxforce = mf;
		  }

		  void run(ArrayList<Boid> boids) {
		    flock(boids);
		    update();
		    borders();
		    // render();
		  }
		  
		  void display(ArrayList<Boid> boids) {
			 render(); 
		  }

		  // We accumulate a new acceleration each time based on three rules
		  void flock(ArrayList<Boid> boids) {
		    PVector sep = separate(boids);   // Separation
		    PVector ali = align(boids);      // Alignment
		    PVector coh = cohesion(boids);   // Cohesion
		    // Arbitrarily weight these forces
		    sep.mult(1.5f);
		    ali.mult(1.0f);
		    coh.mult(1.0f);
		    // Add the force vectors to acceleration
		    acc.add(sep);
		    acc.add(ali);
		    acc.add(coh);
		  }

		  // Method to update location
		  void update() {
		    // Update velocity
		    vel.add(acc);
		    // Limit speed
		    vel.limit(maxspeed);
		    loc.add(vel);
		    // Reset accelertion to 0 each cycle
		    acc.mult(0);
		  }

		  void seek(PVector target) {
		    acc.add(steer(target,false));
		  }

		  void arrive(PVector target) {
		    acc.add(steer(target,true));
		  }

		  // A method that calculates a steering vector towards a target
		  // Takes a second argument, if true, it slows down as it approaches the target
		  PVector steer(PVector target, boolean slowdown) {
		    PVector steer;  // The steering vector
		    PVector desired = target.sub(target,loc);  // A vector pointing from the location to the target
		    float d = desired.mag(); // Distance from the target is the magnitude of the vector
		    // If the distance is greater than 0, calc steering (otherwise return zero vector)
		    if (d > 0) {
		      // Normalize desired
		      desired.normalize();
		      // Two options for desired vector magnitude (1 -- based on distance, 2 -- maxspeed)
		      if ((slowdown) && (d < 100.0)) desired.mult(maxspeed*(d/100.0f)); // This damping is somewhat arbitrary
		      else desired.mult(maxspeed);
		      // Steering = Desired minus Velocity
		      steer = target.sub(desired,vel);
		      steer.limit(maxforce);  // Limit to maximum steering force
		    } 
		    else {
		      steer = new PVector(0,0);
		    }
		    return steer;
		  }

		  void render() {
		    // Draw a triangle rotated in the direction of velocity
		    float theta = vel.heading2D() + p.PI/2;
		    if(isInvert) {
		    	p.fill(14, 116, 188);
		    	p.stroke(14, 116, 188);
		    } else {
		    	p.fill(241, 139, 67);
		    	p.stroke(241, 139, 67);
		    }
		    p.pushMatrix();
		    p.translate(loc.x,loc.y);
		    p.rotate(theta);
		    p.beginShape();
		    p.vertex(0, -r*2);
		    p.vertex(-r, r*2);
		    p.vertex(r, r*2);
		    p.endShape();
		    p.popMatrix();
		  }

		  // Wraparound
		  void borders() {
			if(loc.x < size.x+r) loc.x = size.x+size.width-r;
			if(loc.y < size.y+r) loc.y = size.y+size.height-r;
			if (loc.x > size.x+size.width-r) loc.x = size.x+r;
		    if (loc.y > size.y+size.height-r) loc.y = size.y+r;
			/*
		    if (loc.x < -r) loc.x = size.width+r;
		    if (loc.y < -r) loc.y = size.height+r;
		    if (loc.x > size.width+r) loc.x = -r;
		    if (loc.y > size.height+r) loc.y = -r;
		    */
		  }

		  // Separation
		  // Method checks for nearby boids and steers away
		  PVector separate (ArrayList<Boid> boids) {
		    float desiredseparation = 20.0f;
		    PVector steer = new PVector(0,0,0);
		    int count = 0;
		    // For every boid in the system, check if it's too close
		    for (int i = 0 ; i < boids.size(); i++) {
		      Boid other = (Boid) boids.get(i);
		      float d = PVector.dist(loc,other.loc);
		      // If the distance is greater than 0 and less than an arbitrary amount (0 when you are yourself)
		      if ((d > 0) && (d < desiredseparation)) {
		        // Calculate vector pointing away from neighbor
		        PVector diff = PVector.sub(loc,other.loc);
		        diff.normalize();
		        diff.div(d);        // Weight by distance
		        steer.add(diff);
		        count++;            // Keep track of how many
		      }
		    }
		    // Average -- divide by how many
		    if (count > 0) {
		      steer.div((float)count);
		    }

		    // As long as the vector is greater than 0
		    if (steer.mag() > 0) {
		      // Implement Reynolds: Steering = Desired - Velocity
		      steer.normalize();
		      steer.mult(maxspeed);
		      steer.sub(vel);
		      steer.limit(maxforce);
		    }
		    return steer;
		  }

		  // Alignment
		  // For every nearby boid in the system, calculate the average velocity
		  PVector align (ArrayList<Boid> boids) {
		    float neighbordist = 25.0f;
		    PVector steer = new PVector(0,0,0);
		    int count = 0;
		    for (int i = 0 ; i < boids.size(); i++) {
		      Boid other = (Boid) boids.get(i);
		      float d = PVector.dist(loc,other.loc);
		      if ((d > 0) && (d < neighbordist)) {
		        steer.add(other.vel);
		        count++;
		      }
		    }
		    if (count > 0) {
		      steer.div((float)count);
		    }

		    // As long as the vector is greater than 0
		    if (steer.mag() > 0) {
		      // Implement Reynolds: Steering = Desired - Velocity
		      steer.normalize();
		      steer.mult(maxspeed);
		      steer.sub(vel);
		      steer.limit(maxforce);
		    }
		    return steer;
		  }

		  // Cohesion
		  // For the average location (i.e. center) of all nearby boids, calculate steering vector towards that location
		  PVector cohesion (ArrayList<Boid> boids) {
		    float neighbordist = 25.0f;
		    PVector sum = new PVector(0,0);   // Start with empty vector to accumulate all locations
		    int count = 0;
		    for (int i = 0 ; i < boids.size(); i++) {
		      Boid other = (Boid) boids.get(i);
		      float d = loc.dist(other.loc);
		      if ((d > 0) && (d < neighbordist)) {
		        sum.add(other.loc); // Add location
		        count++;
		      }
		    }
		    if (count > 0) {
		      sum.div((float)count);
		      return steer(sum,false);  // Steer towards the location
		    }
		    return sum;
		  }
		}
	
	class Flock {
		  ArrayList<Boid> boids; // An arraylist for all the boids

		  Flock() {
		    boids = new ArrayList<Boid>(); // Initialize the arraylist
		  }

		  void run() {
		    for (int i = 0; i < boids.size(); i++) {
		      Boid b = (Boid) boids.get(i);  
		      b.run(boids);  // Passing the entire list of boids to each boid individually
		    }
		  }
		  
		  void display() {
			  for (int i = 0; i < boids.size(); i++) {
			      Boid b = (Boid) boids.get(i);  
			      b.display(boids);
			    }
		  }

		  void addBoid(Boid b) {
		    boids.add(b);
		  }

	}
	
	class Fish {
		
		float radius;
		int degree;
		
		Fish(float percentage) {
			// radius = 0.6f*(size.width/2);
			radius = percentage * (size.width/2);
			degree = -90 + 10;
		}
		
		void tick() {
			degree += 10;
			if(degree >= (90 - 10)) {
				degree = -90 + 10;
			}
		}
		
		void display() {
			p.pushMatrix();
			p.translate(size.x+size.width/2, size.y+size.height);
			if(isInvert) {
				p.stroke(58, 190, 194);
				p.fill(58, 190, 194);
			} else {
				p.stroke(197, 65, 61);
				p.fill(197, 65, 61);
			}
			
			// degree++;
			// System.out.println(degree);
			p.rotate(p.radians(degree));
			p.translate(0, -radius);
			// p.scale(55);
			p.scale(size.width * (55f/1024f));
			p.beginShape();
			p.vertex(-0.5f, 0);
			p.bezierVertex(-0.3f, -0.2f, 0.3f, -0.2f, 0.5f, 0);
			p.bezierVertex(0.3f, 0.2f, -0.3f, 0.2f, -0.5f, 0);
			p.vertex(-0.7f, 0.22f);
			p.vertex(-0.7f, -0.22f);
			p.vertex(-0.5f, 0);
			p.endShape();
			
			/*
			for(int i = 180; i < 360; i+=10) {
				p.ellipse(radius*p.cos(p.radians(i)), radius*p.sin(p.radians(i)), 5, 5);
			}
			*/
			p.popMatrix();
		}
	}
}
