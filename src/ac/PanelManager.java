package ac;

import java.awt.Rectangle;
import java.util.ArrayList;
import processing.core.PApplet;

public class PanelManager {

	public static final int GUTTER_SIZE = 8;
	
	public PApplet p;
	public int panelNum;
	public int panelHalfWidth, panelHalfHeight;
	public int panelOneThirdWidth, panelQuorterWidth;
	public ArrayList<Rectangle> panels;
	public boolean isInvert;
	
	public PanelManager(PApplet p) {
		this.p = p;
		panelNum = 1;
		
		panelHalfWidth = p.width / 2;
		panelOneThirdWidth = p.width / 3;
		panelHalfHeight = p.height / 2;
		panelQuorterWidth = p.width / 4;
		
		/*
		panelHalfWidth = (p.width - GUTTER_SIZE * 3) / 2;
		panelOneThirdWidth = (p.width - GUTTER_SIZE * 4) / 3;
	    panelHalfHeight = (p.height - GUTTER_SIZE * 3) / 2;
	    panelQuorterWidth = (p.width - GUTTER_SIZE * 5) / 4;
	    */
	    panels = new ArrayList<Rectangle>();
	    isInvert = false;
	}
	
	public void display() {
		if(isInvert) {
			p.stroke(0);
			p.fill(0);
		} else {
			p.stroke(255);
		    p.fill(255);
		}
		
	    // Draw rims
	    p.rect(0, 0, p.width, GUTTER_SIZE);
	    p.rect(p.width - GUTTER_SIZE, 0, GUTTER_SIZE, p.height);
	    p.rect(0, p.height - GUTTER_SIZE, p.width, GUTTER_SIZE);
	    p.rect(0, 0, GUTTER_SIZE, p.height);
	    
	    // Draw corner edges
	    leftTopEdge(GUTTER_SIZE, GUTTER_SIZE);
	    rightTopEdge(p.width-GUTTER_SIZE, GUTTER_SIZE);
	    rightBottomEdge(p.width-GUTTER_SIZE, p.height-GUTTER_SIZE);
	    leftBottomEdge(GUTTER_SIZE, p.height-GUTTER_SIZE);
	    
	    switch(panelNum) {
	    	case 1:
	    		break;
	    	case 2:
	    		// Draw corner edges
	    		rightTopEdge(GUTTER_SIZE+panelHalfWidth, GUTTER_SIZE);
	    		rightBottomEdge(GUTTER_SIZE+panelHalfWidth, p.height-GUTTER_SIZE);
	    		
	    		leftTopEdge(2*GUTTER_SIZE+panelHalfWidth, GUTTER_SIZE);
	    		leftBottomEdge(2*GUTTER_SIZE+panelHalfWidth, p.height-GUTTER_SIZE);
	    		
	    		// Draw rim
	    	    p.rect(GUTTER_SIZE+panelHalfWidth, 0, GUTTER_SIZE, p.height);
	    		break;
	    	case 3:
	    		// Draw corner edges
	    		rightTopEdge(GUTTER_SIZE+panelOneThirdWidth, GUTTER_SIZE);
	    		rightBottomEdge(GUTTER_SIZE+panelOneThirdWidth, p.height-GUTTER_SIZE);
	    		
	    		leftTopEdge(2*GUTTER_SIZE+panelOneThirdWidth, GUTTER_SIZE);
	    		rightTopEdge(2*GUTTER_SIZE+2*panelOneThirdWidth, GUTTER_SIZE);
	    		rightBottomEdge(2*GUTTER_SIZE+2*panelOneThirdWidth, p.height-GUTTER_SIZE);
	    		leftBottomEdge(2*GUTTER_SIZE+panelOneThirdWidth, p.height-GUTTER_SIZE);
	    		
	    		leftTopEdge(3*GUTTER_SIZE+2*panelOneThirdWidth, GUTTER_SIZE);
	    		leftBottomEdge(3*GUTTER_SIZE+2*panelOneThirdWidth, p.height-GUTTER_SIZE);
	    		
	    		// Draw rims
	    		p.rect(GUTTER_SIZE+panelOneThirdWidth, 0, GUTTER_SIZE, p.height);
	    		p.rect(2*(GUTTER_SIZE+panelOneThirdWidth), 0, GUTTER_SIZE, p.height);
	    		break;
	    	case 4:
	    		// Draw corner edges
	    		rightTopEdge(GUTTER_SIZE+panelHalfWidth, GUTTER_SIZE);
	    		rightBottomEdge(GUTTER_SIZE+panelHalfWidth, GUTTER_SIZE+panelHalfHeight);
	    		leftBottomEdge(GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		
	    		leftTopEdge(2*GUTTER_SIZE+panelHalfWidth, GUTTER_SIZE);
	    		rightBottomEdge(p.width-GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		leftBottomEdge(2*GUTTER_SIZE+panelHalfWidth, GUTTER_SIZE+panelHalfHeight);
	    		
	    		leftTopEdge(GUTTER_SIZE, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightTopEdge(GUTTER_SIZE+panelHalfWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightBottomEdge(GUTTER_SIZE+panelHalfWidth, p.height-GUTTER_SIZE);
	    		
	    		leftTopEdge(2*GUTTER_SIZE+panelHalfWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightTopEdge(p.width-GUTTER_SIZE, 2*GUTTER_SIZE+panelHalfHeight);
	    		leftBottomEdge(2*GUTTER_SIZE+panelHalfWidth, p.height-GUTTER_SIZE);
	    		
	    		// Draw rims
	    		p.rect(GUTTER_SIZE+panelHalfWidth, 0, GUTTER_SIZE, p.height);
	    		p.rect(0, GUTTER_SIZE+panelHalfHeight, p.width, GUTTER_SIZE);
	    		break;
	    	case 5:
	    		// Draw corner edges
	    		rightTopEdge(GUTTER_SIZE+panelHalfWidth, GUTTER_SIZE);
	    		rightBottomEdge(GUTTER_SIZE+panelHalfWidth, GUTTER_SIZE+panelHalfHeight);
	    		leftBottomEdge(GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		
	    		leftTopEdge(2*GUTTER_SIZE+panelHalfWidth, GUTTER_SIZE);
	    		rightBottomEdge(p.width-GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		leftBottomEdge(2*GUTTER_SIZE+panelHalfWidth, GUTTER_SIZE+panelHalfHeight);
	    		
	    		leftTopEdge(GUTTER_SIZE, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightTopEdge(GUTTER_SIZE+panelOneThirdWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightBottomEdge(GUTTER_SIZE+panelOneThirdWidth, p.height-GUTTER_SIZE);
	    		
	    		leftTopEdge(2*GUTTER_SIZE+panelOneThirdWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightTopEdge(2*GUTTER_SIZE+2*panelOneThirdWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightBottomEdge(2*GUTTER_SIZE+2*panelOneThirdWidth, p.height-GUTTER_SIZE);
	    		leftBottomEdge(2*GUTTER_SIZE+panelOneThirdWidth, p.height-GUTTER_SIZE);
	    		
	    		leftTopEdge(3*GUTTER_SIZE+2*panelOneThirdWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightTopEdge(p.width-GUTTER_SIZE, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightBottomEdge(p.width-GUTTER_SIZE, p.height-GUTTER_SIZE);
	    		leftBottomEdge(3*GUTTER_SIZE+2*panelOneThirdWidth, p.height-GUTTER_SIZE);
	    		
	    		// Draw rims
	    		p.rect(GUTTER_SIZE+panelHalfWidth, 0,
	    			   GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		p.rect(0, GUTTER_SIZE+panelHalfHeight, p.width, GUTTER_SIZE);
	    		p.rect(GUTTER_SIZE+panelOneThirdWidth, GUTTER_SIZE+panelHalfHeight,
	    			   GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		p.rect(2*(GUTTER_SIZE+panelOneThirdWidth), GUTTER_SIZE+panelHalfHeight,
	    			   GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		break;
	    	case 6:
	    		// Draw corner edges
	    		rightTopEdge(GUTTER_SIZE+panelOneThirdWidth, GUTTER_SIZE);
	    		rightBottomEdge(GUTTER_SIZE+panelOneThirdWidth, GUTTER_SIZE+panelHalfHeight);
	    		leftBottomEdge(GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		
	    		leftTopEdge(2*GUTTER_SIZE+panelOneThirdWidth, GUTTER_SIZE);
	    		rightTopEdge(2*GUTTER_SIZE+2*panelOneThirdWidth, GUTTER_SIZE);
	    		rightBottomEdge(2*GUTTER_SIZE+2*panelOneThirdWidth, GUTTER_SIZE+panelHalfHeight);
	    		leftBottomEdge(2*GUTTER_SIZE+panelOneThirdWidth, GUTTER_SIZE+panelHalfHeight);
	    		
	    		leftTopEdge(3*GUTTER_SIZE+2*panelOneThirdWidth, GUTTER_SIZE);
	    		rightBottomEdge(p.width-GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		leftBottomEdge(3*GUTTER_SIZE+2*panelOneThirdWidth, GUTTER_SIZE+panelHalfHeight);
	    		
	    		leftTopEdge(GUTTER_SIZE, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightTopEdge(GUTTER_SIZE+panelOneThirdWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightBottomEdge(GUTTER_SIZE+panelOneThirdWidth, p.height-GUTTER_SIZE);
	    		
	    		leftTopEdge(2*GUTTER_SIZE+panelOneThirdWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightTopEdge(2*GUTTER_SIZE+2*panelOneThirdWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightBottomEdge(2*GUTTER_SIZE+2*panelOneThirdWidth, p.height-GUTTER_SIZE);
	    		leftBottomEdge(2*GUTTER_SIZE+panelOneThirdWidth, p.height-GUTTER_SIZE);
	    		
	    		leftTopEdge(3*GUTTER_SIZE+2*panelOneThirdWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightTopEdge(p.width-GUTTER_SIZE, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightBottomEdge(p.width-GUTTER_SIZE, p.height-GUTTER_SIZE);
	    		leftBottomEdge(3*GUTTER_SIZE+2*panelOneThirdWidth, p.height-GUTTER_SIZE);
	    		
	    		// Draw rims
	    		p.rect(0, GUTTER_SIZE+panelHalfHeight, p.width, GUTTER_SIZE);
	    		p.rect(GUTTER_SIZE+panelOneThirdWidth, 0, GUTTER_SIZE, p.width);
	    		p.rect(2*(GUTTER_SIZE+panelOneThirdWidth), 0, GUTTER_SIZE, p.width);
	    		break;
	    	case 7:
	    		// Draw corner edges
	    		rightTopEdge(GUTTER_SIZE+panelOneThirdWidth, GUTTER_SIZE);
	    		rightBottomEdge(GUTTER_SIZE+panelOneThirdWidth, GUTTER_SIZE+panelHalfHeight);
	    		leftBottomEdge(GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		
	    		leftTopEdge(2*GUTTER_SIZE+panelOneThirdWidth, GUTTER_SIZE);
	    		rightTopEdge(2*GUTTER_SIZE+2*panelOneThirdWidth, GUTTER_SIZE);
	    		rightBottomEdge(2*GUTTER_SIZE+2*panelOneThirdWidth, GUTTER_SIZE+panelHalfHeight);
	    		leftBottomEdge(2*GUTTER_SIZE+panelOneThirdWidth, GUTTER_SIZE+panelHalfHeight);
	    		
	    		leftTopEdge(3*GUTTER_SIZE+2*panelOneThirdWidth, GUTTER_SIZE);
	    		rightBottomEdge(p.width-GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		leftBottomEdge(3*GUTTER_SIZE+2*panelOneThirdWidth, GUTTER_SIZE+panelHalfHeight);
	    		
	    		leftTopEdge(GUTTER_SIZE, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightTopEdge(GUTTER_SIZE+panelQuorterWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightBottomEdge(GUTTER_SIZE+panelQuorterWidth, p.height-GUTTER_SIZE);
	    		
	    		leftTopEdge(2*GUTTER_SIZE+panelQuorterWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightTopEdge(2*GUTTER_SIZE+2*panelQuorterWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightBottomEdge(2*GUTTER_SIZE+2*panelQuorterWidth, p.height-GUTTER_SIZE);
	    		leftBottomEdge(2*GUTTER_SIZE+panelQuorterWidth, p.height-GUTTER_SIZE);
	    		
	    		leftTopEdge(3*GUTTER_SIZE+2*panelQuorterWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightTopEdge(3*GUTTER_SIZE+3*panelQuorterWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightBottomEdge(3*GUTTER_SIZE+3*panelQuorterWidth, p.height-GUTTER_SIZE);
	    		leftBottomEdge(3*GUTTER_SIZE+2*panelQuorterWidth, p.height-GUTTER_SIZE);
	    		
	    		leftTopEdge(4*GUTTER_SIZE+3*panelQuorterWidth, 2*GUTTER_SIZE+panelHalfHeight);
	    		rightTopEdge(p.width-GUTTER_SIZE, 2*GUTTER_SIZE+panelHalfHeight);
	    		leftBottomEdge(4*GUTTER_SIZE+3*panelQuorterWidth, p.height-GUTTER_SIZE);
	    		
	    		// Draw rims
	    		p.rect(0, GUTTER_SIZE+panelHalfHeight, p.width, GUTTER_SIZE);
	    		p.rect(GUTTER_SIZE+panelOneThirdWidth, 0,
	    			   GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		p.rect(2*(GUTTER_SIZE+panelOneThirdWidth), 0,
	    			   GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		p.rect(GUTTER_SIZE+panelQuorterWidth, GUTTER_SIZE+panelHalfHeight,
	    			   GUTTER_SIZE, GUTTER_SIZE + panelHalfHeight);
	    		p.rect(2*(GUTTER_SIZE+panelQuorterWidth), GUTTER_SIZE+panelHalfHeight,
	    			   GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		p.rect(3*(GUTTER_SIZE+panelQuorterWidth), GUTTER_SIZE+panelHalfHeight,
	    			   GUTTER_SIZE, GUTTER_SIZE+panelHalfHeight);
	    		break;
	      default:
	        break;
	    }
	    
	    for(int i = 0; i < panels.size(); i++) {
	    	Rectangle temp = (Rectangle) panels.get(i);
	        p.stroke(255, 0, 192);
	        p.noFill();
	        // p.rect(temp.x, temp.y, temp.width, temp.height);
	    }
	}

	public void setPanels() {
		panels.clear();
		
		switch(panelNum) {
		case 1:
			panels.add(new Rectangle(0, 0, p.width, p.height));
			break;
		case 2:
			panels.add(new Rectangle(0, 0, panelHalfWidth, p.height));
			panels.add(new Rectangle(panelHalfWidth, 0, panelHalfWidth, p.height));
			break;
		case 3:
			panels.add(new Rectangle(0, 0, panelOneThirdWidth, p.height));
			panels.add(new Rectangle(panelOneThirdWidth, 0, panelOneThirdWidth, p.height));
			panels.add(new Rectangle(2*panelOneThirdWidth, 0, panelOneThirdWidth, p.height));
			break;
		case 4:
			panels.add(new Rectangle(0, 0, panelHalfWidth, panelHalfHeight));
			panels.add(new Rectangle(panelHalfWidth, 0, panelHalfWidth, panelHalfHeight));
			panels.add(new Rectangle(0, panelHalfHeight, panelHalfWidth, panelHalfHeight));
			panels.add(new Rectangle(panelHalfWidth, panelHalfHeight, panelHalfWidth, panelHalfHeight));
			break;
		case 5:
			panels.add(new Rectangle(0, 0, panelHalfWidth, panelHalfHeight));
			panels.add(new Rectangle(panelHalfWidth, 0, panelHalfWidth, panelHalfHeight));
			panels.add(new Rectangle(0, panelHalfHeight, panelOneThirdWidth, panelHalfHeight));
			panels.add(new Rectangle(panelOneThirdWidth, panelHalfHeight, panelOneThirdWidth, panelHalfHeight));
			panels.add(new Rectangle(2*panelOneThirdWidth, panelHalfHeight, panelOneThirdWidth, panelHalfHeight));
			break;
		case 6:
			panels.add(new Rectangle(0, 0, panelOneThirdWidth, panelHalfHeight));
			panels.add(new Rectangle(panelOneThirdWidth, 0, panelOneThirdWidth, panelHalfHeight));
			panels.add(new Rectangle(2*panelOneThirdWidth, 0, panelOneThirdWidth, panelHalfHeight));
			panels.add(new Rectangle(0, panelHalfHeight, panelOneThirdWidth, panelHalfHeight));
			panels.add(new Rectangle(panelOneThirdWidth, panelHalfHeight, panelOneThirdWidth, panelHalfHeight));
			panels.add(new Rectangle(2*panelOneThirdWidth, panelHalfHeight, panelOneThirdWidth, panelHalfHeight));
			break;
		case 7:
			panels.add(new Rectangle(0, 0, panelOneThirdWidth, panelHalfHeight));
			panels.add(new Rectangle(panelOneThirdWidth, 0, panelOneThirdWidth, panelHalfHeight));
			panels.add(new Rectangle(2*panelOneThirdWidth, 0, panelOneThirdWidth, panelHalfHeight));
			panels.add(new Rectangle(0, panelHalfHeight, panelQuorterWidth, panelHalfHeight));
			panels.add(new Rectangle(panelQuorterWidth, panelHalfHeight, panelQuorterWidth, panelHalfHeight));
			panels.add(new Rectangle(2*panelQuorterWidth, panelHalfHeight, panelQuorterWidth, panelHalfHeight));
			panels.add(new Rectangle(3*panelQuorterWidth, panelHalfHeight, panelQuorterWidth, panelHalfHeight));
			break;
		}
		
		/*
		switch(panelNum) {
		case 1:
	        panels.add(new Rectangle(0, 0, p.width, p.height));
	        break;
	      case 2:
	        panels.add(new Rectangle(0, 0, 2 * GUTTER_SIZE + panelHalfWidth,
	        		 				 p.height - 1));
	        panels.add(new Rectangle(GUTTER_SIZE + panelHalfWidth, 0,
	        		                 2 * GUTTER_SIZE+panelHalfWidth, p.height - 1));
	        break;
	      case 3:
	        panels.add(new Rectangle(0, 0, 2 * GUTTER_SIZE+panelOneThirdWidth,
	        						 p.height - 1));
	        panels.add(new Rectangle(GUTTER_SIZE + panelOneThirdWidth, 0,
	        		                 2 * GUTTER_SIZE + panelOneThirdWidth, p.height - 1));
	        panels.add(new Rectangle(2 * (GUTTER_SIZE + panelOneThirdWidth), 0, 
	        		       			 2 * GUTTER_SIZE + panelOneThirdWidth, p.height - 1));
	        break;
	      case 4:
	        panels.add(new Rectangle(0, 0, 2 * GUTTER_SIZE + panelHalfWidth,
	        		                 2 * GUTTER_SIZE + panelHalfHeight));
	        panels.add(new Rectangle(GUTTER_SIZE + panelHalfWidth, 0,
	        		 				 2 * GUTTER_SIZE+panelHalfWidth,
	        		 				 2 * GUTTER_SIZE+panelHalfHeight));
	        panels.add(new Rectangle(0, GUTTER_SIZE+panelHalfHeight,
	        						 2 * GUTTER_SIZE + panelHalfWidth,
	        						 2 * GUTTER_SIZE+panelHalfHeight));
	        panels.add(new Rectangle(GUTTER_SIZE + panelHalfWidth,
	                                 GUTTER_SIZE + panelHalfHeight,
	                                 2 * GUTTER_SIZE + panelHalfWidth,
	                                 2 * GUTTER_SIZE + panelHalfHeight));
	        break;
	      case 5:
	        panels.add(new Rectangle(0, 0, 2 * GUTTER_SIZE + panelHalfWidth,
	                                 2 * GUTTER_SIZE + panelHalfHeight));
	        panels.add(new Rectangle(GUTTER_SIZE + panelHalfWidth, 0,
	                                 2 * GUTTER_SIZE + panelHalfWidth,
	                                 2 * GUTTER_SIZE + panelHalfHeight));
	        panels.add(new Rectangle(0, GUTTER_SIZE + panelHalfHeight,
	                                 2 * GUTTER_SIZE + panelOneThirdWidth,
	                                 2 * GUTTER_SIZE + panelHalfHeight));
	        panels.add(new Rectangle(GUTTER_SIZE + panelOneThirdWidth,
	                                 GUTTER_SIZE + panelHalfHeight,
	                                 2 * GUTTER_SIZE + panelOneThirdWidth,
	                                 2 * GUTTER_SIZE + panelHalfHeight));
	        panels.add(new Rectangle(2 * (GUTTER_SIZE + panelOneThirdWidth),
	                                 GUTTER_SIZE + panelHalfHeight,
	                                 2 * GUTTER_SIZE + panelOneThirdWidth,
	                                 2 * GUTTER_SIZE + panelHalfHeight));
	        break;
	      case 6:
	    	panels.add(new Rectangle(0, 0, 2 * GUTTER_SIZE + panelOneThirdWidth,
	    			 				 2 * GUTTER_SIZE + panelHalfHeight));
	    	panels.add(new Rectangle(GUTTER_SIZE + panelOneThirdWidth, 0,
	    							 2 * GUTTER_SIZE + panelOneThirdWidth,
	    							 2 * GUTTER_SIZE + panelHalfHeight));
	    	panels.add(new Rectangle(2 * (GUTTER_SIZE + panelOneThirdWidth), 0,
	    							 2 * GUTTER_SIZE + panelOneThirdWidth,
	    							 2 * GUTTER_SIZE + panelHalfHeight));
	    	panels.add(new Rectangle(0, GUTTER_SIZE + panelHalfHeight,
                    				 2 * GUTTER_SIZE + panelOneThirdWidth,
                    				 2 * GUTTER_SIZE + panelHalfHeight));
	    	panels.add(new Rectangle(GUTTER_SIZE + panelOneThirdWidth,
                    				 GUTTER_SIZE + panelHalfHeight,
                    				 2 * GUTTER_SIZE + panelOneThirdWidth,
                    				 2 * GUTTER_SIZE + panelHalfHeight));
	    	panels.add(new Rectangle(2 * (GUTTER_SIZE + panelOneThirdWidth),
                    				 GUTTER_SIZE + panelHalfHeight,
                    				 2 * GUTTER_SIZE + panelOneThirdWidth,
                    				 2 * GUTTER_SIZE + panelHalfHeight));
	    	break;
	      case 7:
	    	panels.add(new Rectangle(0, 0, 2 * GUTTER_SIZE + panelOneThirdWidth,
	    							 2 * GUTTER_SIZE + panelHalfHeight));
	    	panels.add(new Rectangle(GUTTER_SIZE + panelOneThirdWidth, 0,
						 			 2 * GUTTER_SIZE + panelOneThirdWidth,
						 			 2 * GUTTER_SIZE + panelHalfHeight));
	    	panels.add(new Rectangle(2 * (GUTTER_SIZE + panelOneThirdWidth), 0,
						 			 2 * GUTTER_SIZE + panelOneThirdWidth,
						 			 2 * GUTTER_SIZE + panelHalfHeight));
	    	panels.add(new Rectangle(0, GUTTER_SIZE + panelHalfHeight,
	    							 2 * GUTTER_SIZE + panelQuorterWidth,
	    							 2 * GUTTER_SIZE + panelHalfHeight));
	    	panels.add(new Rectangle(GUTTER_SIZE + panelQuorterWidth,
                    				 GUTTER_SIZE + panelHalfHeight,
                    				 2 * GUTTER_SIZE + panelQuorterWidth,
                    				 2 * GUTTER_SIZE + panelHalfHeight));
	    	panels.add(new Rectangle(2 * (GUTTER_SIZE + panelQuorterWidth),
   				 					 GUTTER_SIZE + panelHalfHeight,
   				 					 2 * GUTTER_SIZE + panelQuorterWidth,
   				 					 2 * GUTTER_SIZE + panelHalfHeight));
	    	panels.add(new Rectangle(3 * (GUTTER_SIZE + panelQuorterWidth),
	 					 			 GUTTER_SIZE + panelHalfHeight,
	 					 			 2 * GUTTER_SIZE + panelQuorterWidth,
	 					 			 2 * GUTTER_SIZE + panelHalfHeight));
	    	break;
	      default:
	        break;
		}
		*/
	}
	
	void leftTopEdge(float x, float y) {
		p.pushMatrix();
		p.translate(x, y);
		p.beginShape();
	    p.vertex(0, 2*GUTTER_SIZE);
	    p.bezierVertex(0, 0, 2*GUTTER_SIZE, 0, 2*GUTTER_SIZE, 0);
	    p.vertex(0, 0);
	    p.endShape();
	    p.popMatrix();
	}
	
	void rightTopEdge(float x, float y) {
		p.pushMatrix();
		p.translate(x, y);
		p.beginShape();
		p.vertex(0, 2*GUTTER_SIZE);
	    p.bezierVertex(0, 0, -2*GUTTER_SIZE, 0, -2*GUTTER_SIZE, 0);
	    p.vertex(0, 0);
	    p.endShape();
	    p.popMatrix();
	}
	
	void rightBottomEdge(float x, float y) {
		p.pushMatrix();
		p.translate(x, y);
		p.beginShape();
		p.vertex(0, -2*GUTTER_SIZE);
	    p.bezierVertex(0, 0, -2*GUTTER_SIZE, 0, -2*GUTTER_SIZE, 0);
	    p.vertex(0, 0);
	    p.endShape();
	    p.popMatrix();
	}
	
	void leftBottomEdge(float x, float y) {
		p.pushMatrix();
		p.translate(x, y);
		p.beginShape();
		p.vertex(0, -2*GUTTER_SIZE);
	    p.bezierVertex(0, 0, 2*GUTTER_SIZE, 0, 2*GUTTER_SIZE, 0);
	    p.vertex(0, 0);
	    p.endShape();
	    p.popMatrix();
	}
	
	void toggle() {
		isInvert = !isInvert;
	}
}
