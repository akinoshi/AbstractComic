package ac;

import java.util.ArrayList;
import java.util.Iterator;

import processing.core.PApplet;
import processing.core.PFont;
import toxi.geom.*;
import toxi.processing.ToxiclibsSupport;

public class Bubble {
	
	ToxiclibsSupport gfx;
	PApplet p;
	PFont font;
	String tweetText = "";
	int fontSize = 16;
	ArrayList<ClipCell> clipcells;
	public boolean isInvert;
	
	Bubble(PApplet p) {
		this.p = p;
		gfx = new ToxiclibsSupport(p);
		font = p.loadFont("Purisa-48.vlw");
		p.textFont(font, fontSize);
		clipcells = new ArrayList<ClipCell>();
	}
	
	public void addText(String text, Rect bounds) {
		clipcells.add(new ClipCell(text, bounds));
	}
	
	public void setText(String text) {
		float width = text.length() * (122/15);
		// System.out.println(text.length());
		
		float count = 0;
		for(int i = 0; i < text.length(); i++) {
			count += font.width(text.charAt(i)) * fontSize;
			// System.out.println(font.width(text.charAt(i)));
		}
		// System.out.println(count);
		width = count;
		// w = width;
		
		// c.dot = new Ellipse(width/2, 200-(18/4), width/2, 25*2);
		tweetText = text;
	}
	
	public void draw() {
		
		p.textFont(font, fontSize);
		for(int i = 0; i < clipcells.size(); i++) {
			ClipCell c = (ClipCell) clipcells.get(i);
			c.draw(gfx);
		}
		
		/*
		for(Iterator i = clipcells.iterator(); i.hasNext();) {
			ClipCell c = (ClipCell) i.next();
			c.draw(gfx);
		}
		
		// c.draw(gfx);
		/*
		p.stroke(255, 0, 0);
		p.noFill();
		p.rect(0, 200-fontSize, w, fontSize);
		*/
	}
	
	public void clear() {
		clipcells.clear();
	}
	
	public void toggle() {
		isInvert = !isInvert;
	}
	
	class ClipCell {
		
		Rect bounds;
		Ellipse dot;
		String text, newText;
		float x, y, w, h;
		int color;
		int invertedColor;
		boolean isLeft;
		
		public ClipCell(Rect bounds) {
			this.bounds = bounds;
			this.dot = new Ellipse(500, 200, 200, 300);
		}
		
		public ClipCell(String text, Rect bounds) {
			this.bounds = bounds;
			this.text = text;
			this.newText = "";
			if(p.random(100) > 50.0f) {
				color = p.color(246, 194, 82);
				invertedColor = p.color(9, 61, 173);
			} else {
				color = p.color(101, 255, 255);
				invertedColor = p.color(154, 0, 0);
			}
			if(p.random(100) > 50.0f) {
				isLeft = true;
			} else {
				isLeft = false;
			}
			
			x = bounds.x + 20.0f;
			y = bounds.y + 35.0f;
			
			/*
			String[] temp = p.split(text, "\n");
			float maxWidth = 0;
			for(int i = 0; i < temp.length; i++) {
				// System.out.println(temp[i]);
				float width = 0;
				for(int j = 0; j < temp[i].length(); j++) {
					width += font.width(temp[i].charAt(j)) * fontSize;
				}
				if(width > maxWidth) maxWidth = width;
			}
			w = maxWidth;
			h = temp.length * fontSize + 10 * (temp.length - 1);
			*/
			
			// this.dot = new Ellipse(500, 200, 200, 300);
			
			float count = 20.0f;
			for(int i = 0; i < text.length(); i++) {
				// System.out.println(count);
				// System.out.println(bounds.width);
				float Rw = 0.0f; 
				if(bounds.width >= 346.0) {
					Rw = 346.0f;
				} else {
					Rw = bounds.width;
				}
				// if(count + font.width(text.charAt(i)) * fontSize < bounds.width - 20.0f) {
				if(count + font.width(text.charAt(i)) * fontSize < Rw - 20.0f) {
					newText += text.charAt(i);
					count += font.width(text.charAt(i)) * fontSize;
				} else {
					newText += "\n";
					newText += text.charAt(i);
					count = 20.0f;
				}
			}
			// System.out.println(newText);
			
			String[] temp = p.split(newText, "\n");
			float maxWidth = 0;
			for(int i = 0; i < temp.length; i++) {
				float width = 0;
				for(int j = 0; j < temp[i].length(); j++) {
					width += font.width(temp[i].charAt(j)) * fontSize;
				}
				if(width > maxWidth) maxWidth = width;
			}
			w = maxWidth;
			h = temp.length * fontSize + 10 * (temp.length - 1);
			
			this.dot = new Ellipse(x+w/2, y+h/2-fontSize, w/p.sqrt(2), h/p.sqrt(2));
		}
		
		void draw(ToxiclibsSupport gfx) {
			// p.stroke(0);
			p.noStroke();
			if(isInvert) {
				p.fill(invertedColor);
			} else {
				p.fill(color);
			}
			p.pushMatrix();
			p.strokeWeight(1);
			Polygon2D poly = dot.toPolygon2D(30);
			PolygonClipper2D clipper = new SutherlandHodgemanClipper(bounds);
			poly = clipper.clipPolygon(poly);
			gfx.polygon2D(poly);
			p.popMatrix();
			
			if(isInvert) {
				p.fill(255);
			} else {
				p.fill(0);
			}
			// p.text(text, 0, 200);
			// p.text(text, bounds.x + 20, bounds.y + h + 20);
			// p.text(newText, bounds.x + 20, bounds.y + 35);
			p.text(newText, x, y);
			
			p.stroke(255, 0, 0);
			p.noFill();
			// p.rect(0, 200-fontSize, w, h);
			// p.rect(x, y - fontSize, w, h);
			
			p.noStroke();
			// p.stroke(255, 0, 0);
			if(isInvert) {
				p.fill(invertedColor);
			} else {
				p.fill(color);
			}
			float diff = (h/2) - h/p.sqrt(2);
			diff /= 2;
			// p.line(x+0.4f*w, y+h/2-fontSize+h/p.sqrt(2)+diff, x+0.6f*w, y+h/2-fontSize+h/p.sqrt(2)+diff);
			if(isLeft) {
				p.triangle(x+0.3f*w, y+h/2-fontSize+h/p.sqrt(2)+diff,
						   x+0.4f*w, y+h/2-fontSize+h/p.sqrt(2)+diff,
					       x+0.5f*w, y+h/2-fontSize+h/p.sqrt(2)+diff+h*0.5f);
			} else {
				p.triangle(x+0.6f*w, y+h/2-fontSize+h/p.sqrt(2)+diff,
						   x+0.7f*w, y+h/2-fontSize+h/p.sqrt(2)+diff,
					       x+0.5f*w, y+h/2-fontSize+h/p.sqrt(2)+diff+h*0.5f);
			}
		}
	}
}
