package com.lelloman.identicon;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * an ImageView that displays an IdenticonDrawable, the type of
 * the IdenticonDrawable is unknown and the method to create it
 * is abstract, the alternative would be to pass the drawable directly
 * to the view but setting just the hash looks nicer
 */
public abstract class IdenticonView extends ImageView {


	private int mHash;
	private IdenticonDrawable mIdenticonDrawable;

	public IdenticonView(Context context) {
		super(context);
	}

	public IdenticonView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		if (w > 0 && h > 0) {
			mIdenticonDrawable = makeIdenticonDrawable(getWidth(), getHeight(), mHash);
			// TODO on Oreo the drawable are very stretched, for some reason ?_?
			if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
				mIdenticonDrawable.invalidateBitmap();
				setImageBitmap(mIdenticonDrawable.getBitmap());
			}else {
				setImageDrawable(mIdenticonDrawable);
			}
		}
	}

	public void setHash(int hash) {
		mHash = hash;
		if(mIdenticonDrawable != null) {
			mIdenticonDrawable.setHash(mHash);
			invalidate();
		}
	}

	protected abstract IdenticonDrawable makeIdenticonDrawable(int width, int height, int hash);
}
