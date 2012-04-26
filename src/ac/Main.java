package ac;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.twitter.processing.*;

import oscP5.OscMessage;
import oscP5.OscP5;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import toxi.geom.Rect;
import ac.panels.FifthDay;
import ac.panels.FirstDay;
import ac.panels.FourthDay;
import ac.panels.SecondDay;
import ac.panels.SeventhDay;
import ac.panels.SixthDay;
import ac.panels.ThirdDay;

public class Main extends PApplet {

	PanelManager pm;
	FirstDay d1;
	SecondDay d2;
	ThirdDay d3;
	FourthDay d4;
	FifthDay d5;
	SixthDay d6;
	SeventhDay d7;
	boolean flag;
	PImage b;
	boolean[] mutes, singles;
	ArrayList<Integer> panels;
	// Receiver receiver;
	OscP5 oscP5;
	Bubble bubble;
	Typeface typeface;
	String[] keywords = {" light ", " water ", " land ", " season ", " bird ", " human ", " sleep "};
	String[] speech = new String[7];
	boolean isInvert;
	boolean isEcho;
	
	public static void main(String[] args) {
		PApplet.main(new String[] { "ac.Main" });
	}
	
	public void init() {
		  frame.dispose();
		  frame.setUndecorated(true);
		  super.init();
	}
	
	@SuppressWarnings("deprecation")
	public void setup() {
		// size(1024, 768, PGraphicsOpenGL.OPENGL);
		size(1024, 768, OPENGL);
		// hint(ENABLE_OPENGL_4X_SMOOTH);
		noCursor();
		// frameRate(60);
		// size(1024, 768);
		// smooth();		
		pm = new PanelManager(this);
		pm.setPanels();
		d1 = new FirstDay(this, new Rectangle(0, 0, width, height));
		d2 = new SecondDay(this, new Rectangle(0, 0, width, height));
		d3 = new ThirdDay(this, new Rectangle(0, 0, width, height));
		d4 = new FourthDay(this, new Rectangle(0, 0, width, height));
		d5 = new FifthDay(this, new Rectangle(0, 0, width, height));
		d6 = new SixthDay(this, new Rectangle(0, 0, width, height));
		d7 = new SeventhDay(this, new Rectangle(0, 0, width, height));
		// d1 = new FirstDay(this, pm.panels.get(0));
		// d2 = new SecondDay(this, pm.panels.get(1));
		b = loadImage("bg.png");
		mutes = new boolean[8];
		for(int i = 0; i < mutes.length; i++) {
			mutes[i] = true;
		}
		singles = new boolean[8];
		for(int i = 0; i < singles.length; i++) {
			singles[i] = false;
		}
		panels = new ArrayList<Integer>();
		// panels.add(1);
		// panels.add(2);
		// receiver = new Receiver();
		oscP5 = new OscP5(this, "127.0.0.1", 12000);
		bubble = new Bubble(this);
		
		TweetStream s = new TweetStream(this, "stream.twitter.com", 80, "1/statuses/sample.json", "_akinoshi_", "sdfjkl888?");
		s.go();
		
		// bubble.setText("This is a test.\nNatchan! tastes good.");
		// bubble.setText("Those who believe will stand alongside [the Prophet], their light streaming on ahead of them and to their right. They will say, Our Lord .");
		// bubble.addText("This is a test.\nNatchan! tastes good.", new Rect(0, 0, 512, 768));
		
		typeface = new Typeface(this);
		
		for(int i = 0; i < speech.length; i++) {
			speech[i] = "";
		}
		
		isInvert = false;
	}
	
	public void tweet(Status tweet) {
		// System.out.println(tweet.text());
		// Pattern pattern = Pattern.compile("the");
		// Matcher matcher = pattern.matcher(tweet.text());
		// if(matcher.find()) bubble.setText(tweet.text());
		// bubble.setText(tweet.text());
		for(int i = 0; i < keywords.length; i++) {
			Pattern pattern = Pattern.compile(keywords[i]);
			Matcher matcher = pattern.matcher(tweet.text());
			if(matcher.find()) {
				// System.out.println(tweet.text());
				speech[i] = tweet.text();
			}
		}
	}
	
	public void draw() {
		
		// background(71, 72, 74);
		background(0);
		
		// d5.display();
		
		// d4.update();
		// d4.display();
		
		/*
		d2.update();
		d2.display();
		d1.update();
		d1.display();
		*/
		
		// Draw 1st day first
		for(int i = 0; i < panels.size(); i++) {
			int value = panels.get(i).intValue();
			if(value == 1) {
				if(!isInvert) d1.update();
				d1.display();
			}
		}
		
		// System.out.println(panels.size());
		for(int i = 0; i < panels.size(); i++) {
			int value = panels.get(i).intValue();
			switch(value) {
				/*
				case 1:
					d1.update();
					d1.display();
					break;
				*/
				case 2:
					if(!isInvert) d2.update();
					d2.display();
					break;
				case 3:
					if(!isInvert) d3.update();
					d3.display();
					break;
				case 4:
					if(!isInvert) d4.update();
					d4.display();
					break;
				case 5:
					if(!isInvert) d5.update();
					d5.display();
					break;
				case 6:
					if(!isInvert) d6.update();
					d6.display();
					break;
				case 7:
					if(!isInvert) d7.update();
					d7.display();
					break;
				default:
					break;
			}
		}
		
		// image(b, 0, 0);
		bubble.draw();
		
		// pm.display();
		
		typeface.display();
	}
		
	public void keyPressed() {
		
		typeface.keyPressed();
		
		/*
		int number;
		switch(key) {
			case '1':
				number = 1;
				break;
			case '2':
				number = 2;
				break;
			case '3':
				number = 3;
				break;
			case '4':
				number = 4;
				break;
			case '5':
				number = 5;
				break;
			case '6':
				number = 6;
				break;
			case '7':
				number = 7;
				break;
			case '8':
				number = 8;
				break;
			case '9':
				number = 9;
				break;
			case ' ':
				pm.toggle();
				d1.toggle();
				d2.toggle();
				d3.toggle();
				d4.toggle();
				d5.toggle();
				d6.toggle();
				d7.toggle();
				bubble.toggle();
				number = 0;
				break;
			default:
				number = 0;
				break;
		}
		
		if(number != 0 && number <= 7) {
		
		bubble.clear();
			
		if(mutes[number]) {
			panels.add(number);
		} else {
			for(int i = 0; i < panels.size(); i++) {
				if(panels.get(i).intValue() == number) {
					panels.remove(i);
				}
			}
		}
		mutes[number] = !mutes[number];
		pm.panelNum = panels.size();
		pm.setPanels();
		for(int i = 0; i < panels.size(); i++) {
			int value = panels.get(i).intValue();
			switch(value) {
				case 1:
					d1 = new FirstDay(this, pm.panels.get(i));
					addSpeech(1, i);
					break;
				case 2:
					d2 = new SecondDay(this, pm.panels.get(i));
					addSpeech(2, i);
					break;
				case 3:
					d3 = new ThirdDay(this, pm.panels.get(i));
					addSpeech(3, i);
					break;
				case 4:
					d4 = new FourthDay(this, pm.panels.get(i));
					addSpeech(4, i);
					break;
				case 5:
					d5 = new FifthDay(this, pm.panels.get(i));
					addSpeech(5, i);
					break;
				case 6:
					d6 = new SixthDay(this, pm.panels.get(i));
					addSpeech(6, i);
					break;
				case 7:
					d7 = new SeventhDay(this, pm.panels.get(i));
					addSpeech(7, i);
					break;
				default:
					// d2 = new SecondDay(this, pm.panels.get(i));
					break;
			}
		}
		
		if(keyCode == LEFT) {
			if (pm.panelNum > 1) pm.panelNum--;
		} else if (keyCode == RIGHT) {
		    if (pm.panelNum < 7) pm.panelNum++;
		}
		pm.setPanels();
		
		if(key == '1') {
			// d1.setPanel((Rectangle) pm.panels.get(0));
			// d1 = new FirstDay(this, pm.panels.get(0));
			d2 = new SecondDay(this, pm.panels.get(0));
			flag = !flag;
		} else if(key == '2') {
			if(pm.panels.size() > 1) {
				// d1.setPanel((Rectangle) pm.pap.rect(size.x, size.y+size.height/2, size.width, size.height/6);nels.get(1));
				// d1 = new FirstDay(this, pm.panels.get(1));
				d2 = new SecondDay(this, pm.panels.get(1));
			}
		} else if(key == ' ') {
			save("Screenshot.png");
		}
		
		}
		*/
	}
	
	public void addSpeech(int day, int panel) {
		if(speech[day-1] != "") {
			bubble.addText(speech[day-1],
					new Rect(pm.panels.get(panel).x,
							 pm.panels.get(panel).y,
							 pm.panels.get(panel).width,
							 pm.panels.get(panel).height));
		}
	}
	
	public void mousePressed() {
		// println("(x, y) = (" + mouseX + ", " + mouseY + ")");
		// save("Screenshot-4.png");
		/*
		int where = (int) map(mouseX, 0, width, 1, d2.res-1);
		d2.ropeV[where] += mouseY-height/2;
		d2.ropeV[where-1] += mouseY-height/2;
		d2.ropeV[where+1] += mouseY-height/2;
		*/
		
		// d1.bang();
		// d1.bang(60);
		// d7.bang();
	}
	
	public void oscEvent(OscMessage msg) {
		// System.out.println("foo");
		// System.out.println(msg.get(0).intValue());
		// System.out.println(msg.checkAddrPattern("/test"));
		if(msg.checkAddrPattern("/mute")) {
			int number = msg.get(0).intValue();
			
			if(number != 8) {
			
			bubble.clear();
			
			if(mutes[number]) {
				panels.add(number);
			} else {
				for(int i = 0; i < panels.size(); i++) {
					if(panels.get(i).intValue() == number) {
						panels.remove(i);
					}
				}
			}
			mutes[number] = !mutes[number];
			pm.panelNum = panels.size();
			pm.setPanels();
			for(int i = 0; i < panels.size(); i++) {
				int value = panels.get(i).intValue();
				switch(value) {
					case 1:
						d1 = new FirstDay(this, pm.panels.get(i));
						if(isInvert) d1.toggle();
						if(isEcho) addSpeech(1, i);
						break;
					case 2:
						d2 = new SecondDay(this, pm.panels.get(i));
						if(isInvert) d2.toggle();
						if(isEcho) addSpeech(2, i);
						break;
					case 3:
						d3 = new ThirdDay(this, pm.panels.get(i));
						if(isInvert) d3.toggle();
						if(isEcho) addSpeech(3, i);
						break;
					case 4:
						d4 = new FourthDay(this, pm.panels.get(i));
						if(isInvert) d4.toggle();
						if(isEcho) addSpeech(4, i);
						break;
					case 5:
						d5 = new FifthDay(this, pm.panels.get(i));
						if(isInvert) d5.toggle();
						if(isEcho) addSpeech(5, i);
						break;
					case 6:
						d6 = new SixthDay(this, pm.panels.get(i));
						if(isInvert) d6.toggle();
						if(isEcho) addSpeech(6, i);
						break;
					case 7:
						d7 = new SeventhDay(this, pm.panels.get(i));
						if(isInvert) d7.toggle();
						if(isEcho) addSpeech(7, i);
						break;
					default:
						// d2 = new SecondDay(this, pm.panels.get(i));
						break;
				}
			}
			// System.out.println(panels.size());
			
			}
			
		} else if(msg.checkAddrPattern("/syn1")) {
			// System.out.println(msg.get(0).intValue());
			d1.bang(msg.get(0).intValue());
		} else if(msg.checkAddrPattern("/drum1")) {
			if(msg.get(0).floatValue() != 0.0f) d2.bang();
			// System.out.println(msg.get(0).floatValue());
		} else if(msg.checkAddrPattern("/syn2")) {
			d3.bang(msg.get(0).intValue());
		} else if(msg.checkAddrPattern("/drum2")) {
			if(msg.get(0).floatValue() != 0.0f) d4.bang();
		} else if(msg.checkAddrPattern("/drum3")) {
			if(msg.get(0).floatValue() != 0.0f) d5.bang();
		} else if(msg.checkAddrPattern("/drum4")) {
			if(msg.get(0).floatValue() != 0.0f) d7.bang();
		} else if(msg.checkAddrPattern("/syn3")) {
			d6.bang();
		} else if(msg.checkAddrPattern("/invert")) {
			isInvert = !isInvert;
			pm.toggle();
			d1.toggle();
			d2.toggle();
			d3.toggle();
			d4.toggle();
			d5.toggle();
			d6.toggle();
			d7.toggle();
			bubble.toggle();
		} else if(msg.checkAddrPattern("/echo")) {
			isEcho = !isEcho;
		}
	}
}