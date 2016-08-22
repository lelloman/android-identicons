package com.lelloman.demo.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.lelloman.demo.R;
import com.lelloman.demo.fragment.ExportImageDialogFragment;
import com.lelloman.identicon.classic.ClassicIdenticonDrawable;
import com.lelloman.identicon.github.GithubIdenticonDrawable;

import java.util.Random;

public class DetailActivity extends AppCompatActivity implements ExportImageDialogFragment.ExportImageListener{


	public static final String EXTRA_TRANSITION_NAME = "transitionName";
	public static final String EXTRA_IDENTICON_TYPE = "type";
	public static final String EXTRA_HASH = "hash";
	public static final int TYPE_CLASSIC = 0;
	public static final int TYPE_GITHUB = 1;

	private int mHash,mType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("");

		Intent intent = getIntent();

		String transitionName = intent.getStringExtra(EXTRA_TRANSITION_NAME);
		mType = intent.getIntExtra(EXTRA_IDENTICON_TYPE,TYPE_CLASSIC);
		mHash = intent.getIntExtra(EXTRA_HASH,new Random().nextInt());

		ImageView imageView = (ImageView) findViewById(R.id.image_view);
		imageView.setTransitionName(transitionName);

		getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				Drawable drawable = null;

				int size = Math.min(imageView.getWidth(), imageView.getHeight());
				switch(mType){
					case TYPE_CLASSIC:
						drawable = new ClassicIdenticonDrawable(size,size, mHash);
						break;
					case TYPE_GITHUB:
						drawable = new GithubIdenticonDrawable(size,size, mHash);
						break;
				}

				ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
				layoutParams.height = size;
				layoutParams.width = size;
				imageView.setLayoutParams(layoutParams);

				if(drawable != null)
					imageView.setImageDrawable(drawable);

				getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
			}
		});


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		switch (id){
			case R.id.action_export:
				exportImage();
				return true;
		}

		return false;
	}

	private void exportImage(){
		ExportImageDialogFragment fragment = ExportImageDialogFragment.newInstance(mHash,mType);
		fragment.show(getFragmentManager(),ExportImageDialogFragment.class.getSimpleName());
	}


	@Override
	public void onImageCreated(Uri uri) {

		Intent share = new Intent(Intent.ACTION_SEND);
		share.setType("image/png");
		share.putExtra(Intent.EXTRA_STREAM, uri);

		startActivity(Intent.createChooser(share, "Share Image"));
	}
}
