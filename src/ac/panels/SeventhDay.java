package ac.panels;

import java.awt.Rectangle;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;

public class SeventhDay extends Panel {

	float headWidth, headHeight, headControlY;
	float haloWidth, haloHeight, haloShadowY, haloY;
	float innerHaloWidth, innerHaloHeight;
	float faceWidth, faceHeight;
	float x73, y73;
	float eyeX, eyeY, eyeWidth, eyeHeight;
	float earX, earY, earWidth, earHeight;
	float nx1, ny1, nx2, ny2, nx3, ny3;
	float mustacheX, mustacheY, mustacheControlY, mustacheCenterY;
	
	PFont font;
	float fontSize;
	
	Zzz z;
	ArrayList<Zzz> ZzzManager;
	
	public SeventhDay(PApplet p, Rectangle a) {
		super(p, a);
		
		headWidth = size.width * 400.0f / 1024.0f;
		headHeight = size.width * 450.0f / 1024.0f;
		headControlY = size.width * 650.0f / 1024.0f;
		haloWidth = size.width * 220.0f / 1024.0f;
		haloHeight = size.width * 60.0f / 1024.0f;
		haloShadowY = size.width * 588.0f / 1024.0f;
		haloY = size.width * 625.0f / 1024.0f;
		innerHaloWidth = size.width * 170.0f / 1024.0f;
		innerHaloHeight = size.width * 40.0f / 1024.0f;
		
		faceWidth = size.width * 320.0f / 1024.0f;
		faceHeight = size.width * 380.0f / 1024.0f;
		x73 = size.width * 98.0f / 1024.0f;
		y73 = size.width * 525.0f / 1024.0f;
		
		eyeX = size.width * 77.0f/ 1024.0f;
		eyeY = size.width * 310.0f / 1024.0f;
		eyeWidth = size.width * 80.0f / 1024.0f;
		eyeHeight = size.width * 50.0f / 1024.0f;
		
		earX = size.width * 178.0f / 1024.0f;
		earY = size.width * 210.0f / 1024.0f;
		earWidth = size.width * 72.0f / 1024.0f;
		earHeight = size.width * 72.0f / 1024.0f;
		
		// only this one will translate to left-bottom corner
		nx1 = size.width * 514.0f / 1024.0f;
		ny1 = size.width * 272.0f / 1024.0f;
		nx2 = size.width * 488.0f / 1024.0f;
		ny2 = size.width * 184.0f / 1024.0f;
		nx3 = size.width * 521.0f / 1024.0f;
		ny3 = size.width * 184.0f / 1024.0f;
		
		mustacheX = size.width * 139.0f / 1024.0f;
		mustacheY = size.width * 11.0f / 1024.0f;
		mustacheControlY = size.width * 225.0f / 1024.0f;
		mustacheCenterY = size.width * 96.0f / 1024.0f;
		
		font = p.loadFont("NimbusMonL-Regu-128.vlw");
		fontSize = size.width * 128.0f / 1024.0f;
		p.textFont(font, fontSize);
		
		z = new Zzz();
		ZzzManager = new ArrayList<Zzz>();
	}

	@Override
	public void display() {
		
		// Draw hair
		if(isInvert) {
			p.fill(18, 20, 28);
		} else {
			p.fill(237, 235, 227);
		}
		p.noStroke();
		p.rect(size.x, size.y, size.width, size.height);
		
		// Draw halo shadow
		if(isInvert) {
			p.fill(43, 38, 40);
		} else {
			p.fill(212, 217, 215);
		}
		p.noStroke();
		p.pushMatrix();
		p.translate(size.x+(size.width/2), size.y+size.height);
		p.ellipse(0, -haloShadowY, haloWidth, haloHeight);
		p.popMatrix();
		
		// Draw inner halo shadow
		if(isInvert) {
			p.fill(18, 20, 28);
		} else {
			p.fill(237, 235, 227);
		}
		p.noStroke();
		p.pushMatrix();
		p.translate(size.x+(size.width/2), size.y+size.height);
		p.ellipse(0, -haloShadowY, innerHaloWidth, innerHaloHeight);
		p.popMatrix();
		
		// Draw background
		if(isInvert) {
			p.fill(55, 61, 93);
		} else {
			p.fill(200, 194, 162);
		}
		p.pushMatrix();
		p.translate(size.x+(size.width/2), size.y+size.height);
		p.beginShape();
		p.vertex(-size.width/2, 0);
		p.vertex(-headWidth/2, 0);
		p.vertex(-headWidth/2, -headHeight);
		p.bezierVertex(-headWidth/2, -headControlY, headWidth/2, -headControlY, headWidth/2, -headHeight);
		p.vertex(headWidth/2, 0);
		p.vertex((size.width/2)+1, 0);
		p.vertex((size.width/2)+1, -size.height);
		p.vertex(-size.width/2, -size.height);
		p.endShape(p.CLOSE);
		p.popMatrix();
		
		// Draw halo
		if(isInvert) {
			p.fill(0, 78, 167);
		} else {
			p.fill(255, 177, 88);
		}
		p.noStroke();
		p.pushMatrix();
		p.translate(size.x+(size.width/2), size.y+size.height);
		p.ellipse(0, -haloY, haloWidth, haloHeight);
		p.popMatrix();
		
		// Draw inner halo
		if(isInvert) {
			p.fill(55, 61, 93);
		} else {
			p.fill(200, 194, 162);
		}
		p.noStroke();
		p.pushMatrix();
		p.translate(size.x+(size.width/2), size.y+size.height);
		p.ellipse(0, -haloY, innerHaloWidth, innerHaloHeight);
		p.popMatrix();
		
		// Draw face
		if(isInvert) {
			p.fill(2, 35, 58);
		} else {
			p.fill(253, 220, 167);
		}
		p.noStroke();
		p.pushMatrix();
		p.translate(size.x+(size.width/2), size.y+size.height);
		p.beginShape();
		p.vertex(-faceWidth/2, 0);
		p.vertex(-faceWidth/2, -faceHeight);
		p.bezierVertex(-faceWidth/2, -faceHeight, x73, -faceHeight, x73, -y73);
		p.bezierVertex(x73, -faceHeight, faceWidth/2, -faceHeight, faceWidth/2, -faceHeight);
		p.vertex(faceWidth/2, 0);
		p.endShape(p.CLOSE);
		p.popMatrix();
		
		// Draw eyes
		if(isInvert) {
			p.stroke(0, 96, 121);
		} else {
			p.stroke(255, 159, 134);
		}
		p.noFill();
		p.strokeWeight(3);
		p.pushMatrix();
		p.translate(size.x+(size.width/2), size.y+size.height);
		p.arc(-eyeX, -eyeY, eyeWidth, eyeHeight, 0, p.PI);
		p.arc(eyeX, -eyeY, eyeWidth, eyeHeight, 0, p.PI);
		p.popMatrix();
		p.strokeWeight(1);
		
		// Draw ears
		if(isInvert) {
			p.fill(2, 35, 58);
		} else {
			p.fill(253, 220, 167);
		}
		p.noStroke();
		p.pushMatrix();
		p.translate(size.x+(size.width/2), size.y+size.height);
		p.arc(-earX, -earY, earWidth, earHeight, p.HALF_PI, 3*p.HALF_PI);
		p.arc(earX, -earY, earWidth, earHeight, p.TWO_PI-p.HALF_PI, p.TWO_PI+p.HALF_PI);
		p.popMatrix();
		
		// Draw nose
		if(isInvert) {
			p.fill(0, 66, 119);
		} else {
			p.fill(255, 189, 136);
		}
		p.noStroke();
		p.pushMatrix();
		p.translate(size.x, size.y+size.height);
		p.triangle(nx1, -ny1, nx2, -ny2, nx3, -ny3);
		p.popMatrix();
		
		// Draw mustache
		if(isInvert) {
			p.fill(18, 20, 28);
		} else {
			p.fill(237, 235, 227);
		}
		p.noStroke();
		p.pushMatrix();
		p.translate(size.x+(size.width/2), size.y+size.height);
		p.beginShape();
		p.vertex(-mustacheX, -mustacheY);
		p.bezierVertex(-mustacheX, -mustacheControlY, mustacheX, -mustacheControlY, mustacheX, -mustacheY);
		p.bezierVertex(mustacheX, -mustacheY, 0, -mustacheY, 0, -mustacheCenterY);
		p.bezierVertex(0, -mustacheY, -mustacheX, -mustacheY, -mustacheX, -mustacheY);
		p.endShape(p.CLOSE);
		p.popMatrix();
		
		// Draw Zzz
		// z.display();
		for(int i = 0; i < ZzzManager.size(); i++) {
			Zzz tempZzz = (Zzz) ZzzManager.get(i);
			tempZzz.display();
		}
	}

	@Override
	public void update() {
		
		for(int i = ZzzManager.size()-1; i >= 0; i--) {
			Zzz tempZzz = (Zzz) ZzzManager.get(i);
			tempZzz.update();
			if(tempZzz.isDead()) {
				ZzzManager.remove(i);
			}
		}
		
		/*
		for(int i = 0; i < ZzzManager.size(); i++) {
			Zzz tempZzz = (Zzz) ZzzManager.get(i);
			tempZzz.update();
		}
		*/
		// z.update();
	}
	
	public void bang() {
		ZzzManager.add(new Zzz());
	}

	class Zzz {
		
		float x, y;
		float startY;
		boolean isUpperCase = false;
		float life;
		
		public Zzz() {
			x = size.x + size.width / 2;
			x += p.random(-fontSize, fontSize);
			y = size.y + size.height - mustacheCenterY;
			startY = y;
			if(p.random(100) > 50) isUpperCase = true;
		}
		
		public void update() {
			x += p.random(-1, 1);
			y--;
			life = p.map(y, startY, size.y, fontSize, 0);
		}
		
		public void display() {
			if(isInvert) {
				p.fill(192, 207, 207, p.map(y, startY, size.y, 255, 0));
			} else {
				// p.fill(63, 48, 48);
				p.fill(63, 48, 48, p.map(y, startY, size.y, 255, 0));
			}
			// float s = p.map(y, startY, size.y, fontSize, 0);
			// p.textFont(font, fontSize);
			p.textFont(font, life);
			if(isUpperCase) {
				p.text("Z", x, y);
			} else {
				p.text("z", x, y);
			}
		}
		
		public boolean isDead() {
			if(life < 0.0f) {
				return true;
			} else {
				return false;
			}
		}
	}
}
