package ac.panels;

import java.awt.Rectangle;
import java.util.ArrayList;

import processing.core.PApplet;

public class ThirdDay extends Panel {
	
	ArrayList<Tree> trees;
	BackTree t;
	ArrayList<BackTree> backTrees;
	
	public ThirdDay(PApplet p, Rectangle a) {
		super(p, a);
		trees = new ArrayList<Tree>();
		for(int i = -45; i <= 45; i+=10) {
			trees.add(new Tree(p.radians(i)));
		}
		t = new BackTree(p.radians(-40));
		backTrees = new ArrayList<BackTree>();
		for(int i = -50; i <= 50; i+=10) {
			backTrees.add(new BackTree(p.radians(i)));
		}
	}

	@Override
	public void display() {
		/*
		// Draw background
		p.noStroke();
		p.fill(91, 160, 167);
		p.rect(size.x, size.y, size.width, size.height);
		*/
		
		p.noStroke();
		if(isInvert) {
			p.fill(153, 101, 176);
		} else {
			p.fill(102, 154, 79);
		}
		p.rect(size.x, size.y, size.width, size.height/2);
		if(isInvert) {
			p.fill(138, 88, 183);
		} else {
			p.fill(117, 167, 72);
		}
		p.rect(size.x, size.y+size.height/2, size.width, size.height/6);
		if(isInvert) {
			p.fill(115, 72, 190);
		} else {
			p.fill(140, 183, 65);
		}
		p.rect(size.x, (size.y+4*size.height/6)-1, size.width, size.height/6);
		if(isInvert) {
			p.fill(77, 46, 202);
		} else {
			p.fill(178, 209, 53);
		}
		p.rect(size.x, (size.y+5*size.height/6)-2, size.width, size.height/6);
		
		/*
		// Tree test
		p.pushMatrix();
		p.translate(size.x+size.width/2, size.y+size.height);
		p.translate(0, -size.width/2);
		p.noStroke();
		p.fill(2, 36, 38);
		p.rect(-10, -40, 20, 40);
		p.fill(178, 210, 51);
		p.triangle(0, -160, -40, -40, 0, -40);
		p.fill(102, 154, 79);
		p.triangle(0, -160, 40, -40, 0, -40);
		// Shadow
		p.fill(2, 36, 38);
		p.rect(-10, 0, 20, 40);
		p.triangle(0, 160, -40, 40, 40, 40);
		p.popMatrix();
		
		p.pushMatrix();
		p.translate(size.x+size.width/2, size.y+size.height);
		p.rotate(p.PI/4);
		p.translate(0, -size.width/2);
		p.noStroke();
		p.fill(2, 36, 38);
		p.rect(-10, -40, 20, 40);
		p.fill(178, 210, 51);
		p.triangle(0, -160, -40, -40, 0, -40);
		p.popMatrix();
		*/
		
		/*
		// Tree shadow test
		p.pushMatrix();
		p.translate(size.x+size.width/2, size.y+size.height);
		p.translate(0, -size.width/2);
		p.fill(197, 65, 61);
		p.rect(-10, 0, 20, 40);
		p.triangle(0, 160, -40, 40, 40, 40);
		p.popMatrix();
		*/
		
		// t.display();
		for(int i = 0; i < backTrees.size(); i++) {
			BackTree tempTree = (BackTree) backTrees.get(i);
			tempTree.display();
		}
		
		for(int i = 0; i < trees.size(); i++) {
			Tree tempTree = (Tree) trees.get(i);
			tempTree.display();
		}
		
		// Draw ground
		p.pushMatrix();
		p.translate(size.x+size.width/2, size.y+size.height);
		p.noStroke();
		if(isInvert) {
			p.fill(171, 219, 231);
		} else {
			p.fill(84, 36, 24);
		}
		p.arc(0, 0, size.width, size.width, p.PI, p.TWO_PI);
		if(isInvert) {
			p.fill(137, 203, 221);
		} else {
			p.fill(118, 52, 34);
		}
		p.arc(0, 0, size.width, size.width, p.PI, p.PI+p.HALF_PI);
		p.arc(0, 0, size.width*0.85f, size.width, p.PI, p.TWO_PI);
		p.popMatrix();
	}

	@Override
	public void update() {
		
		for(int i = 0; i < trees.size(); i++) {
			Tree tempTree = (Tree) trees.get(i);
			tempTree.update();
		}
		
		for(int i = 0; i < backTrees.size(); i++) {
			BackTree tempTree = (BackTree) backTrees.get(i);
			tempTree.update();
		}
		
	}
	
	public void bang(int midi) {
		if(p.random(100) > 50) {
		// int value = (int) p.map(midi, 40, 70, 0, trees.size()-1);
			int value = (int) p.random(trees.size()-1);
			Tree tempTree = (Tree) trees.get(value);
			tempTree.posY = -(size.width/2)+tempTree.leafHeight+tempTree.stemHeight;

		} else {
			int value = (int) p.random(backTrees.size()-1);
			BackTree tempTree = (BackTree) backTrees.get(value);
			tempTree.posY = -(size.width/2)+tempTree.leafHeight+tempTree.stemHeight;
		}
	}

	class Tree {
		
		float stemWidth, stemHeight, leafWidth, leafHeight;
		float angle;
		float posY;
		
		Tree(float r) {
			stemWidth = size.width * (20f/1024f);
			stemHeight = size.width * (40f/1024f);
			leafWidth = size.width * (80f/1024f);
			leafHeight = size.width * (160f/1024f);
			angle = r;
			posY = -size.width/2;
		}
		
		public void update() {
			// posY -= size.height/240;
			posY -= 768/240;
			if(posY < -size.width/2) posY = -size.width/2;
		}
		
		public void display() {
			p.pushMatrix();
			p.translate(size.x+size.width/2, size.y+size.height);
			p.rotate(angle);
			// p.translate(0, -size.width/2);
			p.translate(0, posY);
			p.noStroke();
			if(isInvert) {
				p.fill(27, 68, 194);
			} else {
				p.fill(228, 187, 61);
			}
			p.rect(-stemWidth/2, -stemHeight, stemWidth, stemHeight);
			if(isInvert) {
				p.fill(77, 45, 204);
			} else {
				p.fill(178, 210, 51);
			}
			p.triangle(0, -leafHeight, -leafWidth/2, -stemHeight, 0, -stemHeight);
			if(isInvert) {
				p.fill(153, 101, 176);
			} else {
				p.fill(102, 154, 79);
			}
			p.triangle(0, -leafHeight, leafWidth/2, -stemHeight, 0, -stemHeight);
			/*
			p.fill(2, 36, 38);
			p.rect(-stemWidth/2, 0, stemWidth, stemHeight);
			p.triangle(0, leafHeight, -leafWidth/2, stemHeight, leafWidth/2, stemHeight);
			*/
			p.popMatrix();
		}
	}
	
	class BackTree extends Tree {

		BackTree(float r) {
			super(r);
			leafHeight = size.width * (140f/1024f);
			stemHeight = size.width * (20f/1024f);
		}
		
		public void display() {
			p.pushMatrix();
			p.translate(size.x+size.width/2, size.y+size.height);
			p.rotate(angle);
			// p.translate(0, -size.width/2);
			p.translate(0, posY);
			p.noStroke();
			if(isInvert) {
				p.fill(203, 156, 150);
			} else {
				p.fill(52, 99, 105);
			}
			p.rect(-stemWidth/2, -stemHeight, stemWidth, stemHeight);
			p.triangle(0, -leafHeight, -leafWidth/2, -stemHeight, 0, -stemHeight);
			p.triangle(0, -leafHeight, leafWidth/2, -stemHeight, 0, -stemHeight);
			p.popMatrix();
		}
	}
}
