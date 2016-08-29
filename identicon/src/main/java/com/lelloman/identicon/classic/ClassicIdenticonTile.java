package com.lelloman.identicon.classic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import com.lelloman.identicon.util.Path2;
import com.lelloman.identicon.util.TileDrawer;
import com.lelloman.identicon.util.TileMeasures;

/**
 * 	Collection of all shapes, 32 of them in total.
 * 	the tiles at positions %4 = 0 are symmetric in both axis
 * 	and are the ones that can be used as central tile
 */
public class ClassicIdenticonTile {

	private ClassicIdenticonTile(){}

	public enum Tiles{
		FOUR_SQUARES(new TileDrawer() {
			@Override // 1
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addRectangle(0,0,meas.w3,meas.h3);
				path.addRectangle(meas.w32, 0, meas.width, meas.h3);
				path.addRectangle(0, meas.h32, meas.w3, meas.height);
				path.addRectangle(meas.w32, meas.h32, meas.width, meas.height);
			}
		}),

		HALF_SQUARE_TRIANGLE(new TileDrawer() {
			@Override // 2
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(0,0,meas.width,0,0,meas.height);
			}
		}),
		BIG_TRIANGLE(new TileDrawer() {
			@Override // 3
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(meas.wMid,0,0,meas.height,meas.width,meas.height);
			}
		}),
		HALF_SQUARE_RECTANGLE(new TileDrawer() {
			@Override // 4
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addRectangle( 0,0,meas.wMid,meas.height);
			}
		}),
		ROTATED_SQUARE(new TileDrawer() {
			@Override // 5
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addPolygon(meas.wMid,0,meas.width,meas.hMid,meas.wMid,meas.height,0,meas.hMid);
			}
		}),
		SPEAR_TIP(new TileDrawer() {
			@Override // 6
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addPolygon(0,0,meas.width,meas.hMid,meas.width,meas.height,meas.wMid,meas.height);
			}
		}),
		THREE_TRIANGLES(new TileDrawer() {
			@Override // 7
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(meas.wMid,0,meas.w43,meas.hMid,meas.w4,meas.hMid);
				path.addTriangle(0,meas.height,meas.w4,meas.hMid,meas.wMid,meas.height);
				path.addTriangle(meas.width,meas.height,meas.w43,meas.hMid,meas.wMid,meas.height);
			}
		}),
		SLIM_TRIANGLE(new TileDrawer() {
			@Override // 8
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(0,0,meas.width,meas.hMid,meas.wMid,meas.height);
			}
		}),
		LITTLE_SQUARE(new TileDrawer() {
			@Override // 9
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addRectangle(meas.w4,meas.h4,meas.w43,meas.h43);
			}
		}),
		TWO_TRIANGLES(new TileDrawer() {
			@Override // 10
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(0,meas.height,0,meas.hMid,meas.wMid,meas.hMid);
				path.addTriangle(meas.width,0,meas.wMid,0,meas.wMid,meas.hMid);
			}
		}),
		LITTLE_SIDE_SQUARE(new TileDrawer() {
			@Override // 11
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addRectangle(0,0,meas.wMid,meas.hMid);
			}
		}),
		MARLBORO_TRIANGLE(new TileDrawer() {
			@Override // 13
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle( 0,meas.height,meas.width,meas.height,meas.wMid,meas.hMid);
			}
		}),
		FOUR_TRIANGLES(new TileDrawer() {
			@Override // 12
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(meas.wMid,0,meas.w4,meas.h4,meas.w43,meas.h4);
				path.addTriangle(meas.width,meas.hMid,meas.w43,meas.h43,meas.w43,meas.h4);
				path.addTriangle(meas.wMid,meas.height,meas.w4,meas.h43,meas.w43,meas.h43);
				path.addTriangle(0,meas.hMid,meas.w4,meas.h4,meas.w4,meas.h43);
			}
		}),
		LITTLE_TRIANGLE_INSIDE(new TileDrawer() {
			@Override // 14
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(meas.wMid,0,meas.wMid,meas.hMid,0,meas.hMid);
			}
		}),
		LITTLE_TRIANGLE_TIP(new TileDrawer() {
			@Override // 15
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(meas.wMid,0,0,meas.hMid,0,0);
			}
		}),
		TWO_SQUARES_DIAGONAL(new TileDrawer() {
			@Override // 16
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addRectangle(0,0,meas.wMid,meas.hMid);
				path.addRectangle(meas.wMid,meas.hMid,meas.width,meas.height);
			}
		}),
		LITTLE_ROTATED_SQUARE(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addPolygon(meas.wMid,meas.h4,meas.w43,meas.hMid,meas.wMid,meas.h43,meas.w4,meas.hMid);
			}
		}),
		SHIFTED_MARLBORO_TRIANGLE(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(meas.wMid,0,meas.width,meas.hMid,0,meas.hMid);
			}
		}),
		TUNNELING_TRIANGLES(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(0,0,meas.width,0,0,meas.hMid);
				path.addTriangle(meas.width,meas.height,0,meas.height,meas.width,meas.hMid);
			}
		}),
		TWO_TIPS_TRIANGLES(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(meas.wMid,0,0,meas.hMid,0,0);
				path.addTriangle(meas.wMid,meas.height,meas.width,meas.hMid,meas.width,meas.height);
			}
		}),
		FOUR_TRIANGLES_FACING_CENTER(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(0,0,meas.width,0,meas.wMid,meas.h3);
				path.addTriangle(meas.width,0,meas.width,meas.height,meas.w32,meas.hMid);
				path.addTriangle(meas.width,meas.height,0,meas.height,meas.wMid,meas.h32);
				path.addTriangle(0,meas.height,0,0,meas.w3,meas.hMid);
			}
		}),
		TWO_SQUARES_STRAIGHT_LINE(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addPolygon(meas.wMid,0,meas.w43,meas.h4,meas.wMid,meas.hMid,meas.w4,meas.h4);
				path.addPolygon(meas.wMid,meas.hMid,meas.w43,meas.h43,meas.wMid,meas.height,meas.w4,meas.h43);
			}
		}),
		TWO_TRAPEZOIDS(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addPolygon( 0,0,meas.wMid,0,meas.wMid,meas.h4,0,meas.hMid);
				path.addPolygon(meas.width,meas.height,meas.width,meas.hMid,meas.wMid,meas.h43,meas.wMid,meas.height);
			}
		}),
		ARROW(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addPolygon( meas.width,0,meas.wMid,meas.hMid,meas.width,meas.height,0,meas.hMid);
			}
		}),
		ROTATE_SQUARE_WITH_HOLE(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {

				path.setFillType(Path.FillType.EVEN_ODD);

				path.moveTo(meas.wMid,0);
				path.lineTo(meas.width,meas.hMid);
				path.lineTo(meas.wMid,meas.height);
				path.lineTo(0,meas.hMid);
				path.lineTo(meas.wMid,0);
				path.close();

				path.moveTo(meas.wMid,meas.h4);
				path.lineTo(meas.w43,meas.hMid);
				path.lineTo(meas.wMid, meas.h43);
				path.lineTo(meas.w4,meas.hMid);
				path.lineTo(meas.wMid,meas.h4);
				path.close();
			}
		}),
		TWO_OPPOSITE_TRIANGLES(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(0,0,meas.width,0,meas.wMid,meas.h4);
				path.addTriangle(meas.width,meas.height,0,meas.height,meas.wMid, meas.h43);
			}
		}),
		TRIANGLE_SANDWICH(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(0,0,meas.width,0,meas.wMid,meas.h4);
				path.addPolygon(meas.wMid,meas.h4,meas.w4,meas.hMid,meas.wMid,meas.h43,meas.w43,meas.hMid);
				path.addTriangle(meas.width,meas.height,0,meas.height,meas.wMid, meas.h43);
			}
		}),
		SPIKE(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle( 0,0,meas.width,meas.hMid,meas.width,meas.height);
			}
		}),
		FOUR_TRIANGLES_STAR(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle( 0,0,meas.wMid,meas.h4,meas.w4,meas.hMid);
				path.addTriangle( meas.width,0, meas.w43,meas.hMid,meas.wMid,meas.h4);
				path.addTriangle( meas.width,meas.height,meas.wMid, meas.h43, meas.w43,meas.hMid);
				path.addTriangle( 0,meas.height,meas.w4,meas.hMid,meas.wMid, meas.h43);
			}
		}),
		BIG_TRIANGLE_TIP(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(0,0,meas.width,0,0,meas.hMid);
			}
		}),
		DIAMOND(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addPolygon(0,meas.hMid,meas.wMid,meas.h4,meas.width,meas.hMid,meas.wMid,meas.h43);
			}
		}),
		TWO_OPPOSITE_TRIANGLES_BIG(new TileDrawer() {
			@Override
			public void onDraw(Path2 path, TileMeasures meas) {
				path.addTriangle(0,0,meas.wMid,meas.hMid,0,meas.height);
				path.addTriangle(meas.width,0,meas.wMid,meas.hMid,meas.width,meas.height);
			}
		})
		;

		private TileDrawer drawer;

		Tiles(TileDrawer drawer){
			this.drawer = drawer;
			Log.d("asd","asd");
		}

		public void draw(Canvas canvas, TileMeasures measures, int rotation, Paint bgPaint, Paint fgPaint){
			drawer.draw(canvas, measures, rotation, bgPaint, fgPaint);
		}
	}

	/**
	 * 	drawing values, 0-31
	 */
	public static Tiles[] all = new Tiles[]{

			Tiles.FOUR_SQUARES,
			Tiles.HALF_SQUARE_TRIANGLE,
			Tiles.BIG_TRIANGLE,
			Tiles.HALF_SQUARE_RECTANGLE,

			Tiles.ROTATED_SQUARE,
			Tiles.SPEAR_TIP,
			Tiles.THREE_TRIANGLES,
			Tiles.SLIM_TRIANGLE,

			Tiles.LITTLE_SQUARE,
			Tiles.TWO_TRIANGLES,
			Tiles.LITTLE_SIDE_SQUARE,
			Tiles.MARLBORO_TRIANGLE,

			Tiles.FOUR_TRIANGLES,
			Tiles.LITTLE_TRIANGLE_INSIDE,
			Tiles.LITTLE_TRIANGLE_TIP,
			Tiles.TWO_SQUARES_DIAGONAL,

			Tiles.LITTLE_ROTATED_SQUARE,
			Tiles.SHIFTED_MARLBORO_TRIANGLE,
			Tiles.TUNNELING_TRIANGLES,
			Tiles.TWO_TIPS_TRIANGLES,

			Tiles.FOUR_TRIANGLES_FACING_CENTER,
			Tiles.TWO_SQUARES_STRAIGHT_LINE,
			Tiles.TWO_TRAPEZOIDS,
			Tiles.ARROW,

			Tiles.ROTATE_SQUARE_WITH_HOLE,
			Tiles.TWO_OPPOSITE_TRIANGLES,
			Tiles.TRIANGLE_SANDWICH,
			Tiles.SPIKE,

			Tiles.FOUR_TRIANGLES_STAR,
			Tiles.BIG_TRIANGLE_TIP,
			Tiles.DIAMOND,
			Tiles.TWO_OPPOSITE_TRIANGLES_BIG

	};
}

