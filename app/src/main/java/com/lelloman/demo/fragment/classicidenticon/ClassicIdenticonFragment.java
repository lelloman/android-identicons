package com.lelloman.demo.fragment.classicidenticon;

import com.lelloman.demo.fragment.IdenticonFragment;
import com.lelloman.identicon.drawable.IdenticonDrawable;
import com.lelloman.identicon.drawable.ClassicIdenticonDrawable;


public abstract class ClassicIdenticonFragment extends IdenticonFragment {

	@Override
	protected IdenticonFragment.RecyclerViewAdapter getAdapter() {
		return new IdenticonFragment.RecyclerViewAdapter() {
			@Override
			protected int getHashForPosition(int position) {
				return ClassicIdenticonFragment.this.getHashForPosition(position);
			}

			@Override
			protected IdenticonDrawable makeIdenticonDrawable(int width, int height, int hash) {
				return new ClassicIdenticonDrawable(width, height, hash);
			}

		};
	}

	protected abstract int getHashForPosition(int position);
}
