package com.lelloman.identicon;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * an ImageView that displays an IdenticonDrawable, the type of
 * the IdenticonDrawable is unknown and the method to create it
 * is abstract, the alternative would be to pass the drawable directly
 * to the view but setting just the hash looks nicer
 */
public abstract class IdenticonView extends android.support.v7.widget.AppCompatImageView {


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
			setImageDrawable(mIdenticonDrawable);
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
