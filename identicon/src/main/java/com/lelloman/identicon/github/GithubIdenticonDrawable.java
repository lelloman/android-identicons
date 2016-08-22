package com.lelloman.identicon.github;


import android.graphics.Canvas;
import android.graphics.Paint;

import com.lelloman.identicon.IdenticonDrawable;


/**
 * 	Draws an identicon as the ones from github, it just uses 15bits
 * 	for the tiles and 6bits - 4 values for the colors
 */
public class GithubIdenticonDrawable extends IdenticonDrawable {

	// create 4*4*4 colors but remove the grays
	public static int[] COLORS;
	static {
		int n = 4;
		int size = n*n*n - n;
		COLORS = new int[size];
		int index = 0;
		int colorBase = 150;
		int colorTot = 100 / n;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				for(int k=0;k<n;k++){
					if(i == j && j == k) continue;
					int r = colorBase + i * colorTot;
					int g = colorBase + j * colorTot;
					int b = colorBase + k * colorTot;

					int color = 0xff000000 + r *0x10000 + g *0x100 + b;
					COLORS[index++] = color;
				}
			}
		}
	}

	private Paint mFgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	public GithubIdenticonDrawable(int width, int height, int hash){
		super(width,height,hash,5);

		mFgPaint.setStyle(Paint.Style.FILL);
		invalidateBitmap();
	}

	public int getColor(){
		int i = Math.abs((getHash() >> 15) % COLORS.length);

		return COLORS[i];
	}

	@Override
	protected void drawBitmap(Canvas canvas) {

		mFgPaint.setColor(getColor());

		int w = (int) (canvas.getWidth() / 5f);
		int h = (int) (canvas.getHeight() / 5f);

		canvas.drawColor(0xffffffff);

		int i = 0;
		for(int x=0;x<3;x++){
			int startX = x*w;
			int endX = startX +w;

			int startXMirror = (4-x) * w;
			int endXMirror = startXMirror + w;

			for(int y=0;y<5;y++){
				int value = getHash() >> i++;
				boolean draw = value % 2 == 0;
				if(!draw) continue;

				float startY = y*h;
				float endY = startY+h;

				canvas.drawRect(startX,startY,endX,endY, mFgPaint);
				if(x != 2)
					canvas.drawRect(startXMirror,startY,endXMirror, endY, mFgPaint);
			}
		}
	}

}
