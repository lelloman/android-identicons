package com.lelloman.demo.fragment;

import com.lelloman.identicon.IdenticonDrawable;
import com.lelloman.identicon.github.GithubIdenticonDrawable;


public class GithubIdenticonFragment extends IdenticonFragment {

	@Override
	protected RecyclerViewAdapter getAdapter() {
		return new IdenticonFragment.RecyclerViewAdapter() {
			@Override
			protected int getHashForPosition(int position) {
				return RANDOM.nextInt();
			}

			@Override
			protected IdenticonDrawable makeIdenticonDrawable(int width, int height, int hash) {
				return new GithubIdenticonDrawable(width, height, hash);
			}
		};
	}
}
