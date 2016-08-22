package com.lelloman.identicon.util;

import android.graphics.Path;

/**
 * 	a Path with a few utility methods
 */
public class Path2 extends Path {

	public void addTriangle(int x0,int y0, int x1, int y1, int x2, int y2){
		moveTo(x0,y0);
		lineTo(x1,y1);
		lineTo(x2,y2);
		lineTo(x0,y0);
	}

	public void addRectangle(int left,int top,int right,int bottom){
		moveTo(left,top);
		lineTo(right,top);
		lineTo(right,bottom);
		lineTo(left,bottom);
		lineTo(left,top);
	}

	public void addPolygon(int x0, int y0, int x1, int y1,int x2,int y2,int x3, int y3){
		moveTo(x0,y0);
		lineTo(x1,y1);
		lineTo(x2,y2);
		lineTo(x3,y3);
		lineTo(x0,y0);
	}
}
