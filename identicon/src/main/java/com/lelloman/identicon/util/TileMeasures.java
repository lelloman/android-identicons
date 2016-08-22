package com.lelloman.identicon.util;

/**
 * 	Utility class that holds some useful points for drawing all the shapes
 */
public class TileMeasures {

	public final int width,height,wMid,hMid,w3,h3,w4,h4,w32,h32,w43,h43,width2,height2, width3, height3;

	public TileMeasures(int width,int height){
		this.width = width;
		this.height = height;

		wMid = width / 2;
		hMid = height / 2;
		w3 = width / 3;
		h3 = height / 3;
		w4 = width / 4;
		h4 = height / 4;
		w32 = w3 * 2;
		h32 = h3 * 2;
		w43 = w4 * 3;
		h43 = h4 * 3;

		width2 = width * 2;
		height2 = height * 2;

		width3 = width * 3;
		height3 = height * 3;
	}
}
