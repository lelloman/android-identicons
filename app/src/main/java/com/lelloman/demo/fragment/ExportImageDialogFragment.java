package com.lelloman.demo.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.widget.NumberPicker;

import com.lelloman.demo.R;
import com.lelloman.demo.activity.DetailActivity;
import com.lelloman.identicon.drawable.IdenticonDrawable;
import com.lelloman.identicon.drawable.ClassicIdenticonDrawable;
import com.lelloman.identicon.drawable.GithubIdenticonDrawable;

import java.io.File;
import java.io.FileOutputStream;

public class ExportImageDialogFragment extends DialogFragment{

	public interface ExportImageListener {
		void onImageCreated(Uri uri);
	}

	private static final int[] SIZES = {32,64,128,256,512,1024,2048};
	private static final String[] DISPLAYED_SIZES = new String[SIZES.length];
	static {
		for(int i = 0; i< DISPLAYED_SIZES.length; i++){
			int n = SIZES[i];
			DISPLAYED_SIZES[i] = String.format("%sx%s",n,n);
		}
	}

	private static final String ARG_HASH = "hash";
	private static final String ARG_TYPE = "type";

	private ExportImageListener mListener;

	public static ExportImageDialogFragment newInstance(int hash, int type){
		ExportImageDialogFragment output = new ExportImageDialogFragment();

		Bundle args = new Bundle();
		args.putInt(ARG_HASH, hash);
		args.putInt(ARG_TYPE,type);
		output.setArguments(args);

		return output;
	}

	public ExportImageDialogFragment(){

	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mListener = (ExportImageListener) context;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener  = null;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		Context context = getActivity();

		final NumberPicker view = new NumberPicker(context);

		view.setMinValue(0);
		view.setMaxValue(SIZES.length-1);

		view.setDisplayedValues(DISPLAYED_SIZES);

		return new AlertDialog.Builder(context)
				.setTitle(R.string.select_size)
				.setView(view)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						onSizeSelected(SIZES[view.getValue()]);
					}
				})
				.setNegativeButton(android.R.string.cancel,null)
				.create();
	}

	private void onSizeSelected(int size){

		Bundle args = getArguments();
		int type = args.getInt(ARG_TYPE, DetailActivity.TYPE_CLASSIC);
		int hash = args.getInt(ARG_HASH);

		Bitmap bitmap = Bitmap.createBitmap(size,size, Bitmap.Config.ARGB_8888);

		IdenticonDrawable drawable = makeIdenticonDrawable(size,type,hash);
		if(drawable == null) return;

		drawable.draw(new Canvas(bitmap));
		String filename = String.format("%s.png",System.currentTimeMillis());

		Context context = getActivity();

		File file = new File(context.getExternalCacheDir(),filename);
		try {
			file.createNewFile();
			FileOutputStream fos = new FileOutputStream(file);
			bitmap.compress(Bitmap.CompressFormat.PNG,100,fos);
			fos.close();

			if(mListener != null){
				mListener.onImageCreated(Uri.fromFile(file));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private IdenticonDrawable makeIdenticonDrawable(int size, int type, int hash){
		switch (type){
			case DetailActivity.TYPE_CLASSIC:
				return new ClassicIdenticonDrawable(size,size,hash);
			case DetailActivity.TYPE_GITHUB:
				return new GithubIdenticonDrawable(size,size,hash);
		}
		return  null;
	}
}
