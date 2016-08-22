package com.lelloman.demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lelloman.identicon.IdenticonDrawable;
import com.lelloman.identicon.IdenticonView;

import java.util.Random;

public abstract class IdenticonFragment extends Fragment{

	public interface IdenticonFragmentListener {
		void onIdenticonSelected(int hash, ImageView identiconView, IdenticonFragment fragment);
	}

	protected static final Random RANDOM = new Random();

	private IdenticonFragmentListener mListener;

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		mListener = (IdenticonFragmentListener) context;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		RecyclerView recyclerView = new RecyclerView(getContext()){
			@Override
			protected void onSizeChanged(int w, int h, int oldw, int oldh) {
				super.onSizeChanged(w, h, oldw, oldh);
				if(w != 0) {
					int columns = (int) (w / (getResources().getDisplayMetrics().density * 64));
					setLayoutManager(new GridLayoutManager(getContext(), columns, GridLayoutManager.VERTICAL, false));
					getLayoutManager().scrollToPosition(Integer.MAX_VALUE / 2);
				}
			}
		};

		recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		recyclerView.setAdapter(getAdapter());


		return recyclerView;
	}

	protected abstract RecyclerViewAdapter getAdapter();

	public abstract class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			IdenticonView view = new IdenticonView(getContext()){
				@Override
				protected IdenticonDrawable makeIdenticonDrawable(int width, int height, int hash) {
					return RecyclerViewAdapter.this.makeIdenticonDrawable(width,height,hash);
				}

				@Override
				protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
					super.onMeasure(widthMeasureSpec,heightMeasureSpec);
					setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
				}
			};
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {
			int hash = getHashForPosition(position);
			holder.hash = hash;
			holder.identiconView.setHash(hash);
			ViewCompat.setTransitionName(holder.identiconView, String.valueOf(hash));
		}

		@Override
		public int getItemCount() {
			return Integer.MAX_VALUE;
		}

		protected abstract int getHashForPosition(int position);
		protected abstract IdenticonDrawable makeIdenticonDrawable(int width, int height, int hash);
	}

	public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		public final IdenticonView identiconView;
		public int hash;

		public ViewHolder(View itemView) {
			super(itemView);
			identiconView = (IdenticonView) itemView;
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View view) {
			identiconView.setTransitionName(String.valueOf(hash));
			if(mListener != null)
				mListener.onIdenticonSelected(hash, identiconView, IdenticonFragment.this);
		}
	}
}
