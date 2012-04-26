package ac;

import processing.core.PApplet;

public class Typeface {

	public PApplet p;
	private FutureFont font;
	private TypeWriter typewriter;
	int counter;
	boolean isRotate;
	
	public Typeface(PApplet p) {
		this.p = p;
		font = new FutureFont();
		typewriter = new TypeWriter();
		isRotate = false;
	}
	
	public void display() {
		p.pushMatrix();
		p.scale(10);
		if(isRotate) {
			p.rotateX(p.sin(counter * 0.01f) * 0.1f);
			p.rotateY(p.sin(counter * 0.01f) * 0.1f);
			counter++;
		}
		font.drawText(typewriter.buffer, 0, 0, -20);
		p.popMatrix();
		
	}
	
	public void keyPressed() {
		typewriter.type((char) p.key);
	}
	
	class FutureFont {
		float pCharX_au[] = {0,0,5,5,4,4,1,1,0};
		float pCharY_au[] = {-5.5f,-10,-10,-5.5f,-5.5f,-9,-9,-5.5f,-5.5f};
		float pCharX_al[] = {0,0,5,5,4,4,1,1,0};
		float pCharY_al[] = {0,-5.5f,-5.5f,0,0,-4.5f,-4.5f,0,0};
		
		float pCharX_ub[] = {0,0,3,3,2,2,1,1,0};
		float pCharY_ub[] = {-5.5f,-10,-10,-5.5f,-5.5f,-9,-9,-5.5f,-5.5f};
		float pCharX_mb[] = {0,0,5,5,0};
		float pCharY_mb[] = {-4.5f,-5.5f,-5.5f,-4.5f,-4.5f};
		float pCharX_lb[] = {0,0,1,1,4,4,5,5,0};
		float pCharY_lb[] = {0,-4.5f,-4.5f,-1,-1,-4.5f,-4.5f,0,0};
		
		float pCharX_c[] = {0,0,5,5,1,1,5,5,0};
		float pCharY_c[] = {0,-10,-10,-9,-9,-1,-1,0,0};
		
		float pCharX_ud[] = {2,2,0,0,5,5,4,4,3,3,2};
		float pCharY_ud[] = {-5.5f,-9,-9,-10,-10,-5.5f,-5.5f,-9,-9,-5.5f,-5.5f};
		float pCharX_ld[] = {2,2,0,0,5,5,4,4,3,3,2};
		float pCharY_ld[] = {-5.5f,-1,-1,0,0,-5.5f,-5.5f,-1,-1,-5.5f,-5.5f};
		
		float pCharX_e[] = {0,0,5,5,1,1,2.5f,2.5f,1,1,5,5,0};
		float pCharY_e[] = {0,-10,-10,-9,-9,-5.5f,-5.5f,-4.5f,-4.5f,-1,-1,0,0};
		
		float pCharX_f[] = {0,0,5,5,1,1,2.5f,2.5f,1,1,0};
		float pCharY_f[] = {0,-10,-10,-9,-9,-5.5f,-5.5f,-4.5f,-4.5f,0,0};
		
		float pCharX_g[] = {0,0,5,5,1,1,4,4,2.5f,2.5f,5,5,0};
		float pCharY_g[] = {0,-10,-10,-9,-9,-1,-1,-4.5f,-4.5f,-5.5f,-5.5f,0,0};
		
		float pCharX_h[] = {0,0,1,1,4,4,5,5,4,4,1,1,0};
		float pCharY_h[] = {0,-10,-10,-5.5f,-5.5f,-10,-10,0,0,-4.5f,-4.5f,0,0};
		
		float pCharX_i[] = {0,0,1,1,0};
		float pCharY_i[] = {0,-10,-10,0,0};
		
		float pCharX_j[] = {0,0,5,5,2.5f,2.5f,3.5f,3.5f,4,4,1,1,0};
		float pCharY_j[] = {-3,0,0,-10,-10,-7,-7,-9,-9,-1,-1,-3,-3};
		
		float pCharX_k[] = {0,0,1,1,2f,2f,3.5f,3.5f,3,3,5,5,4,4,1,1,0};
		float pCharY_k[] = {0,-10,-10,-5.5f,-5.5f,-10,-10,-9,-9,-5.5f,-5.5f,0,0,-4.5f,-4.5f,0,0};
		
		float pCharX_l[] = {0,0,1,1,5,5,0};
		float pCharY_l[] = {0,-10,-10,-1,-1,0,0};
		
		float pCharX_m[] = {0,0,5,5,4,4,3,3,2,2,1,1,0};
		float pCharY_m[] = {0,-10,-10,0,0,-9,-9,-3,-3,-9,-9,0,0};
		
		float pCharX_n[] = {0,0,3,3,4,4,5,5,4,4,2,2,1,1,0};
		float pCharY_n[] = {0,-10,-10,-5.5f,-5.5f,-10,-10,0,0,-4.5f,-4.5f,-9,-9,0,0};
		
		float pCharX_uo[] = {0,0,5,5,4,4,1,1,0};
		float pCharY_uo[] = {-5.5f,-10,-10,-5.5f,-5.5f,-9,-9,-5.5f,-5.5f};
		float pCharX_lo[] = {0,0,5,5,4,4,1,1,0};
		float pCharY_lo[] = {-5.5f,0,0,-5.5f,-5.5f,-1,-1,-5.5f,-5.5f};
		
		float pCharX_up[] = {0,0,5,5,4,4,1,1,0};
		float pCharY_up[] = {-5.5f,-10,-10,-5.5f,-5.5f,-9,-9,-5.5f,-5.5f};
		float pCharX_lp[] = {0,0,5,5,1,1,0};
		float pCharY_lp[] = {0,-5.5f,-5.5f,-4.5f,-4.5f,0,0};
		
		float pCharX_uq[] = {0,0,5,5,4,4,1,1,0};
		float pCharY_uq[] = {-5.5f,-10,-10,-5.5f,-5.5f,-9,-9,-5.5f,-5.5f};
		float pCharX_lq[] = {0,0,1,1,2,2,1.5f,1.5f,3,3,4,4,5,5,0};
		float pCharY_lq[] = {0,-5.5f,-5.5f,-1,-1,-4.5f,-4.5f,-5.5f,-5.5f,-1,-1,-5.5f,-5.5f,0,0};
		
		float pCharX_r[] = {0,0,5,5,3,3,5,5,2,2,1,1,0};
		float pCharY_r[] = {0,-10,-10,-4.5f,-4.5f,-1,-1,0,0,-4.5f,-4.5f,0,0};
		
		float pCharX_ur[] = {0,0,5,5,4,4,1,1,0};
		float pCharY_ur[] = {-5.5f,-10,-10,-5.5f,-5.5f,-9,-9,-5.5f,-5.5f};
		float pCharX_lr[] = {0,0,5,5,3,3,5,5,2,2,1,1,0};
		float pCharY_lr[] = {0,-5.5f,-5.5f,-4.5f,-4.5f,-1,-1,0,0,-4.5f,-4.5f,0,0};
		
		float pCharX_s[] = {0,0,1,1,4,4,0,0,5,5,4,4,1,1,5,5,0};
		float pCharY_s[] = {0,-2,-2,-1,-1,-4.5f,-4.5f,-10,-10,-8,-8,-9,-9,-5.5f,-5.5f,0,0};
		
		float pCharX_t[] = {2,2,0,0,5,5,3,3,2};
		float pCharY_t[] = {0,-9,-9,-10,-10,-9,-9,0,0};
		
		float pCharX_u[] = {0,0,1,1,4,4,5,5,0};
		float pCharY_u[] = {0,-10,-10,-1,-1,-10,-10,0,0};
		
		float pCharX_v[] = {0,0,1,1,2,2,4,4,5,5,3,3,0};
		float pCharY_v[] = {0,-10,-10,-1,-1,-5.5f,-5.5f,-10,-10,-4.5f,-4.5f,0,0};
		
		float pCharX_w[] = {0,0,1,1,2,2,3,3,4,4,5,5,0};
		float pCharY_w[] = {0,-10,-10,-1,-1,-5.5f,-5.5f,-1,-1,-10,-10,0,0};
		
		float pCharX_x[] = {0,0,2,2,3,3,4,4,5,5,3,3,2,2,1,1,0};
		float pCharY_x[] = {0,-5.5f,-5.5f,-7,-7,-5.5f,-5.5f,-10,-10,-4.5f,-4.5f,-3,-3,-4.5f,-4.5f,0,0};
		
		float pCharX_xu[] = {0,0,1,1,0};
		float pCharY_xu[] = {-7,-10,-10,-7,-7};
		
		float pCharX_xb[] = {4,4,5,5,4};
		float pCharY_xb[] = {0,-3,-3,0,0};
		
		float pCharX_y[] = {0,0,1,1,4,4,5,5,3,3,2,2,0};
		float pCharY_y[] = {-4.5f,-10,-10,-5.5f,-5.5f,-10,-10,-4.5f,-4.5f,0,0,-4.5f,-4.5f};
		
		float pCharX_z[] = {0,0,4,4,0,0,5,5,1,1,5,5,0};
		float pCharY_z[] = {0,-5.5f,-5.5f,-9,-9,-10,-10,-4.5f,-4.5f,-1,-1,0,0};
		
		FutureFont() {}
		
		void drawText(String str, float x, float y, float depth) {
			char[] chars = str.toLowerCase().toCharArray();
			// System.out.println(chars.length);
			
			p.pushMatrix();
			p.translate(x, y);
			for(int i = 0; i < chars.length; i++) {
				switch(chars[i]) {
					case '\n':
						p.popMatrix();
						p.pushMatrix();
						y += 11;
						p.translate(x, y);
						break;
					case 'i':
						drawChar(chars[i], 0, 20, depth);
						p.translate(2, 0);
						break;
					default:
						drawChar(chars[i], 0, 20, depth);
						p.translate(6, 0);
						break;
				}
				
				/*
				drawChar(chars[i], 0, 20, depth);
				if(chars[i] == 'i') {
					p.translate(2, 0);
				} else {
					p.translate(6, 0);
				}
				*/
			}
			p.popMatrix();
		}
		
		void drawChar(char c, float x, float y, float depth) {
			p.pushMatrix();
			p.translate(x, y);
			switch(c) {
				case 'a':
					drawDeep(pCharX_au, pCharY_au, depth);
					drawDeep(pCharX_al, pCharY_al, depth);
					break;
				case 'b':
					drawDeep(pCharX_ub, pCharY_ub, depth);
					drawDeep(pCharX_mb, pCharY_mb, depth);
					drawDeep(pCharX_lb, pCharY_lb, depth);
					break;
				case 'c':
					drawDeep(pCharX_c, pCharY_c, depth);
					break;
				case 'd':
					drawDeep(pCharX_ud, pCharY_ud, depth);
					drawDeep(pCharX_ld, pCharY_ld, depth);
					break;
				case 'e':
					drawDeep(pCharX_e, pCharY_e, depth);
					break;
				case 'f':
					drawDeep(pCharX_f, pCharY_f, depth);
					break;
				case 'g':
					drawDeep(pCharX_g, pCharY_g, depth);
					break;
				case 'h':
					drawDeep(pCharX_h, pCharY_h, depth);
					break;
				case 'i':
					drawDeep(pCharX_i, pCharY_i, depth);
					break;
				case 'j':
					drawDeep(pCharX_j, pCharY_j, depth);
					break;
				case 'k':
					drawDeep(pCharX_k, pCharY_k, depth);
					break;
				case 'l':
					drawDeep(pCharX_l, pCharY_l, depth);
					break;
				case 'm':
					drawDeep(pCharX_m, pCharY_m, depth);
					break;
				case 'n':
					drawDeep(pCharX_n, pCharY_n, depth);
					break;
				case 'o':
					drawDeep(pCharX_uo, pCharY_uo, depth);
					drawDeep(pCharX_lo, pCharY_lo, depth);
					break;
				case 'p':
					drawDeep(pCharX_up, pCharY_up, depth);
					drawDeep(pCharX_lp, pCharY_lp, depth);
					break;
				case 'q':
					drawDeep(pCharX_uq, pCharY_uq, depth);
					drawDeep(pCharX_lq, pCharY_lq, depth);
					break;
				case 'r':
					drawDeep(pCharX_ur, pCharY_ur, depth);
					drawDeep(pCharX_lr, pCharY_lr, depth);
					break;
				case 's':
					drawDeep(pCharX_s, pCharY_s, depth);
					break;
				case 't':
					drawDeep(pCharX_t, pCharY_t, depth);
					break;
				case 'u':
					drawDeep(pCharX_u, pCharY_u, depth);
					break;
				case 'v':
					drawDeep(pCharX_v, pCharY_v, depth);
					break;
				case 'w':
					drawDeep(pCharX_w, pCharY_w, depth);
					break;
				case 'x':
					drawDeep(pCharX_x, pCharY_x, depth);
					drawDeep(pCharX_xu, pCharY_xu, depth);
					drawDeep(pCharX_xb, pCharY_xb, depth);
					break;
				case 'y':
					drawDeep(pCharX_y, pCharY_y, depth);
					break;
				case 'z':
					drawDeep(pCharX_z, pCharY_z, depth);
					break;
				default:
					// drawDeep(pCharX_a, pCharY_a, depth);
					break;
			}
			p.popMatrix();
		}
		
		void drawDeep(float vtX[], float vtY[], float depth) {
			p.pushMatrix();
			// p.scale(10);
			p.stroke(174, 85, 55);
			p.fill(174, 85, 55);
			p.beginShape(p.QUAD_STRIP);
			for(int i = 0; i < vtX.length; i++) {
				p.vertex(vtX[i], vtY[i], 0);
				p.vertex(vtX[i], vtY[i], depth);
			}
			p.endShape();
			p.popMatrix();
			
			p.pushMatrix();
			// p.scale(10);
			p.stroke(210, 96, 60);
			p.fill(210, 96, 60);
			p.beginShape();
			for(int i = 1; i < vtX.length; i++) {
				p.vertex(vtX[i], vtY[i], 0);
			}
			p.endShape();
			p.popMatrix();
		}
	}
	
	class TypeWriter {
		String buffer = " abstract\n comic";
		
		TypeWriter() {}
		
		void type(int c) {
			switch(c) {
				case 8:
					delete();
					break;
				case 13:
				case 65535:
				case 127:
				case 27:
					break;
				default:
					buffer += (char)c;
					break;
			}
		}
		
		void delete() {
			if(buffer.length() > 0) {
				buffer = buffer.substring(0, buffer.length()-1);
			}
		}
	}
}
