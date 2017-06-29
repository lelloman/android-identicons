package com.lelloman.identicon;

import android.content.Context;
import android.util.AttributeSet;

import com.lelloman.identicon.classic.ClassicIdenticonDrawable;

public class ClassicIdenticonView extends IdenticonView {

	public ClassicIdenticonView(Context context) {
		super(context);
	}

	public ClassicIdenticonView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected IdenticonDrawable makeIdenticonDrawable(int width, int height, int hash) {
		return new ClassicIdenticonDrawable(width, height, hash);
	}
}
