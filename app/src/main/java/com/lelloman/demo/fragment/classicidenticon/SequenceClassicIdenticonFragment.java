package com.lelloman.demo.fragment.classicidenticon;


public class SequenceClassicIdenticonFragment extends ClassicIdenticonFragment {

	private int mSalt;

	public SequenceClassicIdenticonFragment(){
		mSalt = RANDOM.nextInt();
	}

	@Override
	protected int getHashForPosition(int position) {
		return position + mSalt;
	}
}
