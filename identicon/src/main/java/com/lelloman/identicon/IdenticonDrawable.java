package com.lelloman.identicon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/**
 * 	Basically a Bitmap wrapper, the Bitmap size must be known when instantiating it
 * 	but when drawing it will draw the Bitmap to fit the canvas
 */
public abstract class IdenticonDrawable extends Drawable {

	private Rect mBitmapRect, mDestinationRect;
	private Bitmap mBitmap;
	private Canvas mCanvas;
	private Paint mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	private int mWidth,mHeight,mHash;

	public IdenticonDrawable(int width, int height, int hash,int multipleOf) {

		while(width % multipleOf != 0){
			width++;
		}
		while(height % multipleOf != 0){
			height++;
		}
		mWidth = width;
		mHeight = height;
		mHash = hash;
		mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
		mBitmapRect = new Rect(0, 0, width, height);
		mDestinationRect = new Rect(0, 0, width, height);
		mCanvas = new Canvas(mBitmap);
	}

	public int getWidth(){
		return mWidth;
	}
	public int getHeight(){
		return mHeight;
	}

	protected void invalidateBitmap() {
		drawBitmap(mCanvas);
		invalidateSelf();
	}

	public int getHash(){
		return mHash;
	}

	public void setHash(int hash) {
		mHash = hash;
		onSetHash(hash);
		invalidateBitmap();
	}

	protected abstract void drawBitmap(Canvas canvas);

	protected void onSetHash(int newHash){

	}

	@Override
	public void draw(Canvas canvas) {
		mDestinationRect.set(0, 0, canvas.getWidth(), canvas.getHeight());
		canvas.drawBitmap(mBitmap, mBitmapRect, mDestinationRect, mBitmapPaint);
	}

	@Override
	public void setAlpha(int i) {
		mBitmapPaint.setAlpha(i);
	}

	@Override
	public void setColorFilter(ColorFilter colorFilter) {
		mBitmapPaint.setColorFilter(colorFilter);
	}

	@Override
	public int getOpacity() {
		return mBitmapPaint.getAlpha();
	}

	public Bitmap getBitmap(){
		return mBitmap;
	}
}
