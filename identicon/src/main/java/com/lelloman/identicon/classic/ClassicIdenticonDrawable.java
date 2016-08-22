package com.lelloman.identicon.classic;

import android.graphics.Canvas;
import android.graphics.Paint;

import com.lelloman.identicon.IdenticonDrawable;
import com.lelloman.identicon.util.TileMeasures;

/**
 * 	draws the identicon given an {@link ClassicIdenticonHash}
 */
public class ClassicIdenticonDrawable extends IdenticonDrawable {

	protected ClassicIdenticonHash mIdenticonHash;

	private Paint mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private Paint mFgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	private TileMeasures mTileMeasure;

	public ClassicIdenticonDrawable(int width, int height, int hash) {
		super(width,height, hash,3);
		onSetHash(hash);

		mBgPaint.setStyle(Paint.Style.FILL);
		mFgPaint.setStyle(Paint.Style.FILL);

		mBgPaint.setColor(0xffffffff);
		mFgPaint.setColor(0xff000000);

		mTileMeasure = new TileMeasures(getWidth() / 3, getHeight() / 3);

		invalidateBitmap();
	}

	@Override
	protected void onSetHash(int hash) {
		mIdenticonHash = new ClassicIdenticonHash(hash);
	}

	@Override
	protected void drawBitmap(Canvas canvas) {

		if(mIdenticonHash == null)
			return;

		mFgPaint.setColor(getColor());

		canvas.drawColor(0xffffffff);

		drawMiddle(canvas);
		drawCorners(canvas);
		drawSides(canvas);
	}

	protected int getColor(){
		int r = 100 + mIdenticonHash.getR() * 8;
		int g = 100 + mIdenticonHash.getG() * 8;
		int b = 100 + mIdenticonHash.getB() * 8;

		return 0xff000000 + r * 0x10000 + g * 0x100 + b;
	}

	protected ClassicIdenticonTile.Tiles getTile(int position){
		return ClassicIdenticonTile.all[position];
	}

	private void drawMiddle(Canvas canvas) {

		boolean invert = mIdenticonHash.getInvertMiddle() == 0;

		Paint fg = invert ? mFgPaint : mBgPaint;
		Paint bg = invert ? mBgPaint : mFgPaint;

		canvas.save();
		canvas.translate(mTileMeasure.width, mTileMeasure.height);
		getTile(mIdenticonHash.getMiddle()).draw(canvas, mTileMeasure, 0, bg, fg);
		canvas.restore();

	}

	private void drawCorners(Canvas canvas) {

		ClassicIdenticonTile.Tiles drawer = getTile(mIdenticonHash.getCorners());

		boolean invert = mIdenticonHash.getInvertCorners() == 0;
		int rotation = mIdenticonHash.getCornersRotation() * 90;

		Paint fg = invert ? mFgPaint : mBgPaint;
		Paint bg = invert ? mBgPaint : mFgPaint;

		drawer.draw(canvas, mTileMeasure, 0 + rotation, bg, fg);

		canvas.save();
		canvas.translate(mTileMeasure.width2, 0);
		drawer.draw(canvas, mTileMeasure, 90 + rotation, bg, fg);
		canvas.restore();

		canvas.save();
		canvas.translate(mTileMeasure.width2, mTileMeasure.height2);
		drawer.draw(canvas, mTileMeasure, 180 + rotation, bg, fg);
		canvas.restore();

		canvas.save();
		canvas.translate(0, mTileMeasure.height2);
		drawer.draw(canvas, mTileMeasure, 270 + rotation, bg, fg);
		canvas.restore();
	}

	private void drawSides(Canvas canvas) {

		ClassicIdenticonTile.Tiles drawer = getTile(mIdenticonHash.getSides());

		boolean invert = mIdenticonHash.getInvertSides() == 0;
		int rotation = mIdenticonHash.getSidesRotation() * 90;

		Paint fg = invert ? mFgPaint : mBgPaint;
		Paint bg = invert ? mBgPaint : mFgPaint;

		canvas.save();
		canvas.translate(0, mTileMeasure.height);
		drawer.draw(canvas, mTileMeasure, 0 + rotation, bg, fg);
		canvas.restore();

		canvas.save();
		canvas.translate(mTileMeasure.width, mTileMeasure.height2);
		drawer.draw(canvas, mTileMeasure, 90 + rotation, bg, fg);
		canvas.restore();

		canvas.save();
		canvas.translate(mTileMeasure.width2, mTileMeasure.height);
		drawer.draw(canvas, mTileMeasure, 180 + rotation, bg, fg);
		canvas.restore();

		canvas.save();
		canvas.translate(mTileMeasure.width, 0);
		drawer.draw(canvas, mTileMeasure, 270 + rotation, bg, fg);
		canvas.restore();

	}

}
