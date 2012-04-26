package ac;

import processing.core.PApplet;

public class PopFont {
	
	public PApplet p;
	/*
	float pCharX_uh[] = {1 ,5 ,5 ,1 ,1 };  // Upper Hole
	float pCharY_uh[] = {-4.5f,-4.5f,-7,-7,-4.5f};
	*/
	
	float pCharX_uh[] = {1,1,4,4,1};  // Upper Hole
	float pCharY_uh[] = {-5,-9,-9,-5,-5};
	
	float pCharX_bh[] = {1,1,4,4,1};	// Bottom Hole
	float pCharY_bh[] = {-1,-4,-4,-1,-1};
	
	/*
	float pCharX_a[] = {0, 0, 6,6,4, 4, 2,2,0};
	float pCharY_a[] = {0,-8,-8,0,0,-3,-3,0,0};
	*/
	float pCharX_a[] = {0,0,5,5,4,4,1,1,0};
	float pCharY_a[] = {0,-10,-10,0,0,-4,-4,0,0};
	
	float pCharX_b[] = {0,0,3,3,5,5,0};
	float pCharY_b[] = {0,-10,-10,-5,-5,0,0};
	
	float pCharX_ulh[] = {1,1,2,2,1};
	float pCharY_ulh[] = {-5,-9,-9,-5,-5};
	
	float pCharX_c[] = {0, 0, 5, 5, 1, 1, 5, 5, 0};
	float pCharY_c[] = {0,-8,-8,-7,-7,-1,-1,0,0};
	
	// 18x36
	float pCharX_d[] = {0,0,2.25f,2.25f,0,0,5,5,0};
	float pCharY_d[] = {0,-0.5f,-0.5f,-9.5f,-9.5f,-10,-10,0,0};
	
	public PopFont(PApplet p) {
		this.p = p;
	}
	
	public void drawDeep(float vtX[], float vtY[], float depth) {
		p.pushMatrix();
		p.scale(10);
		// p.stroke(210, 96, 60);
		// p.fill(210, 96, 60);
		p.stroke(174, 85, 55);
		p.fill(174, 85, 55);
		p.beginShape(p.QUAD_STRIP);
		for(int i = 0; i < vtX.length; i++) {
			p.vertex(vtX[i], vtY[i], 0);
			p.vertex(vtX[i], vtY[i], depth);
			/*
			if(i % 2 == 0) {
				p.vertex(vtX[i], vtY[i], 0);
				p.vertex(vtX[i], vtY[i], depth);
			} else {
				p.vertex(vtX[i], vtY[i], depth);
				p.vertex(vtX[i], vtY[i], 0);
			}
			*/
		}
		p.endShape();
		p.popMatrix();
		
		p.pushMatrix();
		p.scale(10);
		// p.stroke(174, 85, 55);
		p.stroke(210, 96, 60);
		p.fill(210, 96, 60);
		p.beginShape();
		for(int i = 1; i < vtX.length; i++) {
			p.vertex(vtX[i], vtY[i], 0);
		}
		p.endShape();
		p.popMatrix();
	}
	
	public void drawUpperHole() {
		p.pushMatrix();
		p.scale(10);
		p.stroke(174, 85, 55);
		p.fill(174, 85, 55);
		p.beginShape();
		for(int i = 1; i < pCharX_uh.length; i++) {
			p.vertex(pCharX_uh[i], pCharY_uh[i], .1f);
		}
		p.endShape();
		p.popMatrix();
	}
	
	public void drawBottomHole() {
		p.pushMatrix();
		p.scale(10);
		p.stroke(174, 85, 55);
		p.fill(174, 85, 55);
		p.beginShape();
		for(int i = 1; i < pCharX_uh.length; i++) {
			p.vertex(pCharX_bh[i], pCharY_bh[i], .1f);
		}
		p.endShape();
		p.popMatrix();
	}
	
	public void drawULHole() {
		p.pushMatrix();
		p.scale(10);
		p.stroke(174, 85, 55);
		p.fill(174, 85, 55);
		p.beginShape();
		for(int i = 1; i < pCharX_ulh.length; i++) {
			p.vertex(pCharX_ulh[i], pCharY_ulh[i], .1f);
		}
		p.endShape();
		p.popMatrix();
	}
	
	public void drawShadow(float vtX[], float vtY[], float depth) {
		p.pushMatrix();
		p.scale(30);
		p.stroke(220, 222, 173);
		p.fill(220, 222, 173);
		p.beginShape();
		for(int i = 0; i < vtX.length; i++) {
			p.vertex(vtX[i], vtY[i], -21);
		}
		p.endShape();
		p.popMatrix();
	}
}
