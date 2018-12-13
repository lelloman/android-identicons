package com.lelloman.identicon.drawable

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

import com.lelloman.identicon.util.Path2
import com.lelloman.identicon.util.TileDrawer
import com.lelloman.identicon.util.TileMeasures

/**
 * Collection of all shapes, 32 of them in total.
 * The tiles at positions %4 = 0 are symmetric in both axis
 * and are the ones that can be used as central tile.
 */
object ClassicIdenticonTile {

    /**
     * drawing values, 0-31
     */
    var all = arrayOf(

        Tiles.FOUR_SQUARES, Tiles.HALF_SQUARE_TRIANGLE, Tiles.BIG_TRIANGLE, Tiles.HALF_SQUARE_RECTANGLE,

        Tiles.ROTATED_SQUARE, Tiles.SPEAR_TIP, Tiles.THREE_TRIANGLES, Tiles.SLIM_TRIANGLE,

        Tiles.LITTLE_SQUARE, Tiles.TWO_TRIANGLES, Tiles.LITTLE_SIDE_SQUARE, Tiles.MARLBORO_TRIANGLE,

        Tiles.FOUR_TRIANGLES, Tiles.LITTLE_TRIANGLE_INSIDE, Tiles.LITTLE_TRIANGLE_TIP, Tiles.TWO_SQUARES_DIAGONAL,

        Tiles.LITTLE_ROTATED_SQUARE, Tiles.SHIFTED_MARLBORO_TRIANGLE, Tiles.TUNNELING_TRIANGLES, Tiles.TWO_TIPS_TRIANGLES,

        Tiles.FOUR_TRIANGLES_FACING_CENTER, Tiles.TWO_SQUARES_STRAIGHT_LINE, Tiles.TWO_TRAPEZOIDS, Tiles.ARROW,

        Tiles.ROTATE_SQUARE_WITH_HOLE, Tiles.TWO_OPPOSITE_TRIANGLES, Tiles.TRIANGLE_SANDWICH, Tiles.SPIKE,

        Tiles.FOUR_TRIANGLES_STAR, Tiles.BIG_TRIANGLE_TIP, Tiles.DIAMOND, Tiles.TWO_OPPOSITE_TRIANGLES_BIG)

    enum class Tiles(private val drawer: TileDrawer) {
        FOUR_SQUARES(object : TileDrawer() {
            override// 1
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addRectangle(0, 0, meas.w3, meas.h3)
                path.addRectangle(meas.w32, 0, meas.width, meas.h3)
                path.addRectangle(0, meas.h32, meas.w3, meas.height)
                path.addRectangle(meas.w32, meas.h32, meas.width, meas.height)
            }
        }),

        HALF_SQUARE_TRIANGLE(object : TileDrawer() {
            override// 2
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(0, 0, meas.width, 0, 0, meas.height)
            }
        }),
        BIG_TRIANGLE(object : TileDrawer() {
            override// 3
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(meas.wMid, 0, 0, meas.height, meas.width, meas.height)
            }
        }),
        HALF_SQUARE_RECTANGLE(object : TileDrawer() {
            override// 4
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addRectangle(0, 0, meas.wMid, meas.height)
            }
        }),
        ROTATED_SQUARE(object : TileDrawer() {
            override// 5
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addPolygon(meas.wMid, 0, meas.width, meas.hMid, meas.wMid, meas.height, 0, meas.hMid)
            }
        }),
        SPEAR_TIP(object : TileDrawer() {
            override// 6
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addPolygon(0, 0, meas.width, meas.hMid, meas.width, meas.height, meas.wMid, meas.height)
            }
        }),
        THREE_TRIANGLES(object : TileDrawer() {
            override// 7
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(meas.wMid, 0, meas.w43, meas.hMid, meas.w4, meas.hMid)
                path.addTriangle(0, meas.height, meas.w4, meas.hMid, meas.wMid, meas.height)
                path.addTriangle(meas.width, meas.height, meas.w43, meas.hMid, meas.wMid, meas.height)
            }
        }),
        SLIM_TRIANGLE(object : TileDrawer() {
            override// 8
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(0, 0, meas.width, meas.hMid, meas.wMid, meas.height)
            }
        }),
        LITTLE_SQUARE(object : TileDrawer() {
            override// 9
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addRectangle(meas.w4, meas.h4, meas.w43, meas.h43)
            }
        }),
        TWO_TRIANGLES(object : TileDrawer() {
            override// 10
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(0, meas.height, 0, meas.hMid, meas.wMid, meas.hMid)
                path.addTriangle(meas.width, 0, meas.wMid, 0, meas.wMid, meas.hMid)
            }
        }),
        LITTLE_SIDE_SQUARE(object : TileDrawer() {
            override// 11
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addRectangle(0, 0, meas.wMid, meas.hMid)
            }
        }),
        MARLBORO_TRIANGLE(object : TileDrawer() {
            override// 13
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(0, meas.height, meas.width, meas.height, meas.wMid, meas.hMid)
            }
        }),
        FOUR_TRIANGLES(object : TileDrawer() {
            override// 12
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(meas.wMid, 0, meas.w4, meas.h4, meas.w43, meas.h4)
                path.addTriangle(meas.width, meas.hMid, meas.w43, meas.h43, meas.w43, meas.h4)
                path.addTriangle(meas.wMid, meas.height, meas.w4, meas.h43, meas.w43, meas.h43)
                path.addTriangle(0, meas.hMid, meas.w4, meas.h4, meas.w4, meas.h43)
            }
        }),
        LITTLE_TRIANGLE_INSIDE(object : TileDrawer() {
            override// 14
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(meas.wMid, 0, meas.wMid, meas.hMid, 0, meas.hMid)
            }
        }),
        LITTLE_TRIANGLE_TIP(object : TileDrawer() {
            override// 15
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(meas.wMid, 0, 0, meas.hMid, 0, 0)
            }
        }),
        TWO_SQUARES_DIAGONAL(object : TileDrawer() {
            override// 16
            fun onDraw(path: Path2, meas: TileMeasures) {
                path.addRectangle(0, 0, meas.wMid, meas.hMid)
                path.addRectangle(meas.wMid, meas.hMid, meas.width, meas.height)
            }
        }),
        LITTLE_ROTATED_SQUARE(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addPolygon(meas.wMid, meas.h4, meas.w43, meas.hMid, meas.wMid, meas.h43, meas.w4, meas.hMid)
            }
        }),
        SHIFTED_MARLBORO_TRIANGLE(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(meas.wMid, 0, meas.width, meas.hMid, 0, meas.hMid)
            }
        }),
        TUNNELING_TRIANGLES(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(0, 0, meas.width, 0, 0, meas.hMid)
                path.addTriangle(meas.width, meas.height, 0, meas.height, meas.width, meas.hMid)
            }
        }),
        TWO_TIPS_TRIANGLES(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(meas.wMid, 0, 0, meas.hMid, 0, 0)
                path.addTriangle(meas.wMid, meas.height, meas.width, meas.hMid, meas.width, meas.height)
            }
        }),
        FOUR_TRIANGLES_FACING_CENTER(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(0, 0, meas.width, 0, meas.wMid, meas.h3)
                path.addTriangle(meas.width, 0, meas.width, meas.height, meas.w32, meas.hMid)
                path.addTriangle(meas.width, meas.height, 0, meas.height, meas.wMid, meas.h32)
                path.addTriangle(0, meas.height, 0, 0, meas.w3, meas.hMid)
            }
        }),
        TWO_SQUARES_STRAIGHT_LINE(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addPolygon(meas.wMid, 0, meas.w43, meas.h4, meas.wMid, meas.hMid, meas.w4, meas.h4)
                path.addPolygon(meas.wMid, meas.hMid, meas.w43, meas.h43, meas.wMid, meas.height, meas.w4, meas.h43)
            }
        }),
        TWO_TRAPEZOIDS(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addPolygon(0, 0, meas.wMid, 0, meas.wMid, meas.h4, 0, meas.hMid)
                path.addPolygon(meas.width, meas.height, meas.width, meas.hMid, meas.wMid, meas.h43, meas.wMid, meas.height)
            }
        }),
        ARROW(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addPolygon(meas.width, 0, meas.wMid, meas.hMid, meas.width, meas.height, 0, meas.hMid)
            }
        }),
        ROTATE_SQUARE_WITH_HOLE(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {

                path.fillType = Path.FillType.EVEN_ODD

                path.moveTo(meas.wMid.toFloat(), 0f)
                path.lineTo(meas.width.toFloat(), meas.hMid.toFloat())
                path.lineTo(meas.wMid.toFloat(), meas.height.toFloat())
                path.lineTo(0f, meas.hMid.toFloat())
                path.lineTo(meas.wMid.toFloat(), 0f)
                path.close()

                path.moveTo(meas.wMid.toFloat(), meas.h4.toFloat())
                path.lineTo(meas.w43.toFloat(), meas.hMid.toFloat())
                path.lineTo(meas.wMid.toFloat(), meas.h43.toFloat())
                path.lineTo(meas.w4.toFloat(), meas.hMid.toFloat())
                path.lineTo(meas.wMid.toFloat(), meas.h4.toFloat())
                path.close()
            }
        }),
        TWO_OPPOSITE_TRIANGLES(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(0, 0, meas.width, 0, meas.wMid, meas.h4)
                path.addTriangle(meas.width, meas.height, 0, meas.height, meas.wMid, meas.h43)
            }
        }),
        TRIANGLE_SANDWICH(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(0, 0, meas.width, 0, meas.wMid, meas.h4)
                path.addPolygon(meas.wMid, meas.h4, meas.w4, meas.hMid, meas.wMid, meas.h43, meas.w43, meas.hMid)
                path.addTriangle(meas.width, meas.height, 0, meas.height, meas.wMid, meas.h43)
            }
        }),
        SPIKE(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(0, 0, meas.width, meas.hMid, meas.width, meas.height)
            }
        }),
        FOUR_TRIANGLES_STAR(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(0, 0, meas.wMid, meas.h4, meas.w4, meas.hMid)
                path.addTriangle(meas.width, 0, meas.w43, meas.hMid, meas.wMid, meas.h4)
                path.addTriangle(meas.width, meas.height, meas.wMid, meas.h43, meas.w43, meas.hMid)
                path.addTriangle(0, meas.height, meas.w4, meas.hMid, meas.wMid, meas.h43)
            }
        }),
        BIG_TRIANGLE_TIP(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(0, 0, meas.width, 0, 0, meas.hMid)
            }
        }),
        DIAMOND(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addPolygon(0, meas.hMid, meas.wMid, meas.h4, meas.width, meas.hMid, meas.wMid, meas.h43)
            }
        }),
        TWO_OPPOSITE_TRIANGLES_BIG(object : TileDrawer() {
            override fun onDraw(path: Path2, meas: TileMeasures) {
                path.addTriangle(0, 0, meas.wMid, meas.hMid, 0, meas.height)
                path.addTriangle(meas.width, 0, meas.wMid, meas.hMid, meas.width, meas.height)
            }
        });

        fun draw(canvas: Canvas, measures: TileMeasures, rotation: Int, bgPaint: Paint, fgPaint: Paint) {
            drawer.draw(canvas, measures, rotation, bgPaint, fgPaint)
        }
    }
}

