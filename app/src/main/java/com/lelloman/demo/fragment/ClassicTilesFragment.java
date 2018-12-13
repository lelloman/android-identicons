package com.lelloman.demo.fragment;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lelloman.identicon.drawable.ClassicIdenticonTile;
import com.lelloman.identicon.util.TileMeasures;

@SuppressWarnings("ResourceType")
public class ClassicTilesFragment extends Fragment {

	public static final int TILE_VIEW_ID = 1;
	public static final int TEXT_VIEW_ID = 2;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		RecyclerView recyclerView = new RecyclerView(getContext());

		recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		recyclerView.setAdapter(new TilesAdapter());
		recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));


		return recyclerView;
	}

	public class TilesAdapter extends RecyclerView.Adapter<ViewHolder> {

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

			LinearLayout view = new LinearLayout(getContext());
			view.setOrientation(LinearLayout.HORIZONTAL);
			view.setBackgroundColor(0xff999999);
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

			TileView tileView = new TileView(getContext());
			int tileSize = (int) (getContext().getResources().getDisplayMetrics().density * 32);
			int tileMargin = tileSize / 4;
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(tileSize, tileSize);
			layoutParams.setMargins(tileMargin, tileMargin, tileMargin, tileMargin);
			tileView.setLayoutParams(layoutParams);
			tileView.setId(TILE_VIEW_ID);

			view.addView(tileView);

			TextView textView = new TextView(getContext());
			textView.setId(TEXT_VIEW_ID);

			layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
			layoutParams.weight = 1;
			layoutParams.gravity = Gravity.CENTER;
			textView.setLayoutParams(layoutParams);

			view.addView(textView);

			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(ViewHolder holder, int position) {

			ClassicIdenticonTile.Tiles tile = ClassicIdenticonTile.INSTANCE.getAll()[position];
			holder.tileView.setTile(tile);

			String text = String.format("%s - %s%s", position, tile.name(), position % 4 == 0 ? "*" : "");
			holder.textView.setText(text);

		}

		@Override
		public int getItemCount() {
			return ClassicIdenticonTile.INSTANCE.getAll().length;
		}
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public final TileView tileView;
		public final TextView textView;

		public ViewHolder(View view){
			super(view);
			tileView = (TileView) view.findViewById(TILE_VIEW_ID);
			textView = (TextView) view.findViewById(TEXT_VIEW_ID);
		}
	}

	public static class TileView extends View {

		Paint whitePaint, blackPaint;
		ClassicIdenticonTile.Tiles tile;

		public TileView(Context context) {
			super(context);

			whitePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			whitePaint.setColor(0xffffffff);
			whitePaint.setStyle(Paint.Style.FILL);

			blackPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
			blackPaint.setColor(0xff000000);
			blackPaint.setStyle(Paint.Style.FILL);
		}

		public void setTile(ClassicIdenticonTile.Tiles tile){
			this.tile = tile;
			invalidate();
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			if(tile == null)
				return;

			TileMeasures measures = new TileMeasures(canvas.getWidth(),canvas.getHeight());

			tile.draw(canvas,measures,0,whitePaint,blackPaint);
		}
	}
}
