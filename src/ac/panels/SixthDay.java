package ac.panels;

import java.awt.Rectangle;
import java.util.Arrays;

import processing.core.PApplet;
import toxi.math.*;

public class SixthDay extends Panel {

	float lensSmooth;
	ZoomLensInterpolation zoomLens = new ZoomLensInterpolation();
	float y1, y2, y3;
	int counter;
	int pseudoCounter;
	float[] groundLines;
	int[] colors = {p.color(196, 190, 131),
					p.color(196, 180, 124),
					p.color(199, 166, 104)};
	int[] invertedColors = {p.color(59, 65, 124),
							p.color(59, 75, 131),
							p.color(56, 89, 151)};
	float[] cowShadowPosX = { 16.251f,  15.862f,  15.818f,  16.395f,   9.208f,
							   4.124f,   2.998f,   4.508f,   1.979f,   0.544f,
							   1.957f,   8.311f,  16.833f,  26.358f,  67.508f,
							  78.380f,  87.308f,  93.040f,  94.666f,  93.742f,
							  96.782f,  98.321f, 100.049f,  98.666f, 111.981f,
							 107.895f,  98.487f,  93.079f,  89.726f,  84.277f,
							  79.387f,  79.326f,  76.507f,  75.329f,  67.410f,
							  66.554f,  64.388f,  63.505f,  47.073f,  44.501f,
							  40.665f,  36.475f,  34.176f,  31.668f,  32.831f,
							  28.376f,  27.047f,  20.980f,  18.493f };
	float[] cowShadowPosY = { 90.658f,  47.697f,  38.007f,  31.925f,  33.485f,
			  				  39.632f,  50.896f,  59.584f,  59.640f,  50.421f,
			  				  37.660f,  30.872f,  28.844f,  21.225f,  21.971f,
			  				  16.271f,   9.269f,   0.544f,   0.934f,   3.981f,
			  				   5.043f,   2.362f,   3.020f,   6.493f,  18.932f,
			  				   24.961f, 21.297f,  23.095f,  29.551f,  39.344f,
			  				   59.724f, 90.658f,  90.658f,  61.463f,  61.398f,
			  				   90.658f, 90.658f,  60.625f,  60.321f,  64.753f,
			  				   67.106f, 68.135f,  90.658f,  90.658f,  67.829f,
			  				   63.506f, 58.389f,  57.884f,  90.658f };
	float[] humanShadowPosX = {4.380f, 13.490f, 2.911f, 0.000f, 11.693f,
							   18.117f, 18.194f, 15.792f, 17.741f, 19.307f,
							   23.107f, 26.057f, 28.444f, 27.894f, 24.414f,
							   24.377f, 29.847f, 36.487f, 34.033f, 27.466f,
							   25.291f, 22.041f, 21.029f, 14.041f, 8.374f};
	float[] humanShadowPosY = {80.879f, 22.056f, 48.286f, 47.183f, 17.796f,
							   15.927f, 13.006f, 9.671f, 4.733f, 1.215f,
							   0.500f, 2.232f, 5.720f, 9.975f, 13.201f,
							   16.073f, 17.895f, 48.371f, 49.498f, 23.469f,
							   80.879f, 80.879f, 52.467f, 52.379f, 80.879f};
	float[] dogShadowPosX = {13.431f, 11.276f,  8.564f,  1.610f,  0.500f,
							 10.056f, 10.078f, 11.528f, 11.665f, 14.740f,
							 44.631f, 48.855f, 52.080f, 53.305f, 49.681f,
							 44.654f, 43.972f, 45.493f, 44.399f, 42.004f,
							 37.434f, 39.035f, 37.811f, 35.357f, 20.086f,
							 20.717f, 19.661f, 18.181f, 13.256f, 14.806f };
	float[] dogShadowPosY = {38.593f, 21.045f,   8.075f,  9.947f,  7.471f,
							  2.956f,  0.500f,   0.672f,  3.059f, 13.014f,
							 14.789f, 12.168f,  7.497f,   8.093f, 13.206f,
							 16.248f, 21.139f, 38.593f, 38.593f, 21.280f,
							 21.281f, 38.593f, 38.593f, 21.321f, 21.401f,
							 38.593f, 38.593f, 21.706f, 21.731f, 38.593f };
	float cowWidth, humanWidth, dogWidth;
	float[] mountainPosX = {1.000f, 182.500f, 205.844f, 208.500f, 228.500f,
							304.562f, 328.500f, 333.031f, 359.500f, 692.625f,
							765.281f, 778.031f, 781.438f, 794.781f, 845.188f,
							856.312f, 861.688f, 878.688f, 972.000f, 1024.000f,
							1024.000f, 1.000f };
	float[] mountainPosY = {20.625f, 56.500f, 93.469f, 167.500f, 186.500f,
			 				188.031f, 167.500f, 98.562f, 54.500f, 37.625f,
			 				42.250f, 66.812f, 93.875f, 104.219f, 103.375f,
			 				92.781f, 61.500f, 37.906f, 23.438f, 20.625f,
			 				0.000f, 0.000f };
							
	public SixthDay(PApplet p, Rectangle a) {
		super(p, a);
		lensSmooth = 0.05f;
		y1 = size.height / 4;
		y2 = (size.height / 12) + y1;
		y3 = (size.height / 12) + y2;
		
		groundLines = new float[3];
		groundLines[2] = size.height / 4;
		groundLines[1] = (size.height / 12) + groundLines[2];
		groundLines[0] = (size.height / 12) + groundLines[1];
		
		// Adjust cow shadow size
		float maxY = 0;
		for(int i = 0; i < cowShadowPosY.length; i++) {
			cowShadowPosX[i] *= (size.width / 1024.0f);
			cowShadowPosY[i] *= (size.width / 1024.0f);
			if(cowShadowPosX[i] > cowWidth) cowWidth = cowShadowPosX[i];
			if(cowShadowPosY[i] > maxY) maxY = cowShadowPosY[i];
		}
		
		// Adjust cow shadow position
		for(int i = 0; i < cowShadowPosY.length; i++) {
			cowShadowPosY[i] += (size.height / 2) - maxY;
		}
		
		// Adjust human shadow size
		maxY = 0;
		for(int i = 0; i < humanShadowPosY.length; i++) {
			humanShadowPosX[i] *= (size.width / 1024.0f);
			humanShadowPosY[i] *= (size.width / 1024.0f);
			if(humanShadowPosX[i] > humanWidth) humanWidth = humanShadowPosX[i];
			if(humanShadowPosY[i] > maxY) maxY = humanShadowPosY[i];
		}
		
		// Adjust human shadow position
		for(int i = 0; i < humanShadowPosY.length; i++) {
			// humanShadowPosY[i] = p.map(humanShadowPosY[i], 0.500f, 80.879f, 0, (size.height/2));
			// humanShadowPosY[i] += (size.height/2) - 80.879f;
			humanShadowPosY[i] += (size.height/2) - maxY;
		}
		
		// Adjust dog shadow size
		maxY = 0;
		for(int i = 0; i < dogShadowPosY.length; i++) {
			dogShadowPosX[i] *= (size.width / 1024.0f);
			dogShadowPosY[i] *= (size.width / 1024.0f);
			if(dogShadowPosX[i] > dogWidth) dogWidth = dogShadowPosX[i];
			if(dogShadowPosY[i] > maxY) maxY = dogShadowPosY[i];
		}
		
		// Adjust dog shadow position
		for(int i = 0; i < dogShadowPosY.length; i++) {
			dogShadowPosY[i] += (size.height/2) - maxY;
		}
		
		// Adjust mountain position
		for(int i = 0; i < mountainPosX.length; i++) {
			mountainPosX[i] *= (size.width / 1024.0f);
			mountainPosY[i] *= (size.width / 1024.0f);
		}
	}

	@Override
	public void display() {
		// Draw background
		if(isInvert) {
			p.fill(98, 31, 33);
		} else {
			p.fill(157, 224, 222);
		}
		p.noStroke();
		p.rect(size.x, size.y, size.width, size.height);
		
		// Draw mountain
		if(isInvert) {
			p.fill(90, 53, 70);
		} else {
			p.fill(165, 202, 185);
		}
		p.noStroke();
		p.pushMatrix();
		p.translate(size.x, size.y + (size.height/2));
		p.beginShape();
		for(int i = 0; i < mountainPosX.length; i++) {
			p.vertex(mountainPosX[i], -mountainPosY[i]);
		}
		p.endShape();
		p.popMatrix();
		
		// Draw 1st ground
		if(isInvert) {
			p.fill(64, 55, 120);
		} else {
			p.fill(191, 199, 137);
		}
		p.noStroke();
		p.rect(size.x, size.y + size.height / 2, size.width, size.height / 2);
		
		// counter++;
		// pseudoCounter++;
		float temp = (size.height / 2) * p.sin(counter * 0.01f) + (size.height / 2);
		zoomLens.setLensPos(temp / size.height, lensSmooth);
		
		p.pushMatrix();
		p.translate(size.x, size.y + size.height);
		for(int i = 0; i < groundLines.length; i++) {
			float t = groundLines[i]  / (size.height / 2);
			float y = zoomLens.interpolate(0, (size.height / 2), t);
			if(isInvert) {
				p.fill(invertedColors[i]);
			} else {
				p.fill(colors[i]);
			}
			p.noStroke();
			p.rect(0, -y, size.width, y);
			// p.rect(0, -groundLines[i], size.width, groundLines[i]);
		}
		p.popMatrix();
		
		// Draw human shadow
		if(isInvert) {
			p.fill(92, 115, 157);
		} else {
			p.fill(163, 140, 98);
		}
		p.noStroke();
		p.pushMatrix();
		// p.translate(size.x + (size.width / 2), size.y + (size.height/2) + 80.879f);
		p.translate(size.x + (size.width/2) - (humanWidth/2), size.y + size.height);
		p.beginShape();
		for(int i = 0; i < humanShadowPosX.length; i++) {
			float t = humanShadowPosY[i] / (size.height / 2);
			float y = zoomLens.interpolate(0, (size.height / 2), t);
			// p.vertex(cowPosX[i], -cowPosY[i]);
			p.vertex(humanShadowPosX[i], -y);
			// p.vertex(humanShadowPosX[i], -humanShadowPosY[i]);
		}
		p.endShape();
		p.popMatrix();
		
		// Draw human
		if(isInvert) {
			p.fill(255);
		} else {
			p.fill(0);
		}
		p.noStroke();
		p.pushMatrix();
		// p.translate(size.x + (size.width / 2), size.y + (size.height/2) + 80.879f);
		p.translate(size.x + (size.width/2) - (humanWidth/2), size.y);
		p.beginShape();
		for(int i = 0; i < humanShadowPosX.length; i++) {
			p.vertex(humanShadowPosX[i], humanShadowPosY[i]);
		}
		p.endShape();
		p.popMatrix();
		
		// Draw cow shadow
		if(isInvert) {
			p.fill(92, 115, 157);
			p.stroke(92, 115, 157);
		} else {
			p.fill(163, 140, 98);
			p.stroke(163, 140, 98);
		}
		p.pushMatrix();
		p.translate(size.x + (size.width/3) - (cowWidth/2), size.y + size.height);
		p.beginShape();
		for(int i = 0; i < cowShadowPosX.length; i++) {
			float t = cowShadowPosY[i] / (size.height / 2);
			float y = zoomLens.interpolate(0, (size.height / 2), t);
			p.vertex(cowShadowPosX[i], -y);
		}
		p.endShape();
		p.popMatrix();
		
		// Draw cow
		if(isInvert) {
			p.fill(255);
			p.stroke(255);
		} else {
			p.fill(0);
			p.stroke(0);
		}
		p.pushMatrix();
		// p.translate(size.x + (size.width / 2), size.y + (size.height/2) + 80.879f);
		p.translate(size.x + (size.width/3) - (cowWidth/2), size.y);
		p.beginShape();
		for(int i = 0; i < cowShadowPosX.length; i++) {
			p.vertex(cowShadowPosX[i], cowShadowPosY[i]);
		}
		p.endShape();
		p.popMatrix();

		// Draw dog shadow
		if(isInvert) {
			p.fill(92, 115, 157);
			p.stroke(92, 115, 157);
		} else {
			p.fill(163, 140, 98);
			p.stroke(163, 140, 98);
		}
		p.pushMatrix();
		p.translate(size.x + (2*size.width/3) - (dogWidth/2), size.y + size.height);
		p.beginShape();
		for(int i = 0; i < dogShadowPosX.length; i++) {
			float t = dogShadowPosY[i] / (size.height / 2);
			float y = zoomLens.interpolate(0, (size.height / 2), t);
			p.vertex(dogShadowPosX[i], -y);
		}
		p.endShape();
		p.popMatrix();
		
		// Draw dog
		if(isInvert) {
			p.fill(255);
			p.stroke(255);
		} else {
			p.fill(0);
			p.stroke(0);
		}
		p.pushMatrix();
		p.translate(size.x + (2*size.width/3) - (dogWidth/2), size.y);
		p.beginShape();
		for(int i = 0; i < dogShadowPosX.length; i++) {
			p.vertex(dogShadowPosX[i], dogShadowPosY[i]);
		}
		p.endShape();
		p.popMatrix();
	}

	@Override
	public void update() {
		pseudoCounter++;
	}
	
	public void bang() {
		counter = pseudoCounter;
	}

}
