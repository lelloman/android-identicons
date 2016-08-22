package com.lelloman.demo.fragment.classicidenticon;


public class RandomClassicIdenticonFragment extends ClassicIdenticonFragment{

	public RandomClassicIdenticonFragment(){

	}

	@Override
	protected int getHashForPosition(int position) {
		return RANDOM.nextInt();
	}
}
