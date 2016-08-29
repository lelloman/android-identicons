package com.lelloman.identicon.util;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * 	the base implementation of a tile, the actual drawing of the shape
 * 	is performed in {@link TileDrawer#onDraw(Path2, TileMeasures)},
 * 	this one just sets up the rotation and clear the background
 */
public abstract class TileDrawer {

	public void draw(Canvas canvas, TileMeasures measures, int rotation, Paint bgPaint, Paint fgPaint) {

		canvas.drawRect(0, 0, measures.width, measures.height, bgPaint);

		Path2 path = new Path2();

		while(rotation > 360){
			rotation -= 360;
		}

		if (rotation != 0) {
			canvas.save();
			canvas.rotate(rotation, measures.wMid, measures.hMid);
			onDraw(path, measures);
			canvas.drawPath(path,fgPaint);
			canvas.restore();
		} else {
			onDraw(path, measures);
			canvas.drawPath(path,fgPaint);
		}
	}

	public abstract void onDraw(Path2 path, TileMeasures measures);

}
