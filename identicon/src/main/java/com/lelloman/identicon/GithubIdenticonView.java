package com.lelloman.identicon;

import android.content.Context;
import android.util.AttributeSet;

import com.lelloman.identicon.github.GithubIdenticonDrawable;

public class GithubIdenticonView extends IdenticonView {

	public GithubIdenticonView(Context context) {
		super(context);
	}

	public GithubIdenticonView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected IdenticonDrawable makeIdenticonDrawable(int width, int height, int hash) {
		return new GithubIdenticonDrawable(width, height, hash);
	}
}
