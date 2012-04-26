package ac.panels;

import java.awt.Rectangle;

import processing.core.PApplet;

public abstract class Panel extends PApplet {
	
	PApplet p;
	Rectangle size;
	public boolean isInvert;
	
	public abstract void update();
	public abstract void display();
	
	public Panel(PApplet p, Rectangle a) {
		this.p = p;
		size = a;
		isInvert = false;
	}
	
	public void setPanel(Rectangle a) {
		size = a;
	}
	
	public void toggle() {
		isInvert = !isInvert;
	}
}