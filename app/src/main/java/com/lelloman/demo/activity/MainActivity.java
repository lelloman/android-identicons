package com.lelloman.demo.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.view.Window;
import android.widget.ImageView;

import com.lelloman.demo.R;
import com.lelloman.demo.fragment.ClassicTilesFragment;
import com.lelloman.demo.fragment.GithubIdenticonFragment;
import com.lelloman.demo.fragment.IdenticonFragment;
import com.lelloman.demo.fragment.classicidenticon.RandomClassicIdenticonFragment;
import com.lelloman.demo.fragment.classicidenticon.SequenceClassicIdenticonFragment;
import com.lelloman.identicon.classic.ClassicIdenticonDrawable;
import com.lelloman.identicon.github.GithubIdenticonDrawable;

public class MainActivity extends AppCompatActivity implements IdenticonFragment.IdenticonFragmentListener {

	Class<Fragment>[] fragments = new Class[]{
			RandomClassicIdenticonFragment.class,
			SequenceClassicIdenticonFragment.class,
			ClassicTilesFragment.class,
			GithubIdenticonFragment.class
	};
	String[] titles = new String[]{
			"Random",
			"Sequence",
			"Tiles",
			"Github"
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
			getWindow().setEnterTransition(new Explode());
			getWindow().setExitTransition(new Explode());
		}
		setContentView(R.layout.activity_main);

		ViewPager pager = (ViewPager) findViewById(R.id.view_pager);

		pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

			@Override
			public int getCount() {
				return fragments.length;
			}

			@Override
			public Fragment getItem(int position) {
				try {
					return fragments[position].newInstance();
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return titles[position];
			}
		});
	}

	@Override
	public void onIdenticonSelected(int hash, ImageView identiconView, IdenticonFragment identiconFragment) {


		Intent intent = new Intent(this, DetailActivity.class);
		intent.putExtra(DetailActivity.EXTRA_TRANSITION_NAME, String.valueOf(hash));
		intent.putExtra(DetailActivity.EXTRA_HASH, hash);
		Drawable drawable = identiconView.getDrawable();
		if (drawable instanceof GithubIdenticonDrawable) {
			intent.putExtra(DetailActivity.EXTRA_IDENTICON_TYPE, DetailActivity.TYPE_GITHUB);
		}else if (drawable instanceof ClassicIdenticonDrawable){
			intent.putExtra(DetailActivity.EXTRA_IDENTICON_TYPE,DetailActivity.TYPE_CLASSIC);
		}

		ActivityOptionsCompat options = ActivityOptionsCompat.
				makeSceneTransitionAnimation(this, identiconView, String.valueOf(hash));

		startActivity(intent, options.toBundle());
	}
}
